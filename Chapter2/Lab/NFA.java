package Chapter2.Lab;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NFA { 
    public Set<String> states = new HashSet<>();
    public Set<Character> alphabet = new HashSet<>();
    public Map<String, Map<Character, Set<String>>> transitions = new HashMap<>();
    public String startState;
    public Set<String> acceptStates = new HashSet<>();

    public void addTransition(String from, char symbol, String to) {
        transitions.computeIfAbsent(from, k -> new HashMap<>())
                   .computeIfAbsent(symbol, k -> new HashSet<>())
                   .add(to);
    }
}
