package csemachine;

import java.util.Stack;

import csemachine.elements.Element;
import csemachine.elements.Primitive;
import csemachine.elements.Variable;

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
        if(element instanceof Variable){
            Element value = currEnv.findValue(((Variable) element).name);
            if ( value != null) {
                stack.push(value);
            }
//            else throw new IllegalArgumentException("Variable not reachable from current environment: "+((Variable) element).name);
            else stack.push(element);

        }
        else{
            stack.push(element);
        }
    }


}
