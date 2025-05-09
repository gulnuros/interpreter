package parser;

public class IdentifierNode implements ASTNode {
    final String value;

    IdentifierNode(String value){
        this.value = value;
    }
    @Override
    public String toString() {
        return "\tIdentifier: " + value;
    }
}
