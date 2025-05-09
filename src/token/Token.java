package token;

public class Token {
    public final TokenType tokenType;
    public final String value;

    public Token(TokenType tokenType, String value) {
        this.tokenType = tokenType;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Token{" + "type=" + tokenType + ", value='" + value + "'}";
    }
}
