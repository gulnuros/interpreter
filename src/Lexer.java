

public class Lexer {
    private final String input;
    private int position;

    public Lexer(String input){
        this.input = input;
    }

    public Token nextToken(){
        // skip whitespace
        while(position < input.length && Character.isWhiteSpace(input.charAt(position))){
            position++;
        }


    }
}