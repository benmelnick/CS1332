import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class PatternMatchingTest {

    public static final Integer[][] matches = {{10, 11, 19, 20, 21, 22, 23},{2, 12},{11},{11},{10, 11, 12, 13},{},{2},{},{},{},{1},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},
            {11, 12, 13, 14, 19, 20, 25, 26, 27, 28},{15, 23},{0, 5, 16},{1},{},{16},{},{},{21},{},{16, 17, 18, 19, 20},{},{},{},{},{},{},{},{},{},{20},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},
            {16, 17, 31, 32},{12},{2, 10, 29},{25, 32},{25},{},{},{1, 13},{18},{},{15, 20, 21, 22, 23, 24},{5},{},{},{},{},{},{},{},{},{11},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},
            {7, 19, 30, 31, 35},{1, 3, 6, 8, 27, 31},{5, 19},{20, 36},{30},{},{35},{},{},{},{5, 19, 20, 21, 33, 34, 35, 36},{},{},{},{27},{},{},{},{},{},{25},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},
            {1, 2, 9, 10, 24, 36, 37},{4, 8, 20, 36, 42},{19, 22},{16, 34},{},{6},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},
            {1, 2, 9, 10, 26, 30, 31, 32, 33, 36, 41},{1, 4, 25, 38},{36},{17, 28, 42, 43},{1, 6, 25, 28, 45},{2},{0},{41},{},{},{},{25},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},
            {13, 14, 41, 46, 47, 48, 49, 50, 53},{7, 8, 26, 49, 53},{17, 26, 27, 34},{5, 26},{},{},{43},{2},{47},{5, 51},{15, 27, 46},{27, 41},{},{},{},{},{},{},{},{},{11},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},
            {7, 14, 18, 22, 25, 28, 29, 43, 46, 58},{2, 14, 15, 16, 36},{11, 18, 36, 39, 45},{57},{},{19},{40},{25, 41},{8},{},{},{},{25, 47},{},{30},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},
            {1, 2, 7, 8, 9, 10, 13, 20, 21, 22, 30, 31, 35, 36, 37, 41, 42, 43, 46, 49, 57},{4, 28, 32, 33, 34, 35, 50},{8, 24, 28, 40},{10, 31},{33},{},{},{23, 39, 43},{},{49},{2, 20, 21},{},{},{},{},{},{},{},{},{},{29},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},
            {8, 9, 10, 14, 17, 18, 19, 30, 31, 32, 35, 36, 41, 46, 47, 48, 66},{12, 29},{7, 8, 12, 40, 51, 56},{0, 19, 52, 59},{11, 18, 60},{},{42},{},{},{9},{9, 10, 18, 19, 27, 28},{},{},{},{},{},{},{},{},{},{6, 34, 35, 36, 60, 61},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{}};

    public static final int[] bmComps = {27,22,21,17,18,17,17,14,14,13,18,15,8,11,7,10,9,6,7,7,7,6,12,9,8,5,7,6,7,5,3,23,9,8,10,5,3,5,3,3,2,4,7,3,6,8,2,6,2,2,4,5,5,3,3,6,4,3,2,7,1,4,1,6,1,3,2,4,2,2,1,8,2,2,5,1,1,1,1,1,1,5,1,2,1,4,1,3,2,2,1,2,1,1,2,1,5,2,2,1,
            31,28,27,22,20,18,20,16,17,19,26,14,21,11,18,10,9,8,9,11,15,25,7,14,6,19,7,6,5,6,4,19,5,6,10,5,9,4,4,5,6,7,12,3,3,7,5,5,3,3,4,10,6,7,5,2,2,2,3,3,3,28,7,6,4,4,5,2,2,2,1,6,1,5,4,3,4,2,4,3,2,1,5,2,2,3,1,2,1,3,4,1,10,5,1,1,3,7,1,2,
            28,33,32,26,24,19,22,24,20,20,37,18,15,10,16,15,8,10,11,8,12,25,12,17,8,10,8,9,11,8,4,9,10,10,6,8,7,11,5,6,6,6,14,7,4,6,6,5,3,4,9,7,2,6,4,6,2,5,7,5,2,2,2,7,12,3,3,4,6,2,6,10,8,2,2,3,2,3,5,2,1,5,1,6,2,1,4,2,3,2,1,1,2,2,6,4,1,7,5,6,
            38,39,33,29,25,25,26,22,21,24,48,30,19,20,30,15,12,10,18,13,19,36,15,8,23,13,6,7,10,8,9,9,20,8,9,8,12,6,5,8,6,7,8,5,15,11,5,4,6,6,7,8,11,14,7,7,6,5,6,7,2,6,3,7,4,3,6,2,5,5,4,4,11,9,3,5,4,3,2,3,4,2,6,6,6,2,2,2,4,3,3,3,2,6,3,4,4,2,3,2,
            37,43,30,28,32,29,26,28,27,30,15,28,17,13,14,18,14,14,16,17,13,9,12,11,12,16,16,12,13,12,7,25,19,8,11,15,7,6,5,6,16,9,21,15,6,9,6,10,11,6,8,8,9,7,5,17,13,6,5,4,9,7,4,16,8,5,6,8,4,5,10,3,4,8,5,6,2,9,4,3,6,5,6,14,10,5,4,8,6,4,5,3,4,4,12,3,4,2,2,2,
            50,44,39,38,40,30,34,33,31,32,25,34,25,19,14,18,16,18,19,19,16,15,16,17,18,19,13,9,10,10,15,18,21,12,16,11,7,9,11,7,9,15,12,19,8,14,9,10,5,7,15,16,8,17,4,7,7,7,6,9,19,17,9,12,8,6,5,6,7,5,6,5,8,10,5,6,4,4,3,4,3,4,6,9,5,8,5,4,7,5,8,11,15,10,10,3,6,5,9,6,
            48,42,41,44,36,39,31,32,36,34,35,55,28,22,19,19,19,14,23,15,38,22,18,24,11,21,16,14,12,10,19,15,15,14,10,12,13,10,14,8,5,10,10,9,14,6,8,7,10,9,7,19,20,7,8,8,6,7,6,9,5,6,9,15,5,6,10,7,5,10,6,5,12,9,12,5,7,4,5,5,7,14,7,7,3,11,11,7,5,6,6,7,3,13,15,4,5,6,4,6,
            58,43,47,44,46,33,35,39,37,34,25,35,44,27,23,20,18,18,16,22,20,21,23,18,15,22,16,16,12,14,14,10,28,17,23,9,14,10,11,12,10,12,15,15,11,11,10,14,7,9,16,12,11,11,7,11,16,17,8,5,10,5,9,11,9,11,12,7,12,5,8,23,6,7,8,8,10,5,4,8,5,8,14,12,11,8,7,5,10,4,4,15,5,7,18,7,3,7,8,5,
            71,50,53,47,45,41,39,39,42,35,37,45,24,24,19,31,19,20,19,17,30,19,19,14,25,21,19,21,14,12,20,24,20,21,12,20,18,15,12,10,12,13,20,13,13,17,9,12,11,8,8,10,16,7,19,8,7,12,7,7,12,12,24,10,17,9,7,7,7,8,15,5,7,7,10,6,5,5,10,5,6,16,9,11,7,6,6,5,7,4,4,7,7,6,5,5,4,7,4,4,
            72,42,51,52,50,43,37,44,44,40,48,31,31,30,27,28,25,20,22,20,58,35,24,28,22,19,21,16,18,14,17,25,18,19,20,20,16,13,12,20,16,13,33,22,23,15,7,9,8,10,6,15,14,15,12,10,27,11,8,9,6,12,13,16,17,11,7,7,6,9,7,10,12,11,7,9,11,10,11,6,3,21,8,8,7,9,10,10,5,7,8,6,10,9,12,7,6,8,7,5};

    public static final int[] kmpComps = {31,34,27,29,27,29,29,26,29,27,37,31,31,27,29,28,30,28,26,28,36,33,29,30,32,29,27,27,28,27,34,33,33,30,30,28,29,26,27,27,33,35,33,31,28,32,29,26,26,28,29,31,34,32,29,29,28,28,28,28,31,30,32,33,30,28,29,29,30,29,32,35,30,34,30,31,29,26,29,27,33,37,28,31,29,27,27,29,29,30,28,37,31,29,31,34,29,32,30,25,
            36,38,37,36,34,32,31,32,33,35,43,39,38,36,38,32,32,32,31,33,45,36,41,37,36,36,33,35,35,33,40,43,37,40,32,34,36,35,31,34,40,38,37,33,35,32,33,32,32,34,40,40,37,37,35,36,34,37,32,30,39,46,41,38,34,35,35,34,35,32,39,39,37,37,38,37,34,35,31,33,35,43,41,35,36,33,31,35,35,36,34,42,35,35,37,32,36,34,33,31,
            41,45,40,40,46,38,40,38,38,38,46,44,42,41,42,40,36,40,35,38,51,53,44,42,42,41,38,39,40,36,50,45,46,45,40,41,36,40,35,38,53,47,42,45,41,41,39,39,38,37,47,46,42,46,42,38,37,38,38,40,44,48,45,42,38,42,39,41,42,37,44,46,45,43,41,39,42,38,42,39,44,48,45,42,37,39,41,39,37,41,39,48,38,44,41,40,41,40,37,41,
            49,47,53,47,46,46,46,42,43,46,55,51,51,49,44,46,46,46,44,42,58,52,46,46,46,47,43,46,42,43,54,55,52,48,44,44,45,45,44,43,55,54,51,49,49,44,47,45,43,43,52,51,52,48,44,43,47,42,45,43,48,53,52,47,45,43,44,45,41,42,51,56,49,45,44,44,44,46,44,42,53,58,50,49,44,43,45,49,45,44,49,51,48,51,43,47,46,48,45,43,
            57,54,56,52,50,47,48,51,46,45,66,57,56,56,49,55,49,49,54,48,66,62,53,51,53,57,49,50,48,52,57,57,55,57,54,47,55,52,50,50,73,65,58,54,54,48,47,51,52,47,64,59,50,54,53,54,51,50,50,47,63,59,62,52,56,51,47,50,48,50,63,62,59,58,51,46,48,52,50,52,62,60,52,53,53,52,52,49,51,48,56,65,59,55,52,55,52,49,51,51,
            63,66,65,57,54,57,54,52,57,54,66,68,65,55,53,58,52,53,55,55,71,65,63,64,56,57,54,54,55,54,68,62,63,61,62,56,55,56,54,53,75,69,65,55,58,59,59,52,58,53,72,70,63,64,62,58,55,53,61,54,69,69,62,67,64,63,53,56,53,53,72,69,70,63,59,53,54,54,54,56,67,63,67,57,58,57,57,57,56,52,63,69,61,61,59,58,56,60,53,55,
            67,72,67,64,63,65,62,61,62,59,75,76,73,65,63,67,60,57,58,61,84,74,70,66,64,60,59,59,62,61,81,68,72,67,61,62,61,61,58,62,76,64,66,65,70,61,64,60,59,59,71,75,72,73,65,60,61,66,62,61,74,74,72,65,61,62,59,61,60,60,76,73,71,71,66,64,61,60,61,59,71,69,70,66,68,65,64,62,59,61,70,77,74,69,67,66,66,62,59,62,
            75,71,74,65,66,68,66,64,66,64,87,80,73,72,65,72,66,68,63,68,92,73,71,75,68,67,62,71,64,63,86,83,75,69,72,65,65,67,65,64,85,83,72,75,66,71,71,64,67,63,86,79,72,70,67,66,69,64,70,68,82,79,77,73,64,66,69,67,66,70,87,82,76,74,65,70,66,70,67,69,80,82,74,71,69,69,73,65,69,65,87,80,81,70,69,66,67,68,66,66,
            80,80,77,79,72,77,71,68,70,71,93,81,78,81,72,71,74,75,70,69,100,86,74,82,81,71,70,71,73,70,92,84,88,79,70,73,74,68,68,73,95,82,89,82,80,77,75,68,73,70,90,85,81,80,76,72,75,71,72,69,89,87,80,77,78,79,73,70,70,73,93,85,85,80,74,70,74,74,69,76,86,93,92,81,74,72,76,73,71,71,90,93,79,74,71,72,77,74,78,69,
            88,86,87,78,75,80,77,79,81,73,98,85,83,84,82,77,76,77,80,80,104,90,85,82,82,77,76,72,82,77,102,84,87,84,83,76,76,76,79,79,103,91,93,85,79,75,79,75,76,81,94,89,93,82,80,82,79,78,77,78,99,94,85,82,79,81,77,79,83,74,97,87,85,89,86,78,81,76,79,73,100,100,90,84,85,83,78,78,81,79,93,92,93,86,81,80,76,78,78,75};

    public static final int[] rkComps = {14,4,2,2,8,0,2,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            20,4,6,2,0,2,0,0,2,0,20,0,0,0,0,0,0,0,0,0,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            8,2,6,4,2,0,0,4,2,0,24,4,0,0,0,0,0,0,0,0,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            10,12,4,4,2,0,2,0,0,0,32,0,0,0,4,0,0,0,0,0,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            14,10,4,4,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            22,8,2,8,10,2,2,2,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            18,10,8,4,0,0,2,2,2,4,12,8,0,0,0,0,0,0,0,0,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            20,10,10,2,0,2,2,4,2,0,0,0,8,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            42,14,8,4,2,0,0,6,0,2,12,0,0,0,0,0,0,0,0,0,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            34,4,12,8,6,0,2,0,0,2,24,0,0,0,0,0,0,0,0,0,36,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

    public static final int[][] failures = {{0, 1},{0, 0},{0, 0},{0, 1},{0, 1},{0, 0},{0, 0},{0, 1},{0, 0},{0, 0},
            {0, 1, 2, 3},{0, 1, 0, 0},{0, 0, 0, 0},{0, 0, 0, 0},{0, 0, 0, 0},{0, 0, 0, 1},{0, 0, 0, 0},{0, 0, 1, 0},{0, 0, 0, 0},{0, 0, 0, 0},
            {0, 1, 2, 3, 4, 5},{0, 1, 0, 1, 0, 0},{0, 0, 1, 0, 0, 0},{0, 0, 0, 0, 0, 0},{0, 1, 0, 0, 1, 0},{0, 0, 1, 2, 0, 0},{0, 0, 0, 0, 0, 1},{0, 0, 1, 2, 0, 0},{0, 0, 0, 1, 1, 0},{0, 0, 0, 0, 0, 0},
            {0, 1, 2, 3, 4, 5, 6, 7},{0, 0, 0, 1, 1, 1, 1, 2},{0, 0, 1, 2, 0, 1, 2, 0},{0, 1, 0, 0, 0, 0, 0, 0},{0, 0, 0, 0, 0, 0, 1, 0},{0, 0, 0, 0, 1, 0, 0, 1},{0, 0, 0, 0, 0, 0, 0, 0},{0, 0, 0, 0, 1, 0, 0, 0},{0, 0, 0, 0, 0, 0, 0, 0},{0, 0, 0, 0, 1, 1, 0, 0},
            {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},{0, 1, 0, 0, 0, 0, 1, 0, 1, 2},{0, 0, 0, 1, 2, 0, 1, 1, 0, 1},{0, 0, 0, 0, 0, 1, 0, 0, 0, 0},{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},{0, 1, 2, 0, 1, 2, 0, 1, 0, 1},{0, 0, 0, 0, 0, 0, 0, 1, 1, 0},{0, 0, 0, 0, 0, 0, 0, 0, 0, 1},{0, 1, 0, 0, 0, 0, 0, 0, 0, 0},{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11},{0, 1, 2, 3, 0, 0, 0, 0, 0, 1, 0, 1},{0, 0, 0, 1, 1, 0, 1, 2, 1, 1, 0, 1},{0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0},{0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 1},{0, 1, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0},{0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0},{0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0},{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},{0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13},{0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 2, 3, 0, 0},{0, 1, 0, 0, 0, 0, 1, 0, 1, 2, 2, 2, 3, 4},{0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 2, 0},{0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},{0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},{0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0},{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15},{0, 1, 2, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0},{0, 0, 1, 2, 0, 1, 0, 0, 1, 2, 0, 0, 0, 0, 0, 0},{0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 2, 0, 1, 1, 1, 1},{0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 2, 0},{0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1},{0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1},{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0},{0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0},{0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 2, 0, 0, 0, 0, 0},
            {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17},{0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 0, 0, 1, 2, 3, 4, 2, 3},{0, 0, 1, 2, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 2, 0, 0, 0, 0},{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},{0, 0, 0, 1, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},{0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0},{0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},{0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0},
            {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19},{0, 1, 2, 3, 0, 0, 1, 2, 3, 4, 5, 1, 0, 1, 2, 0, 1, 2, 3, 0},{0, 0, 1, 1, 2, 0, 0, 0, 0, 1, 2, 0, 0, 0, 1, 2, 3, 4, 1, 1},{0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 2},{0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0},{0, 1, 2, 0, 1, 0, 0, 0, 1, 2, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0},{0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0},{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

    public static final int[] failureComps = {1,1,1,1,1,1,1,1,1,1,3,4,3,3,3,3,3,4,3,3,5,7,6,5,7,6,5,6,7,5,7,10,9,8,8,8,7,8,7,9,9,11,12,10,9,14,11,9,10,9,11,15,16,13,14,14,14,13,11,12,13,17,17,18,15,14,15,14,15,14,15,21,18,23,18,19,17,16,19,17,17,25,19,20,18,18,19,20,19,20,19,29,25,22,23,28,22,22,24,19};

    public static final String[] lasts = {"{ [a,1] }","{ [a,1] [b,0] }","{ [a,1] [b,0] }","{ [c,1] }","{ [c,1] }","{ [e,1] [f,0] }","{ [b,1] [e,0] }","{ [f,1] }","{ [b,0] [h,1] }","{ [g,1] [j,0] }",
            "{ [a,3] }","{ [a,3] [b,1] }","{ [a,0] [b,3] [c,1] }","{ [a,3] [b,2] [c,0] }","{ [a,0] [b,3] [c,2] [e,1] }","{ [c,1] [d,3] [e,2] }","{ [c,1] [d,3] [f,0] [g,2] }","{ [e,3] [f,2] [g,1] }","{ [f,0] [g,3] [i,2] }","{ [e,3] [f,0] [g,2] }",
            "{ [a,5] }","{ [a,5] [b,3] }","{ [a,4] [b,2] [c,5] }","{ [a,4] [b,1] [c,0] [d,5] }","{ [a,4] [b,2] [c,5] [e,3] }","{ [a,5] [b,4] [f,2] }","{ [b,1] [c,5] [d,4] [e,2] [g,3] }","{ [d,3] [e,4] [f,2] [g,5] }","{ [a,5] [d,4] [g,2] [h,1] }","{ [b,5] [d,0] [e,1] [j,4] }",
            "{ [a,7] }","{ [a,7] [b,6] }","{ [b,7] [c,5] }","{ [a,3] [b,6] [c,1] [d,7] }","{ [b,6] [c,5] [d,4] [e,7] }","{ [b,6] [c,7] [d,5] [e,2] [f,1] }","{ [a,3] [c,0] [e,5] [f,7] [g,1] }","{ [b,7] [d,6] [e,2] [f,4] [g,1] }","{ [b,3] [e,5] [f,7] [g,0] [h,6] }","{ [b,7] [c,2] [e,5] [g,6] [h,1] }",
            "{ [a,9] }","{ [a,7] [b,9] }","{ [a,4] [b,8] [c,9] }","{ [a,9] [b,1] [c,5] [d,4] }","{ [a,9] [c,8] [d,6] [e,0] }","{ [c,8] [d,9] [f,6] }","{ [a,8] [c,6] [d,5] [e,9] [f,1] }","{ [a,3] [b,6] [d,7] [e,8] [f,9] }","{ [b,6] [d,9] [f,1] [g,4] [h,8] }","{ [a,3] [b,2] [d,6] [e,0] [g,4] [h,9] }",
            "{ [a,11] }","{ [a,10] [b,11] }","{ [a,10] [b,7] [c,11] }","{ [a,11] [b,10] [c,5] [d,6] }","{ [a,9] [b,1] [d,7] [e,11] }","{ [a,10] [b,3] [c,6] [e,11] [f,8] }","{ [a,11] [b,7] [c,2] [e,6] [f,9] [g,10] }","{ [a,9] [b,10] [d,5] [e,1] [f,7] [g,11] [h,4] }","{ [a,0] [b,10] [c,7] [e,6] [f,8] [h,5] [i,11] }","{ [a,11] [c,8] [d,3] [f,7] [h,9] [i,10] }",
            "{ [a,13] }","{ [a,13] [b,10] }","{ [a,13] [c,11] }","{ [a,12] [b,13] [c,11] [d,9] }","{ [a,4] [b,11] [c,13] [d,10] [e,8] }","{ [a,9] [b,2] [c,10] [d,13] [e,7] [f,6] }","{ [a,12] [b,13] [c,6] [d,7] [e,2] [f,8] [g,10] }","{ [a,13] [c,9] [e,4] [f,10] [g,12] [h,11] }","{ [a,9] [b,1] [c,4] [d,8] [e,12] [f,13] [g,10] [h,6] [i,11] }","{ [a,3] [b,6] [c,10] [d,7] [e,13] [f,2] [g,12] [h,9] [j,11] }",
            "{ [a,15] }","{ [a,15] [b,14] }","{ [a,8] [b,14] [c,15] }","{ [a,3] [b,10] [c,15] [d,11] }","{ [a,12] [b,3] [c,8] [d,13] [e,15] }","{ [a,15] [d,14] [e,10] [f,13] }","{ [a,6] [b,12] [d,1] [e,13] [f,15] [g,14] }","{ [a,8] [b,10] [c,9] [e,14] [f,13] [g,7] [h,15] }","{ [a,4] [b,11] [c,1] [e,5] [f,7] [g,12] [h,9] [i,15] }","{ [a,15] [b,11] [d,3] [e,12] [f,4] [g,9] [h,14] [i,10] [j,5] }",
            "{ [a,17] }","{ [a,16] [b,17] }","{ [a,16] [b,17] [c,11] }","{ [a,16] [b,13] [c,12] [d,17] }","{ [a,16] [b,9] [c,14] [d,17] [e,13] }","{ [a,17] [b,14] [c,15] [d,16] [e,8] [f,3] }","{ [a,12] [b,16] [c,17] [d,6] [f,13] [g,10] }","{ [a,15] [b,11] [c,17] [d,13] [e,1] [f,14] [g,7] [h,12] }","{ [a,5] [b,16] [c,6] [d,15] [e,13] [f,17] [h,12] }","{ [a,14] [b,10] [c,6] [d,17] [e,11] [f,1] [g,16] [h,5] [i,12] [j,8] }",
            "{ [a,19] }","{ [a,19] [b,18] }","{ [a,19] [b,15] [c,12] }","{ [a,19] [b,10] [c,18] [d,15] }","{ [a,19] [b,17] [c,14] [d,10] [e,18] }","{ [a,10] [c,15] [d,19] [e,6] [f,18] }","{ [a,18] [b,19] [c,12] [d,14] [e,9] [f,17] [g,13] }","{ [b,17] [c,19] [d,16] [e,18] [f,13] [g,5] [h,9] }","{ [a,9] [b,7] [c,15] [d,18] [e,13] [f,14] [g,5] [h,19] [i,6] }","{ [a,19] [b,12] [c,5] [d,15] [e,6] [g,18] [h,0] [i,16] [j,17] }"};

    @Test
    public void bm() {
        for (int tL = 1; tL <= 10; tL++) {
            for (int pL = 1; pL <= 10; pL ++) {
                for (int dS = 1; dS <= 10; dS++) {
                    int seed = (tL-1)*100+(pL-1)*10+(dS-1);
                    CharacterComparator comp = new CharacterComparator();
                    String pattern = generateRandomText(seed, pL*2, dS);
                    String text = generateRandomText(seed, tL*5+20, dS+1);
                    List<Integer> mL = PatternMatching.boyerMoore(pattern, text, comp);
                    Integer[] m = mL.toArray(new Integer[mL.size()]);
                    if (!compareIntArrays(matches[seed], m)) {
                        fail(
                                "Boyer-Moore did not return the correct matches.\n"
                                        + "Seed: " + seed + "\n"
                                        + "Text: " + text + "\n"
                                        + "Pattern: " + pattern + "\n"
                                        + "Correct matches: " + getIntArrayString(matches[seed]) +"\n"
                                        + "Your matches: "+ getIntArrayString(m) + "\n"
                                        + "Comparisons: " + comp.getComparisonCount() + "\n"
                                        + "Target Comparisons: " + bmComps[seed]
                        );
                    }
                    if (comp.getComparisonCount() != bmComps[seed]) {
                        fail(
                                "Boyer-Moore did not have the correct number of comparisons.\n"
                                        + "Seed: " + seed + "\n"
                                        + "Text: " + text + "\n"
                                        + "Pattern: " + pattern + "\n"
                                        + "Correct matches: " + getIntArrayString(matches[seed]) +"\n"
                                        + "Your matches: "+ getIntArrayString(m) + "\n"
                                        + "Comparisons: " + comp.getComparisonCount() + "\n"
                                        + "Target Comparisons: " + bmComps[seed]
                        );
                    }
                }
            }
        }
    }

    @Test
    public void kmp() {
        for (int tL = 1; tL <= 10; tL++) {
            for (int pL = 1; pL <= 10; pL ++) {
                for (int dS = 1; dS <= 10; dS++) {
                    int seed = (tL-1)*100+(pL-1)*10+(dS-1);
                    CharacterComparator comp = new CharacterComparator();
                    String pattern = generateRandomText(seed, pL*2, dS);
                    String text = generateRandomText(seed, tL*5+20, dS+1);
                    List<Integer> mL = PatternMatching.kmp(pattern, text, comp);
                    Integer[] m = mL.toArray(new Integer[mL.size()]);
                    if (!compareIntArrays(matches[seed], m)) {
                        fail(
                                "KMP did not return the correct matches.\n"
                                        + "Seed: " + seed + "\n"
                                        + "Text: " + text + "\n"
                                        + "Pattern: " + pattern + "\n"
                                        + "Correct matches: " + getIntArrayString(matches[seed]) + "\n"
                                        + "Your matches: "+getIntArrayString(m) + "\n"
                                        + "Comparisons: " + comp.getComparisonCount() + "\n"
                                        + "Target Comparisons: " + kmpComps[seed]
                        );
                    }
                    if (comp.getComparisonCount() != kmpComps[seed]) {
                        fail(
                                "KMP did not have the correct number of comparisons.\n"
                                        + "Seed: " + seed + "\n"
                                        + "Text: " + text + "\n"
                                        + "Pattern: " + pattern + "\n"
                                        + "Correct matches: " + getIntArrayString(matches[seed]) +"\n"
                                        + "Your matches: "+ getIntArrayString(m) + "\n"
                                        + "Comparisons: " + comp.getComparisonCount() + "\n"
                                        + "Target Comparisons: " + kmpComps[seed]
                        );
                    }
                }
            }
        }
    }

    @Test
    public void rk() {
        for (int tL = 1; tL <= 10; tL++) {
            for (int pL = 1; pL <= 10; pL ++) {
                for (int dS = 1; dS <= 10; dS++) {
                    int seed = (tL-1)*100+(pL-1)*10+(dS-1);
                    CharacterComparator comp = new CharacterComparator();
                    String pattern = generateRandomText(seed, pL*2, dS);
                    String text = generateRandomText(seed, tL*5+20, dS+1);
                    List<Integer> mL = PatternMatching.rabinKarp(pattern, text, comp);
                    Integer[] m = mL.toArray(new Integer[mL.size()]);
                    if (!compareIntArrays(matches[seed], m)) {
                        fail(
                                "Rabin-Karp did not return the correct matches.\n"
                                        + "Seed: " + seed + "\n"
                                        + "Text: " + text + "\n"
                                        + "Pattern: " + pattern + "\n"
                                        + "Correct matches: " + getIntArrayString(matches[seed]) + "\n"
                                        + "Your matches: "+getIntArrayString(m) + "\n"
                                        + "Comparisons: " + comp.getComparisonCount() + "\n"
                                        + "Target Comparisons: " + rkComps[seed]
                        );
                    }
                    if (comp.getComparisonCount() != rkComps[seed]) {
                        fail(
                                "Rabin-Karp did not have the correct number of comparisons.\n"
                                        + "Seed: " + seed + "\n"
                                        + "Text: " + text + "\n"
                                        + "Pattern: " + pattern + "\n"
                                        + "Correct matches: " + getIntArrayString(matches[seed]) +"\n"
                                        + "Your matches: "+ getIntArrayString(m) + "\n"
                                        + "Comparisons: " + comp.getComparisonCount() + "\n"
                                        + "Target Comparisons: " + rkComps[seed]
                        );
                    }
                }
            }
        }
    }

    @Test
    public void failureTable() {
        for (int pL = 1; pL <= 10; pL ++) {
            for (int dS = 1; dS <= 10; dS++) {
                int seed = (pL-1)*10+(dS-1);
                CharacterComparator comp = new CharacterComparator();
                String pattern = generateRandomText(seed, pL*2, dS);
                int[] failure = PatternMatching.buildFailureTable(pattern, comp);
                if (!compareIntArrays(failure, failures[seed])) {
                    fail(
                            "Your failure table was not correct.\n"
                                    + "Seed: " + seed + "\n"
                                    + "Pattern: " + pattern + "\n"
                                    + "Correct table:\n"
                                    + pattern + "\n" + getFailureTableString(failures[seed]) + "\n"
                                    + "Your table: \n"
                                    + pattern + "\n" + getFailureTableString(failure) + "\n"
                                    + "Comparisons: " + comp.getComparisonCount() + "\n"
                                    + "Target Comparisons: " + failureComps[seed]
                    );
                }
                if (comp.getComparisonCount() != failureComps[seed]) {
                    fail(
                            "Your failure table was generated with the wrong number of comparisons.\n"
                                    + "Seed: " + seed + "\n"
                                    + "Pattern: " + pattern + "\n"
                                    + "Correct table:\n"
                                    + pattern + "\n" + getFailureTableString(failures[seed]) + "\n"
                                    + "Your table: \n"
                                    + pattern + "\n" + getFailureTableString(failure) + "\n"
                                    + "Comparisons: " + comp.getComparisonCount() + "\n"
                                    + "Target Comparisons: " + failureComps[seed]
                    );
                }
            }
        }
    }

    @Test
    public void lastTable() {
        for (int pL = 1; pL <= 10; pL ++) {
            for (int dS = 1; dS <= 10; dS++) {
                int seed = (pL-1)*10+(dS-1);
                String pattern = generateRandomText(seed, pL*2, dS);
                Map<Character,Integer> ls = PatternMatching.buildLastTable(pattern);
                String str = getLastTableString(ls);
                if (!str.equals(lasts[seed])) {
                    fail(
                            "Your last occurrence table was not correct.\n"
                                    + "Seed: " + seed + "\n"
                                    + "Pattern: " + pattern + "\n"
                                    + "Correct table: \n" + lasts[seed] +"\n"
                                    + "Your table: \n" + str
                    );
                }
            }
        }
    }

    @Test
    public void bmOddities() {
        try {
            PatternMatching.boyerMoore(null, "sample text", new CharacterComparator());
            fail("Failed to throw an exception when pattern was null");
        } catch (IllegalArgumentException e) {}
        try {
            PatternMatching.boyerMoore("", "sample text", new CharacterComparator());
            fail("Failed to throw an exception when pattern was empty");
        } catch (IllegalArgumentException e) {}
        try {
            PatternMatching.boyerMoore("pattern", null, new CharacterComparator());
            fail("Failed to throw an exception when text was null");
        } catch (IllegalArgumentException e) {}
        try {
            PatternMatching.boyerMoore("pattern", "sample text", null);
            fail("Failed to throw an exception when comparator was null");
        } catch (IllegalArgumentException e) {}
        CharacterComparator c = new CharacterComparator();
        PatternMatching.boyerMoore("pattern that is really long", "short text", c);
        assertEquals("When the pattern was longer than the text, some comparisons were made despite matches being impossible.", 0, c.getComparisonCount());
        c = new CharacterComparator();
        List<Integer> list = PatternMatching.boyerMoore("a","baaabaababba", c);
        Integer[] arr = list.toArray(new Integer[list.size()]);
        assertArrayEquals("A single-character pattern returned the wrong matches.", new Integer[]{1,2,3,5,6,8,11}, arr);
        assertEquals("A single-character pattern resulted in a wrong number of comparisons.", 12, c.getComparisonCount());
    }

    @Test
    public void kmpOddities() {
        try {
            PatternMatching.kmp(null, "sample text", new CharacterComparator());
            fail("Failed to throw an exception when pattern was null");
        } catch (IllegalArgumentException e) {}
        try {
            PatternMatching.kmp("", "sample text", new CharacterComparator());
            fail("Failed to throw an exception when pattern was empty");
        } catch (IllegalArgumentException e) {}
        try {
            PatternMatching.kmp("pattern", null, new CharacterComparator());
            fail("Failed to throw an exception when text was null");
        } catch (IllegalArgumentException e) {}
        try {
            PatternMatching.kmp("pattern", "sample text", null);
            fail("Failed to throw an exception when comparator was null");
        } catch (IllegalArgumentException e) {}
        CharacterComparator c = new CharacterComparator();
        PatternMatching.kmp("pattern that is really long", "short text", c);
        assertEquals("When the pattern was longer than the text, some comparisons were made despite matches being impossible.", 0, c.getComparisonCount());
        c = new CharacterComparator();
        List<Integer> list = PatternMatching.kmp("a","baaabaababba", c);
        Integer[] arr = list.toArray(new Integer[list.size()]);
        assertArrayEquals("A single-character pattern returned the wrong matches.", new Integer[]{1,2,3,5,6,8,11}, arr);
        assertEquals("A single-character pattern resulted in a wrong number of comparisons.", 12, c.getComparisonCount());
    }

    @Test
    public void failureOddities() {
        try {
            PatternMatching.buildFailureTable(null, new CharacterComparator());
            fail("Failed to throw an exception when pattern was null.");
        } catch (IllegalArgumentException e) {}
        try {
            PatternMatching.buildFailureTable("pattern", null);
            fail("Failed to throw an exception when comparator was null.");
        } catch (IllegalArgumentException e) {}

        CharacterComparator comp = new CharacterComparator();
        int[] fail = PatternMatching.buildFailureTable("AAAAAAAAAA", comp);
        assertArrayEquals("A repeated-character pattern resulted in the wrong failure table.", new int[]{0,1,2,3,4,5,6,7,8,9}, fail);
        assertEquals("A repeated-character pattern used too many comparisons to generate the failure table.", 9, comp.getComparisonCount());

        comp = new CharacterComparator();
        fail = PatternMatching.buildFailureTable("aabaabaaab", comp);
        assertArrayEquals("A pattern resulted in the wrong failure table.", new int[]{0,1,0,1,2,3,4,5,2,3}, fail);
        assertEquals("A pattern used too many comparisons to generate the failure table.", 12, comp.getComparisonCount());

        comp = new CharacterComparator();
        fail = PatternMatching.buildFailureTable("babaababab", comp);
        assertArrayEquals("A pattern resulted in the wrong failure table.", new int[]{0,0,1,2,0,1,2,3,4,3}, fail);
        assertEquals("A pattern used too many comparisons to generate the failure table.", 11, comp.getComparisonCount());
    }

    @Test
    public void lastOddities() {
        try {
            PatternMatching.buildLastTable(null);
            fail("Failed to throw an exception when pattern was null.");
        } catch (IllegalArgumentException e) {}
        Map<Character, Integer> map = PatternMatching.buildLastTable("AAAAAAAAAA");
        assertEquals("The last occurrence table generated by a repeated-character pattern contains too many elements.", 1, map.size());
        assertTrue("The last occurrence table generated by a repeated-character pattern does not contain the correct character.", map.containsKey('A'));
        assertEquals("The last occurrence table generated by a repeated-character pattern does not have the correct index mapping for A.", 9, (int)map.get('A'));
    }

    @Test
    public void rkOddities() {
        try {
            PatternMatching.rabinKarp(null, "sample text", new CharacterComparator());
            fail("Failed to throw an exception when pattern was null");
        } catch (IllegalArgumentException e) {}
        try {
            PatternMatching.rabinKarp("", "sample text", new CharacterComparator());
            fail("Failed to throw an exception when pattern was empty");
        } catch (IllegalArgumentException e) {}
        try {
            PatternMatching.rabinKarp("pattern", null, new CharacterComparator());
            fail("Failed to throw an exception when text was null");
        } catch (IllegalArgumentException e) {}
        try {
            PatternMatching.rabinKarp("pattern", "sample text", null);
            fail("Failed to throw an exception when comparator was null");
        } catch (IllegalArgumentException e) {}
        CharacterComparator c = new CharacterComparator();
        PatternMatching.rabinKarp("pattern that is really long", "short text", c);
        assertEquals("When the pattern was longer than the text, some comparisons were made despite matches being impossible.", 0, c.getComparisonCount());
        c = new CharacterComparator();
        List<Integer> list = PatternMatching.rabinKarp("a","baaabaababba", c);
        Integer[] arr = list.toArray(new Integer[list.size()]);
        assertArrayEquals("A single-character pattern returned the wrong matches.", new Integer[]{1,2,3,5,6,8,11}, arr);
        assertEquals("A single-character pattern resulted in a wrong number of comparisons.", 7, c.getComparisonCount());
    }

    public static String generateRandomText(int seed, int length, int dictSize) {
        Random rand = new Random(seed);
        String str = "";
        for (int i = 0; i < length; i++) {
            str+=(char)('a'+rand.nextInt(dictSize));
        }
        return str;
    }

    public static String getIntArrayString(Integer[] arr) {
        if (arr.length == 0) return "{}";
        String s = "{";
        for (int n : arr) {
            s+=n+", ";
        }
        s=s.substring(0, s.length()-2)+"}";
        return s;
    }

    public static String getIntArrayString(int[] arr) {
        if (arr.length == 0) return "{}";
        String s = "{";
        for (int n : arr) {
            s+=n+", ";
        }
        s=s.substring(0, s.length()-2)+"}";
        return s;
    }

    public static boolean compareIntArrays(Integer[] a, Integer[] b) {
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) {
            if (!a[i].equals(b[i])) return false;
        }
        return true;
    }

    public static boolean compareIntArrays(int[] a, int[] b) {
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i]!=b[i]) return false;
        }
        return true;
    }

    public static String getFailureTableString(int[] failure) {
        String s = "";
        for(int i:failure)s+="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".charAt(i);
        return s;
    }

    public static String getLastTableString(Map<Character,Integer> map) {
        List<Character> chars = new ArrayList<>(map.keySet());
        String s = "{ ";
        for(char c : chars) {
            s+="["+c+","+map.get(c)+"] ";
        }
        s+="}";
        return s;
    }

}