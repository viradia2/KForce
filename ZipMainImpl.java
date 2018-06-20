import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.management.InvalidAttributeValueException;

public class ZipMainImpl {
	
	/*Different Test Cases*/
	
//	private static int zipRanges[][] = { { 94133, 94133 }, { 94200, 94299 }, { 94600, 94699 } };
//	private static int zipRanges[][] = { { 93133, 94133 }, { 94200, 94299 }, { 94226, 94399 } };
//	private static int zipRanges[][] = { { 94133, 94133 } };
	private static int zipRanges[][] = { { 94133, 94133 }, { 22043, 22099 }, { 22042, 22199}, { 94200, 94299 }, { 94226, 94399 } };
	
	private static ZipCodeRange zip = null;

	public static void main(String[] args) {
		//Sorting Array by first column
		sortbyColumn(zipRanges, 0);
		
		//To Print Sorted Matrix
		printTheSortedMatrix(zipRanges);

		List<ZipCodeRange> normalizedZipRangelist;
		
		try {
			normalizedZipRangelist = normalizedZipRanges(zipRanges);
			System.out.println(normalizedZipRangelist);
		} catch (InvalidAttributeValueException e) {
			System.out.println("Provided Array is Empty or NULL");
		}

	}

	private static List<ZipCodeRange> normalizedZipRanges(int[][] arr) throws InvalidAttributeValueException {
		
		//if Array is NULL or Empty throwing an Exception
		if(arr == null || arr.length == 0)
			throw  new InvalidAttributeValueException() ;
		
		//Initializing upperbound and lowerbound 
		int upperBound = arr[0][0];
		int lowerBound = arr[0][1];

		List<ZipCodeRange> normalizedZipRange = new ArrayList<ZipCodeRange>();

		for (int i = 0; i < arr.length; i++) {

			boolean flag = false;

			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] >= upperBound && arr[i][j] <= lowerBound)
					flag = true;
				else if (arr[i][j] >= lowerBound) {
					if (!flag) {
						upperBound = arr[i][j];
						flag = true;
					} else {
						lowerBound = arr[i][j];
					}
				} else {
					lowerBound = arr[i][j];
				}
			}
			//Set the normalized Zip Range Array
			normalizedZipRange = setTheNormalizedZip(upperBound, lowerBound, normalizedZipRange);
		}
		return normalizedZipRange;
	}

	private static List<ZipCodeRange> setTheNormalizedZip(int upperBound, int lowerBound, List<ZipCodeRange> list) {
		zip = new ZipCodeRange();
		zip.setLowerBound(lowerBound);
		zip.setUpperBound(upperBound);
		
		//Check if upperbound value element is already available check for the correct element and replace it. 
		if (list.size() > 0 && list.get(list.size() - 1).getUpperBound() == zip.getUpperBound())
			list.set(list.size() - 1, zip);
		else
			list.add(zip);

		return list;
	}

	private static void printTheSortedMatrix(int[][] matrix) {
		// Display the sorted Matrix
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++)
				System.out.print(matrix[i][j] + " ");
			System.out.println();
		}
	}

	// Function to sort by column
	public static void sortbyColumn(int arr[][], int col) {
		// Using built-in sort function Arrays.sort
		Arrays.sort(arr, new Comparator<int[]>() {

			@Override
			// Compare values according to columns
			public int compare(final int[] entry1, final int[] entry2) {

				// To sort in descending order revert
				// the '>' Operator
				if (entry1[col] > entry2[col])
					return 1;
				else
					return -1;
			}
		}); // End of function call sort().
	}

}
