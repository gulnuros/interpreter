package lexer;

import token.Token;
import token.TokenType;

import java.util.HashMap;
import java.util.Map;

public class Lexer {
    private static final char EOF_CHAR = '\0';
    private static final Map<String, TokenType> KEYWORDS = new HashMap<>();

    static {
        KEYWORDS.put("if", TokenType.IF);
        KEYWORDS.put("else", TokenType.ELSE);
        KEYWORDS.put("while", TokenType.WHILE);
        KEYWORDS.put("print", TokenType.PRINT);
    }

    private final String input;
    private int position;
    private char currentChar;

    public Lexer(String input) {
        this.input = input;
        this.position = 0;
        this.currentChar = input == null || input.isEmpty() ? EOF_CHAR : input.charAt(position);
    }

    public Token nextToken() {
        while (currentChar != EOF_CHAR) {
            if (Character.isWhitespace(currentChar)) {
                skipWhiteSpace();
            }

            if (Character.isDigit(currentChar)) {
                StringBuilder number = new StringBuilder();
                while (Character.isDigit(currentChar)) {
                    number.append(currentChar);
                    advance();
                }
                return new Token(TokenType.NUMBER, number.toString());
            }
            if (Character.isLetter(currentChar)) {
                StringBuilder identifier = new StringBuilder();

                while (Character.isLetterOrDigit(currentChar)) {
                    identifier.append(currentChar);
                    advance();
                }
                String value = identifier.toString();
                TokenType type = KEYWORDS.getOrDefault(value, TokenType.IDENTIFIER);
                return new Token(type, value);
            }

            if (position >= input.length()) {
                return new Token(TokenType.EOF, "");
            }
            switch (currentChar) {
                case '=':
                    advance();
                    return new Token(TokenType.ASSIGNMENT, "=");
                case ';':
                    advance();
                    return new Token(TokenType.SEMICOLON, ";");
                case '+':
                    advance();
                    return new Token(TokenType.OP_PLUS, "+");
                case '-':
                    advance();
                    return new Token(TokenType.OP_MINUS, "-");
                case '*':
                    advance();
                    return new Token(TokenType.OP_MULTIPLY, "*");
                case '/':
                    advance();
                    return new Token(TokenType.OP_DIVIDE, "/");
                case '(':
                    advance();
                    return new Token(TokenType.OPEN_PAREN, "(");
                case ')':
                    advance();
                    return new Token(TokenType.CL_PAREN, ")");
                case '{':
                    advance();
                    return new Token(TokenType.OPEN_BRACE, "{");
                case '}':
                    advance();
                    return new Token(TokenType.CL_BRACE, "}");
                case '[':
                    advance();
                    return new Token(TokenType.OPEN_BRACKET, "[");
                case ']':
                    advance();
                    return new Token(TokenType.CL_BRACKET, "]");
                case '>':
                    if (peek() == '=') {
                        advance();
                        advance();
                        return new Token(TokenType.GREATER_OR_EQUAL, ">=");
                    } else {
                        advance();
                        return new Token(TokenType.GREATER_THAN, ">");
                    }
                case '<':
                    if (peek() == '=') {
                        advance();
                        advance();
                        return new Token(TokenType.LESS_OR_EQUAL, "<=");
                    } else {
                        advance();
                        return new Token(TokenType.LESS_THAN, "<");
                    }
                case '!':
                    if (peek() == '=') {
                        advance();
                        advance();
                        return new Token(TokenType.NOT_EQUAL, "!=");
                    } else {
                        advance();
                        throw new RuntimeException("Unexpected character: " + currentChar);
                    }
                default:
                    throw new RuntimeException("Unexpected character: " + currentChar);
            }


        }
        return new Token(TokenType.EOF, "");

    }

    public void skipWhiteSpace() {
        while (Character.isWhitespace(currentChar)) {
            advance();
        }
    }

    public void advance() {
        position++;
        currentChar = position < input.length() ? input.charAt(position) : EOF_CHAR;
    }

    public char peek() {
        int nextPos = position + 1;
        return nextPos < input.length() ? input.charAt(nextPos) : EOF_CHAR;
    }
}
