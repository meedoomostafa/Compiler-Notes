package Chapter2.Lab;

import java.util.*;

public class NFAtoDFAConverter {
    private final NFA nfa;

    public NFAtoDFAConverter(NFA nfa) {
        this.nfa = nfa;
    }

    private Set<String> closure(Set<String> inputStates) {
        Set<String> result = new HashSet<>(inputStates);
        Stack<String> stack = new Stack<>();
        stack.addAll(inputStates);

        while (!stack.isEmpty()) {
            String state = stack.pop();

            if (nfa.transitions.containsKey(state) && nfa.transitions.get(state).containsKey('ε')) {
                for (String next : nfa.transitions.get(state).get('ε')) { 
                    if (!result.contains(next)) {
                        result.add(next);
                        stack.push(next);
                    }
                }
            }
        }
        return result;
    }

    private Set<String> DFAedge(Set<String> stateSet, char c) {
        Set<String> result = new HashSet<>();
        for (String state : stateSet) {
            if (nfa.transitions.containsKey(state) && nfa.transitions.get(state).containsKey(c)) {
                result.addAll(nfa.transitions.get(state).get(c));
            }
        }
        return result;
    }

    private int findStateIndex(List<Set<String>> states, Set<String> e) {
        for (int i = 0; i < states.size(); i++) {
            if (states.get(i).equals(e)) {
                return i;
            }
        }
        return -1;
    }

    public DFA convert() {
        DFA dfa = new DFA();
        dfa.alphabet = new HashSet<>(nfa.alphabet);

        dfa.states.add(new HashSet<>());
        
        Set<String> start = new HashSet<>();
        start.add(nfa.startState);
        Set<String> startClosure = closure(start);
        dfa.states.add(startClosure);

        int p = 1;
        int j = 0;

        for (String s : startClosure) {
            if (nfa.acceptStates.contains(s)) {
                dfa.acceptStates.add(1);
                break;
            }
        }

        while (j <= p) {
            Set<String> current = dfa.states.get(j); 

            for (char c : dfa.alphabet) {
                Set<String> e = DFAedge(current, c);
                e = closure(e); 

                int i = findStateIndex(dfa.states, e);
                if (i != -1 && i <= p) {
                    dfa.transitions.computeIfAbsent(j, k -> new HashMap<>()).put(c, i);
                } 
                else {
                    p++;
                    dfa.states.add(e);
                    dfa.transitions.computeIfAbsent(j, k -> new HashMap<>()).put(c, p);

                    for (String s : e) {
                        if (nfa.acceptStates.contains(s)) {
                            dfa.acceptStates.add(p);
                            break;
                        }
                    }
                }
            }
            j++;
        }

        return dfa;
    }
}
