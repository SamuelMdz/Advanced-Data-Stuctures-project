import java.util.ArrayList;

public class ResponseNetworkGraph {

    // Data fields
    private ArrayList<String> locations;    // Represents the use locations of responders and incidents
    private ArrayList<ArrayList<RouteEdge>> routes; //Represents routes from locations

    // Default constructor
    public ResponseNetworkGraph() {
        locations = new ArrayList<>();
        routes = new ArrayList<>();
    }

    // Constructor using locations and route data
    public ResponseNetworkGraph(String[] locations, int[][] routeData) {
        this();

        for (String location : locations) {
            addLocation(location);
        }

        for (int[] route : routeData) {
            addRoute(route[0], route[1], route[2]);
        }
    }

    // Add a station or city location
    public boolean addLocation(String location) {
        if (!locations.contains(location)) {
            locations.add(location);
            routes.add(new ArrayList<>());
            return true;
        }

        return false;
    }

    // Add a travel route between two locations
    public boolean addRoute(int fromLocation, int toLocation, double travelTime) {
        // If Locations are invalid, return false
        if (fromLocation < 0 || fromLocation >= locations.size()
                || toLocation < 0 || toLocation >= locations.size()) {
            return false;
        }

        //Else, return the route and its validity
        routes.get(fromLocation).add(new RouteEdge(fromLocation, toLocation, travelTime));
        routes.get(toLocation).add(new RouteEdge(toLocation, fromLocation, travelTime));

        return true;
    }

    // Get the amount of locations in the graph
    public int getSize() {
        return locations.size();
    }

    // Get the location at a specific index
    public String getLocation(int index) {
        return locations.get(index);
    }

    // Get the index of a specific location
    public int getLocationIndex(String location) {
        return locations.indexOf(location);
    }

    // Get the travel time (weight) between one Location and another
    public double getTravelTime(int fromLocation, int toLocation) throws Exception {
        for (RouteEdge route : routes.get(fromLocation)) {
            if (route.toLocation == toLocation) {
                return route.travelTime;
            }
        }

        throw new Exception("Route does not exist.");
    }

    public void printRoutes() {
        System.out.println("Response Network Routes:");

        for (int i = 0; i < getSize(); i++) {
            System.out.print(getLocation(i) + " (" + i + "): ");

            for (RouteEdge route : routes.get(i)) {
                System.out.print(
                        "(" + route.fromLocation +
                                ", " + route.toLocation +
                                ", " + route.travelTime + " min) "
                );
            }

            System.out.println();
        }
    }

    // Prim's Algorithm
    public MinimumResponseNetwork getMinimumResponseNetwork() {
        return getMinimumResponseNetwork(0);
    }

    public MinimumResponseNetwork getMinimumResponseNetwork(int startingLocation) {
        double[] travelCost = new double[getSize()];

        for (int i = 0; i < travelCost.length; i++) {
            travelCost[i] = Double.POSITIVE_INFINITY;
        }

        travelCost[startingLocation] = 0;

        int[] parent = new int[getSize()];
        parent[startingLocation] = -1;

        double totalTravelTime = 0;
        ArrayList<Integer> connectedLocations = new ArrayList<>();

        while (connectedLocations.size() < getSize()) {
            int currentLocation = -1;
            double currentLowestTravelTime = Double.POSITIVE_INFINITY;

            for (int i = 0; i < getSize(); i++) {
                if (!connectedLocations.contains(i) && travelCost[i] < currentLowestTravelTime) {
                    currentLowestTravelTime = travelCost[i];
                    currentLocation = i;
                }
            }

            if (currentLocation == -1) {
                break;
            } else {
                connectedLocations.add(currentLocation);
            }

            totalTravelTime += travelCost[currentLocation];

            for (RouteEdge route : routes.get(currentLocation)) {
                if (!connectedLocations.contains(route.toLocation)
                        && travelCost[route.toLocation] > route.travelTime) {
                    travelCost[route.toLocation] = route.travelTime;
                    parent[route.toLocation] = currentLocation;
                }
            }
        }

        return new MinimumResponseNetwork(startingLocation, parent, connectedLocations, totalTravelTime);
    }

    // Dijkstra's Algorithm
    public ShortestResponsePath getShortestPath(int sourceLocation) {
        double[] travelCost = new double[getSize()];

        for (int i = 0; i < travelCost.length; i++) {
            travelCost[i] = Double.POSITIVE_INFINITY;
        }

        travelCost[sourceLocation] = 0;

        int[] parent = new int[getSize()];
        parent[sourceLocation] = -1;

        ArrayList<Integer> settledLocations = new ArrayList<>();

        while (settledLocations.size() < getSize()) {
            int currentLocation = -1;
            double currentLowestTravelTime = Double.POSITIVE_INFINITY;

            for (int i = 0; i < getSize(); i++) {
                if (!settledLocations.contains(i) && travelCost[i] < currentLowestTravelTime) {
                    currentLowestTravelTime = travelCost[i];
                    currentLocation = i;
                }
            }

            if (currentLocation == -1) {
                break;
            } else {
                settledLocations.add(currentLocation);
            }

            for (RouteEdge route : routes.get(currentLocation)) {
                if (!settledLocations.contains(route.toLocation)
                        && travelCost[route.toLocation] > travelCost[currentLocation] + route.travelTime) {
                    travelCost[route.toLocation] = travelCost[currentLocation] + route.travelTime;
                    parent[route.toLocation] = currentLocation;
                }
            }
        }

        return new ShortestResponsePath(sourceLocation, parent, settledLocations, travelCost);
    }

    protected static class RouteEdge {
        protected int fromLocation;
        protected int toLocation;
        protected double travelTime;

        public RouteEdge(int fromLocation, int toLocation, double travelTime) {
            this.fromLocation = fromLocation;
            this.toLocation = toLocation;
            this.travelTime = travelTime;
        }
    }

    public class SearchResult {
        private int rootLocation;
        private int[] parent;
        private ArrayList<Integer> searchOrder;

        public SearchResult(int rootLocation, int[] parent, ArrayList<Integer> searchOrder) {
            this.rootLocation = rootLocation;
            this.parent = parent;
            this.searchOrder = searchOrder;
        }

        public int getRootLocation() {
            return rootLocation;
        }

        public int getParent(int location) {
            return parent[location];
        }

        public ArrayList<Integer> getSearchOrder() {
            return searchOrder;
        }

        public void printPath(int destinationLocation) {
            ArrayList<Integer> path = new ArrayList<>();

            do {
                path.add(destinationLocation);
                destinationLocation = parent[destinationLocation];
            } while (destinationLocation != -1);

            for (int i = path.size() - 1; i >= 0; i--) {
                System.out.print(locations.get(path.get(i)));

                if (i != 0) {
                    System.out.print(" -> ");
                }
            }
        }
    }

    public class MinimumResponseNetwork extends SearchResult {
        private double totalTravelTime;

        public MinimumResponseNetwork(int rootLocation, int[] parent,
                                      ArrayList<Integer> searchOrder,
                                      double totalTravelTime) {
            super(rootLocation, parent, searchOrder);
            this.totalTravelTime = totalTravelTime;
        }

        public double getTotalTravelTime() {
            return totalTravelTime;
        }

        public void printMinimumResponseNetwork() {
            System.out.println("Minimum Response Network:");

            for (int i = 0; i < getSearchOrder().size(); i++) {
                int location = getSearchOrder().get(i);

                if (getParent(location) != -1) {
                    System.out.println(
                            getLocation(getParent(location)) + " -> " + getLocation(location)
                    );
                }
            }

            System.out.println("Total travel time: " + totalTravelTime + " minutes");
        }
    }

    public class ShortestResponsePath extends SearchResult {
        private double[] travelCost;

        public ShortestResponsePath(int sourceLocation, int[] parent,
                                    ArrayList<Integer> searchOrder,
                                    double[] travelCost) {
            super(sourceLocation, parent, searchOrder);
            this.travelCost = travelCost;
        }

        public double getTravelCost(int location) {
            return travelCost[location];
        }

        public void printPathTo(int destinationLocation) {
            System.out.print("Shortest response path: ");
            printPath(destinationLocation);
            System.out.println();
            System.out.println("Total travel time: " + travelCost[destinationLocation] + " minutes");
        }

        public void printAllPaths() {
            System.out.println("Shortest response paths from " + getLocation(getRootLocation()) + ":");

            for (int i = 0; i < travelCost.length; i++) {
                printPath(i);
                System.out.println(" (travel time: " + travelCost[i] + " minutes)");
            }
        }
    }
}