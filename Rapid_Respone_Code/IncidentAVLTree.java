import java.util.ArrayList;

public class IncidentAVLTree {
    protected AVLTreeNode root;
    protected int size = 0;

    //Default constructor
    public IncidentAVLTree() {
    }

    //Returns true if tree contains no nodes
    public boolean isEmpty() {
        return root == null;
    }

    //Returns number of nodes in tree
    public int getSize() {
        return size;
    }

    //Search for incident by unique incident ID
    public Incident search(String incidentId) {
        AVLTreeNode current = root;

        while (current != null) {
            if (incidentId.compareTo(current.incident.getId()) < 0) {
                current = current.left;
            } else if (incidentId.compareTo(current.incident.getId()) > 0) {
                current = current.right;
            } else {
                return current.incident;
            }
        }

        return null;
    }

    //Insert incident into AVL tree
    public boolean insert(Incident incident) {
        if (root == null) {
            root = createNewNode(incident);
        } else {
            AVLTreeNode parent = null;
            AVLTreeNode current = root;

            while (current != null) {
                if (incident.getId().compareTo(current.incident.getId()) < 0) {
                    parent = current;
                    current = current.left;
                } else if (incident.getId().compareTo(current.incident.getId()) > 0) {
                    parent = current;
                    current = current.right;
                } else {
                    return false; //Duplicate incident IDs are not allowed
                }
            }

            if (incident.getId().compareTo(parent.incident.getId()) < 0) {
                parent.left = createNewNode(incident);
            } else {
                parent.right = createNewNode(incident);
            }

            balancePath(incident.getId());
        }

        size++;
        return true;
    }

    //Delete incident by ID
    public boolean delete(String incidentId) {
        if (root == null) {
            return false;
        }

        AVLTreeNode parent = null;
        AVLTreeNode current = root;

        while (current != null) {
            if (incidentId.compareTo(current.incident.getId()) < 0) {
                parent = current;
                current = current.left;
            } else if (incidentId.compareTo(current.incident.getId()) > 0) {
                parent = current;
                current = current.right;
            } else {
                break;
            }
        }

        if (current == null) {
            return false;
        }

        //Case 1: current has no left child
        if (current.left == null) {
            if (parent == null) {
                root = current.right;
            } else {
                if (incidentId.compareTo(parent.incident.getId()) < 0) {
                    parent.left = current.right;
                } else {
                    parent.right = current.right;
                }

                balancePath(parent.incident.getId());
            }
        } else {
            //Case 2: current has a left child
            AVLTreeNode parentOfRightMost = current;
            AVLTreeNode rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right;
            }

            current.incident = rightMost.incident;

            if (parentOfRightMost.right == rightMost) {
                parentOfRightMost.right = rightMost.left;
            } else {
                parentOfRightMost.left = rightMost.left;
            }

            balancePath(parentOfRightMost.incident.getId());
        }

        size--;
        return true;
    }

    //Return all incidents in sorted ID order
    public ArrayList<Incident> inorderTraversal() {
        ArrayList<Incident> list = new ArrayList<>();
        inorderTraversal(root, list);
        return list;
    }

    //Recursive helper for inorder traversal
    private void inorderTraversal(AVLTreeNode root, ArrayList<Incident> list) {
        if (root == null) {
            return;
        }

        inorderTraversal(root.left, list);
        list.add(root.incident);
        inorderTraversal(root.right, list);
    }

    //Creates new AVL node
    protected AVLTreeNode createNewNode(Incident incident) {
        return new AVLTreeNode(incident);
    }

    //Returns path from root to target incident ID
    private ArrayList<AVLTreeNode> path(String incidentId) {
        ArrayList<AVLTreeNode> list = new ArrayList<>();
        AVLTreeNode current = root;

        while (current != null) {
            list.add(current);

            if (incidentId.compareTo(current.incident.getId()) < 0) {
                current = current.left;
            } else if (incidentId.compareTo(current.incident.getId()) > 0) {
                current = current.right;
            } else {
                break;
            }
        }

        return list;
    }

    //Updates height of a node
    private void updateHeight(AVLTreeNode node) {
        if (node.left == null && node.right == null) {
            node.height = 0;
        } else if (node.left == null) {
            node.height = 1 + node.right.height;
        } else if (node.right == null) {
            node.height = 1 + node.left.height;
        } else {
            node.height = 1 + Math.max(node.left.height, node.right.height);
        }
    }

    //Balance nodes in path from inserted/deleted node to root
    private void balancePath(String incidentId) {
        ArrayList<AVLTreeNode> path = path(incidentId);

        for (int i = path.size() - 1; i >= 0; i--) {
            AVLTreeNode A = path.get(i);
            updateHeight(A);

            AVLTreeNode parentOfA = (A == root) ? null : path.get(i - 1);

            switch (balanceFactor(A)) {
                case -2:
                    if (balanceFactor(A.left) <= 0) {
                        balanceLL(A, parentOfA);
                    } else {
                        balanceLR(A, parentOfA);
                    }
                    break;

                case 2:
                    if (balanceFactor(A.right) >= 0) {
                        balanceRR(A, parentOfA);
                    } else {
                        balanceRL(A, parentOfA);
                    }
                    break;
            }
        }
    }

    //Returns balance factor of node
    private int balanceFactor(AVLTreeNode node) {
        if (node.right == null) {
            return -node.height;
        } else if (node.left == null) {
            return node.height;
        } else {
            return node.right.height - node.left.height;
        }
    }

    //Balance LL
    private void balanceLL(AVLTreeNode A, AVLTreeNode parentOfA) {
        AVLTreeNode B = A.left;

        if (A == root) {
            root = B;
        } else if (parentOfA.left == A) {
            parentOfA.left = B;
        } else {
            parentOfA.right = B;
        }

        A.left = B.right;
        B.right = A;

        updateHeight(A);
        updateHeight(B);
    }

    //Balance LR
    private void balanceLR(AVLTreeNode A, AVLTreeNode parentOfA) {
        AVLTreeNode B = A.left;
        AVLTreeNode C = B.right;

        if (A == root) {
            root = C;
        } else if (parentOfA.left == A) {
            parentOfA.left = C;
        } else {
            parentOfA.right = C;
        }

        A.left = C.right;
        B.right = C.left;
        C.left = B;
        C.right = A;

        updateHeight(A);
        updateHeight(B);
        updateHeight(C);
    }

    //Balance RR
    private void balanceRR(AVLTreeNode A, AVLTreeNode parentOfA) {
        AVLTreeNode B = A.right;

        if (A == root) {
            root = B;
        } else if (parentOfA.left == A) {
            parentOfA.left = B;
        } else {
            parentOfA.right = B;
        }

        A.right = B.left;
        B.left = A;

        updateHeight(A);
        updateHeight(B);
    }

    //Balance RL
    private void balanceRL(AVLTreeNode A, AVLTreeNode parentOfA) {
        AVLTreeNode B = A.right;
        AVLTreeNode C = B.left;

        if (A == root) {
            root = C;
        } else if (parentOfA.left == A) {
            parentOfA.left = C;
        } else {
            parentOfA.right = C;
        }

        A.right = C.left;
        B.left = C.right;
        C.left = A;
        C.right = B;

        updateHeight(A);
        updateHeight(B);
        updateHeight(C);
    }

    //AVL node class
    protected static class AVLTreeNode {
        protected Incident incident;
        protected AVLTreeNode left;
        protected AVLTreeNode right;
        protected int height = 0;

        public AVLTreeNode(Incident incident) {
            this.incident = incident;
        }
    }
}