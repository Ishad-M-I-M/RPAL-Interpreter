package nodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LetNode extends InnerNode implements Standardizable{
    public Node left;
    public Node right;
    public LetNode() {
        super("let");
    }

    @Override
    public void setChild(Node child) {
        if (left == null) this.left = child;
        else if (right == null) this.right = child;
        else throw new IllegalStateException("Cannot assign more children");
    }

    public List<Node> getChildren(){
        return new ArrayList<Node>(Arrays.asList(left, right));
    }

    @Override
    public Node standardize() {
        LambdaNode newLeft = new LambdaNode();
        if (left instanceof Standardizable) this.left = ((Standardizable) left).standardize();

        if (left instanceof EqualNode){
            newLeft.left = ((EqualNode) left).left;
            newLeft.right = right;
            this.right = ((EqualNode) left).right;
            this.left = newLeft;
            return this;
        }
        else throw new IllegalStateException("AST cannot be standardized");
    }
}
