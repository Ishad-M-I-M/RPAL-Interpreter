package ast.nodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UopNode extends InnerNode{
    public Node child;
    public UopNode(String name) {
        super(name);
    }

    @Override
    public void setChild(Node child) {
        if(child == null) this.child = child;
        else throw new IllegalStateException("Cannot assign more children to : "+ this.name);
    }

    @Override
    public List<Node> getChildren() {
        return new ArrayList<>(Collections.singletonList(child));
    }

    @Override
    public void removeChildren() {
        child = null;
    }

}
