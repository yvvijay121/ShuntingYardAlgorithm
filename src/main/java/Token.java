public class Token {
    private final Object value;
    private final TokenType type;
    private int precedence;


    public Token(float value) {
        this.value = value;
        type = TokenType.NUMBER;
    }

    public Token(String function) {
        this.value = function;
        type = TokenType.FUNCTION;
        precedence = 5;
    }

    public Token(char operator) {
        this.value = operator;
        String operatorString = Character.toString(operator);
        if (isBracket(operatorString)) {
            if (operatorString.equals("(") || operatorString.equals("[") || operatorString.equals("{")) {
                type = TokenType.BRACKET_LEFT;
            } else {
                type = TokenType.BRACKET_RIGHT;
            }
        } else {
            type = TokenType.OPERATOR;
            switch (operator) {
                case '^' -> precedence = 4;
                case '/', '*' -> precedence = 3;
                case '+', '-' -> precedence = 2;
                default -> throw new IllegalArgumentException("that's not an operator chief: " + operator);
            }
        }
    }

    public static boolean isBracket(String i) {
        return i.equals("(") || i.equals(")") || i.equals("{") || i.equals("}") || i.equals("[") || i.equals("]");
    }

    public static boolean isNumeric(String string) {
        if (string == null) {
            return false;
        }
        try {
            float d = Float.parseFloat(string);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isOperator(String string) {
        return string.equals("*") || string.equals("/") || string.equals("+") || string.equals("-") || string.equals("^");
    }

    public static boolean isFunction(String string) {
        return string.equals("sin") || string.equals("cos") || string.equals("tan");
    }

    public int getPrecedence() {
        return precedence;
    }

    public Object getValue() {
        return value;
    }

    public TokenType getType() {
        return type;
    }

    public String toString() {
        return "Value: " + value.toString() + "\nType: " + type.toString() + "\nPrecedence: " + precedence;
    }
}
