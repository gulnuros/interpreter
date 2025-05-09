package parser;

import lexer.Lexer;
import token.Token;
import token.TokenType;

import java.text.ParseException;

public class Parser {
    private final Lexer lexer;
    private Token currentToken;

    AssignmentNode assignmentNode;
    BinaryOperatorNode binaryOperatorNode;
    NumberNode numberNode;
    IdentifierNode identifierNode;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        this.currentToken = lexer.nextToken();
    }

    public ASTNode parse() throws ParserException {
        return parseStatement();

    }
    // <statement> ::= <assignment> | <expression>
    private ASTNode parseStatement() throws ParserException {
        if (currentToken.tokenType == TokenType.IDENTIFIER) {
            String identifier = currentToken.value;
            consume(TokenType.IDENTIFIER);
            if (currentToken.tokenType == TokenType.ASSIGNMENT) {
                consume(TokenType.ASSIGNMENT);
                ASTNode expression = parseExpression();
                return new AssignmentNode(identifier, expression);
            }
        }
        return parseExpression();
    }


    private ASTNode parseExpression() throws ParserException{
        ASTNode leftNode = parseTerm();
        if(currentToken.tokenType == TokenType.EOF){
            return leftNode;
        }
        if(currentToken.tokenType == TokenType.OP_PLUS || currentToken.tokenType == TokenType.OP_MINUS){
            TokenType operator = currentToken.tokenType;
            consume(currentToken.tokenType);
            ASTNode rightNode = parseExpression();
            return new BinaryOperatorNode(operator, leftNode, rightNode);
        }

        return leftNode;
    }

    private ASTNode parseTerm() throws ParserException{
        ASTNode leftNode = parseFactor();
        if(currentToken.tokenType == TokenType.EOF){
            return leftNode;
        }

        if(currentToken.tokenType == TokenType.OP_MULTIPLY || currentToken.tokenType == TokenType.OP_DIVIDE){
            TokenType operator = currentToken.tokenType;
            ASTNode rightNode = parseTerm();
            consume(currentToken.tokenType);
            return new BinaryOperatorNode(operator, leftNode, rightNode);
        }
        return leftNode;
    }
    private ASTNode parseFactor() throws ParserException {
        if(currentToken.tokenType == TokenType.NUMBER){
            String numberValue = currentToken.value;
            consume(TokenType.NUMBER);
            return new NumberNode(numberValue);
        }
        if (currentToken.tokenType == TokenType.IDENTIFIER){
            String identifierValue = currentToken.value;
            consume(TokenType.IDENTIFIER);
            return new IdentifierNode(identifierValue);
        }
        throw new ParserException("Unsupported token type" + currentToken.value);
    }

    private void consume(TokenType tokenType) throws ParserException {
        if(currentToken.tokenType == tokenType){
            currentToken = lexer.nextToken();
        } else {
            throw new ParserException("Unsupported token type" + currentToken.value);
        }
    }

    public void print(ASTNode node){
        System.out.println(node.toString());
    }
}

