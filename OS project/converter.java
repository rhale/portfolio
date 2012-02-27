public class converter {
public static int hexToDec(String hex) throws NumberFormatException {

      // Do not continue on an empty string
      if (hex.isEmpty()) {
        throw new NumberFormatException("Empty string is not a hexadecimal number");
      }
      int res = Integer.valueOf(hex, 16).intValue();
      return res;
    }

public static int binToDec(String binary) throws NumberFormatException {
  // Initialize result to 0

  // Do not continue on an empty string
  if (binary.isEmpty()) {
    throw new NumberFormatException("Empty string is not a binary number");
  }
  int res = Integer.valueOf(binary, 2).intValue();
  return res;
}
public static String decToHex(int dec)
{
    String res = Integer.toHexString(dec);
    return res;
}

public static String decToBin(int bin)
{
    String res = Integer.toBinaryString(bin);
    return res;
}

public static String hexToBin(String hex)
{
	int dec=hexToDec(hex);
	String res = decToHex(dec);
	return res;
}

public static String binToHex(String bin)
{
	int dec=binToDec(bin);
	String res = decToHex(dec);
	return res;
}




/*    public static void main(String[] args) {
       Main blargh = new Main();
       System.out.println("Boot Man");
       System.out.println(blargh.hexToDec("A"));
       System.out.println(blargh.binToDec("10"));
       System.out.println(blargh.decToHex(15));
       System.out.println(blargh.decToBin(8));
    }*/

}
