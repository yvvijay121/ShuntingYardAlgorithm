package io.github.kickit123.alg;

public class Token {
    public final static Object[][] functions = {
            {"sin", 1},
            {"cos", 1},
            {"tan", 1},
            {"csc", 1},
            {"sec", 1},
            {"cot", 1},
            {"asin", 1},
            {"acos", 1},
            {"atan", 1},
            {"acsc", 1},
            {"asec", 1},
            {"acot", 1},
            {"sinh", 1},
            {"cosh", 1},
            {"tanh", 1},
            {"sech", 1},
            {"csch", 1},
            {"coth", 1},
            {"sqrt", 1},
            {"pow", 2},
            {"sin", 1},
            {"exp", 2},
            {"ln", 1},
            {"log", 2},
            {"min", 2},
            {"max", 2},
            {"sum", 4},
            {"lim", 3},
            {"derivative", 2},
            {"defderivative", 3},
            {"integral", 2},
            {"defderivative", 4},
            {"abs", 1}
    };

    private final Object value;
    private final TokenType type;
    private final Associativity assoc;
    private int precedence;

    public Token(float value) {
        this.value = value;
        type = TokenType.NUMBER;
        assoc = Associativity.N_A;
    }

    public Token(String string) {
        if (Token.isFunction(string)) {
            this.value = string;
            type = TokenType.FUNCTION;
            precedence = 5;
            assoc = Associativity.RIGHT;
        } else if (Token.isVariable(string)) {
            this.value = string;
            type = TokenType.VARIABLE;
            assoc = Associativity.N_A;
        } else {
            throw new IllegalArgumentException("something went wrong.");
        }
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
            assoc = Associativity.N_A;
        } else {
            type = TokenType.OPERATOR;
            switch (operator) {
                case '^' -> {
                    precedence = 4;
                    assoc = Associativity.RIGHT;
                }
                case '/', '*' -> {
                    precedence = 3;
                    assoc = Associativity.LEFT;
                }
                case '+', '-' -> {
                    precedence = 2;
                    assoc = Associativity.LEFT;
                }
                case ',' -> {
                    precedence = 1;
                    assoc = Associativity.LEFT;
                }
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
            Float.parseFloat(string);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isOperator(String string) {
        return string.equals("*") || string.equals("/") || string.equals("+") || string.equals("-") || string.equals("^") || string.equals(",");
    }

    public static boolean isFunction(String string) {
        return string.equals("sin") || string.equals("cos") || string.equals("pow");
    }

    public static boolean isVariable(String string) {
        return Character.getType(string.charAt(0)) == Character.LOWERCASE_LETTER;
    }

    public Associativity getAssociativity() {
        return assoc;
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
        //return "Value: " + value.toString() + "\nType: " + type.toString() + "\nPrecedence: " + precedence;
        return "Value: " + value.toString();
    }
}
