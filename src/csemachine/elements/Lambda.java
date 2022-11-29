package csemachine.elements;

public class Lambda extends Element{
    public int environmentTag;
    public int controlTag;
    public Name boundedVariable;

    Lambda(int controlTag, Name boundedVariable){
        this.controlTag = controlTag;
        this.boundedVariable = boundedVariable;
    }
}
