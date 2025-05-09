import lexer.Lexer;
import parser.ASTNode;
import parser.Parser;
import parser.ParserException;
import token.Token;
import token.TokenType;

public class Main {
    public static void main(String[] args) throws ParserException {
        String rawInput = "x = 5 + 3";
        Lexer lexer = new Lexer(rawInput);

        Token token;
        do {
            token = lexer.nextToken();
            System.out.println(token);
        } while (token.tokenType != TokenType.EOF);

        Parser parser = new Parser(new Lexer(rawInput));
        ASTNode tree = parser.parse();
        parser.print(tree);
    }
}