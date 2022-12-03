package csemachine;

import csemachine.elements.Element;
import csemachine.elements.Environment;

/**
 * Class to maintain the environment of the stack
 * Stores data in a Tree structure
 * */
public class MachineEnvironment {
    private Node current;

    protected MachineEnvironment(){
        current = null;
    }

    /**
     * Add new node to the environment tree and move to it
     * */
    public void addNewEnvironment(Environment environment){
        this.current = new Node(environment, current);
    }


    /**
     * @return resolved variable value
     * */
    public Element findValue(String variableName){
        Element found = null;
        Node searchNode = current;
        while (found == null){
            found = searchNode.environment.getValue(variableName);
            if(searchNode.getParent() == null && found == null){
                break;
            }
            searchNode = searchNode.getParent();
        }
        return found;
    }

    /**
     * Inner class to represent Nodes in the tree
     * */
    class Node{
        private final Node parent;
        private final Environment environment;
        Node(Environment environment, Node parent){
            this.environment = environment;
            this.parent = parent;
        }

        Node getParent(){
            return this.parent;
        }
    }
}
