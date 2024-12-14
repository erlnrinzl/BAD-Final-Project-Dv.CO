package util;

public class StringHelper {

	public static String addLeadingZeros(Integer digit, Integer length) {
		String result = "";
		String digitInString = digit.toString();

		if (digitInString.length() >= length)
			return digitInString;

		while (result.length() < length - digitInString.length()) {
			result += "0";
		}

		return result + digitInString;
	}

	public static boolean isAlphanumeric(String string) {
		for (int i = 0; i < string.length(); i++) {

			Character ch = string.charAt(i);

			if (!Character.isLetterOrDigit(ch))
				return false;

		}

		return true;
	}

	public static boolean isNumeric(String string) {
		for (int i = 0; i < string.length(); i++) {

			Character ch = string.charAt(i);

			if (!Character.isDigit(ch))
				return false;

		}

		return true;
	}
}
