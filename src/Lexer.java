

public class Lexer {
    private final String input;
    private int position;

    public Lexer(String input){
        this.input = input;
        this.position = 0;
    }

    public Token nextToken(){
        // skip whitespace
        while(position < input.length && Character.isWhiteSpace(input.charAt(position))){
            position++;
        }
        char current = input.charAt(position);

        if (Character.isDigit(current)) {
            StringBuilder number = new StringBuilder();
            while (position < input.length() && Character.isDigit(input.charAt(position))) {
                number.append(input.charAt(position));
                position++;
            }
            return new Token(TokenType.NUMBER, number.toString());
        }

        if (current == '+') {
            position++;
            return new Token(TokenType.PLUS, "+");
        }

        if (Character.isLetter(current)) {
            StringBuilder identifier = new StringBuilder();
            while (position < input.length() && Character.isLetterOrDigit(input.charAt(position))) {
                identifier.append(input.charAt(position));
                position++;
            }
            return new Token(TokenType.IDENTIFIER, identifier.toString());
        }

        throw new RuntimeException("Unexpected character: " + current);


    }
}