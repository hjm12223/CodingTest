package programmers.hash.levelOne;

import java.util.Arrays;
import java.util.HashMap;

public class failsToCompleteRace {
    public static void main(String[] args) {
        String[] participant = new String[]{"mislav", "stanko", "mislav", "ana","ana","ana"};
        String[] completion = new String[]{"stanko", "ana", "mislav","mislav","ana"};
        String solution = solution(participant, completion);
        System.out.println("solution = " + solution);
    }
    public static String solution(String[] participant, String[] completion) {

        HashMap<String,Integer> hashMap = new HashMap<>();
        for (String s : completion) {
            if (hashMap.containsKey(s)){
                hashMap.put(s,hashMap.getOrDefault(s,hashMap.get(s))+1);
            }
            hashMap.put(s,hashMap.getOrDefault(s,1));
        }
        /**
         * 동명이인은 HashMap 의 value 를 1씩 추가해준다
         */
        for (String s : participant) {
            if (hashMap.containsKey(s)){
                hashMap.put(s,hashMap.getOrDefault(s,hashMap.get(s))-1);
            }else {
                return s;
            }
        }

        System.out.println("hashMap = " + hashMap);
        for (String s : hashMap.keySet()) {
            if (hashMap.get(s) < 0){
                return s;
            }
        }
        return null;
    }
}
