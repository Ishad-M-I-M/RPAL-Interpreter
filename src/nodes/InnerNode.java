package nodes;

import java.util.List;

public abstract class InnerNode extends Node {

    public InnerNode(String name) {
        super(name);
    }

    public abstract void setChild(Node child);

    public abstract List<Node> getChildren();
}
