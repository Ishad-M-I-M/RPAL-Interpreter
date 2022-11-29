package csemachine;

import ast.ASTTree;

/**
 * Class to represent the CSE Machine
 * */
public class Machine {
    private ASTTree astTree;

    /**
     * Input need to be a Standardized tree
     * */
    Machine(ASTTree astTree){
        this.astTree = astTree;
    }

    /**
     * @return result of the expression in AST
     * */
    public Object evaluate(){
        //TODO : evaluate the expression using 13 CSE Machine rules
        return null;
    }
}
