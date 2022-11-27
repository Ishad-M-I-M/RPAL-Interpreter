import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try{
            ASTTree astTree = ASTParser.parse(args[0]);
            astTree.traverse();
        }
        catch (FileNotFoundException | IllegalArgumentException e){
            System.out.println(e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}