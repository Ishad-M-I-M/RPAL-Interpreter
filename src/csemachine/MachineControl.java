package csemachine;

import csemachine.elements.Element;

import java.util.Stack;

public class MachineControl {
    private Stack<Element> control;

    protected MachineControl(){
        control = new Stack<>();
    }

    /**
     * @return Element at the top of the MachineControl
     * */
    protected Element pop(){
        return control.pop();
    }

    /**
     * Push Element to the control
     * */
    protected void push(Element element){
        control.push(element);
    }
}
