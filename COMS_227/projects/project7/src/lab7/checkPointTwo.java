package lab7;

import java.io.File;

public class checkPointTwo {

	public static void main(String[] args) {
		
		File testFile = new File("../project7/src/lab7");
		System.out.println(countFiles(testFile)); //expected 5
		
		File testFile1 = new File("../ArraySum.java");
		System.out.println(countFiles(testFile1)); //expected 1
		
		System.out.println(countPatterns(5));

	}
	
	public static int countFiles(File f) {
		int count = 0;
		
		if (!f.isDirectory()) {
			return 1;
		} else {
			File[] files = f.listFiles();
			for (int i = 0; i < files.length; ++i) {
				count += 1;
			}
			return count;
			
		}
	}
	
	public static int countPatterns(int n) {
		int count = 0;
		
		if (n == 1) {
			return n;
		} else {
			return count + countPatterns(n - 1);
		}
	}

}
