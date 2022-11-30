package csemachine.elements;

import csemachine.MachineControl;

public class Delta extends Element{
    public int tag;
    public MachineControl control;

    public Delta(int tag, MachineControl controlStructure){
        this.tag = tag;
        this.control = controlStructure;
    }
}
