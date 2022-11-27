package nodes;

import java.util.ArrayList;
import java.util.List;

public class FcnFormNode extends InnerNode{
    public IDNode functionName;
    public List<Node> other = new ArrayList<>();
    public FcnFormNode() {
        super("function_form");
    }


    @Override
    public void setChild(Node child) {
        if (functionName == null) this.functionName = (IDNode) child;
        else other.add(child);
    }

    @Override
    public List<Node> getChildren() {
        List<Node> children = new ArrayList<>();
        children.add(functionName);
        children.addAll(other);
        return children;
    }
}
