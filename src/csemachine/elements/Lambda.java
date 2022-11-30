package csemachine.elements;

public class Lambda extends Element{
    public int environmentTag;
    public int controlTag;
    public Variable boundedVariable;

    public Lambda(int controlTag, Variable boundedVariable){
        this.controlTag = controlTag;
        this.boundedVariable = boundedVariable;
    }
}
