package programmers.greedy.levelTwo;

import java.util.*;

public class JoyStick2 {
    public static void main(String[] args) {
        String name = "AAB";
        int solution = solution(name);
        System.out.println("solution = " + solution);
    }
    public static int solution(String name) {
        HashMap<Character,Integer> nextSearch = new HashMap<>();
        HashMap<Character,Integer> preSearch = new HashMap<>();
        char[] charArray = name.toCharArray();
        int n = 0;
        int answer = 0;
        int exception =0;
        for (char c : charArray) {
            if (c == 'A'){
                exception++;
            }
        }
        if (exception == charArray.length){
            return 0;
        }

        for (char ch = 'A' ; ch <= 'Z' ; ch++){
            nextSearch.put(ch,n++);
        }
        /*
        왼쪽으로 이동과 오른쪽으로 이동 중 최선의 선택을 해야함
        만약 AABA 가 있을경우 왼쪽과 오른쪽 비용은 똑같음
        하지만 AABAA 이 있을 경우 오른족으로 이동하는 비용이 왼쪽으로 이동하는 비용보다 쌈
         */
        n = 0;
        for (char ch = 'Z' ; ch>= 'A' ; ch--) {
            preSearch.put(ch,++n);
        }
        for (int i = 0; i < charArray.length; i++) {
            if (i == 0){
                answer -= 1;
            }
            if (charArray[i] != 'A') {
                answer += Math.min(nextSearch.get(charArray[i]), preSearch.get(charArray[i]));
                answer +=  Math.min(rightCursor(charArray, i), leftCursor(charArray, i));
            }
        }
        return answer;
    }
    public static int rightCursor(char[] chars,int currentIndex){
        int result = 0;
        while (currentIndex < chars.length - 1 && chars[currentIndex] == 'A') {
            currentIndex++;
            result++;
        }
        if (result == 0){
            return 1;
        }
        return result;
    }

    public static int leftCursor(char[] chars, int currentIndex) {
        int result = 0;

        while (currentIndex < chars.length - 1 && chars[currentIndex] == 'A') {
            currentIndex++;
            result++;
        }
        if (result == 0){
            return 1;
        }
        return result;
    }
}