package programmers.hash.levelTwo;

import java.util.HashMap;

public class costume {

    public static void main(String[] args) {
        String[][] cos = new String[][]{{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};
        int i  =solution(cos);
        System.out.println("i = " + i);
    }

    public static int solution(String[][] clothes) {
        int answer = 1;
        HashMap<String,Integer> map = new HashMap<>();
        for (int i = 0 ; i< clothes.length ; i++){
                map.put(clothes[i][1], map.getOrDefault(clothes[i][1],0)+1);
        }
        if (map.size() ==1){
          return   map.get(clothes[0][1]);
        }
        for (Integer value : map.values()) {
            answer *= (value +1) ;
        }
        return answer-1;
    }
}
