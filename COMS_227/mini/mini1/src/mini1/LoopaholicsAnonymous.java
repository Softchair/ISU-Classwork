package mini1;

import java.util.Scanner;

/**
 * Utility class with some loop practice problems.
 */
public class LoopaholicsAnonymous
{

  /**
   * Private constructor prevents instantiation.
   */
  private LoopaholicsAnonymous() {}
  
  /**
   * Determines how many iterations of the following operation
   * are required until the condition (a * a + b * b) > 4 is reached:
   * <pre>
   *   newA = a * a - b * b + x
   *   newB = 2 * a * b + y
   *   a = newA
   *   b = newB
   * </pre>
   * where a and b are initially zero. For example, given x = -1 and y = 1,
   * there would be three iterations:
   * <ul>
   *   <li>initially: a = 0, b = 0
   *   <li>1: a = -1, b = 1
   *   <li>2: a = -1, b = -1
   *   <li>3: a = -1, b = 3
   * </ul>
   * <p>
   * If the condition (a * a + b * b) > 4 
   * is not reached within <code>maxIterations</code>, the method returns
   * <code>maxIterations</code>.
   *     
   * @param x
   *   given x value
   * @param y
   *   given y value
   * @param maxIterations
   *   maximum number of iterations to attempt
   * @return
   *   number of iterations required to get (a * a + b * b) &gt; 4,
   *   or maxIterations
   */
  public static int findEscapeCount(double x, double y, int maxIterations)
  {
	double a = 0;
	double b = 0;
	int count = 0;
	
	for (int i = 0; i < maxIterations; i++) {
		double newA = a * a - b * b + x;
		double newB = 2 * a * b + y;
		a = newA;
		b = newB;
		count += 1;
		if ((a* a + b * b) > 4) {
			return count;
		}
	}
	
    return maxIterations;
  }

  /**
   * Returns the index for the nth occurrence of the given character
   * in the given string, or -1 if the character does not occur n or more
   * times.  This method is case sensitive.
   * Examples:
   * <ul>
   * <li>findNth("mississippi", 's', 1) returns 2
   * <li>findNth("mississippi", 's', 4) returns 6
   * <li>findNth("mississippi", 's', 5) returns -1
   * <li>findNth("mississippi", 'w', 1) returns -1
   * </ul>
   * @param s
   *   given string
   * @param ch
   *   given character to find
   * @param n
   *   occurrence to find
   * @return
   *   index of nth occurrence, or -1 if the character does not occur 
   *   n or more times
   */
  public static int findNth(String s, char ch, int n)
  {
	int lttrCount = 1; //how many times has letter been seen
    for (int i = 0; i < s.length(); i++) {
    	char current = s.charAt(i);
    	if (current == ch && lttrCount == n) {
    		return i;
    	} else if (current == ch && lttrCount != n) {
    		lttrCount += 1;
    	}
    }
    return -1;
  }
  
  /**
   * Returns the longest substring starting at the given position
   * in which all characters are the same.  For example,
   * <ul>
   *   <li>getRun("bbbbbbcbc", 2) returns "bbbb"
   *   <li>getRun("abcd", 1) returns "b"
   * </ul>
   * Returns an empty string if the given start index
   * is out of range.
   * @param s
   *   given string
   * @param start
   *   index at which to start the run
   * @return
   *   substring of all matching characters starting at the given position
   */
  public static String getRun(String s, int start)
  {
	if (start > s.length() - 1) {
		return "";
	} else {
		char current = s.charAt(start);
		String run = "" + s.charAt(start);
		for (int i = start + 1; i < s.length(); i++) {
			current = s.charAt(i);
			char last = s.charAt(i - 1);
			if (current == last) {
				run += s.charAt(i);
			} else {
				break;
			}
		}
		return run;
	}
  }
 
  /**
   * Given a string of text containing numbers separated by
   * commas, returns true if the numbers form an <em>arithmetic</em> sequence
   * (a sequence in which each value differs from the previous
   * one by a fixed amount).  For example,
   * <ul>
   * <li>given "2, 4, 6, 8", the method returns true
   * <li>given "-2, 5, 12, 19, 26", returns true
   * <li>given "2, 4, 7", returns false
   * <li>given "1, 2, 23skidoo", returns false
   * </ul>
   * The method should return true for any string containing
   * two or fewer numbers and false for any invalid string.  Note
   * that there may or may not be spaces after each comma.
   * @param text
   *   a string of text containing numbers separated by commas
   * @return
   *   true if each number differs from the previous one by the same amount
   */
  public static boolean isArithmetic(String text)
  {
	Scanner sc = new Scanner(text);
	sc.useDelimiter(",");
	
	while (sc.hasNext()) {
		if (!sc.hasNextInt()) {
			sc.close();
			return false;
		} else {
			sc.next();
		}
	}
	
	sc.close();
	
	String[] temp = text.split(",");
	
	int[] vals = new int[temp.length];
	
	if (temp.length <= 2) {
		return true;
	} else {
		for (int i = 0; i < temp.length; i++) {
			vals[i] = Integer.parseInt(temp[i]);
		}
	}
	
	int first = vals[0];
	int second = vals[1];
	int dif = second - first;
	
	
	for (int i = 1; i < vals.length; i++) { //wrong
		first = vals[i -1];
		second = vals[i];
		if (second - first != dif) {
			return false;
		}
	}
	
	return true;
  }
  
  
  /**
   * Determines whether the string <code>target</code> occurs as a subsequence
   * of string <code>source</code> where "gaps" are allowed between characters 
   * of <code>target</code>. That is, the characters in <code>target</code> 
   * occur in <code>source</code> in their given order but do not have to be 
   * adjacent.  (Pictured another way, this method returns true if 
   * <code>target</code> could be obtained from <code>source</code>
   * by removing some of the letters of <code>source</code>.)
   * This method is case sensitive. 
   * For example,
   * <ul>
   * <li>containsWithGaps("hamburgers", "mug") returns true
   * <li>containsWithGaps("hamburgers", "burrs") returns true
   * <li>containsWithGaps("hamburgers", "hamburgers") returns true
   * <li>containsWithGaps("hamburgers", "gum") returns false
   * <li>containsWithGaps("hamburgers", "hamm") returns false
   * <li>containsWithGaps("hamburgers", "") returns true
   * </ul>
   * @param source
   *   the given string in which to find the target characters
   * @param target
   *   the characters to be found  
   * @return
   *   true if the characters in <code>target</code> can be found
   *   as a subsequence in <code>source</code>, false otherwise
   */
  public static boolean subsequenceWithGaps(String source, String target)
  {
	if (target.equals("")) {
		return true;
	}
	
	int count = 0;
	int temp = 0;
    for (int i = 0; i < source.length(); i++) {
    	char targ = target.charAt(temp);
    	char current = source.charAt(i);
    	if (current == targ) {
    		count += 1;
    		temp += 1;
    	    if (count == target.length()) {
    	    	return true;
    	    }
    	}
    }
    return false;
  }
  

  
  /**
   * Separates s into two strings, each made of alternating characters
   * from s, except that runs of the same character are kept together.
   * The two strings are concatenated with a space between them to make
   * a single returned string. If the given string is empty, the returned 
   * string is a single space.
   * For example,
   * <ul>
   * <li><code>takeApartPreservingRuns("abcdefa")</code> returns "acea bdf"
   * <li><code>takeApartPreservingRuns("aabcccddddefa")</code> returns "aacccea bddddf"
   * </ul>
   * @param s
   *   any string
   * @return
   *   pair of strings obtained by taking alternating characters from s, 
   *   keeping runs of the same character together, concatenated with 
   *   one space between them into a single string 
   */
  public static String takeApartPreservingRuns(String s)
  {
	String a = "";
	String b = "";
	int length = 0;
	int count = 0;
    for (int i = 0; i < s.length(); i++) {
    	if (getRun(s, i).length() > 1) {
    		if (count % 2 == 0) {
        		a += getRun(s, i);
        	} else {
        		b += getRun(s, i);
        	}
    		length = getRun(s, i).length();
    		i += length - 1;
    		count += 1;
    	} else {
    	if (count % 2 == 0) {
    		a += s.charAt(i);
    		count += 1;
    	} else {
    		b += s.charAt(i);
    		count += 1;
    	}
    	}
    }
    return a + " " + b;
  }
  
  
  /**
   * Returns the longest substring of consecutive characters in a given
   * string that are in ascending (nondecreasing) order.  For example,
   * <ul>
   *   <li>longestAscendingSubstring("xyzabcdzyx") returns "abcdz"
   *   <li>longestAscendingSubstring("dcba") returns "d"
   *   <li>longestAscendingSubstring("bbbbaaaaa") returns "aaaaa"
   * </ul>
   * If there are multiple runs of the same maximal length,
   * the methods returns the leftmost one.
   * @param s
   *   any given string
   * @return
   *   longest nondecreasing substring
   */
  public static String longestAscendingSubstring(String s)
  {
    String a = "";
    
    if (s.length() == 0) {
    	return a;
    }
    
    char last = s.charAt(0); //last (or first) character
    
    String max = "" + last;
    
    for (int i = 1; i < s.length(); i++) {
    	char c = s.charAt(i); //current
    	if (c < last) {
    		a += c;
    	} else {
    		max = a + c;
    		a = "";
    		last = c;
    	}
    }
    
    if (max.length() > a.length()) {
    	return max;
    } else {
    	return a;
    }
  }

   /**
   * Prints a pattern of 2n rows containing dashes and stars,
   * as illustrated below for n = 5.  Note that the first row
   * starts with exactly n - 1 spaces and the middle rows have
   * no spaces.
   * 
   * <pre>
   *     
   *----**----
   *---*  *---
   *--*    *--
   *-*      *-
   **        *
   **        *
   *-*      *-
   *--*    *--
   *---*  *---
   *----**----
   * </pre>
   * 
   * @param n
   *   one-half the number of rows in the output
   */
  public static void printStars(int n)
  {
	n -= 1;
	
	for (int i = 0; i <= n; i++) { // top
		
		for (int d = n; d > i; d--) {
			System.out.print("-");
		}
		
		System.out.print("*");
		
		for (int s = 0; s < i; s++) {
			System.out.print("  ");
		}
		
		System.out.print("*");
		
		for (int d = n; d > i; d--) {
			System.out.print("-");
		}
		System.out.println();
	}
	
	for (int i = n; i >= 0; i--) { // bottom

		for (int d = n; d > i; d--) {
			System.out.print("-");
		}
		
		System.out.print("*");
		
		for (int s = 0; s < i; s++) {
			System.out.print("  ");
		}
		
		System.out.print("*");
		
		for (int d = n; d > i; d--) {
			System.out.print("-");
		}
		System.out.println();
	}
	
	//end
  }
 
}
