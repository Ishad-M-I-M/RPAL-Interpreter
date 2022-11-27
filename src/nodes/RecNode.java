package nodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecNode extends InnerNode{
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
}
