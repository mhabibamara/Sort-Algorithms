/**
 * sortAlgorithms
 * Mohamed Amara 
 */

import java.io.FileWriter;
import java.io.IOException;

public class sortAlgorithms {

    /**
     * Citation for selection sort algorithm
     * Mishra, R. (2022, February 3). Selection sort. GeeksforGeeks. Retrieved February 3, 2022, from https://www.geeksforgeeks.org/selection-sort/ 
     */
    static void selectionSort(int arr[])
    {
        int n = arr.length;
  
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;
  
            // Swap the found minimum element with the first
            // element
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }

    /**
     * Citation for insertion sort algorithm
     * Mishra, R. (2021, July 8). Insertion sort. GeeksforGeeks. Retrieved February 3, 2022, from https://www.geeksforgeeks.org/insertion-sort/ 
     */
    static void insertionSort(int arr[])
    {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;
 
            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }
    
    /**
     * Citation for merge sort algorithm
     * Mishra, R. (2022, January 10). Merge sort. GeeksforGeeks. Retrieved February 3, 2022, from https://www.geeksforgeeks.org/merge-sort/ 
     */

    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    static void merge(int arr[], int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;
  
        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];
  
        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];
  
        /* Merge the temp arrays */
  
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
  
        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
  
        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    static void mergeSort(int arr[], int l, int r)
    {
        if (l < r) {

            int m =l+ (r-l)/2;               // Find the middle point
  
            mergeSort(arr, l, m);            // Sort first and second halves
            mergeSort(arr, m + 1, r);
  
            merge(arr, l, m, r);             // Merge the sorted halves
        }

    }

    /**
     * Citation for quick sort algorithm
     * Choudhary, A. (2022, January 25). Quicksort. GeeksforGeeks. Retrieved February 3, 2022, from https://www.geeksforgeeks.org/quick-sort/ 
     */

    // A utility function to swap two elements
    static void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /*  This function takes last element as pivot, places
        the pivot element at its correct position in sorted
        array, and places all smaller (smaller than pivot)
        to left of pivot and all greater elements to right
        of pivot */
    static int partition(int[] arr, int low, int high)
    {
        int pivot = arr[high];                //pivot

        // Index of smaller element and indicates the right position of pivot found so far
        int i = (low - 1);
 
        for(int j = low; j <= high - 1; j++)
        {
         
            // If current element is smaller than the pivot
            if (arr[j] < pivot)
            {             
                i++;                        // Increment index of smaller element
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    /* The main function that implements QuickSort
          arr[] --> Array to be sorted,
          low --> Starting index,
          high --> Ending index
    */
    static void quickSort(int[] arr, int low, int high)
    {
        if (low < high)
        {
         
            // pi is partitioning index, arr[p] is now at right place
            int pi = partition(arr, low, high);
 
            // Separately sort elements before partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // Prints the array into text file
    public static void printFile(int arr[], String fileName) throws IOException
    {
        FileWriter fw = new FileWriter(fileName);
        int n = arr.length;
        for (int i=0; i<n; ++i){
            fw.write(String.valueOf(arr[i]) + '\n');
        }
        fw.close();
    }
  
    // Driver code to test above
    public static void main(String args[]) throws IOException
    {
        int size = 0;
        String order = args[0].toLowerCase();
        size = Integer.parseInt(args[1]);
        String algorithm = args[2].toLowerCase();
        String outputFile = args[3].toLowerCase();
        
        //Checking for invalid number of arguments entered
        if (args.length != 4){
            System.out.println("ERROR! Invalid number of arguments entered.\nPlease invoke program from the command line as follows: java <program> <order> <size> <algorithm> <outputFile>");
            System.out.println("Program Exited...");
            System.exit(1);
        } 

        //Checking for invalid array size, array size cannot be negative
        if (Integer.parseInt(args[1]) <= 0) {
            System.out.println("ERROR! Invalid array size provided.\nPlease enter a positive number for <size>");
            System.out.println("Program Exited...");
            System.exit(1);
        }

        //Chosing order of array based on input
        int[] array = new int[size];
        if(order.toLowerCase().equals("descending")){
            for(int i = 0; i < size; i++){
                array[i] = size - i;
            }
        }
        else if(order.toLowerCase().equals("ascending")){
            for(int i = 0; i < size; i++){
                array[i] = i;
            }
        }
        else if(order.toLowerCase().equals("random")){
            for(int i = 0; i < size; i++){
                Double rnd = Math.random() * Integer.MAX_VALUE;
                array[i] = rnd.intValue();
            }
        }
        else{
            System.out.println("ERROR! Invalid array order provided.\nPlease enter either 'descending', 'ascending', or 'random' for <order>");
            System.out.println("Program Exited...");
            System.exit(1);
        }

        //Determine which sorting function to call based on input
        if(algorithm.equals("selection")){
            long startTime = System.nanoTime();
            selectionSort(array);
            long elapsedTime = System.nanoTime() - startTime;
            System.out.println("Sorting Complete.");
            System.out.println("Time taken to sort array: " + elapsedTime + " ns");
            printFile(array, outputFile);
        }
        else if(algorithm.equals("insertion")){
            long startTime = System.nanoTime();
            insertionSort(array);
            long elapsedTime = System.nanoTime() - startTime;
            System.out.println("Sorting Complete.");
            System.out.println("Time taken to sort array: " + elapsedTime + " ns");
            printFile(array, outputFile);
        }
        else if(algorithm.equals("merge")){
            long startTime = System.nanoTime();
            mergeSort(array, 0, array.length - 1);
            long elapsedTime = System.nanoTime() - startTime;
            System.out.println("Sorting Complete.");
            System.out.println("Time taken to sort array: " + elapsedTime + " ns");
            printFile(array, outputFile);
        }
        else if(algorithm.equals("quick")){
            long startTime = System.nanoTime();
            quickSort(array, 0, array.length - 1);
            long elapsedTime = System.nanoTime() - startTime;
            System.out.println("Sorting Complete.");
            System.out.println("Time taken to sort array: " + elapsedTime + " ns");
            printFile(array, outputFile);
        }
        else{
            System.out.println("ERROR! sorting algorithm provided.\nPlease enter either 'selection', 'insertion', 'merge', or 'quick' for <algorithm>");
            System.out.println("Program Exited...");
            System.exit(1);
        }
    }
}