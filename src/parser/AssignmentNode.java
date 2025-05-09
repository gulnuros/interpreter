package parser;

public class AssignmentNode implements ASTNode {
    String identifier;
    ASTNode expression;
    public AssignmentNode(String identifier, ASTNode expression) {
        this.identifier = identifier;
        this.expression = expression;
    }
    @Override
    public String toString() {
        return "Assignment: = "
                + "\n\t Identifier: " + identifier
                + "\n\t " + expression;
    }
}
