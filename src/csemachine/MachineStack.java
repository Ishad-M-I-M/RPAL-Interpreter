package csemachine;

import java.util.Stack;

import csemachine.elements.Element;

public class MachineStack {
    private Stack<Element> stack;

    protected MachineStack(){
        stack = new Stack<>();
    }

    /**
     * @return Element last element of the MachineStack
     * */
    protected Element pop(){
        return stack.pop();
    }

    /**
     * push to the MachineStack with applying the necessary changes.
     * */
    protected void push(Element element, MachineEnvironment currEnv){
        //TODO: apply the values from the environment
        // or tag the environment symbol with lambda
        stack.push(element);
    }


}
