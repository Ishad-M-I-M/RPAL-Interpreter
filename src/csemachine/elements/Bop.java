package csemachine.elements;

public class Bop extends Element{

    private final String operation;
    public Bop(String operation){
        this.operation = operation;
    }

    public Object apply(Object operand1, Object operand2){
        if (operand1 instanceof Integer && operand2 instanceof Integer ){
            int op1 = (int) operand1;
            int op2 = (int) operand2;
            return switch (this.operation){
                // Mathematical operations
                case "+" -> op1 + op2;
                case "-" -> op1 - op2;
                case "*" -> op1 * op2;
                case "/" -> op1 / op2;
                case "**" -> Math.pow (op1, op2);

                // Boolean operations
                case "eq" -> op1 == op2;
                case "ls" -> op1 < op2;
                case "le" -> op1 <= op2;
                case "gr" -> op1 > op2;
                case "ge" -> op1 >= op2;
                case "ne" -> op1 != op2;
                default -> throw new IllegalArgumentException("Unsupported operands");
            };
        }
        else if (operand1 instanceof String op1 && operand2 instanceof String op2){
            return switch (this.operation){
                // Boolean operations
                case "eq" -> op1.equals(op2);
                case "ls" -> op1.compareTo(op2) < 0;
                case "le" -> op1.compareTo(op2) <= 0;
                case "gr" -> op1.compareTo(op2) > 0;
                case "ge" -> op1.compareTo(op2) >= 0;
                case "ne" -> op1.compareTo(op2) != 0;
                default -> throw new IllegalArgumentException("Unsupported operands");
            };
        }
        else if (operand1 instanceof Boolean && operand2 instanceof Boolean){
            boolean op1 = (boolean) operand1;
            boolean op2 = (boolean) operand2;
            return switch (this.operation){
                case "or" -> op1 || op2;
                case "&" -> op1 && op2;
                default -> throw new IllegalArgumentException("Unsupported operands");
            };
        }
        else {
            throw new IllegalArgumentException("Provided two arguments are in different types");
        }

    }
}
