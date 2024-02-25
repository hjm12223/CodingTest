package programmers.ex;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Pro12981 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(3, new String[]{"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"})));
    }
    public static int[] solution(int n, String[] words) {
        Set<String> set = new HashSet<>();
        set.add(words[0]);
        int turn = 1;
        for (int i = 1 ; i< words.length; i++){
            if (i % n == 0){
                turn++;
            }
            if (set.contains(words[i]) || words[i-1].charAt(words[i-1].length()-1) != words[i].charAt(0)){
                return new int[]{i%n +1  ,turn};
            }
            set.add(words[i]);
        }
        return new int[]{0,0};
    }
}
