package csemachine;

import java.util.*;

import ast.ASTTree;
import ast.nodes.*;

import csemachine.elements.*;

/**
 * Class to represent the CSE Machine
 * */
public class Machine {
    private ASTTree astTree;

    /**
     * Input need to be a Standardized tree
     * */
    Machine(ASTTree astTree){
        this.astTree = astTree;
    }

    /**
     * @return result of the expression in AST
     * */
    public Object evaluate(){
        //TODO : evaluate the expression using 13 CSE Machine rules
        return null;
    }

    /**
     * @return flatten control structure as list of Deltas
     * */
    private List<Delta> getControlStructures(){
        List<Delta> controlStructures = new ArrayList<>();
        Queue<Node> toBeParsed = new ArrayDeque<>();
        toBeParsed.add(astTree.root);

        int tag = 0;
        while (! toBeParsed.isEmpty()){
            Node curr = toBeParsed.poll();

        }

        return null;
    }

    /**
     * Traverse in depth first method
     * - Create a control structure for a Delta node with given tag
     * - If a Lambda node found add it to the `toBeParsed` queue
     * - Add the delta node to the `controlStructures` array
     *
     * @return tag after incrementing
     * */
    private int flatten(int tag, Node curr, MachineControl control, List<Delta> controlStructures, Queue<Node> toBeParsed){
        if (curr instanceof LeafNode){

        }
        else{
            System.out.println(curr.name);
            for (Node child:
                    ((InnerNode)curr).getChildren()) {

            }
        }

        return tag;
    }

    /**
     * @return corresponding element for the Node
     * */
    private Element getElement(Node node, int tag) throws IllegalArgumentException{
        return switch (node){
            case GammaNode n -> new Gamma();
            case LambdaNode n -> (n.left instanceof IDNode)?new Lambda(tag, new Variable(n.left.name)): null;
            default -> throw new IllegalArgumentException("Unexpected value: " + node.name);
        };
    }
}
