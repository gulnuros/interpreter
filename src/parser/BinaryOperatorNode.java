package parser;

import token.TokenType;

public class BinaryOperatorNode implements ASTNode{
    final TokenType operator;
    final ASTNode left;
    final ASTNode right;

    BinaryOperatorNode(TokenType operator, ASTNode left, ASTNode right){
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        String opSymbol = switch (operator) {
            case OP_PLUS -> "+";
            case OP_MINUS -> "-";
            case OP_MULTIPLY -> "*";
            case OP_DIVIDE -> "/";
            default -> operator.name();
        };

        return "Operation: " + opSymbol
                + "\n\t" + left
                + "\n\t" + right;
    }
}
