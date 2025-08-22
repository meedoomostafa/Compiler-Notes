package Chapter1.Lab;
import Chapter1.Lab.DataStructure.*;

public class Program 
{
    public static void main( String[] args )
    {
        /*
        a := 5 + 3;
        b := (print(a, a-1), 10 * a);
        print(b);
        */
        stm prog = 
            new CompoundStm(
                new AssignStm("a", 
                    new OpExp( 
                        new NumExp(5), 2, 
                        new NumExp(3))), 

                new CompoundStm(
                    new AssignStm("b", 
                        new ESeqExp(
                            new PrintStm(
                                new PairExpList(new IdExp("a"), 
                                new LastExpList(
                                    new OpExp(new IdExp("a"), 1, new NumExp(1))))),
                            new OpExp(new NumExp(10), 3, new IdExp("a")))), 
                    new PrintStm(new LastExpList(new IdExp("b")))));

        MaxArgs maxArgs = new MaxArgs();
        System.out.println(maxArgs.GetMaxPrintArgs(prog));
    }
}
