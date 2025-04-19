public class Main {

    public static void main(String[] args){
        String rawInput = "x = 1 + 3";
        Lexer lexer = new Lexer(rawInput);

        Token token;
        do {
            token = lexer.nextToken();
            System.out.println(token);
        } while (token.tokenType != TokenType.EOF);
    }

}
