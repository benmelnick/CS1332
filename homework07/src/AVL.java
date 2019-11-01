import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * Your implementation of an AVL Tree.
 *
 * @author Benjamin Melnick
 * @userid bmelnick3
 * @GTID 903305201
 * @version 1.0
 */
public class AVL<T extends Comparable<? super T>> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private AVLNode<T> root;
    private int size;

    /**
     * A no-argument constructor that should initialize an empty AVL.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public AVL() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Initializes the AVL tree with the data in the Collection. The data
     * should be added in the same order it appears in the Collection.
     *
     * Hint: Not all Collections are indexable like Lists, so a regular
     * for loop will not work here. What other type of loop would work?
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add "
            + "null collections to AVL.");
        }
        for (T item : data) {
            add(item);
        }
    }

    /**
     * Add the data to the AVL. Start by adding it as a leaf and rotate the tree
     * as needed. Should traverse the tree to find the appropriate location.
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors going up the tree,
     * rebalancing if necessary.
     *
     * @throws java.lang.IllegalArgumentException if the data is null
     * @param data the data to be added
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data to AVL.");
        }
        root = addHelper(data, root);
    }

    /**
     * recursive helper method for adding
     * @param data the data to be added
     * @param node the node to check
     * @return new node with added data
     */
    private AVLNode<T> addHelper(T data, AVLNode<T> node) {
        if (node == null) {
            size++;
            return new AVLNode<>(data);
        } else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(addHelper(data, node.getLeft()));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(addHelper(data, node.getRight()));
        }
        updateNode(node);
        if (Math.abs(node.getBalanceFactor()) > 1) {
            node = balance(node);
        }
        return node;
    }

    /**
     * private helper method for updating the balance
     *      factor and height of a node
     * @param node node whose BF and height must be updated
     */
    private void updateNode(AVLNode<T> node) {
        int left = nodeHeight(node.getLeft());
        int right = nodeHeight(node.getRight());
        node.setBalanceFactor(left - right);
        node.setHeight(1 + Math.max(left, right));
    }

    /**
     * helper method for quickly getting the height of any node
     * @param node node to get height for
     * @return height of node
     */
    private int nodeHeight(AVLNode<T> node) {
        if (node == null) {
            return -1;
        }
        return node.getHeight();
    }

    public void mysteryRec(AVLNode<T> curr) {
        if (curr == null) {
            return;
        }
        if (curr.getBalanceFactor() > 0) {
            mysteryRec(curr.getLeft());
        } else {
            mysteryRec(curr.getRight());
        }
        if ((Integer)(curr.getData()) % 2 == 0) {
            System.out.println(curr.getData());
        }
    }

    /**
     * helper method to balance the tree
     * @param node the root of a subtree to balance
     * @return the new root of the subtree
     */
    private AVLNode<T> balance(AVLNode<T> node) {
        AVLNode<T> returnNode = node;
        if (node.getBalanceFactor() > 0) {
            //left heavy - rotate right
            if (node.getLeft().getBalanceFactor() < 0) {
                //left-right rotation
                node.setLeft(rotateLeft(node.getLeft()));
                returnNode = rotateRight(node);
            } else {
                //right rotation
                returnNode = rotateRight(node);
            }
        } else if (node.getBalanceFactor() < 0) {
            //right heavy - rotate left
            if (node.getRight().getBalanceFactor() > 0) {
                //right-left rotation
                node.setRight(rotateRight(node.getRight()));
                returnNode = rotateLeft(node);
            } else {
                //left rotation
                returnNode = rotateLeft(node);
            }
        }
        return returnNode;
    }

    /**
     * private helper method for performing left rotation
     * @param node root of subtree to rotate
     * @return root node of updated subtree
     */
    private AVLNode<T> rotateLeft(AVLNode<T> node) {
        AVLNode<T> rightNode = node.getRight();
        node.setRight(rightNode.getLeft());
        rightNode.setLeft(node);
        updateNode(node);
        updateNode(rightNode);
        return rightNode;
    }

    /**
     * private helper method for performing right rotation
     * @param node root of subtree to rotate
     * @return root node of updated subtree
     */
    private AVLNode<T> rotateRight(AVLNode<T> node) {
        AVLNode<T> leftNode = node.getLeft();
        node.setLeft(leftNode.getRight());
        leftNode.setRight(node);
        updateNode(node);
        updateNode(leftNode);
        return leftNode;
    }

    /**
     * Removes the data from the tree. There are 3 cases to consider:
     *
     * 1: the data is a leaf. In this case, simply remove it.
     * 2: the data has one child. In this case, simply replace it with its
     * child.
     * 3: the data has 2 children. Use the predecessor to replace the data,
     * not the successor.
     * You must use recursion to find and remove the predecessor (you will
     * likely need an additional helper method to handle this case efficiently).
     *
     * Remember to recalculate heights going up the tree, rebalancing if
     * necessary.
     *
     * @throws IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     * @param data the data to remove from the tree.
     * @return the data removed from the tree. Do not return the same data
     * that was passed in.  Return the data that was stored in the tree.
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot remove null data.");
        }
        //create dummy node reference to return data that was removed
        AVLNode<T> removeRef = new AVLNode(null);
        root = removeHelper(data, root, removeRef);
        size--;
        return removeRef.getData();
    }

    /**
     * recursively traverses the BST to find the node containing the data
     * @param data data to remove
     * @param node node to check for data
     * @param removeRef reference to dummy node w/ removed data
     * @return node containing data
     */
    private AVLNode<T> removeHelper(T data, AVLNode<T> node,
                                    AVLNode<T> removeRef) {
        if (node == null) {
            throw new NoSuchElementException(data + " was not "
                    + "found in the tree.");
        }
        //recurse until you reach the node
        if (data.compareTo(node.getData()) < 0) {
            node.setLeft(removeHelper(data, node.getLeft(), removeRef));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(removeHelper(data, node.getRight(), removeRef));
        } else {
            //if data is the same as node's data, delete this node
            //case 1 and 2: no child or one child
            if (removeRef.getData() == null) {
                //ensures dummy node's data is only changed once
                removeRef.setData(node.getData());
            }
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }
            //case 3: two children
            // get successor, swap data, delete old successor node
            node.setData(predecessor(node));
            node.setLeft(removeHelper(node.getData(), node.getLeft(),
                    removeRef));
        }
        updateNode(node);
        if (Math.abs(node.getBalanceFactor()) > 1) {
            node = balance(node);
        }
        return node;
    }

    /**
     * helper method to return the data in the predecessor node
     * @param node node being removed
     * @return data of node's predecessor
     */
    private T predecessor(AVLNode<T> node) {
        node = node.getLeft();
        T data = node.getData();
        while (node.getRight() != null) {
            data = node.getRight().getData();
            node = node.getRight();
        }
        return data;
    }

    /**
     * Returns the data in the tree matching the parameter passed in (think
     * carefully: should you use value equality or reference equality?).
     *
     * @throws IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     * @param data the data to search for in the tree.
     * @return the data in the tree equal to the parameter. Do not return the
     * same data that was passed in.  Return the data that was stored in the
     * tree.
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot retrieve null data.");
        }
        return getHelper(data, root);
    }

    /**
     * recursive helper method for get
     * @param data data to search for
     * @param node node to examine for data
     * @return data if it was found in node
     */
    private T getHelper(T data, AVLNode<T> node) {
        if (node == null) {
            throw new NoSuchElementException(data
                    + " was not found in the AVL.");
        }
        if (node.getData().equals(data)) {
            return node.getData();
        } else {
            if (data.compareTo(node.getData()) > 0) {
                return getHelper(data, node.getRight());
            } else {
                return getHelper(data, node.getLeft());
            }
        }
    }

    /**
     * Returns whether or not data equivalent to the given parameter is
     * contained within the tree. The same type of equality should be used as
     * in the get method.
     *
     * @throws IllegalArgumentException if the data is null
     * @param data the data to search for in the tree.
     * @return whether or not the parameter is contained within the tree.
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot check "
                    + "contains on null data.");
        }
        return containsHelper(data, root);
    }

    /**
     * recursive helper method for contains
     * @param data data to search for
     * @param node node to examine
     * @return boolean stating if data is in BST
     */
    private boolean containsHelper(T data, AVLNode<T> node) {
        if (node == null) {
            //reached end of the BST without reaching the node
            return false;
        } else if (node.getData().equals(data)) {
            return true;
        } else {
            if (data.compareTo(node.getData()) > 0) {
                //move right
                return containsHelper(data, node.getRight());
            } else {
                //move left
                return containsHelper(data, node.getLeft());
            }
        }
    }

    /**
     * Returns the data in the deepest node. If there are more than one node
     * with the same deepest depth, return the right most node with the
     * deepest depth.
     *
     * Must run in O(log n) for all cases
     *
     * Example
     * Tree:
     *           2
     *        /    \
     *       0      3
     *        \
     *         1
     * Max Deepest Node:
     * 1 because it is the deepest node
     *
     * Example
     * Tree:
     *           2
     *        /    \
     *       0      4
     *        \    /
     *         1  3
     * Max Deepest Node:
     * 3 because it is the maximum deepest node (1 has the same depth but 3 > 1)
     *
     * @return the data in the maximum deepest node or null if the tree is empty
     */
    public T maxDeepestNode() {
        if (size == 0) {
            return null;
        }
        return deepestNodeHelper(root);
    }

    /**
     * recursive helper method for finding the deepest node in a subtree
     * @param node the root of the subtree
     * @return data of the deepest node
     */
    private T deepestNodeHelper(AVLNode<T> node) {
        if (node.getLeft() == null && node.getRight() == null) {
            return node.getData();
        } else {
            int left = nodeHeight(node.getLeft());
            int right = nodeHeight(node.getRight());
            if (left > right) {
                return deepestNodeHelper(node.getLeft());
            } else {
                return deepestNodeHelper(node.getRight());
            }
        }
    }

    /**
     * Returns the data of the deepest common ancestor between two nodes with
     * the given data. The deepest common ancestor is the lowest node (i.e.
     * deepest) node that has both data1 and data2 as descendants.
     * If the data are the same, the deepest common ancestor is simply the node
     * that contains the data. You may not assume data1 < data2.
     * (think carefully: should you use value equality or reference equality?).
     *
     * Must run in O(log n) for all cases
     *
     * Example
     * Tree:
     *           2
     *        /    \
     *       0      3
     *        \
     *         1
     * deepestCommonAncestor(3, 1): 2
     *
     * Example
     * Tree:
     *           3
     *        /    \
     *       1      4
     *      / \
     *     0   2
     * deepestCommonAncestor(0, 2): 1
     *
     * @param data1 the first data
     * @param data2 the second data
     * @throws java.lang.IllegalArgumentException if one or more of the data
     *          are null
     * @throws java.util.NoSuchElementException if one or more of the data are
     *          not in the tree
     * @return the data of the deepest common ancestor
     */
    public T deepestCommonAncestor(T data1, T data2) {
        if (data1 == null || data2 == null) {
            throw new IllegalArgumentException("One or more of "
                    + "the data is null.");
        }
        AVLNode<T> dcaNode = ancestorHelper(data1, data2, root);
        if (dcaNode == null) {
            throw new NoSuchElementException("One or more of the "
                    + "data is not in the tree.");
        }
        return dcaNode.getData();
    }

    /**
     * recursive helper method for returning ancestor
     * @param data1 first node
     * @param data2 second node
     * @param node current node to compare
     * @return ancestor node
     */
    private AVLNode<T> ancestorHelper(T data1, T data2, AVLNode<T> node) {
        if (node == null) {
            return null;
        }
        if (data1.compareTo(node.getData()) < 0
                && data2.compareTo(node.getData()) < 0) {
            return ancestorHelper(data1, data2, node.getLeft());
        } else if (data1.compareTo(node.getData()) > 0
                && data2.compareTo(node.getData()) > 0) {
            return ancestorHelper(data1, data2, node.getRight());
        }
        return node;
    }

    /**
     * Clear the tree.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Return the height of the root of the tree.
     *
     * Since this is an AVL, this method does not need to traverse the tree
     * and should be O(1)
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return nodeHeight(root);
    }

    /**
     * THIS METHOD IS ONLY FOR TESTING PURPOSES.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the number of elements in the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD
        return size;
    }

    /**
     * THIS METHOD IS ONLY FOR TESTING PURPOSES.
     * DO NOT USE IT IN YOUR CODE
     * DO NOT CHANGE THIS METHOD
     *
     * @return the root of the tree
     */
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
}
