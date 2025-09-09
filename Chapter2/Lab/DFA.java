package Chapter2.Lab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DFA {
    public List<Set<String>> states = new ArrayList<>();
    public Set<Character> alphabet = new HashSet<>();
    public Map<Integer, Map<Character, Integer>> transitions = new HashMap<>();
    public int startState = 1; 
    public Set<Integer> acceptStates = new HashSet<>();
}
