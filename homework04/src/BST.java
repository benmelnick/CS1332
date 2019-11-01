import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.LinkedList;

/**
 * Your implementation of a binary search tree.
 *
 * @author Benjamin Melnick
 * @userid bmelnick3
 * @GTID 903305201
 * @version 1.0
 */
public class BST<T extends Comparable<? super T>> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no-argument constructor that should initialize an empty BST.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Initializes the BST with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * Hint: Not all Collections are indexable like Lists, so a regular
     * for loop will not work here. What other type of loop would work?
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add "
                    + "data from a null collection.");
        }
        for (T item : data) {
            add(item);
        }
    }

    /**
     * Add the data as a leaf in the BST. Should traverse the tree to find the
     * appropriate location. If the data is already in the tree, then nothing
     * should be done (the duplicate shouldn't get added, and size should not be
     * incremented).
     * 
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n).
     *
     * @throws IllegalArgumentException if the data is null
     * @param data the data to be added
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data.");
        }
        root = addHelper(data, root);
    }

    /**
     * recursive helper method for add
     * @param data the data to be added
     * @param node the node to check
     * @return root node that now links to updated BST
     */
    private BSTNode<T> addHelper(T data, BSTNode<T> node) {
        if (node == null) {
            size++;
            return new BSTNode(data);
        } else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(addHelper(data, node.getLeft()));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(addHelper(data, node.getRight()));
        }
        return node;
    }

    /**
     * Removes the data from the tree. There are 3 cases to consider:
     *
     * 1: the data is a leaf. In this case, simply remove it.
     * 2: the data has one child. In this case, simply replace it with its
     * child.
     * 3: the data has 2 children. Use the successor to replace the data.
     * You must use recursion to find and remove the successor (you will likely
     * need an additional helper method to handle this case efficiently).
     *
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n).
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
        BSTNode<T> removeRef = new BSTNode(null);
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
    private BSTNode<T> removeHelper(T data, BSTNode<T> node,
                                    BSTNode<T> removeRef) {
        if (node == null) {
            throw new NoSuchElementException(data + " was not "
                    + " found in the BST.");
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
            node.setData(successor(node.getRight()));
            node.setRight(removeHelper(node.getData(), node.getRight(),
                    removeRef));
        }
        return node;
    }

    /**
     * returns the data from a node's succcessor
     * @param node starting point from which to find the successor
     * @return data from the successor node
     */
    private T successor(BSTNode<T> node) {
        T data = node.getData();
        while (node.getLeft() != null) {
            data = node.getLeft().getData();
            node = node.getLeft();
        }
        return data;
    }

    /**
     * Returns the data in the tree matching the parameter passed in (think
     * carefully: should you use value equality or reference equality?).
     *
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n).
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
            throw new IllegalArgumentException("Cannot run get on null data.");
        }
        return getHelper(data, root);
    }

    /**
     * recursive helper method for get
     * @param data data to look for
     * @param node node to examine for data
     * @return data in the BST equal to passed in data
     */
    private T getHelper(T data, BSTNode<T> node) {
        if (node == null) {
            throw new NoSuchElementException("The data was "
                    + "not found in this tree.");
        } else {
            if (data.compareTo(node.getData()) < 0) {
                //move to left
                return getHelper(data, node.getLeft());
            } else if (data.compareTo(node.getData()) > 0) {
                //move to right
                return getHelper(data, node.getRight());
            }
        }
        return node.getData();
    }

    /**
     * Returns whether or not data equivalent to the given parameter is
     * contained within the tree. The same type of equality should be used as
     * in the get method.
     *
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n).
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
    private boolean containsHelper(T data, BSTNode<T> node) {
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
     * Should run in O(n).
     *
     * @return a preorder traversal of the tree
     */
    public List<T> preorder() {
        List<T> list = new ArrayList<T>();
        return preOrderHelper(root, list);
    }

    /**
     * recursive helper method for pre order traversal
     * @param node node to examine for data
     * @param list list to add data to
     * @return list containing data in the BST
     */
    private List<T> preOrderHelper(BSTNode<T> node, List<T> list) {
        if (node == null) {
            return list;
        } else {
            list.add(node.getData());
            preOrderHelper(node.getLeft(), list);
            preOrderHelper(node.getRight(), list);
        }
        return list;
    }


    /**
     * Should run in O(n).
     *
     * @return an inorder traversal of the tree
     */
    public List<T> inorder() {
        List<T> list = new ArrayList<T>();
        return inOrderHelper(root, list);
    }

    /**
     * recursive helper method for in order traversal
     * @param node node to examine for data
     * @param list list to add data to
     * @return list containing data in the BST
     */
    private List<T> inOrderHelper(BSTNode<T> node, List<T> list) {
        if (node == null) {
            return list;
        } else {
            inOrderHelper(node.getLeft(), list);
            list.add(node.getData());
            inOrderHelper(node.getRight(), list);
        }
        return list;
    }

    /**
     * Should run in O(n).
     *
     * @return a postorder traversal of the tree
     */
    public List<T> postorder() {
        List<T> list = new ArrayList<T>();
        return postOrderHelper(root, list);
    }

    /**
     * recursive helper method for post order traversal
     * @param node node to examine for data
     * @param list list to add data to
     * @return list containing data in the BST
     */
    private List<T> postOrderHelper(BSTNode<T> node, List<T> list) {
        if (node == null) {
            return list;
        } else {
            postOrderHelper(node.getLeft(), list);
            postOrderHelper(node.getRight(), list);
            list.add(node.getData());
        }
        return list;
    }

    public void removeLeaves() {
        root = removeLeavesHelper(root);
    }

    private BSTNode<T> removeLeavesHelper(BSTNode<T> node) {
        if (node.getRight() == null && node.getLeft() == null) {
            return null;
        } else if (node.getLeft() == null) {
            node.setRight(removeLeavesHelper(node.getRight()));
        } else if (node.getRight() == null) {
            node.setLeft(removeLeavesHelper(node.getLeft()));
        } else {
            removeLeavesHelper(node.getRight());
            removeLeavesHelper(node.getLeft());
        }
        return node;
    }


    /**
     * Generate a level-order traversal of the tree.
     *
     * To do this, add the root node to a queue. Then, while the queue isn't
     * empty, remove one node, add its data to the list being returned, and add
     * its left and right child nodes to the queue. If what you just removed is
     * {@code null}, ignore it and continue with the rest of the nodes.
     *
     * Should run in O(n).
     *
     * @return a level order traversal of the tree
     */
    public List<T> levelorder() {
        List<T> returned = new ArrayList<T>();
        LinkedList<BSTNode<T>> items = new LinkedList<>();
        items.addLast(root);
        while (!items.isEmpty()) {
            BSTNode<T> node = items.removeFirst();
            if (node != null) {
                returned.add(node.getData());
                items.addLast(node.getLeft());
                items.addLast(node.getRight());
            }
        }
        return returned;
    }

    /**
     * Finds and retrieves the k-largest elements from the BST in sorted order,
     * least to greatest.
     *
     * In most cases, this method will not need to traverse the entire tree to
     * function properly, so you should only traverse the branches of the tree
     * necessary to get the data and only do so once. Failure to do so will
     * result in the efficiency penalty.
     *
     * EXAMPLE: Given the BST below composed of Integers:
     *
     *                50
     *              /    \
     *            25      75
     *           /  \
     *          12   37
     *         /  \    \
     *        10  15    40
     *           /
     *          13
     *
     * kLargest(5) should return the list [25, 37, 40, 50, 75].
     * kLargest(3) should return the list [40, 50, 75].
     *
     * Should have a running time of O(log(n) + k) for a balanced tree and a
     * worst case of O(n + k).
     *
     * @throws java.lang.IllegalArgumentException if k > n, the number of data
     * in the BST
     * @param k the number of largest elements to return
     * @return sorted list consisting of the k largest elements
     */
    public List<T> kLargest(int k) {
        if (k > size) {
            throw new IllegalArgumentException(k + " is larger "
                    + "than the size of the list.");
        }
        if (k < 0) {
            throw new IllegalArgumentException("Parameter for "
                + "kLargest cannot be negative.");
        }
        List<T> list = new ArrayList(k);
        return kLargestHelper(list, root, k);
    }

    /**
     * helper recursive method for kLargest
     * @param list - list to add values too
     * @param node - node being currently visited
     * @param k - number of values needed
     * @return sorted list of the k largest elements
     */
    private List<T> kLargestHelper(List<T> list, BSTNode<T> node, int k) {
        if (node == null) {
            return list;
        } else {
            //perform a reverse in-order traversal
            kLargestHelper(list, node.getRight(), k);
            if (list.size() == k) {
                return list;
            } else {
                list.add(0, node.getData());
                kLargestHelper(list, node.getLeft(), k);
            }
        }
        return list;
    }

    /**
     * Clears the tree.
     *
     * Should run in O(1).
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Calculate and return the height of the root of the tree. A node's
     * height is defined as {@code max(left.height, right.height) + 1}. A leaf
     * node has a height of 0 and a null child should be -1.
     *
     * Should be calculated in O(n).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return heightHelper(root);
    }

    /**
     * private height recursive helper
     * @param node - node to check
     * @return int representing height of node being checked
     */
    private int heightHelper(BSTNode<T> node) {
        if (node == null) {
            //base case: null node
            return -1;
        } else {
            return 1 + Math.max(heightHelper(node.getLeft()),
                    heightHelper(node.getRight()));
        }
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
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
}
