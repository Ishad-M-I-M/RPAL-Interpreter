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
    private MachineControl control;
    private MachineStack stack;
    private MachineEnvironment environment;

    private final ArrayList<String> definedFunctions = new ArrayList<>(Arrays.asList("Print", "Conc", "Stern", "Stem"));

    /**
     * Input need to be a Standardized tree
     * */
    public Machine(ASTTree astTree){
        this.astTree = astTree;
        control = new MachineControl();
        stack = new MachineStack();
        environment = new MachineEnvironment();
    }

    /**
     * @return result of the expression in AST
     * */
    public Object evaluate(){
        Map<Integer, Queue<Element>> controlStructures = controlStructureArrayToMap(getControlStructures());

        int envTag = 0;
        Environment initialEnv = new Environment(envTag++);
        control.push(initialEnv);

        pushToControl(controlStructures.get(0));

        while (!control.isEmpty()){
            Element element = control.pop();

            // Applying CSE rules accordingly

            // CSE Rule 1
            if (element instanceof Variable || element instanceof Primitive){
                stack.push(element, environment);
            }

            // CSE Rule 2
            else if(element instanceof Lambda){
                stack.push(element, environment);
            }
            // CSE Rule 3
            else if (element instanceof Gamma) {
                Element operator = stack.pop();
                if(operator instanceof Variable){
                    // handling defined functions
                    String functionName = (String) ((Variable) operator).name;
                    if (definedFunctions.contains(functionName)) functionCall(functionName);
                    //TODO: handle function calls
                }
                else{
                    throw new IllegalArgumentException("Unsupported operator");
                }
            }

            // CSE Rule 4

            // CSE Rule 5


            // CSE Rule 6
            else if(element instanceof Bop){
                Element op1 = stack.pop();
                Element op2 = stack.pop();

                Object operand1, operand2;
                if (op1 instanceof Variable) operand1 = ((Variable) op1).value;
                else if (op1 instanceof Primitive) operand1 = ((Primitive) op1).value;
                else throw new IllegalArgumentException("Unsupported operand");

                if (op2 instanceof Variable) operand2 = ((Variable) op2).value;
                else if (op2 instanceof Primitive) operand2 = ((Primitive) op2).value;
                else throw new IllegalArgumentException("Unsupported operand");

                Element result = new Primitive(((Bop)element).apply(operand1, operand2));
                stack.push(result, environment);
            }

            // CSE Rule 7
            else if(element instanceof Uop){
                Element op1 = stack.pop();

                Object operand1;
                if (op1 instanceof Variable) operand1 = ((Variable) op1).value;
                else if (op1 instanceof Primitive) operand1 = ((Primitive) op1).value;
                else throw new IllegalArgumentException("Unsupported operand");

                Element result = new Primitive(((Uop)element).apply(operand1));
                stack.push(result, environment);
            }

            // CSE Rule 8

            // CSE Rule 9

            // CSE Rule 10

            // CSE Rule 10

            // CSE Rule 11

            // CSE Rule 12

            // CSE Rule 13

        }

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

    private Map<Integer, Queue<Element>> controlStructureArrayToMap(List<Delta> array){
        Map<Integer, Queue<Element>> map = new HashMap<>();
        for (Delta d:
             array) {
            ArrayDeque<Element> elements = new ArrayDeque<>();
            while (!d.control.isEmpty()){
                elements.addFirst(d.control.pop());
            }
            map.put(d.tag, elements);
        }
        return map;
    }

    /**
     * push control structure to the control
     * */
    private void pushToControl(Queue<Element> elements){
        while (! elements.isEmpty()){
            control.push(elements.poll());
        }
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

    /**
     * Apply defined Functions
     * */
    private void functionCall(String functionName){
        if (functionName.equals("Print")){
            Element element = stack.pop();
            if (element instanceof Primitive)
                System.out.println(((Primitive) element).value);
            else throw new IllegalArgumentException("Operation is not applicable");
        }
        //TODO: implement other defined functions
        else{
            throw new IllegalArgumentException("Not a valid functions");
        }
    }
}
