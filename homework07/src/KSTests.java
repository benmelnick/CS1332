import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Random;

/**
 * These tests are not exhaustive.
 * @author Kyle Stachowicz
 * @version 1.1
 */
public class KSTests {
    private AVL<Integer> avlTree;

    @Before
    public void setup() {
        avlTree = new AVL<>();
    }

    /**
     * Print the tree to stdout with * replacing null nodes, in the form of:
     *----------------------------- 4 ---------------------------
     *------------- 3 ---------------------------- 6 ------------
     *----- 1 ----------------------------- 5 ------------- 7 ---
     *- * ----- 2 ----- * ----- * ----- * ----- * ----- * ----- 8
     * (spaces shown here as '-' so the formatter doesn't break it)
     *
     * @param tree the root of the tree to print
     */
    private void printTree(AVLNode<Integer> tree) {
        if (tree == null) {
            return;
        }

        Queue<AVLNode<Integer>> operationQueue = new ArrayDeque<>();
        int level = 0;
        int height = tree.getHeight();

        operationQueue.add(tree);

        int i = 0;
        for (int j = 0; j < Math.pow(2, height - level); j++) {
            System.out.print("\t");
        }
        while (level <= height && !operationQueue.isEmpty()) {
            int currentLevelElems = (int) Math.pow(2, level);

            AVLNode<Integer> next = operationQueue.remove();
            if (next.getData() == null) {
                System.out.print("*\t");
                operationQueue.add(next);
                operationQueue.add(next);
            } else {
                System.out.print(next.getData() + "\t");
                if (next.getLeft() != null) {
                    operationQueue.add(next.getLeft());
                } else {
                    operationQueue.add(new AVLNode<>(null));
                }

                if (next.getRight() != null) {
                    operationQueue.add(next.getRight());
                } else {
                    operationQueue.add(new AVLNode<>(null));
                }
            }

            int spacing = (int) Math.pow(2.0, height - level + 1) - 1;
            for (int j = 0; j < spacing; j++) {
                System.out.print("\t");
            }

            i++;
            if (i == currentLevelElems) {
                i = 0;
                level++;
                System.out.println();
                int beginSpacing = (int) Math.pow(2, height - level);
                for (int j = 0; j < beginSpacing; j++) {
                    System.out.print("\t");
                }
            }
        }
    }

    @Test
    public void fuzz() {
        Random random = new Random();
        HashSet<Integer> oracle = new HashSet<>();

        int max = 547;

        for (int i = 0; i < 1000; i++) {
            int op = random.nextInt(2);

            int n;
            switch (op) {
                case 0:
                    n = random.nextInt(max);
                    System.out.println("Add " + n);
                    oracle.add(n);
                    avlTree.add(n);
                    break;
                case 1:
                    if (oracle.size() > 0) {
                        // Grab a random element from oracle
                        n = random.nextInt(oracle.size());
                        for (Integer j : oracle) {
                            if (n-- == 0) {
                                n = j;
                                break;
                            }
                        }

                        System.out.println("Remove " + n);
                        oracle.remove(n);
                        avlTree.remove(n);
                    }
                    break;
                default:
                    break;
            }

            assertEquals(oracle.size(), verifyTree(avlTree.getRoot()));
            assertEquals(oracle.size(), avlTree.size());
        }
    }

    /**
     * Verify the given tree recursively, checking both height and balance
     * factor for each node.
     * @param tree the root of the tree to check.
     * @return the number of nodes checked
     */
    private int verifyTree(AVLNode<Integer> tree) {
        if (tree == null) {
            return 0;
        }

        int heightLeft = -1;
        int heightRight = -1;
        if (tree.getLeft() != null) {
            heightLeft = tree.getLeft().getHeight();
        }
        if (tree.getRight() != null) {
            heightRight = tree.getRight().getHeight();
        }

        assertEquals(tree.getHeight(), Math.max(heightLeft, heightRight) + 1);
        assertEquals(tree.getBalanceFactor(), heightLeft - heightRight);

        if (tree.getBalanceFactor() < -1 || tree.getBalanceFactor() > 1) {
            printTree(tree);
        }

        int leftSize = verifyTree(tree.getLeft());
        int rightSize = verifyTree(tree.getRight());

        assertTrue(tree.getBalanceFactor() <= 1);
        assertTrue(tree.getBalanceFactor() >= -1);

        return rightSize + leftSize + 1;
    }
}