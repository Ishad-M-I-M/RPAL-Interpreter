package csemachine;

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
     * move to parent Environment
     * */
    public void removeEnvironment(){
        this.current = current.getParent();
    }

    /**
     * @return resolved variable value
     * */
    public Object findValue(String variableName){
        Object found = null;
        Node searchNode = current;
        while (found == null){
            found = searchNode.environment.getValue(variableName);
            if(searchNode.getParent() == null && found == null){
                throw new IllegalArgumentException("Variable in not reachable from current environment :"+variableName);
            }
        }
        return found;
    }

    /**
     * Inner class to represent Nodes in the tree
     * */
    class Node{
        private Node parent;
        private Environment environment;
        Node(Environment environment, Node parent){
            this.environment = environment;
            this.parent = parent;
        }

        Node getParent(){
            return this.parent;
        }
    }
}
