package csemachine.elements;

public class Lambda extends Element{
    public int environmentTag;
    public int controlTag;
    public Element boundedVariable;

    public Lambda(int controlTag, Element boundedVariable){
        this.controlTag = controlTag;
        if (boundedVariable instanceof Variable || boundedVariable instanceof Comma)
            this.boundedVariable = boundedVariable;
        else throw new IllegalArgumentException("Unsupported variable :"+boundedVariable.toString());
    }
}
