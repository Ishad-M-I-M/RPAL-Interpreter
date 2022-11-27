package nodes;

import java.util.ArrayList;
import java.util.List;

public class CommaNode extends InnerNode{
    public List<Node> children = new ArrayList<>();
    public CommaNode() {
        super(",");
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
