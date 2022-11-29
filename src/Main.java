import ast.ASTParser;
import ast.ASTTree;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try{
            ASTTree astTree = ASTParser.parse(args[0]);
            astTree.traverse();
            astTree.standardize();
            System.out.println("\nStandardized Tree\n");
            astTree.traverse();
        }
        catch (FileNotFoundException | IllegalArgumentException | IllegalStateException e){
            System.out.println(e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}