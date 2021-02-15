import java.util.ArrayList;
import java.util.Arrays;

public class Tokenizer {
    static public final String WITH_DELIMITER = "((?<=%1$s)|(?=%1$s))";

    public static ArrayList<Token> parse(String input) {
        String[] inputArray = input.split(String.format(WITH_DELIMITER, "\\*|/|\\+|\\-|\\^|\\(|\\)|\\[|\\]|\\{|\\}"));
        ArrayList<Token> tokenList = new ArrayList<>();
        System.out.println("Spliced String: " + Arrays.toString(inputArray));
        for (String i : inputArray) {
            if (Token.isNumeric(i)) {
                tokenList.add(new Token(Float.parseFloat(i)));
            } else if (Token.isOperator(i)) {
                tokenList.add(new Token(i.charAt(0)));
            } else if (Token.isBracket(i)) {
                tokenList.add(new Token(i.charAt(0)));
            } else if (Token.isFunction(i)) {
                tokenList.add(new Token(i));
            }
        }
        return tokenList;
    }
}
