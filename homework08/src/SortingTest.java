import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class SortingTest {

    public static final boolean FAIL_IF_TOO_MANY_COMPARISONS = true;

    public static final int[] COCKTAIL_SORTED = {9,18,27,36,45,54,63,72,81,90};
    public static final int[] COCKTAIL_RANDOM = {40,88,282,497,644,965,1235,1814,2558,3001};
    public static final int[] COCKTAIL_NEARLY = {18,32,52,81,125,145,152,194,252,252};
    public static final int[] COCKTAIL_REVERSED = {45,171,378,666,1035,1485,2016,2628,3321,4095};
    public static final int[] COCKTAIL_SORTED_DUPED = {9,18,27,36,45,54,63,72,81,90};
    public static final int[] COCKTAIL_RANDOM_DUPED = {28,77,268,484,612,949,1188,1806,2512,2981};
    public static final int[] COCKTAIL_NEARLY_DUPED = {18,18,48,59,125,97,142,184,196,178};
    public static final int[] COCKTAIL_REVERSED_DUPED = {39,150,378,665,1029,1485,2016,2628,3321,4080};
    public static final int[] INSERTION_SORTED = {9,18,27,36,45,54,63,72,81,90};
    public static final int[] INSERTION_RANDOM = {38,73,230,385,493,738,995,1417,1961,2359};
    public static final int[] INSERTION_NEARLY = {14,22,33,48,64,72,88,98,114,120};
    public static final int[] INSERTION_REVERSED = {45,171,378,666,1035,1485,2016,2628,3321,4095};
    public static final int[] INSERTION_SORTED_DUPED = {9,18,27,36,45,54,63,72,81,90};
    public static final int[] INSERTION_RANDOM_DUPED = {24,50,216,351,456,709,915,1383,1890,2267};
    public static final int[] INSERTION_NEARLY_DUPED = {12,18,30,42,57,62,68,85,92,102};
    public static final int[] INSERTION_REVERSED_DUPED = {30,133,366,612,1002,1423,1934,2590,3221,3952};
    public static final int[] SELECTION = {45,171,378,666,1035,1485,2016,2628,3321,4095};
    public static final int[] MERGE_SORTED = {15,37,64,90,119,151,192,214,245,279};
    public static final int[] MERGE_RANDOM = {24,55,103,146,197,249,310,370,424,474};
    public static final int[] MERGE_NEARLY = {19,39,69,96,130,163,206,229,269,298};
    public static final int[] MERGE_REVERSED = {19,45,72,105,139,170,192,242,283,321};
    public static final int[] MERGE_SORTED_DUPED = {15,37,64,90,119,151,192,214,245,279};
    public static final int[] MERGE_RANDOM_DUPED = {21,52,101,143,196,247,308,367,422,476};
    public static final int[] MERGE_NEARLY_DUPED = {17,37,68,92,128,157,194,224,253,287};
    public static final int[] MERGE_REVERSED_DUPED = {19,49,80,115,154,177,215,257,309,341};
    public static final int[] KTH_SORTED = {191,850,1866,3611,5770,7873,10232,13797,18091,22493};
    public static final int[] KTH_RANDOM = {194,843,1864,3655,5221,8294,10785,14624,19001,23083};
    public static final int[] KTH_NEARLY = {210,870,1986,3572,5380,8318,11103,14718,18463,22881};
    public static final int[] KTH_REVERSED = {181,748,1848,3605,5043,8112,10579,14522,18938,22592};
    public static final int[] KTH_SORTED_DUPED = {178,1122,1725,4072,5722,8638,11608,15113,19186,23472};
    public static final int[] KTH_RANDOM_DUPED = {190,855,1740,4179,5541,8444,11333,14531,18891,24840};
    public static final int[] KTH_NEARLY_DUPED = {176,1122,2147,4636,6135,8023,11224,15015,18710,25754};
    public static final int[] KTH_REVERSED_DUPED = {195,807,1805,4191,5621,8069,10462,14258,19826,24009};

    @Test
    public void crazyRadix() {
        int[] arr = new int[]{0,1,1,-1,-1,4,-4,16,64,-256,1024,-4096,Integer.MAX_VALUE, Integer.MIN_VALUE,
                Integer.MAX_VALUE-1,Integer.MAX_VALUE-10,Integer.MIN_VALUE+1,Integer.MIN_VALUE+10,
                999999999,1000000000,1000000001,1000000000,999999999,-999999999,-1000000000,-1000000001,-1000000000,-999999999};
        Sorting.lsdRadixSort(arr);
        if(!checkSorted(arr))failAndDumpArray(arr, "Your radixSort could not sort an array with Integer.MAX and MIN.");
    }

    @Test
    public void cocktailNearlyExample() {
        NumberedNumber[] arr = new NumberedNumber[10];
        int[] values = {0,1,3,2,5,4,7,8,9,6};
        for(int i = 0; i < 10; i++) {
            arr[i] = new NumberedNumber();
            arr[i].number = values[i];
            arr[i].numbering = i;
        }
        NumComp comp = new NumComp();
        System.out.println("Original Array: "+getNNArrayString(arr));
        Sorting.cocktailSort(arr, comp);
        System.out.println("Sorted Array: "+getNNArrayString(arr));
        System.out.println("Comparisons: "+comp.compCount);

        arr = new NumberedNumber[10];
        values = new int[]{0,1,3,2,5,4,8,7,9,6};
        for(int i = 0; i < 10; i++) {
            arr[i] = new NumberedNumber();
            arr[i].number = values[i];
            arr[i].numbering = i;
        }
        comp = new NumComp();
        System.out.println("Original Array: "+getNNArrayString(arr));
        Sorting.cocktailSort(arr, comp);
        System.out.println("Sorted Array: "+getNNArrayString(arr));
        System.out.println("Comparisons: "+comp.compCount);

        arr = new NumberedNumber[10];
        values = new int[]{0,1,3,2,5,4,8,9,7,6};
        for(int i = 0; i < 10; i++) {
            arr[i] = new NumberedNumber();
            arr[i].number = values[i];
            arr[i].numbering = i;
        }
        comp = new NumComp();
        System.out.println("Original Array: "+getNNArrayString(arr));
        Sorting.cocktailSort(arr, comp);
        System.out.println("Sorted Array: "+getNNArrayString(arr));
        System.out.println("Comparisons: "+comp.compCount);
    }

    @Test
    public void cocktailSorted() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getSortedDupefreeNumbers(i*9+10);
            Sorting.cocktailSort(arr, comp);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = COCKTAIL_SORTED[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void cocktailRandom() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getRandomDupefreeNumbers(i*9+10, i);
            Sorting.cocktailSort(arr, comp);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = COCKTAIL_RANDOM[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void cocktailNearly() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getNearlySortedDupefreeNumbers(i*9+10, i, i*5+5);
            Sorting.cocktailSort(arr, comp);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = COCKTAIL_NEARLY[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void cocktailReversed() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getReversedDupefreeNumbers(i*9+10);
            Sorting.cocktailSort(arr, comp);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = COCKTAIL_REVERSED[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void cocktailSortedDuped() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getSortedDupedNumbers(i*9+10, i);
            Sorting.cocktailSort(arr, comp);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            if(!checkStable(arr))failAndDumpArray(arr, "On iteration "+i+" the sort was unstable.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = COCKTAIL_SORTED_DUPED[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void cocktailRandomDuped() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getRandomDupedNumbers(i*9+10, i);
            Sorting.cocktailSort(arr, comp);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            if(!checkStable(arr))failAndDumpArray(arr, "On iteration "+i+" the sort was unstable.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = COCKTAIL_RANDOM_DUPED[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void cocktailNearlyDuped() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getNearlySortedDupedNumbers(i*9+10, i, i*5+5);
            Sorting.cocktailSort(arr, comp);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            if(!checkStable(arr))failAndDumpArray(arr, "On iteration "+i+" the sort was unstable.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = COCKTAIL_NEARLY_DUPED[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void cocktailReversedDuped() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getReversedDupedNumbers(i*9+10, i);
            Sorting.cocktailSort(arr, comp);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            if(!checkStable(arr))failAndDumpArray(arr, "On iteration "+i+" the sort was unstable.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = COCKTAIL_REVERSED_DUPED[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void insertionSorted() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getSortedDupefreeNumbers(i*9+10);
            Sorting.insertionSort(arr, comp);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = INSERTION_SORTED[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void insertionRandom() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getRandomDupefreeNumbers(i*9+10, i);
            Sorting.insertionSort(arr, comp);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = INSERTION_RANDOM[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void insertionNearly() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getNearlySortedDupefreeNumbers(i*9+10, i, i*5+5);
            Sorting.insertionSort(arr, comp);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = INSERTION_NEARLY[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void insertionReversed() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getReversedDupefreeNumbers(i*9+10);
            Sorting.insertionSort(arr, comp);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = INSERTION_REVERSED[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void insertionSortedDuped() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getSortedDupedNumbers(i*9+10, i);
            Sorting.insertionSort(arr, comp);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            if(!checkStable(arr))failAndDumpArray(arr, "On iteration "+i+" the sort was unstable.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = INSERTION_SORTED_DUPED[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void insertionRandomDuped() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getRandomDupedNumbers(i*9+10, i);
            Sorting.insertionSort(arr, comp);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            if(!checkStable(arr))failAndDumpArray(arr, "On iteration "+i+" the sort was unstable.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = INSERTION_RANDOM_DUPED[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void insertionNearlyDuped() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getNearlySortedDupedNumbers(i*9+10, i, i*5+5);
            Sorting.insertionSort(arr, comp);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            if(!checkStable(arr))failAndDumpArray(arr, "On iteration "+i+" the sort was unstable.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = INSERTION_NEARLY_DUPED[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void insertionReversedDuped() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getReversedDupedNumbers(i*9+10, i);
            Sorting.insertionSort(arr, comp);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            if(!checkStable(arr))failAndDumpArray(arr, "On iteration "+i+" the sort was unstable.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = INSERTION_REVERSED_DUPED[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void selectionSorted() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getSortedDupefreeNumbers(i*9+10);
            Sorting.selectionSort(arr, comp);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = SELECTION[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void selectionRandom() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getRandomDupefreeNumbers(i*9+10, i);
            Sorting.selectionSort(arr, comp);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = SELECTION[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void selectionNearly() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getNearlySortedDupefreeNumbers(i*9+10, i, i*5+5);
            Sorting.selectionSort(arr, comp);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = SELECTION[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void selectionReversed() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getReversedDupefreeNumbers(i*9+10);
            Sorting.selectionSort(arr, comp);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = SELECTION[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void selectionSortedDuped() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getSortedDupedNumbers(i*9+10, i);
            Sorting.selectionSort(arr, comp);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = SELECTION[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void selectionRandomDuped() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getRandomDupedNumbers(i*9+10, i);
            Sorting.selectionSort(arr, comp);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = SELECTION[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void selectionNearlyDuped() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getNearlySortedDupedNumbers(i*9+10, i, i*5+5);
            Sorting.selectionSort(arr, comp);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = SELECTION[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void selectionReversedDuped() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getReversedDupedNumbers(i*9+10, i);
            Sorting.selectionSort(arr, comp);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = SELECTION[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void mergeSorted() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getSortedDupefreeNumbers(i*9+10);
            Sorting.mergeSort(arr, comp);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = MERGE_SORTED[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void mergeRandom() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getRandomDupefreeNumbers(i*9+10, i);
            Sorting.mergeSort(arr, comp);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = MERGE_RANDOM[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void mergeNearly() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getNearlySortedDupefreeNumbers(i*9+10, i, i*5+5);
            Sorting.mergeSort(arr, comp);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = MERGE_NEARLY[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void mergeReversed() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getReversedDupefreeNumbers(i*9+10);
            Sorting.mergeSort(arr, comp);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = MERGE_REVERSED[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void mergeSortedDuped() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getSortedDupedNumbers(i*9+10, i);
            Sorting.mergeSort(arr, comp);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            if(!checkStable(arr))failAndDumpArray(arr, "On iteration "+i+" the sort was unstable.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = MERGE_SORTED_DUPED[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void mergeRandomDuped() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getRandomDupedNumbers(i*9+10, i);
            Sorting.mergeSort(arr, comp);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            if(!checkStable(arr))failAndDumpArray(arr, "On iteration "+i+" the sort was unstable.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = MERGE_RANDOM_DUPED[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void mergeNearlyDuped() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getNearlySortedDupedNumbers(i*9+10, i, i*5+5);
            Sorting.mergeSort(arr, comp);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            if(!checkStable(arr))failAndDumpArray(arr, "On iteration "+i+" the sort was unstable.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = MERGE_NEARLY_DUPED[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void mergeReversedDuped() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getReversedDupedNumbers(i*9+10, i);
            Sorting.mergeSort(arr, comp);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            if(!checkStable(arr))failAndDumpArray(arr, "On iteration "+i+" the sort was unstable.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = MERGE_REVERSED_DUPED[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void radixSorted() {
        for (int i = 0; i < 10; i++) {
            int[] arr = getSortedDupefreeInts(i*9+10, false);
            Sorting.lsdRadixSort(arr);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            System.out.println("Array sorted. Array:");
            System.out.println(getIntArrayString(arr)+"\n");
        }

        for (int i = 0; i < 10; i++) {
            int[] arr = getSortedDupefreeInts(i*9+10, true);
            Sorting.lsdRadixSort(arr);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted when negative numbers were used.");
            System.out.println("Array sorted. Array:");
            System.out.println(getIntArrayString(arr)+"\n");
        }
    }

    @Test
    public void radixRandom() {
        for (int i = 0; i < 10; i++) {
            int[] arr = getRandomDupefreeInts(i*9+10, i, false);
            Sorting.lsdRadixSort(arr);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            System.out.println("Array sorted. Array:");
            System.out.println(getIntArrayString(arr)+"\n");
        }

        for (int i = 0; i < 10; i++) {
            int[] arr = getRandomDupefreeInts(i*9+10, i, true);
            Sorting.lsdRadixSort(arr);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted when negative numbers were used.");
            System.out.println("Array sorted. Array:");
            System.out.println(getIntArrayString(arr)+"\n");
        }
    }

    @Test
    public void radixNearly() {
        for (int i = 0; i < 10; i++) {
            int[] arr = getNearlySortedDupefreeInts(i*9+10, i, i*5+5, false);
            Sorting.lsdRadixSort((int[])arr);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            System.out.println("Array sorted. Array:");
            System.out.println(getIntArrayString(arr)+"\n");
        }

        for (int i = 0; i < 10; i++) {
            int[] arr = getNearlySortedDupefreeInts(i*9+10, i, i*5+5, true);
            Sorting.lsdRadixSort((int[])arr);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted when negative numbers were used.");
            System.out.println("Array sorted. Array:");
            System.out.println(getIntArrayString(arr)+"\n");
        }
    }

    @Test
    public void radixReversed() {
        for (int i = 0; i < 10; i++) {
            int[] arr = getReversedDupefreeInts(i*9+10, false);
            Sorting.lsdRadixSort(arr);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            System.out.println("Array sorted. Array:");
            System.out.println(getIntArrayString(arr)+"\n");
        }

        for (int i = 0; i < 10; i++) {
            int[] arr = getReversedDupefreeInts(i*9+10, true);
            Sorting.lsdRadixSort(arr);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted when negative numbers were used.");
            System.out.println("Array sorted. Array:");
            System.out.println(getIntArrayString(arr)+"\n");
        }
    }

    @Test
    public void radixSortedDuped() {
        for (int i = 0; i < 10; i++) {
            int[] arr = getSortedDupedInts(i*9+10, i, false);
            Sorting.lsdRadixSort(arr);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            System.out.println("Array sorted. Array:");
            System.out.println(getIntArrayString(arr)+"\n");
        }

        for (int i = 0; i < 10; i++) {
            int[] arr = getSortedDupedInts(i*9+10, i, true);
            Sorting.lsdRadixSort(arr);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted when negative numbers were used.");
            System.out.println("Array sorted. Array:");
            System.out.println(getIntArrayString(arr)+"\n");
        }
    }

    @Test
    public void radixRandomDuped() {
        for (int i = 0; i < 10; i++) {
            int[] arr = getRandomDupedInts(i*9+10, i, false);
            Sorting.lsdRadixSort(arr);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            System.out.println("Array sorted. Array:");
            System.out.println(getIntArrayString(arr)+"\n");
        }

        for (int i = 0; i < 10; i++) {
            int[] arr = getRandomDupedInts(i*9+10, i, true);
            Sorting.lsdRadixSort(arr);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted when negative numbers were used.");
            System.out.println("Array sorted. Array:");
            System.out.println(getIntArrayString(arr)+"\n");
        }
    }

    @Test
    public void radixNearlyDuped() {
        for (int i = 0; i < 10; i++) {
            int[] arr = getNearlySortedDupedInts(i*9+10, i, i*5+5, false);
            Sorting.lsdRadixSort(arr);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            System.out.println("Array sorted. Array:");
            System.out.println(getIntArrayString(arr)+"\n");
        }

        for (int i = 0; i < 10; i++) {
            int[] arr = getNearlySortedDupedInts(i*9+10, i, i*5+5, true);
            Sorting.lsdRadixSort(arr);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted when negative numbers were used.");
            System.out.println("Array sorted. Array:");
            System.out.println(getIntArrayString(arr)+"\n");
        }
    }

    @Test
    public void radixReversedDuped() {
        for (int i = 0; i < 10; i++) {
            int[] arr = getReversedDupedInts(i*9+10, i, false);
            Sorting.lsdRadixSort(arr);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted.");
            System.out.println("Array sorted. Array:");
            System.out.println(getIntArrayString(arr)+"\n");
        }

        for (int i = 0; i < 10; i++) {
            int[] arr = getReversedDupedInts(i*9+10, i, true);
            Sorting.lsdRadixSort(arr);
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted when negative numbers were used.");
            System.out.println("Array sorted. Array:");
            System.out.println(getIntArrayString(arr)+"\n");
        }
    }

    @Test
    public void kthSorted() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getSortedDupefreeNumbers(i*9+10);
            ArrayList<NumberedNumber> selects = new ArrayList<>(arr.length);
            Random rand = new Random(i);
            for (int j = 0; j < arr.length; j++) {
                selects.add(Sorting.kthSelect(j+1, arr, comp, rand));
            }
            if(!checkSorted(selects.toArray(new NumberedNumber[0])))failAndDumpArray(selects.toArray(new NumberedNumber[0]), "On iteration "+i+" calling kthSelect on each index in order did not produce a sorted list.");
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted as a result of calling kthSelect on every index.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = KTH_SORTED[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza."
                            +"\nFor the kthSelect algorithm in particular, please ensure that you are using the algorithm exactly shown in lecture."
                            +"\nSpecifically, ensure that after each recursive call, the provided subarray has undergone a complete quicksort step;"
                            +"\nthat is, the pivot is in the correct position in the subarray, and all elements to the left and right of the pivot are smaller or larger than the pivot.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void kthReversed() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getReversedDupefreeNumbers(i*9+10);
            ArrayList<NumberedNumber> selects = new ArrayList<>(arr.length);
            Random rand = new Random(i);
            for (int j = 0; j < arr.length; j++) {
                selects.add(Sorting.kthSelect(j+1, arr, comp, rand));
            }
            if(!checkSorted(selects.toArray(new NumberedNumber[0])))failAndDumpArray(selects.toArray(new NumberedNumber[0]), "On iteration "+i+" calling kthSelect on each index in order did not produce a sorted list.");
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted as a result of calling kthSelect on every index.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = KTH_REVERSED[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza."
                            +"\nFor the kthSelect algorithm in particular, please ensure that you are using the algorithm exactly shown in lecture."
                            +"\nSpecifically, ensure that after each recursive call, the provided subarray has undergone a complete quicksort step;"
                            +"\nthat is, the pivot is in the correct position in the subarray, and all elements to the left and right of the pivot are smaller or larger than the pivot.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void kthRandom() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getRandomDupefreeNumbers(i*9+10, i);
            ArrayList<NumberedNumber> selects = new ArrayList<>(arr.length);
            Random rand = new Random(i);
            for (int j = 0; j < arr.length; j++) {
                selects.add(Sorting.kthSelect(j+1, arr, comp, rand));
            }
            if(!checkSorted(selects.toArray(new NumberedNumber[0])))failAndDumpArray(selects.toArray(new NumberedNumber[0]), "On iteration "+i+" calling kthSelect on each index in order did not produce a sorted list.");
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted as a result of calling kthSelect on every index.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = KTH_RANDOM[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza."
                            +"\nFor the kthSelect algorithm in particular, please ensure that you are using the algorithm exactly shown in lecture."
                            +"\nSpecifically, ensure that after each recursive call, the provided subarray has undergone a complete quicksort step;"
                            +"\nthat is, the pivot is in the correct position in the subarray, and all elements to the left and right of the pivot are smaller or larger than the pivot.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void kthNearly() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getNearlySortedDupefreeNumbers(i*9+10, i, i*5+5);
            ArrayList<NumberedNumber> selects = new ArrayList<>(arr.length);
            Random rand = new Random(i);
            for (int j = 0; j < arr.length; j++) {
                selects.add(Sorting.kthSelect(j+1, arr, comp, rand));
            }
            if(!checkSorted(selects.toArray(new NumberedNumber[0])))failAndDumpArray(selects.toArray(new NumberedNumber[0]), "On iteration "+i+" calling kthSelect on each index in order did not produce a sorted list.");
            if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted as a result of calling kthSelect on every index.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = KTH_NEARLY[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza."
                            +"\nFor the kthSelect algorithm in particular, please ensure that you are using the algorithm exactly shown in lecture."
                            +"\nSpecifically, ensure that after each recursive call, the provided subarray has undergone a complete quicksort step;"
                            +"\nthat is, the pivot is in the correct position in the subarray, and all elements to the left and right of the pivot are smaller or larger than the pivot.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void kthSortedDuped() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getSortedDupedNumbers(i*9+10, i);
            ArrayList<NumberedNumber> selects = new ArrayList<>(arr.length);
            Random rand = new Random(i);
            for (int j = 0; j < arr.length; j++) {
                selects.add(Sorting.kthSelect(j+1, arr, comp, rand));
            }
            if(!checkSorted(selects.toArray(new NumberedNumber[0])))failAndDumpArray(selects.toArray(new NumberedNumber[0]), "On iteration "+i+" calling kthSelect on each index in order did not produce a sorted list.");
            //if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted as a result of calling kthSelect on every index.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = KTH_SORTED_DUPED[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza."
                            +"\nFor the kthSelect algorithm in particular, please ensure that you are using the algorithm exactly shown in lecture."
                            +"\nSpecifically, ensure that after each recursive call, the provided subarray has undergone a complete quicksort step;"
                            +"\nthat is, the pivot is in the correct position in the subarray, and all elements to the left and right of the pivot are smaller or larger than the pivot.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void kthReversedDuped() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getReversedDupedNumbers(i*9+10, i);
            ArrayList<NumberedNumber> selects = new ArrayList<>(arr.length);
            Random rand = new Random(i);
            for (int j = 0; j < arr.length; j++) {
                selects.add(Sorting.kthSelect(j+1, arr, comp, rand));
            }
            if(!checkSorted(selects.toArray(new NumberedNumber[0])))failAndDumpArray(selects.toArray(new NumberedNumber[0]), "On iteration "+i+" calling kthSelect on each index in order did not produce a sorted list.");
            //if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted as a result of calling kthSelect on every index.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = KTH_REVERSED_DUPED[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza."
                            +"\nFor the kthSelect algorithm in particular, please ensure that you are using the algorithm exactly shown in lecture."
                            +"\nSpecifically, ensure that after each recursive call, the provided subarray has undergone a complete quicksort step;"
                            +"\nthat is, the pivot is in the correct position in the subarray, and all elements to the left and right of the pivot are smaller or larger than the pivot.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void kthRandomDuped() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getRandomDupedNumbers(i*9+10, i);
            ArrayList<NumberedNumber> selects = new ArrayList<>(arr.length);
            Random rand = new Random(i);
            for (int j = 0; j < arr.length; j++) {
                selects.add(Sorting.kthSelect(j+1, arr, comp, rand));
            }
            if(!checkSorted(selects.toArray(new NumberedNumber[0])))failAndDumpArray(selects.toArray(new NumberedNumber[0]), "On iteration "+i+" calling kthSelect on each index in order did not produce a sorted list.");
            //if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted as a result of calling kthSelect on every index.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = KTH_RANDOM_DUPED[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza."
                            +"\nFor the kthSelect algorithm in particular, please ensure that you are using the algorithm exactly shown in lecture."
                            +"\nSpecifically, ensure that after each recursive call, the provided subarray has undergone a complete quicksort step;"
                            +"\nthat is, the pivot is in the correct position in the subarray, and all elements to the left and right of the pivot are smaller or larger than the pivot.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    @Test
    public void kthNearlyDuped() {
        for (int i = 0; i < 10; i++) {
            NumComp comp = new NumComp();
            NumberedNumber[] arr = getNearlySortedDupedNumbers(i*9+10, i, i*5+5);
            ArrayList<NumberedNumber> selects = new ArrayList<>(arr.length);
            Random rand = new Random(i);
            for (int j = 0; j < arr.length; j++) {
                selects.add(Sorting.kthSelect(j+1, arr, comp, rand));
            }
            if(!checkSorted(selects.toArray(new NumberedNumber[0])))failAndDumpArray(selects.toArray(new NumberedNumber[0]), "On iteration "+i+" calling kthSelect on each index in order did not produce a sorted list.");
            //if(!checkSorted(arr))failAndDumpArray(arr, "On iteration "+i+" the array was not sorted as a result of calling kthSelect on every index.");
            if(!checkUnique(arr))failAndDumpArray(arr, "On iteration "+i+" the sorted list did not contain the same elements as before the sort; two elements in the array are the same, when every element was originally unique.");
            if (FAIL_IF_TOO_MANY_COMPARISONS) {
                int target = KTH_NEARLY_DUPED[i];
                if (target != comp.compCount) {
                    fail("On iteration "+i+" an incorrect number of comparisons were made. Target: "+target+", Actual: "+comp.compCount
                            +"\nNote: if your number is above the target, it is possible your sorting algorithm is inefficient."
                            +"\nIf it is below the target, your algorithm could actually be better than the algorithm used to generate the targets."
                            +"\nIf you fall below the target, please let me know on Piazza."
                            +"\nFor the kthSelect algorithm in particular, please ensure that you are using the algorithm exactly shown in lecture."
                            +"\nSpecifically, ensure that after each recursive call, the provided subarray has undergone a complete quicksort step;"
                            +"\nthat is, the pivot is in the correct position in the subarray, and all elements to the left and right of the pivot are smaller or larger than the pivot.");
                }
            }
            System.out.println("Array sorted in "+comp.compCount+" comparisons. Array:");
            System.out.println(getNNArrayString(arr)+"\n");
        }
    }

    public static class NumberedNumber implements Comparable<NumberedNumber>{
        public int number;
        public int numbering;
        @Override
        public boolean equals(Object n) {
            if (!(n instanceof NumberedNumber)) return false;
            return ((NumberedNumber)n).number == this.number;
        }
        @Override
        public int hashCode() {
            return number;
        }

        @Override
        public int compareTo(NumberedNumber n) {
            return this.number - n.number;
        }

        public String toString() {
            return this.number+"."+this.numbering;
        }
    }

    public static class NumComp implements Comparator<NumberedNumber> {
        public int compCount = 0;
        public int compare (NumberedNumber a, NumberedNumber b) {
            compCount++;
            return a.number - b.number;
        }
        public boolean equals(Object o) {
            return o instanceof NumComp;
        }
    }

    public static NumberedNumber[] getRandomDupefreeNumbers(int size, int seed){
        Random rand = new Random(seed);
        List<NumberedNumber> list = Arrays.asList(getSortedDupefreeNumbers(size));
        Collections.shuffle(list, new Random(seed));
        for (int i = 0; i < size; i++) {
            list.get(i).numbering = i;
        }
        return list.toArray(new NumberedNumber[0]);
    }

    public static NumberedNumber[] getSortedDupefreeNumbers(int size) {
        NumberedNumber[] arr = new NumberedNumber[size];
        for (int i = 0; i < size; i++) {
            arr[i] = new NumberedNumber();
            arr[i].number = i;
            arr[i].numbering = i;
        }
        return arr;
    }

    public static NumberedNumber[] getReversedDupefreeNumbers(int size) {
        NumberedNumber[] arr = new NumberedNumber[size];
        for (int i = 0; i < size; i++) {
            arr[size-1-i] = new NumberedNumber();
            arr[size-1-i].number = i;
            arr[size-1-i].numbering = size-1-i;
        }
        return arr;
    }

    public static NumberedNumber[] getNearlySortedDupefreeNumbers(int size, int seed, int swaps) {
        Random rand = new Random(seed);
        NumberedNumber[] arr = getSortedDupefreeNumbers(size);
        for (int i = 0; i < swaps; i++) {
            int r = rand.nextInt(size - 1);
            swap(arr, r, r + 1);
        }
        for (int i = 0; i < size; i++) {
            arr[i].numbering = i;
        }
        return arr;
    }

    public static NumberedNumber[] getSortedDupedNumbers(int size, int seed) {
        int base = 0;
        Random rand = new Random(seed);
        NumberedNumber[] arr = new NumberedNumber[size];
        for(int i = 0; i < size; i++) {
            if (rand.nextInt(3)==0) {
                base=i;
            }
            arr[i] = new NumberedNumber();
            arr[i].number = base;
            arr[i].numbering = i;
        }
        return arr;
    }

    public static NumberedNumber[] getReversedDupedNumbers(int size, int seed) {
        int base = 0;
        Random rand = new Random(seed);
        NumberedNumber[] arr = new NumberedNumber[size];
        for(int i = 0; i < size; i++) {
            if (rand.nextInt(3)==0) {
                base=i;
            }
            arr[size-1-i] = new NumberedNumber();
            arr[size-1-i].number = base;
            arr[size-1-i].numbering = size-1-i;
        }
        return arr;
    }

    public static NumberedNumber[] getRandomDupedNumbers(int size, int seed){
        Random rand = new Random(seed);
        List<NumberedNumber> list = Arrays.asList(getSortedDupedNumbers(size, seed));
        Collections.shuffle(list, new Random(seed));
        for (int i = 0; i < size; i++) {
            list.get(i).numbering = i;
        }
        return list.toArray(new NumberedNumber[0]);
    }

    public static NumberedNumber[] getNearlySortedDupedNumbers(int size, int seed, int swaps) {
        Random rand = new Random(seed);
        NumberedNumber[] arr = getSortedDupedNumbers(size, seed);
        for (int i = 0; i < swaps; i++) {
            int r = rand.nextInt(size - 1);
            swap(arr, r, r + 1);
        }
        for (int i = 0; i < size; i++) {
            arr[i].numbering = i;
        }
        return arr;
    }

    public static int[] getRandomDupefreeInts(int size, int seed, boolean negatives){
        Random rand = new Random(seed);
        ArrayList<Integer> list = new ArrayList<>(size);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            list.add(i+(negatives?-size/2:0));
        }
        Collections.shuffle(list, new Random(seed));
        for (int i = 0; i < size; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    public static int[] getSortedDupefreeInts(int size, boolean negatives) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i+(negatives?-size/2:0);
        }
        return arr;
    }

    public static int[] getReversedDupefreeInts(int size, boolean negatives) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[size-1-i] = i+(negatives?-size/2:0);
        }
        return arr;
    }

    public static int[] getNearlySortedDupefreeInts(int size, int seed, int swaps, boolean negatives) {
        Random rand = new Random(seed);
        int[] arr = getSortedDupefreeInts(size, negatives);
        for (int i = 0; i < swaps; i++) {
            int r = rand.nextInt(size - 1);
            swap(arr, r, r + 1);
        }
        return arr;
    }

    public static int[] getSortedDupedInts(int size, int seed, boolean negatives) {
        int base = 0;
        Random rand = new Random(seed);
        int[] arr = new int[size];
        for(int i = 0; i < size; i++) {
            if (rand.nextInt(3)==0) {
                base=i+(negatives?-size/2:0);
            }
            arr[i] = base;
        }
        return arr;
    }

    public static int[] getReversedDupedInts(int size, int seed, boolean negatives) {
        int base = 0;
        Random rand = new Random(seed);
        int[] arr = new int[size];
        for(int i = 0; i < size; i++) {
            if (rand.nextInt(3)==0) {
                base=i+(negatives?-size/2:0);
            }
            arr[size-1-i] = base;
        }
        return arr;
    }

    public static int[] getRandomDupedInts(int size, int seed, boolean negatives){
        Random rand = new Random(seed);
        ArrayList<Integer> list = new ArrayList<>(size);
        int[] arr = getSortedDupedInts(size, seed, negatives);
        for (int i = 0; i < size; i++) {
            list.add(arr[i]);
        }
        Collections.shuffle(list, new Random(seed));
        for (int i = 0; i < size; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    public static int[] getNearlySortedDupedInts(int size, int seed, int swaps, boolean negatives) {
        Random rand = new Random(seed);
        int[] arr = getSortedDupedInts(size, seed, negatives);
        for (int i = 0; i < swaps; i++) {
            int r = rand.nextInt(size - 1);
            swap(arr, r, r + 1);
        }
        return arr;
    }

    public static boolean checkSorted(NumberedNumber[] arr) {
        NumComp comp = new NumComp();
        for (int i = 0; i < arr.length - 1; i++) {
            if (comp.compare(arr[i], arr[i+1]) > 0) return false;
        }
        return true;
    }

    public static boolean checkSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i+1]) return false;
        }
        return true;
    }

    public static boolean checkStable(NumberedNumber[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].number == arr[i + 1].number) {
                if (arr[i].numbering > arr[i+1].numbering) return false;
            }
        }
        return true;
    }

    /**
     * Ensures that all elements in the array are unique.
     */
    public static boolean checkUnique(NumberedNumber[] arr) {
        HashMap<Integer,ArrayList<Integer>> map = new HashMap<>();
        for (NumberedNumber n : arr) {
            if (map.containsKey(n.number)) {
                if (map.get(n.number).contains(n.numbering)) return false;
            } else {
                map.put(n.number, new ArrayList<>());
            }
            map.get(n.number).add(n.numbering);
        }
        return true;
    }

    public static void swap(NumberedNumber[] arr, int a, int b) {
        NumberedNumber temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void failAndDumpArray(NumberedNumber[] arr, String message) {
        System.out.println("Actual array:");
        System.out.println(getNNArrayString(arr));
        System.out.println("Sorted array:");
        List<NumberedNumber> sorted = Arrays.asList(arr);
        Collections.sort(sorted);
        System.out.println(getNNArrayString(sorted.toArray(new NumberedNumber[0])));
        System.out.println("Any numbers with dashes (e.g. 1-4) signify duplicate elements; for stable algorithms, the second numbers for each element in a group of duplicates should be arranged in ascending order.");
        System.out.println("If your sorting algorithm is stable, the number after the dots should be arranged in increasing order across all the duplicates.");
        System.out.println("Note: I use Collections.sort(array you generated) to generate the sorted array example, so the sorted array shown may not be stable (but yours should be).");
        fail(message);
    }
    public static void failAndDumpArray(int[] arr, String message) {
        System.out.println("Actual array:");
        System.out.println(getIntArrayString(arr));
        System.out.println("Sorted array:");
        ArrayList<Integer> sortedList = new ArrayList<>(arr.length);
        for(int n:arr)sortedList.add(n);
        Collections.sort(sortedList);
        int[] sortedArr = new int[arr.length];
        for(int i = 0; i < sortedList.size(); i++)sortedArr[i] = sortedList.get(i);
        System.out.println(getIntArrayString(sortedArr));
        fail(message);
    }

    public static String getNNArrayString(NumberedNumber[] arr) {
        HashMap<Integer, Integer> counts = new HashMap<>();
        for(NumberedNumber n : arr) {
            if (n == null) {
                //do nothing
            } else if (!counts.containsKey(n.number)) {
                counts.put(n.number, 1);
            } else {
                counts.put(n.number, counts.get(n.number) + 1);
            }
        }
        String s = "[";
        for (NumberedNumber n : arr) {
            if (n == null) {
                s+="null, ";
            } else if (counts.containsKey(n.number) && counts.get(n.number) > 1) {
                s+=n.number+"-"+n.numbering+", ";
            } else {
                s+=n.number+", ";
            }
        }
        s=s.substring(0, s.length()-2)+"]";
        return s;
    }

    public static String getIntArrayString(int[] arr) {
        String s = "[";
        for (int n : arr) {
            s+=n+", ";
        }
        s=s.substring(0, s.length()-2)+"]";
        return s;
    }
}