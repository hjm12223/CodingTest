package programmers.bruteForce.levelTwo;

import java.util.HashMap;

public class GatherDict {
    static int cnt = 0;
    public static void main(String[] args) {
        int solution = solution("AAAAE");
        System.out.println("solution = " + solution);
    }
    public static int solution(String word) {
/**
 * permutation 을 사용하면 nHr 이기 떄문에 시간복잡도가 O(n^r) 이기때문에 사용에 유의해야한다
 */
        HashMap<String,Integer> hashMap = new HashMap<>();
        String[] dict = new String[]{"A","E","I","O","U"}; // 모음 사전을 설정
        StringBuilder str = new StringBuilder(); // StringBuilder 설정
        permu(dict, 0, hashMap,str); // 중복순열을 통해서 값들을 모두 추출
        int result = hashMap.get(word); // hashMap 에 저장된 word 의 알맞은 Value를 반환
        return result;
    }

    public static void permu(String[] dict, int depth, HashMap<String, Integer> hashMap,StringBuilder str) {
        if (depth == dict.length){
            return; // 만약 dict.length 와 depth 가 같을 경우 탈출
        }
        for (int i = 0 ; i< dict.length ; i++){
            str.append(dict[i]); // str 을 dict[i]를 append
            cnt++; // count ++
            hashMap.put(String.valueOf(str),cnt); // 해당 str의 값과 cnt 를 hashMap에 input
            permu(dict, depth+1, hashMap,str); // 다시 로테이션
            str.deleteCharAt(str.length()-1); // 만약 넘어갈 경우 마지막값을 빼줌
    }
}
}
