package nodes;

import java.util.ArrayList;
import java.util.List;

public class AndNode extends InnerNode{

    List<Node> children = new ArrayList<>();

    public AndNode() {
        super("and");
    }

    @Override
    public void setChild(Node child) {
        if(child instanceof EqualNode) children.add((EqualNode) child);
        else throw new IllegalArgumentException("Children of an AndNode should be EqualNodes");
    }

    @Override
    public List<Node> getChildren() {
        return children;
    }
}
