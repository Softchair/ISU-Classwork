package lab7;

public class checkPointOne {

	public static void main(String[] args) {
		//first
		int[] test = {3, 5, 2, 7, 8, 4, 2};
		
		int max = maxValue(test);
		System.out.println(max);
		
		//second
		int testNum = 2;
		int testPyramid = getPyramidCount(testNum);
		System.out.println(testPyramid);
		
		testNum = 4;
		testPyramid = getPyramidCount(testNum);
		System.out.println(testPyramid);
	}
	
	public static int maxValue(int[] arr) {
		return maxValueRec(arr, 0, arr.length - 1);
	}
	
	private static int maxValueRec(int[] arr, int start, int end) {
		
		if (start == end) {
			return arr[start];
		} else {
			int mid = (start + end) / 2;
			int first = maxValueRec(arr, start, mid);
			int last = maxValueRec(arr, mid + 1, end);
			return Math.max(first, last);
		}
	}
	
	public static int getPyramidCount(int num) {
		return getPyramidCountRec(num);
	}
	
	private static int getPyramidCountRec(int num) {
		
		if (num == 1) {
			return num;
		} else {
			return (num * num) + getPyramidCount(num - 1);
		}
	}

}
