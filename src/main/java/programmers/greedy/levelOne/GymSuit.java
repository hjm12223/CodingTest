package programmers.greedy.levelOne;

import java.util.Arrays;
import java.util.HashMap;

public class GymSuit {
    public static void main(String[] args) {
        int n = 5;
        int[] lost = new int[]{2,4};
        int[] reserve = new int[]{3};
        int solution = solution(n, lost, reserve);
        System.out.println("solution = " + solution);
    }
    public static int solution(int n, int[] lost, int[] reserve) {
        int count = 0;
        Arrays.sort(lost);
        Arrays.sort(reserve);

        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 1 ; i <= n ; i++) {
            map.put(i,0);
        }
        for (int i : reserve) {
            map.put(i,map.getOrDefault(i,map.get(i))+1);
        }
        for (int i : lost) {
            map.put(i,map.getOrDefault(i,map.get(i))-1);
        }

        for (int i = 1 ; i < n; i++){
            if (map.get(i) < 0 && map.get(i+1) >0){
                map.put(i,map.getOrDefault(i,map.get(i))+1);
                map.put(i+1,map.getOrDefault(i+1,map.get(i+1))-1);
            }
            if (map.get(i) >= 1 && map.get(i+1) < 0){
                map.put(i+1,map.getOrDefault(i+1,map.get(i+1))+1);
            } else if (map.get(i) < 0){
                count++;
            }
        }
        if (map.get(n) < 0){
            count++;
        }
        if (count == 0) {
            return n;
        }else {
            return n - count;
        }
    }
}
/**
 * 전체 학생의 수는 2명 이상 30명 이하입니다.
 * 체육복을 도난당한 학생의 수는 1명 이상 n명 이하이고 중복되는 번호는 없습니다.
 * 여벌의 체육복을 가져온 학생의 수는 1명 이상 n명 이하이고 중복되는 번호는 없습니다.
 * 여벌 체육복이 있는 학생만 다른 학생에게 체육복을 빌려줄 수 있습니다.
 * 여벌 체육복을 가져온 학생이 체육복을 도난당했을 수 있습니다. 이때 이 학생은 체육복을 하나만 도난당했다고 가정하며, 남은 체육복이 하나이기에 다른 학생에게는 체육복을 빌려줄 수 없습니다.
 */
