public class studying 
{
    public abstract class stm {}
    public abstract class Exp {}
    public abstract class ExpList {}

    public class CompoundStm extends stm {
        public stm stm1,stm2;
        public CompoundStm(stm s1 , stm s2) {
            stm1 = s1; stm2 = s2;
        }
    }
    
    public class AssignStm extends stm {
        public String IdExp;
        public Exp Exp;
        public AssignStm(String idexp , Exp exp){
            IdExp = idexp; Exp = exp;
        }
    }
    
    public class PrintStm extends stm {
        public ExpList expList;
        public PrintStm(ExpList explist){
            expList = explist;
        }
    }
    
    public class LastExpList extends ExpList {
        public ExpList head;
        public LastExpList(ExpList head){
            this.head = head;
        }
    }
    public class PairExpList extends ExpList {
        public ExpList head; public ExpList tail;
        public PairExpList(ExpList head, ExpList tail){
            this.head = head; this.tail = tail;
        }
    }

    public class NumExp extends Exp{
        public int Num;
        public NumExp(int num){
            this.Num = num;
        }
    }

    public class OpExp extends Exp{
        Exp Left , Right;
        int Op;
        final public int MINUS =1 , PLUS = 2 , MULTIPLY = 3;
        public OpExp(Exp left , int op , Exp right){
            Left = left; Op = op; Right = right;
        }
    }

    public class ESeqExp extends Exp{
        public stm stm; public Exp exp;
        public ESeqExp(stm s , Exp e){
            stm = s; exp = e;
        }
    }

    public static void main( String[] args )
    {
    }
}
