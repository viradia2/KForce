import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ZipMainImpl {

	static int zipRanges[][] = { { 94133, 94133 }, { 94200, 94299 }, { 94600, 94699 } };
	static ZipCodeRange zip = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		sortbyColumn(zipRanges, 0);

		printTheSortedMatrix(zipRanges);

		List<ZipCodeRange> normalizedZipRangelist = normalizedZipRanges(zipRanges);

		System.out.println(normalizedZipRangelist);
	}

	private static List<ZipCodeRange> normalizedZipRanges(int[][] arr) {
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
			normalizedZipRange = setTheNormalizedZip(upperBound, lowerBound, normalizedZipRange);
		}
		return normalizedZipRange;
	}

	private static List<ZipCodeRange> setTheNormalizedZip(int upperBound, int lowerBound, List<ZipCodeRange> list) {
		zip = new ZipCodeRange();
		zip.setLowerBound(lowerBound);
		zip.setUpperBound(upperBound);

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
