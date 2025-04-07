package lab5;

public class checkpoint1 {

	public static String initials (String name) {
		String initials = "";
		
		for (int i = 0; i < name.length() - 1; i += 1) {
			if (name.charAt(i) == ' ') {
				initials += name.charAt(i+1);
			} else if (i == 0) {
				initials += name.charAt(i);
			}
		}
		return initials;
	}
	
	public static int hasVowel (String s) {
		for (int i = 0; i < s.length() - 1; i += 1) {
			char ch = s.charAt(i);
			if (isVowel(ch)) {
				return i;
			}
		}
		return -1;
	}
	
	public static boolean isVowel(char s) {
		if ("aeiouAEIOU".indexOf(s) >= 0) {
			return true;
		}
		return false;
	}

}
