package programmers.hash.levelOne;

import java.util.HashSet;
import java.util.Set;

public class phoneKetMon {
    public static void main(String[] args) {
        int[] phoneKetMon = {3,3,3,2,2,2};
        int solution = solution(phoneKetMon);
        System.out.println("solution = " + solution);
    }
    public static int solution(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int integer : nums) {
            set.add(integer);
        }
        if (set.size() >= nums.length /2){
            return nums.length/2;
        }else {
            return set.size();
        }
    }
}
