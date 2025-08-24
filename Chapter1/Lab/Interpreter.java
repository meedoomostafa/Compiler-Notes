package Chapter1.Lab;
import Chapter1.Lab.DataStructure.*;

public class Interpreter {

    private Table table;

    public Interpreter() {
        table = null;
    }

    public Table InterpreterStm(stm s) {
        if (s instanceof CompoundStm cs) return CompoundStmInter(cs);
        if (s instanceof PrintStm ps) return PrintStmInter(ps);
        if (s instanceof AssignStm as) return AssignStmInter(as);
        return table;
    }

    protected Table CompoundStmInter(CompoundStm cs) {
        this.table = InterpreterStm(cs.stm1);
        this.table = InterpreterStm(cs.stm2);
        return this.table;
    }

    protected Table AssignStmInter(AssignStm as) {
        int val = evalExp(as.Exp);
        put(as.IdExp, val); 
        return this.table;
    }

    protected Table PrintStmInter(PrintStm ps) {
        ExpList exps = ps.expList;
        while (exps instanceof PairExpList) {
            PairExpList pair = (PairExpList) exps;
            int v = evalExp(pair.head);
            System.out.print(v + " ");
            exps = pair.tail;
        }
        if (exps instanceof LastExpList) {
            LastExpList last = (LastExpList) exps;
            int v = evalExp(last.head);
            System.out.print(v + " ");
        }
        System.out.println();
        return this.table;
    }

    protected Table ESeqExpStmInter(ESeqExp eseq) {
        this.table = InterpreterStm(eseq.stm);
        evalExp(eseq.exp);
        return this.table;
    }

    // helper methods
    private int evalExp(Exp e) {
        if (e instanceof NumExp) {
            return ((NumExp) e).Num;
        }
        if (e instanceof IdExp) {
            return lookup(table, ((IdExp) e).Id);
        }
        if (e instanceof OpExp) {
            OpExp op = (OpExp) e;
            int left = evalExp(op.Left);
            int right = evalExp(op.Right);
            return switch (op.Op) {
                case OpExp.PLUS -> left + right;
                case OpExp.MINUS -> left - right;
                case OpExp.MULTIPLY -> left * right;
                default -> throw new RuntimeException("Unknown operator: " + op.Op);
            };
        }
        if (e instanceof ESeqExp) {
            ESeqExp es = (ESeqExp) e;
            this.table = InterpreterStm(es.stm);
            return evalExp(es.exp);
        }
        throw new RuntimeException("Unknown Exp type: " + e.getClass().getName());
    }

    private void put(String id, int value) {
        this.table = new Table(id, value, this.table);
    }

    private int lookup(Table t, String id) {
        while (t != null) {
            if (t.Id.equals(id)) {
                return t.Value;
            }
            t = t.Tail;
        }
        throw new RuntimeException("Variable not found: " + id);
    }
}
