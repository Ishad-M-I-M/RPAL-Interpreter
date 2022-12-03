package csemachine;

import java.util.Stack;

import csemachine.elements.Element;
import csemachine.elements.Lambda;
import csemachine.elements.Primitive;
import csemachine.elements.Variable;

public class MachineStack {
    private final Stack<Element> stack;

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
        if(element instanceof Variable){
            Element value = currEnv.findValue(((Variable) element).name);
            if ( value != null) {
                stack.push(value);
            }
            else stack.push(element);

        }
        else if (element instanceof Lambda){
            if (((Lambda) element).environmentTag == null){
                ((Lambda) element).environmentTag = currEnv.getEnvironmentTag();
            }
            stack.push(element);
        }
        else{
            stack.push(element);
        }
    }


}
