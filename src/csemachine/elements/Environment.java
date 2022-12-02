package csemachine.elements;

import java.util.HashMap;
import java.util.Map;

public class Environment extends Element{
    public int tag;
    private Map<String, Object> assignments;
    public Environment(int tag){
        this.tag = tag;
        assignments = new HashMap<>();
    }

    /**
     * @return assignment value for the variable
     * If not found return null
     * */
    public Object getValue(String variableName){
        return assignments.getOrDefault(variableName, null);
    }
}
