package Assignment;

/**
 * Utility class for manipulating alphanumeric ID strings
 */
public final class StringUtil
{
	public static int alphaNumeralToValue(char ch)
	{
		if(ch >= '0' && ch <= '9')return ch - '0';
		else if(ch >= 'A' && ch <= 'Z')return ch - 'A';
		else if(ch >= 'a' && ch <= 'z')return ch - 'a';
		else return -1;
	}
	public static char valueToAlphaNumeral(int val)
	{
		if(val >= 0 && val <= 9)return (char)(val + '0');
		else if(val <= 35)return (char)(val - 10 + 'A');
		else if(val <= 61)return (char)(val - 36 + 'a');
		else return '0';
	}

	/**
	 * Generates a new string corresponding to the incremented alpha-numeric ID string.
	 * Additional digits will be added if the incrementation overflows.
	 *
	 * @param str A string representing an alphanumeric ID string
	 * @param value An integer representing the value to be incremented
	 * @return A string representing the incremented alphanumeric ID string
	 */
	public static String incrementString(String str, int value)
	{
		StringBuilder strIncremented = new StringBuilder();
		int carry = value, base = 62, tmp, charVal;
		for(int i = str.length() - 1; i >= 0; i--)
		{
			charVal = str.charAt(i);
			tmp = (alphaNumeralToValue(str.charAt(i)) + carry)%base;
			carry = (alphaNumeralToValue(str.charAt(i)) + carry)/base;
			strIncremented.append(valueToAlphaNumeral(tmp));
		}
		while(carry != 0)
		{
			tmp = carry%base;
			carry = carry/base;
			strIncremented.append(valueToAlphaNumeral(tmp));
		}
		return strIncremented.reverse().toString();
	}
}