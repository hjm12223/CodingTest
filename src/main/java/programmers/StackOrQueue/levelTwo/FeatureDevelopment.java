package programmers.StackOrQueue.levelTwo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FeatureDevelopment {
    public static void main(String[] args) {


        int[] progress = new int[]{20, 99, 93, 30, 55, 10};
        int[] speeds = new int[]{5, 10, 1, 1, 30, 5};

        System.out.println("solution = " + Arrays.toString(solution(progress, speeds)));
    }

    public static int[] solution(int[] progresses, int[] speeds) {
        HashMap<Integer, Integer> map = new HashMap<>();
        HashMap<Integer,Integer> dayMap = new HashMap<>();
        boolean isEnd = true;
        List<Integer> list = new ArrayList<>();
        int day = 0;
       /*
       한번 돌때마다 progress 배열에 speeds 를 더해줘야함
       만약 progress 배열에 있는 값이 100을 넘으면 저장
       만약 100이 넘으면?

        */

        while (isEnd) {
            int result = 0;
            day++;
            for (int i = 0; i < progresses.length; i++) {
                progresses[i] += speeds[i];
                map.put(i, progresses[i]);
                if (map.get(i) >= 100) {
                    if (!dayMap.containsKey(i)){
                        dayMap.put(i,day);
                    }
                }
            }
            for (Integer value : map.values()) {
                if (value >= 100) {
                    result += 1;
                    if (result == progresses.length) {
                        isEnd = false;
                    }
                }

            }
        }
        System.out.println("dayMap = " + dayMap);
        /**
         * 여기서 dayMap 의 Value 값을 다 뽑아 낸뒤
         * dayMap 의 인덱스 값이 다음 인덱스 값보다 클 경우 result ++; 을 해준다
         * 언제까지? 작아질떄까지 클떄까지
         */
        int breakInt = 0;
        for (int i = 0 ; i < dayMap.size(); i++){
            System.out.println("i = " + i);
            int result = 1;
            int j = i;
            while (true){
                if (j == dayMap.size()-1){
                    i = j-1;
                    break;
                }
                if (dayMap.get(i) >= dayMap.get(j+1)){
                    result++;
                    j++;
                }else {
                    i = j;
                    break;
                }
            }
            breakInt += result;
            list.add(result);
            if (breakInt >= dayMap.size()){
                break;
            }
        }
        return list.stream().mapToInt(i -> i).toArray();
    }
}
