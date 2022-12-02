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
            Object value = currEnv.findValue(((Variable) element).name);
            if ( value != null) {
                Primitive primitive = new Primitive(value);
                stack.push(primitive);
            }
//            else throw new IllegalArgumentException("Variable not reachable from current environment: "+((Variable) element).name);
            else stack.push(element);

        }
        else{
            stack.push(element);
        }
    }


}
