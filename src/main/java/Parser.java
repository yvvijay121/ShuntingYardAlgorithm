import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Parser {
    public static ArrayList<String> parse(String input) {
        ArrayList<Token> tokenList = Tokenizer.parse(input.replaceAll("\\s+", ""));
        Queue<Token> outputQuene = new LinkedList<>();
        Stack<Token> operatorStack = new Stack<>();
        for (Token t : tokenList) {
            System.out.println("Value : " + t.getValue());
            if (t.getType().equals(TokenType.NUMBER) || t.getType().equals(TokenType.VARIABLE)) {
                outputQuene.add(t);
            } else if (t.getType().equals(TokenType.FUNCTION)) {
                operatorStack.add(t);
            } else if (t.getType().equals(TokenType.OPERATOR)) {
                while ((operatorStack.size() > 0) && ((operatorStack.peek().getType().equals(TokenType.OPERATOR) &&
                        (operatorStack.peek().getPrecedence() >= t.getPrecedence() && t.getAssociativity().equals(Associativity.LEFT))) ||
                        ((operatorStack.peek().getPrecedence() > t.getPrecedence() && t.getAssociativity().equals(Associativity.RIGHT))) &&
                                !operatorStack.peek().getType().equals(TokenType.BRACKET_LEFT))) {
                    outputQuene.add(operatorStack.pop());
                }
                operatorStack.push(t);
            } else if (t.getType().equals(TokenType.BRACKET_LEFT)) {
                operatorStack.push(t);
            } else if (t.getType().equals(TokenType.BRACKET_RIGHT)) {
                if (operatorStack.size() > 0) {
                    while (!operatorStack.peek().getType().equals(TokenType.BRACKET_LEFT)) {
                        outputQuene.add(operatorStack.pop());
                    }
                    if ((operatorStack.size() > 0) && (operatorStack.peek().getType().equals(TokenType.BRACKET_LEFT))) {
                        operatorStack.pop();
                    }
                    if ((operatorStack.size() > 0) && (operatorStack.peek().getType().equals(TokenType.FUNCTION))) {
                        outputQuene.add(operatorStack.pop());
                    }
                }
            }
        }

        while (operatorStack.size() > 0) {
            outputQuene.add(operatorStack.pop());
        }

        ArrayList<String> outputArrayList = new ArrayList<>();

        for (Token t : outputQuene) {
            switch (t.getType()) {
                case NUMBER -> outputArrayList.add(Float.toString((float) t.getValue()));
                case OPERATOR -> outputArrayList.add(String.valueOf((char) t.getValue()));
                case BRACKET_LEFT -> outputArrayList.add("(");
                case BRACKET_RIGHT -> outputArrayList.add(")");
                case FUNCTION, VARIABLE -> outputArrayList.add((String) t.getValue());
            }
        }

        return outputArrayList;
    }
}
