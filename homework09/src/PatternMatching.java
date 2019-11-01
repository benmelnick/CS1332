import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Your implementations of various string searching algorithms.
 *
 * @author Benjamin Melnick
 * @userid bmelnick3
 * @GTID 903305201
 * @version 1.0
 */
public class PatternMatching {

    /**
     * Knuth-Morris-Pratt (KMP) algorithm that relies on the failure table (also
     * called failure function). Works better with small alphabets.
     *
     * Make sure to implement the failure table before implementing this method.
     *
     * @throws IllegalArgumentException if the pattern is null or of length 0
     * @throws IllegalArgumentException if text or comparator is null
     * @param pattern the pattern you are searching for in a body of text
     * @param text the body of text where you search for pattern
     * @param comparator you MUST use this for checking character equality
     * @return list containing the starting index for each match found
     */
    public static List<Integer> kmp(CharSequence pattern, CharSequence text,
                                    CharacterComparator comparator) {
        if (pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException("Pattern cannot "
                    + "be null.");
        }
        if (text == null) {
            throw new IllegalArgumentException("Text cannot "
                    + "be null.");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot "
                    + "be null.");
        }
        List<Integer> matches = new ArrayList<Integer>();
        int j = 0; //idx in pattern
        int i = 0; //idx in text
        int m = pattern.length();
        int n = text.length();
        if (m > n) {
            return matches;
        }
        int[] failureTable = buildFailureTable(pattern, comparator);
        while (i <= n - m) {
            while (j < m && comparator.compare(pattern.charAt(j),
                    text.charAt(i + j)) == 0) {
                j++;
            }
            if (j == 0) {
                //move over in the text
                i++;
            } else {
                if (j == m) {
                    //found a match
                    matches.add(i);
                }
                int nextAlignment = failureTable[j - 1];
                i = i + j - nextAlignment;
                j = nextAlignment;
            }
        }
        return matches;
    }

    /**
     * Builds failure table that will be used to run the Knuth-Morris-Pratt
     * (KMP) algorithm.
     *
     * The table built should be the length of the input text.
     *
     * Note that a given index i will be the largest prefix of the pattern
     * indices [0..i] that is also a suffix of the pattern indices [1..i].
     * This means that index 0 of the returned table will always be equal to 0
     *
     * Ex. ababac
     *
     * table[0] = 0
     * table[1] = 0
     * table[2] = 1
     * table[3] = 2
     * table[4] = 3
     * table[5] = 0
     *
     * If the pattern is empty, return an empty array.
     *
     * @throws IllegalArgumentException if the pattern or comparator is null
     * @param pattern a {@code CharSequence} you're building a failure table for
     * @param comparator you MUST use this for checking character equality
     * @return integer array holding your failure table
     */
    public static int[] buildFailureTable(CharSequence pattern,
                                          CharacterComparator comparator) {
        if (pattern == null) {
            throw new IllegalArgumentException("Pattern cannot "
                    + "be null.");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot "
                    + "be null.");
        }
        int[] failureTable = new int[pattern.length()];
        int i = 0;
        int j = 1;
        int m = failureTable.length;
        failureTable[0] = 0;
        while (j < m) {
            if (comparator.compare(pattern.charAt(i), pattern.charAt(j)) == 0) {
                failureTable[j] = i + 1;
                i++;
                j++;
            } else {
                if (i == 0) {
                    failureTable[j] = 0;
                    j++;
                } else {
                    i = failureTable[i - 1];
                }
            }
        }
        return failureTable;
    }

    /**
     * Boyer Moore algorithm that relies on last occurrence table. Works better
     * with large alphabets.
     *
     * Make sure to implement the last occurrence table before implementing this
     * method.
     *
     * @throws IllegalArgumentException if the pattern is null or of length 0
     * @throws IllegalArgumentException if text or comparator is null
     * @param pattern the pattern you are searching for in a body of text
     * @param text the body of text where you search for the pattern
     * @param comparator you MUST use this for checking character equality
     * @return list containing the starting index for each match found
     */
    public static List<Integer> boyerMoore(CharSequence pattern,
                                           CharSequence text,
                                           CharacterComparator comparator) {
        if (pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException("Pattern cannot "
                    + "be null.");
        }
        if (text == null || text.length() == 0) {
            throw new IllegalArgumentException("Text cannot "
                    + "be null.");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot "
                    + "be null.");
        }
        List<Integer> matches = new ArrayList<>();
        int m = pattern.length();
        int n = text.length();
        if (m > n) {
            return matches;
        }
        Map<Character, Integer> lastOccurrence = buildLastTable(pattern);
        int i = 0;
        while (i <= n - m) {
            int j = pattern.length() - 1;
            //checking for character equality
            while (j >= 0 && comparator.compare(text.charAt(i + j),
                    pattern.charAt(j)) == 0) {
                j--;
            }
            if (j == -1) {
                //found a complete match
                matches.add(i);
                i++;
            } else {
                //mismatch
                Integer shiftIndex = lastOccurrence.get(text.charAt(i + j));
                if (shiftIndex == null) {
                    shiftIndex = -1;
                }
                if (shiftIndex < j) {
                    i = i + (j - shiftIndex);
                } else {
                    i++;
                }
            }
        }
        return matches;
    }


    /**
     * Builds last occurrence table that will be used to run the Boyer Moore
     * algorithm.
     *
     * Note that each char x will have an entry at table.get(x).
     * Each entry should be the last index of x where x is a particular
     * character in your pattern.
     * If x is not in the pattern, then the table will not contain the key x,
     * and you will have to check for that in your Boyer Moore implementation.
     *
     * Ex. octocat
     *
     * table.get(o) = 3
     * table.get(c) = 4
     * table.get(t) = 6
     * table.get(a) = 5
     * table.get(everything else) = null, which you will interpret in
     * Boyer-Moore as -1
     *
     * If the pattern is empty, return an empty map.
     *
     * @throws IllegalArgumentException if the pattern is null
     * @param pattern a {@code CharSequence} you are building last table for
     * @return a Map with keys of all of the characters in the pattern mapping
     *         to their last occurrence in the pattern
     */
    public static Map<Character, Integer> buildLastTable(CharSequence pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException("Pattern cannot "
                    + "be null.");
        }
        Map<Character, Integer> lastTable = new HashMap<Character, Integer>();
        for (int i = 0; i < pattern.length(); i++) {
            Character letter = pattern.charAt(i);
            lastTable.put(letter, i);
        }
        return lastTable;
    }

    /**
     * Prime base used for Rabin-Karp hashing.
     * DO NOT EDIT!
     */
    private static final int BASE = 101;

    /**
     * Runs the Rabin-Karp algorithm. This algorithms generates hashes for the
     * pattern and compares this hash to substrings of the text before doing
     * character by character comparisons.
     *
     * When the hashes are equal and you do character comparisons, compare
     * starting from the beginning of the pattern to the end, not from the end
     * to the beginning.
     *
     * You must use the Rabin-Karp Rolling Hash for this implementation. The
     * formula for it is:
     *
     * sum of: c * BASE ^ (pattern.length - 1 - i), where c is the integer
     * value of the current character, and i is the index of the character
     *
     * Note that if you were dealing with very large numbers here, your hash
     * will likely overflow. However, you will not need to handle this case.
     * You may assume there will be no overflow.
     *
     * For example: Hashing "bunn" as a substring of "bunny" with base 101 hash
     * = b * 101 ^ 3 + u * 101 ^ 2 + n * 101 ^ 1 + n * 101 ^ 0 = 98 * 101 ^ 3 +
     * 117 * 101 ^ 2 + 110 * 101 ^ 1 + 110 * 101 ^ 0 = 102174235
     *
     * Another key step for this algorithm is that updating the hashcode from
     * one substring to the next one must be O(1). To update the hash:
     *
     * remove the oldChar times BASE raised to the length - 1, multiply by
     * BASE, and add the newChar.
     *
     * For example: Shifting from "bunn" to "unny" in "bunny" with base 101
     * hash("unny") = (hash("bunn") - b * 101 ^ 3) * 101 + y =
     * (102174235 - 98 * 101 ^ 3) * 101 + 121 = 121678558
     *
     * Keep in mind that calculating exponents is not O(1) in general, so you'll
     * need to keep track of what BASE^{m - 1} is for updating the hash.
     *
     * Do NOT use Math.pow
     *
     * @throws IllegalArgumentException if the pattern is null or of length 0
     * @throws IllegalArgumentException if text or comparator is null
     * @param pattern a string you're searching for in a body of text
     * @param text the body of text where you search for pattern
     * @param comparator the comparator to use when checking character equality
     * @return list containing the starting index for each match found
     */
    public static List<Integer> rabinKarp(CharSequence pattern,
                                          CharSequence text,
                                          CharacterComparator comparator) {
        if (pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException("Pattern cannot "
                    + "be null.");
        }
        if (text == null) {
            throw new IllegalArgumentException("Text cannot "
                    + "be null.");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot "
                    + "be null.");
        }
        List<Integer> matches = new ArrayList<>();
        int n = pattern.length();
        int m = text.length();
        if (n > m) {
            return matches;
        }
        int patternHash = generateHash(pattern, n);
        int textHash = generateHash(text, n);
        int i = 0;
        while (i <= m - n) {
            if (patternHash == textHash) {
                int j = 0;
                while (j < n && comparator.compare(text.charAt(i + j),
                        pattern.charAt(j)) == 0) {
                    j++;
                }
                if (j == n) {
                    matches.add(i);
                }
            }
            i++;
            if (i <= m - n) {
                textHash = updateHash(textHash, n, text.charAt(i - 1),
                        text.charAt(i + n - 1));
            }
        }
        return matches;
    }

    /**
     * hash function used for Rabin-Karp
     * @param current substring you are generating hash for
     * @param length the length of the string you want to generate the hash for
     * @return hash of the substring
     */
    private static int generateHash(CharSequence current, int length) {
        int hash = 0;
        for (int i = 0; i < length; i++) {
            hash = hash + current.charAt(i) * pow(BASE, length - 1 - i);
        }
        return hash;
    }

    /**
     * Updates a hash in constant time to avoid constantly recalculating
     * entire hash. To update the hash:
     *
     *  remove the oldChar times BASE raised to the length - 1, multiply by
     *  BASE, and add the newChar.
     *
     * @param oldHash hash generated by generateHash
     * @param length length of pattern/substring of text
     * @param oldChar character we want to remove from hashed substring
     * @param newChar character we want to add to hashed substring
     * @return updated hash of this substring
     */
    private static int updateHash(int oldHash, int length,
                                  char oldChar, char newChar) {
        return (oldHash - oldChar * pow(BASE, length - 1)) * BASE + newChar;
    }

    /**
     * calculate the result of a number raised to a power
     * @param base base of the number
     * @param exp power to raise the base to; greater than 0
     * @return result of the base raised to the power
     */
    private static int pow(int base, int exp) {
        if (exp == 0) {
            return 1;
        } else if (exp == 1) {
            return base;
        }
        int halfPow = pow(base, exp / 2);
        if (exp % 2 == 0) {
            return halfPow * halfPow;
        } else {
            return halfPow * pow(base, exp / 2 + 1);
        }
    }
}
