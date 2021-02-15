import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Parser {
    public static Object[] parse(String input) {
        ArrayList<Token> tokenList = Tokenizer.parse(input.replaceAll("\\s", ""));
        Queue<Token> outputQuene = new LinkedList<>();
        Stack<Token> operatorStack = new Stack<>();
        for (Token t : tokenList) {
            if (t.getType().equals(TokenType.NUMBER)) {
                outputQuene.add(t);
            } else if (t.getType().equals(TokenType.FUNCTION)) {
                outputQuene.add(t);
            } else if (t.getType().equals(TokenType.OPERATOR)) {

                while ((operatorStack.size() > 0) && ((operatorStack.peek().getType().equals(TokenType.OPERATOR) &&
                        (operatorStack.peek().getPrecedence() > t.getPrecedence())) ||
                        ((operatorStack.peek().getPrecedence() == t.getPrecedence()) &&
                                !operatorStack.peek().getType().equals(TokenType.BRACKET_LEFT)))) {
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

        for (int i = 0; i < operatorStack.size(); i++) {
            outputQuene.add(operatorStack.pop());
        }

        ArrayList<String> outputArray = new ArrayList<>();
        for (Token t : outputQuene) {
            switch (t.getType()) {
                case NUMBER -> outputArray.add(Float.toString((float) t.getValue()));
                case OPERATOR -> outputArray.add(Character.toString((char) t.getValue()));
                case BRACKET_LEFT -> outputArray.add("(");
                case BRACKET_RIGHT -> outputArray.add(")");
                case FUNCTION -> outputArray.add((String) t.getValue());
            }
        }
        return outputArray.toArray();
    }
}
