package programmers.review;

import javax.lang.model.SourceVersion;
import java.util.*;

public class JoyStick {
    public static void main(String[] args) {
        int solution = solution("AAB");
        System.out.println("solution = " + solution);
    }
        public static int solution(String name) {
            Map<Character,Integer> reverseFind = new HashMap<>();
            Map<Character,Integer> find = new HashMap<>();

            int result =0;

            int count = 0;
            for (char i = 'A' ; i <= 'Z' ; i++){
                find.put(i,count);
                count++;
            }
            count =1;
            for (char i = 'Z' ; i >= 'A' ; i--){
                reverseFind.put(i,count);
                count++;
            }
            String[] alphabet = name.split("");
            int alphabetLength = alphabet.length;
            List<Integer> isNotAIdx = new LinkedList<>();
            for (int i = 0 ; i < alphabet.length; i++){
                if (!alphabet[i].equals("A")){
                    isNotAIdx.add(i);
                }
            }
            if (isNotAIdx.size() == 0){
                return 0;
            }
            char[] charArray = name.toCharArray();
            int min = Math.min(leftSearch(isNotAIdx,alphabetLength), rightSearch(isNotAIdx,alphabetLength));
            result += min;
            for (char ch : charArray) {
                result +=  Math.min(reverseFind.get(ch), find.get(ch));
            }
            return result;
        }


    /**
     * 쭉 왼쪽으로 가는 leftSearch 메소드
     */
    public static int leftSearch(List<Integer> isNotA, int alphabetLength){
        int cursor = 0;
        int fullLeftSearch = alphabetLength-isNotA.get(0);


        if (isNotA.size() == 1){
            return fullLeftSearch;
        }
        for (int i = isNotA.size()-1 ; i >= 0; i--){

            if (i == 1){
                int cnt = alphabetLength-isNotA.get(i);
                cursor = cnt*2 + isNotA.get(i-1);
                break;
            }
            if (isNotA.get(i) -isNotA.get(i-1) > 1){
                int cnt = alphabetLength-isNotA.get(i);
                cursor = cnt*2 + isNotA.get(i-1);
                break;
            }
        }
        if (cursor != 0) {
            return Math.min(cursor, fullLeftSearch);
        }else {
            return fullLeftSearch;
        }
    }

    /**
     * 쭉 오른쪽으로 가는 rightSearch 메소드
     * A가 아닌 문자들의 인덱스를 isNotA에 저장
     * 그 후 오른쪽으로 쭉갔을 경우와 아닐 경우와, 오른쪽으로 갔다가 왼쪽으로 선행하는 경우를 비교
     * ex) 0,1,2,3,8 , length = 10 일 경우
     * 쭉 으른쪽으로 가는 경우보다
     * 오른쪽으로 갔다가 왼쪽으로 선행하는 것이 더 횟수를 줄일 수 있다
     * 둘 중 낮은값을 반환한다
     *
     * 0 1 2 3
     */
    public static int rightSearch(List<Integer> isNotA, int alphabetLength){
        int cursor = 0;

        int fullRightSearch = isNotA.get(isNotA.size() - 1);
        for (int i = 0 ; i < isNotA.size(); i++){
            if (i+1 == isNotA.size()){
                break;
            }

            if (isNotA.get(i+1) - isNotA.get(i) > 1 ){
                cursor = isNotA.get(i) * 2;
                cursor += alphabetLength - isNotA.get(i+1);
                break;
            }
        }
        if (cursor != 0) {
            return Math.min(cursor, fullRightSearch);
        }else {
            return fullRightSearch;
        }
    }
}
