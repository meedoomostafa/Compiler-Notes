package Chapter2.Lab;

public class Program {
    public static void main( String[] args ){
        NFA nfa = new NFA();
        nfa.states.add("s1");
        nfa.states.add("s2");
        nfa.alphabet.add('a');
        nfa.alphabet.add('b');
        nfa.startState = "s1";
        nfa.acceptStates.add("s2");

        nfa.addTransition("s1", 'a', "s2");
        nfa.addTransition("s2", 'b', "s1");

        // 2. نحول NFA → DFA
        NFAtoDFAConverter converter = new NFAtoDFAConverter(nfa);
        DFA dfa = converter.convert();

        // 3. نطبع النتائج
        System.out.println("DFA States: " + dfa.states);
        System.out.println("DFA Transitions: " + dfa.transitions);
        System.out.println("DFA Accept States: " + dfa.acceptStates);
    }
    
}
