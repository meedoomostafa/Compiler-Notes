package Chapter1.Lab;
import Chapter1.Lab.DataStructure.*;

public class MaxArgs {
    public int GetMaxPrintArgs(stm s) {
        if (s instanceof CompoundStm) {
            return CompoundStmCalc((CompoundStm) s);
        }
        if (s instanceof PrintStm) {
            return PrintStmCalc((PrintStm) s);
        }
        if (s instanceof AssignStm) {
            return AssignStmCalc((AssignStm) s);
        }
        return 0;
    }

    private int CompoundStmCalc(CompoundStm cs) {
        int max1 = GetMaxPrintArgs(cs.stm1);
        int max2 = GetMaxPrintArgs(cs.stm2);
        return Math.max(max1, max2);
    }

    private int PrintStmCalc(PrintStm ps) {
        int argsCount = countExpListArgs(ps.expList);
        int maxInExpressions = getMaxInExpList(ps.expList);
        return Math.max(argsCount, maxInExpressions);
    }

    private int AssignStmCalc(AssignStm as) {
        return getMaxInExp(as.Exp);
    }

    private int countExpListArgs(ExpList expList) {
        if (expList instanceof LastExpList) {
            return 1;
        }
        if (expList instanceof PairExpList) {
            PairExpList pel = (PairExpList) expList;
            return 1 + countExpListArgs(pel.tail);
        }
        return 0;
    }

    private int getMaxInExpList(ExpList expList) {
        if (expList instanceof LastExpList) {
            LastExpList lel = (LastExpList) expList;
            return getMaxInExp(lel.head);
        }
        if (expList instanceof PairExpList) {
            PairExpList pel = (PairExpList) expList;
            int maxInHead = getMaxInExp(pel.head);
            int maxInTail = getMaxInExpList(pel.tail);
            return Math.max(maxInHead, maxInTail);
        }
        return 0;
    }

    private int getMaxInExp(Exp exp) {
        if (exp instanceof ESeqExp) {
            ESeqExp ese = (ESeqExp) exp;
            return GetMaxPrintArgs(ese.stm);
        }
        if (exp instanceof OpExp) {
            OpExp oe = (OpExp) exp;
            int maxLeft = getMaxInExp(oe.Left);
            int maxRight = getMaxInExp(oe.Right);
            return Math.max(maxLeft, maxRight);
        }
        return 0;
    }
}
