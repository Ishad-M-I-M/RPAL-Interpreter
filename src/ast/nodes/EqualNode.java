package ast.nodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EqualNode extends InnerNode{
    public Node left;
    public Node right;
    public EqualNode() {
        super("=");
    }

    @Override
    public void setChild(Node child) {
        if (left == null) this.left = child;
        else if (right == null) this.right = child;
        else throw new IllegalStateException("Cannot assign more children to : "+ this.name);
    }

    public List<Node> getChildren(){
        return new ArrayList<Node>(Arrays.asList(left, right));
    }

    @Override
    public void removeChildren() {
        left = null;
        right = null;
    }
}
