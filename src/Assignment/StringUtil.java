package Assignment;

public final class StringUtil
{
	private static int alphaNumeralToValue(char ch)
	{
		if(ch >= '0' && ch <= '9')return ch - '0';
		else if(ch >= 'A' && ch <= 'Z')return ch - 'A';
		else if(ch >= 'a' && ch <= 'z')return ch - 'a';
		else return -1;
	}
	private static char valueToAlphaNumeral(int val)
	{
		if(val >= 0 && val <= 9)return (char)(val + '0');
		else if(val <= 35)return (char)(val - 10 + 'A');
		else if(val <= 61)return (char)(val - 36 + 'a');
		else return '0';
	}
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