/**
 * 
 */
package lab2;

/**
 * @author Camden
 *
 */
public class StringTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String message = "Hello, world!";
		System.out.println(message);
		
		int theLength = message.length();
		System.out.println(theLength);
		
		char theChar = message.charAt(0);
		System.out.println(theChar);
		
		theChar = message.charAt(12);
		System.out.println(theChar);
		
		String upperCase = message.toUpperCase();
		System.out.println(upperCase);
		
		String firstFive = message.substring(0, 5);
		System.out.println(firstFive);

	}

}
