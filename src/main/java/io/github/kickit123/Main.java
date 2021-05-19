package io.github.kickit123;

import io.github.kickit123.alg.Node;
import io.github.kickit123.alg.Parser;
import io.github.kickit123.alg.Token;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter equation: ");
        String input = scanner.nextLine();
        System.out.println("Input is: " + input);
        Token[] output = Parser.parse(input);
        System.out.println(Arrays.toString(output));
        Stack<Node<Token>> unassignedOutputs = new Stack<>();
        for (Token t : output) {
            switch (t.getType()) {
                case NUMBER, VARIABLE -> unassignedOutputs.push(new Node<>(t));
                case BRACKET_LEFT, BRACKET_RIGHT -> throw new NumberFormatException("shit went down");
                case OPERATOR, FUNCTION -> {
                    Node<Token> n = new Node<>(t);
                    if (unassignedOutputs.size() > 1) n.addChild(unassignedOutputs.pop());
                    if (unassignedOutputs.size() > 1) n.addChild(unassignedOutputs.pop());
                    unassignedOutputs.push(n);
                }
            }
        }
        for (Node<Token> n : unassignedOutputs) {
            System.out.println(n.toString());
        }
    }
}
