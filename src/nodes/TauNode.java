package nodes;

import java.util.ArrayList;
import java.util.List;

public class TauNode extends InnerNode{
    public List<Node> children = new ArrayList<>();
    public TauNode() {
        super("tau");
    }

    @Override
    public void setChild(Node child) {
        children.add(child);
    }

    @Override
    public List<Node> getChildren() {
        return children;
    }
}
