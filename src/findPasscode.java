import java.io.File;
import java.io.IOException;
import java.util.*;

public class findPasscode {
    public static void main(String[] args) throws IOException {

        // Set the input data file name here
        String fn = "length_4_N_8589.txt";

        // Call helper function to read the input file
        Integer[] data = read_array(fn);

        // We need to know the length of the "strings" in this data file (for output
        // purposes later), but we read and saved them as integers, so the best way to
        // determine this now is to loop over the list, find the largest item, and
        // assume that all of the items are as long as that one.
        Integer max = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] > max) {
                max = data[i];
            }
        }
        int length_of_passcodes = String.valueOf(max).length();
        System.out.println("length of passcodes is " + length_of_passcodes);
        System.out.println(data.length + " data items in the file");

        printMergeSort(data);
    }

    // 1) mergesort
    /*
    * Keep dividing ary into half size like a binary tree until the ary breaks into single elements.
    * Compare the left and right element, merge them back in to a new ary base in order.
    * Keep calling back the precious process, so that in the end the original ary will be
    * merged in a sorted order.
    *
    * */
    public static Integer[] mergeSort(Integer[] ary) {
        int size = ary.length;
        if (size <= 1) {
            return ary;
        }
        Integer[] left;
        Integer[] right;
        left = Arrays.copyOfRange(ary, 0, size / 2);
        right = Arrays.copyOfRange(ary, size / 2, size);

        left = mergeSort(left);
        right = mergeSort(right);
        return merge(left, right);
    }

    public static Integer[] merge(Integer[] left, Integer[] right) {
        int countL = 0;
        int countR = 0;
        int countMerge = 0;
        Integer[] mergeAry = new Integer[left.length + right.length];

        while (countL < left.length || countR < right.length) {
            if (countL < left.length && countR < right.length) {
                if (left[countL] < right[countR]) {
                    mergeAry[countMerge++] = left[countL++];
                } else {
                    mergeAry[countMerge++] = right[countR++];
                }
            } else if (countL < left.length) {
                mergeAry[countMerge++] = left[countL++];
            } else {
                mergeAry[countMerge++] = right[countR++];
            }

        }
        return mergeAry;
    }


    // 2) find the smallest missing number
    /*
    * Known: ary.size, ary items
    * Since there are missing items in the ary, arySize / 2 < maxItem / 2 always true
    * Keep diving ary in half and compare (arySize/2) and (maxItem/2),
    * until we find the Node which both sides equal.
    * Which means, there is no missing value in the current ary.
    * Plus we are looking for the smallest missing value, we start looking up the sorted ary from the left.
    *
    * Repeat the same steps for the Previous ary's right side.
    * */
    public static Integer findMissingSmallest(Integer[] ary) {
        // If the first element in the ary is not 0, return 0.
        if (ary[0] != 0) {
            return 0;
        }

        int min = ary[0];
        int max = ary[ary.length - 1];
        return findMissing(ary, min, max);
    }

    public static Integer findMissing(Integer[] ary, int min, int max) {
        if (min < max) {
            int midVal = (max + min) / 2;
            if (ary[midVal] != midVal) {
                return findMissing(ary, min, midVal);
            } else {
                return findMissing(ary, midVal + 1, max);
            }
        }
        return min;
    }


    // 3) print the passcode
    public static void printMergeSort(Integer[] ary) {
        Integer[] b = mergeSort(ary);
        System.out.println(Arrays.toString(b));
        Integer pw = findMissingSmallest(b);
        System.out.printf("%04d%n", pw);
    }

    // Read file array
    public static Integer[] read_array(String filename) throws IOException {
        //
        // Reads the input file given by "filename", assumed to contain a list of
        // integer numbers. Stores the numbers into an array and returns the
        // array.
        //
        File file = new File(filename);
        Scanner sc = new Scanner(file);

        //
        // Read the items initially into an ArrayList (for dynamic growth)
        //
        ArrayList<Integer> input_list = new ArrayList<Integer>();
        while (sc.hasNext()) {
            int n = sc.nextInt();
            input_list.add(n);
        }

        //
        // Copy the ArrayList to an Integer[] array of the proper size
        //
        Integer[] arr = new Integer[input_list.size()];
        arr = input_list.toArray(arr);
        return arr;
    }
}

