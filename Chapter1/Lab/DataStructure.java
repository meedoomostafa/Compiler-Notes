package Chapter1.Lab;
public class DataStructure {

    public static abstract class stm {}
    public static abstract class Exp {}
    public static abstract class ExpList {}

    public static class CompoundStm extends stm {
        public stm stm1,stm2;
        public CompoundStm(stm s1 , stm s2) {
            stm1 = s1; stm2 = s2;
        }
    }
    
    public static class AssignStm extends stm {
        public String IdExp;
        public Exp Exp;
        public AssignStm(String idexp , Exp exp){
            IdExp = idexp; Exp = exp;
        }
    }
    
    public static class PrintStm extends stm {
        public ExpList expList;
        public PrintStm(ExpList explist){
            expList = explist;
        }
    }
    
    public static class LastExpList extends ExpList {
        public Exp head;
        public LastExpList(Exp head){
            this.head = head;
        }
    }

    public static class PairExpList extends ExpList {
        public Exp head; public ExpList tail;
        public PairExpList(Exp head, ExpList tail){
            this.head = head; this.tail = tail;
        }
    }

    public static class NumExp extends Exp{
        public int Num;
        public NumExp(int num){
            this.Num = num;
        }
    }
    public static class IdExp extends Exp {
        public String id;
        public IdExp(String i) {
            id=i;
        }
    }

    public static class OpExp extends Exp{
        Exp Left , Right;
        int Op;
        final public static int MINUS =1 , PLUS = 2 , MULTIPLY = 3;
        public OpExp(Exp left , int op , Exp right){
            Left = left; Op = op; Right = right;
        }
    }

    public static class ESeqExp extends Exp{
        public stm stm; public Exp exp;
        public ESeqExp(stm s , Exp e){
            stm = s; exp = e;
        }
    } 
}
