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
    public Machine(ASTTree astTree){
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
    public List<Delta> getControlStructures(){
        List<Delta> controlStructures = new ArrayList<>();
        Queue<Node> toBeParsed = new ArrayDeque<>();
        toBeParsed.add(astTree.root);

        int tag = 0;
        int lambdaTag = 1;
        while (! toBeParsed.isEmpty()){
            Node curr = toBeParsed.poll();
            MachineControl controlStructure = new MachineControl();
            lambdaTag = flatten(lambdaTag, curr, controlStructure, toBeParsed);

            if (curr instanceof LambdaNode) lambdaTag++;

            controlStructures.add(new Delta(tag++, controlStructure));
        }

        return controlStructures;
    }

    /**
     * Traverse in depth first method
     * - Create a control structure for a Delta node with given tag
     * - If a Lambda node found add it to the `toBeParsed` queue
     * - Add the delta node to the `controlStructures` array
     *
     * @return tag after incrementing
     * */
    private int flatten(int tag, Node curr, MachineControl control, Queue<Node> toBeParsed){
        if (curr instanceof LeafNode){
            control.push(getElement(curr, tag));
        }
        else{

            if (curr instanceof LambdaNode){
                control.push(getElement(curr, tag));
                toBeParsed.add(((LambdaNode) curr).right);
            }
            else if (curr instanceof ArrowNode){
                control.push(getElement(curr, tag));
                tag = flatten(tag, ((ArrowNode) curr).expression, control, toBeParsed);
                toBeParsed.add(((ArrowNode) curr).trueNode);
                toBeParsed.add(((ArrowNode) curr).falseNode);
            }
            else{
                control.push(getElement(curr, tag));
                for (Node child:
                        ((InnerNode)curr).getChildren()) {
                    tag = flatten(tag, child, control, toBeParsed);
                    if (child instanceof LambdaNode) tag++;
                }
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
            case LambdaNode n ->  new Lambda(tag, getVariable(n.left, tag));
            case ArrowNode n -> new Beta();
            case UopNode n -> new Uop(n.name);
            case BopNode n -> new Bop(n.name);
            case TauNode n -> new Tor(n.getChildren().size());
            case IDNode n -> new Variable(n.name);
            case PrimitiveNode n -> new Primitive(n.value);
            case YNode n -> new Y();
            default -> throw new IllegalArgumentException("Unexpected value: " + node.name);
        };
    }

    /**
     * @return bounded variable for lambda
     * */
    private Element getVariable(Node node, int tag){
        if (node instanceof CommaNode){
            List<Variable> children = new ArrayList<>();
            for (Node child:
                 ((CommaNode) node).getChildren()) {
                children.add((Variable) getElement(child, tag));
            }
            return new Comma(children);
        }
        else return new Variable(node.name);
    }
}
