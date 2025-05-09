package parser;

public class NumberNode implements ASTNode{
    final String value;

    NumberNode(String value){
        this.value=value;
    }
    @Override
    public String toString() {
        return "\tNumber: " + value;
    }
}
