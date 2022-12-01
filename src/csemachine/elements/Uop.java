package csemachine.elements;

public class Uop extends Element{
    private String operation;

    public Uop(String operation){
        this.operation = operation;
    }

    public Object apply(Object operand){
        if (operand instanceof  Integer){
            if (operation == "neg") return - (int) operand;
            else throw new IllegalArgumentException("Unsupported operand");
        }
        else if (operand instanceof Boolean){
            if (operation == "not") return ! (boolean) operand;
            else throw new IllegalArgumentException("Unsupported operand");
        }
        else throw new IllegalArgumentException("Unsupported operand");
    }
}
