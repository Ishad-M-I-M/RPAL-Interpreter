package nodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecNode extends InnerNode implements Standardizable{
    public Node child;
    public RecNode() {
        super("rec");
    }

    @Override
    public void setChild(Node child) {
        if (this.child == null) this.child = child;
        else throw new IllegalStateException("Cannot assign more children");
    }

    public List<Node> getChildren(){
        return new ArrayList<Node>(Arrays.asList(child));
    }

    @Override
    public Node standardize() {
        if (child instanceof Standardizable) this.child = ((Standardizable) child).standardize();

        if(child instanceof EqualNode){
            LambdaNode newRightRight = new LambdaNode();
            newRightRight.left = ((EqualNode) child).left;
            newRightRight.right = ((EqualNode) child).right;

            GammaNode newRight = new GammaNode();
            newRight.left = new YNode();
            newRight.right = newRightRight;

            EqualNode newNode = new EqualNode();
            newNode.left = ((EqualNode) child).left;
            newNode.right = newRight;

            return newNode;
        }
        else throw new IllegalStateException("AST cannot be standardized");
    }
}
