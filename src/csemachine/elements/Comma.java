package csemachine.elements;

import java.util.List;

public class Comma extends Element{
    public List<Variable> children;

    public Comma(List<Variable> children){
        this.children = children;
    }
}
