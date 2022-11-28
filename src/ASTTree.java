import nodes.*;

import java.util.ArrayList;
import java.util.List;

public class ASTTree {
    public Node root;
    public ASTTree(Node root){
        this.root = root;
    }

    /**
     * Print the tree by inorder traversing
     * */
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

    /**
     * Standardize the tree
     * */
    public Node standardize(){
        if (root instanceof InnerNode){
            List<Node> nodes = new ArrayList<>();
            for (Node child:
                    ((InnerNode)root).getChildren()) {
                nodes.add(standardize(child));
            }

            ((InnerNode) root).removeChildren();
            for (Node newNode:
                 nodes) {
                ((InnerNode) root).setChild(newNode);
            }

        }
        if (root instanceof Standardizable) this.root = ((Standardizable) root).standardize();
        return root;
    }

    public Node standardize(Node node){
        if (node instanceof InnerNode){
            for (Node child:
                    ((InnerNode)node).getChildren()) {
                standardize(child);
            }
        }
        if (node instanceof Standardizable) return  ((Standardizable) node).standardize();
        else return node;
    }

}
