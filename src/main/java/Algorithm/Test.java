package Algorithm;

import java.util.HashSet;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for (int i =  0 ; i < 10 ; i++) {
            set.add(i);
            set2.add(i+1);
        }
        set2.removeAll(set);
        System.out.println("set = " + set2);
    }
}
