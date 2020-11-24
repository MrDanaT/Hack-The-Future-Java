package be.ap.ti.htf.Services;

import java.util.ArrayList;
import java.util.Arrays;
import java.math.BigInteger;

import org.springframework.stereotype.Service;
import com.mifmif.common.regex.*;

@Service
public class MazeCommandService {

    public String isAlphabetical(String s) {
        // length of the string
        int n = s.length();

        // create a character array
        // of the length of the string
        char c[] = new char[n];

        // assign the string
        // to character array
        for (int i = 0; i < n; i++) {
            c[i] = s.charAt(i);
        }

        // sort the character array
        Arrays.sort(c);

        // check if the character array
        // is equal to the string or not
        for (int i = 0; i < n; i++)
            if (c[i] != s.charAt(i))
                return "N";

        return "Y";
    }

    public String isDisarium(String s) {
        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            result += Math.pow(Integer.parseInt(s.charAt(i) + ""), i + 1);
        }

        return s.equals(result + "") ? "Y" : "N";
    }

    private String isPerfectNumber(int n) {
        int sum = 1;

        // Find all divisors and add them
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                if (i * i != n)
                    sum = sum + i + n / i;
                else
                    sum = sum + i;
            }
        }
        // If sum of divisors is equal to
        // n, then n is a perfect number
        return (sum == n && n != 1) ? "Y": "N";
    }

    public int nthPerfect(int n) {
        assert (n >= 1);
        int counter = 2, perfectCount = 0;
        while (true) {
            if (isPerfectNumber(counter).equals("Y")) {
                if (++perfectCount == n)
                    return counter;
            }
            counter++;
        }
    }
    
    public  String stringToEnglish(String text) {
        String newEnglish = ""; //sets string for newEnglish
        String selectedEnglish; //sets string for selectedEnglish
        String convertedEnglish; //sets string for convertedEnglish
        String[] morseChars = text.split(" ");
        for (int i = 0; i < morseChars.length; i++) {
            //Select the next Morse character
            selectedEnglish = morseChars[i];
            boolean endsWithWordSeparator = selectedEnglish.endsWith("|");
            if (endsWithWordSeparator) {
                selectedEnglish = selectedEnglish.substring(0, selectedEnglish.length() - 1);
            }
            // Convert the string
            convertedEnglish = decode(selectedEnglish);

            newEnglish = newEnglish + convertedEnglish;
            if (endsWithWordSeparator) {
                newEnglish = newEnglish + " ";
            }
        }

        return newEnglish;
    }

    public String decode(String toEncode) {
        String morse = toEncode;

        if (toEncode.equalsIgnoreCase(".-")) {
            morse = "a";
        }
        if (toEncode.equalsIgnoreCase("-...")) {
            morse = "b";
        }
        if (toEncode.equalsIgnoreCase("-.-.")) {
            morse = "c";
        }
        if (toEncode.equalsIgnoreCase("-..")) {
            morse = "d";
        }
        if (toEncode.equalsIgnoreCase(".")) {
            morse = "e";
        }
        if (toEncode.equalsIgnoreCase("..-.")) {
            morse = "f";
        }
        if (toEncode.equalsIgnoreCase("--.")) {
            morse = "g";
        }
        if (toEncode.equalsIgnoreCase("....")) {
            morse = "h";
        }
        if (toEncode.equalsIgnoreCase("..")) {
            morse = "i";
        }
        if (toEncode.equalsIgnoreCase(".---")) {
            morse = "j";
        }
        if (toEncode.equalsIgnoreCase("-.-")) {
            morse = "k";
        }
        if (toEncode.equalsIgnoreCase(".-..")) {
            morse = "l";
        }
        if (toEncode.equalsIgnoreCase("--")) {
            morse = "m";
        }
        if (toEncode.equalsIgnoreCase("-.")) {
            morse = "n";
        }
        if (toEncode.equalsIgnoreCase("---")) {
            morse = "o";
        }
        if (toEncode.equalsIgnoreCase(".--.")) {
            morse = "p";
        }
        if (toEncode.equalsIgnoreCase("--.-")) {
            morse = "q";
        }
        if (toEncode.equalsIgnoreCase(".-.")) {
            morse = "r";
        }
        if (toEncode.equalsIgnoreCase("...")) {
            morse = "s";
        }
        if (toEncode.equalsIgnoreCase("-")) {
            morse = "t";
        }
        if (toEncode.equalsIgnoreCase("..-")) {
            morse = "u";
        }
        if (toEncode.equalsIgnoreCase("...-")) {
            morse = "v";
        }
        if (toEncode.equalsIgnoreCase(".--")) {
            morse = "w";
        }
        if (toEncode.equalsIgnoreCase("-..-")) {
            morse = "x";
        }
        if (toEncode.equalsIgnoreCase("-.--")) {
            morse = "y";
        }
        if (toEncode.equalsIgnoreCase("--..")) {
            morse = "z";
        }
        if (toEncode.equalsIgnoreCase("-----")) {
            morse = "0";
        }
        if (toEncode.equalsIgnoreCase(".----")) {
            morse = "1";
        }
        if (toEncode.equalsIgnoreCase("..---")) {
            morse = "2";
        }
        if (toEncode.equalsIgnoreCase("...--")) {
            morse = "3";
        }
        if (toEncode.equalsIgnoreCase("....-")) {
            morse = "4";
        }
        if (toEncode.equalsIgnoreCase(".....")) {
            morse = "5";
        }
        if (toEncode.equalsIgnoreCase("-....")) {
            morse = "6";
        }
        if (toEncode.equalsIgnoreCase("--...")) {
            morse = "7";
        }
        if (toEncode.equalsIgnoreCase("---..")) {
            morse = "8";
        }
        if (toEncode.equalsIgnoreCase("----.")) {
            morse = "9";
        }
        if (toEncode.equalsIgnoreCase("|")) {
            morse = "";
        }

        return morse;
    }

    // ChallengeId: 2b4f1a83-5a71-4568-8e87-19be43d0648b
    // Provide a string that matches the regex
    public String stringMatchRegex(String regex){
        Generex generex = new Generex(regex);
        return generex.random();
    }

    // ChallengeId: 5ae1595a-b0a8-4b90-9c13-bba5ef7014a3
    // Reverse the following String
    public String reverseString(String s) {
        byte[] strAsByteArray = s.getBytes();

        byte[] result = new byte[strAsByteArray.length];

        // Store result in reverse order into the
        // result byte[]
        for (int i = 0; i < strAsByteArray.length; i++)
            result[i] = strAsByteArray[strAsByteArray.length - i - 1];

        return new String(result);
    }

    private int[] fibonacci = { 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181 };
    
    // ChallengeId: d80c0c45-a15d-4d3b-b3b4-c9ad5f2ef6bf
    // Find the missing numbers in the sequence. Return them as a comma-separated string
    public ArrayList<Integer> findMissingNumbers(String list) {
        ArrayList<Integer> numbers = new ArrayList();
        ArrayList<Integer> missing = new ArrayList();

        for (int i = 1; i < list.length() - 1; i++) {
            numbers.add(Integer.parseInt(list.charAt(i) + ""));
        }

        for (int i = 0; i < numbers.size(); i++) {
            int number = numbers.get(i % 20);
            if (numbers.get(i % 20) != fibonacci[i % 20]) {
                missing.add(number);
            }
        }
        return missing;
    }

    // ChallengeId: cdebbbe7-d963-463c-a801-fffe09df227d
    // Convert the following String to hexadecimal value (Use a space delimiter between values)
    public String stringToHex(String string) {
        StringBuilder buf = new StringBuilder(200);
        for (char ch : string.toCharArray()) {
            if (buf.length() > 0) {
                buf.append(' ');
            }
            buf.append(String.format("%04x", (int) ch));
        }
        return buf.toString();
    }

    // ChallengeId: 78191545-0997-4740-9ff4-e3d65b578ea0
    // Decode the following String. It uses a quite common encoding, find out which!
    public String decodeBase64(String s) {
        return new String(Base64.getDecoder().decode(s));
    }
}
