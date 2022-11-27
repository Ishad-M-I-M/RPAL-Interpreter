import nodes.*;

public class ASTTree {
    public Node root;
    public ASTTree(Node root){
        this.root = root;
    }

    public void traverse(){
        if (root instanceof LeafNode){
            System.out.println(root.name);
        }
        else{
            System.out.println(root.name);
            for (Node child:
                    ((InnerNode)root).getChildren()) {
                traverse(child, ".");
            }
        }

    }

    public void traverse(Node root, String prefix){
        if (root instanceof LeafNode){
            System.out.println(prefix+root.name);
        }
        else{
            System.out.println(prefix+root.name);
            for (Node child:
                    ((InnerNode)root).getChildren()) {
                traverse(child, prefix+".");
            }
        }
    }

}
