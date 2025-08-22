package Chapter1.Lab;
import Chapter1.Lab.DataStructure.*;

public class Interpreter {

    private Table table;

    public Interpreter() {
        table = null;
    }
    public Table InterpreterStm(stm s){
        if (s instanceof CompoundStm) {
            this.table = CompoundStmInter((CompoundStm)s);
        }
        if (s instanceof PrintStm) {
            this.table = PrintStmInter((PrintStm)s);
            return this.table;
        }
        if (s instanceof AssignStm) {
            this.table = AssignStmInter((AssignStm)s);
            return this.table;
        }
        return this.table;
    }
    public Table CompoundStmInter(CompoundStm cs){
        if(cs.stm1 instanceof AssignStm){
            this.table = AssignStmInter((AssignStm)cs.stm1);
            return this.table;
        }
        if(cs.stm2 instanceof AssignStm){
            this.table = AssignStmInter((AssignStm)cs.stm2);
            return this.table;
        }
        if(cs.stm1 instanceof PrintStm){
            this.table = PrintStmInter((PrintStm)cs.stm1);
            return this.table;
        }
        if(cs.stm2 instanceof PrintStm){
            this.table = PrintStmInter((PrintStm)cs.stm2);
            return this.table;
        }
        if(cs.stm1 instanceof CompoundStm){
            CompoundStmInter((CompoundStm)cs.stm1);
        }
        if(cs.stm2 instanceof CompoundStm){
            CompoundStmInter((CompoundStm)cs.stm2);
        }
        return this.table;
    }
    public Table AssignStmInter(AssignStm as){

        if(as.Exp instanceof ESeqExp){
            this.table = ESeqExpStmInter((ESeqExp)as.Exp);
            return this.table;
        }
        if(as.Exp instanceof OpExp){
            this.table = OpExpStmInter((OpExp)as.Exp);
            return this.table;
        }
        if(as.Exp instanceof NumExp numExp){
            this.table = TableHelper(as, numExp);
            return this.table;
        }
        return this.table;
    }
    public Table PrintStmInter(PrintStm ps){

        return this.table;
    }
    public Table ESeqExpStmInter(ESeqExp esq){
        if (esq.stm instanceof CompoundStm) {
            this.table = CompoundStmInter((CompoundStm)esq.stm);
            return this.table;
        }
        if (esq.stm instanceof PrintStm) {
            this.table = PrintStmInter((PrintStm)esq.stm);
            return this.table;
        }
        if (esq.stm instanceof AssignStm) {
            this.table = AssignStmInter((AssignStm)esq.stm);
            return this.table;
        }
        if(esq.exp instanceof OpExp){
            this.table = OpExpStmInter((OpExp)esq.exp);
            return this.table;
        }
        if(esq.exp instanceof ESeqExp){
            ESeqExpStmInter((ESeqExp)esq.exp);
        }
        return this.table;
    }
    public Table OpExpStmInter(OpExp op){
        return this.table;
    }


    // helper methods section

    private Table TableHelper(AssignStm as , NumExp numExp){
        if(table == null){
            table = new Table(as.IdExp,numExp.Num,null);
            table.Head = table;
        }
        else{
            Table head = table.Head;
            table.Head = new Table(as.IdExp,numExp.Num,head);
        }
        return this.table;
    }
}