import java.util.HashMap;

public class HelloWorld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello....!!");
		int[] array = {1,3,3,3,3,2,1,1,1,1};
		majorityElementIn2N(array);
		
	}

	private static void majorityElementIn2N(int[] array) {
		// TODO Auto-generated method stub
		int length = array.length;
		
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		boolean flag = true;
		for (int i = 0; i < array.length; i++) {
			if(map.containsKey(array[i])) {
				map.put(array[i], map.get(array[i]) + 1);
				
				int count = map.get(array[i]);
				if(count > length/2) {
					System.out.println("Majority Element: " + array[i]);
				}
				else
					flag = false;
			}else
				map.put(array[i], 1);
		}
		
		if(!flag)
			System.out.println("No Majority Element Found");
	}

}
