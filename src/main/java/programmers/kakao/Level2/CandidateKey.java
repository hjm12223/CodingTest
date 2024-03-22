package programmers.kakao.Level2;

import java.util.*;

public class CandidateKey {

    public static void main(String[] args) {
        solution(new String[][]{{"100", "ryan", "music", "2"}, {"200", "apeach", "math", "2"}, {"300", "tube", "computer", "3"}, {"400", "con", "computer", "4"}, {"500", "muzi", "music", "3"}, {"600", "apeach", "music", "2"}});
    }
    static Set<String> combination;
    public static int solution(String[][] relation) {
        /*
        콤비네이션을 통해 모든 경우의 Column 의 경우의 수를 생성
        생성 한 이후 해당 값을 List, Map 에 저장해서 있는지의 여부를 확인 없을 경우
        한 컬럼을 기준으로 중복된 값이 있을경우
        한개의 로우를 기준으로 컬럼의 값을 빼내온다
         */
        int answer = 0;
        List<Set<String>> candidates = new ArrayList<>();


        for (int i = 0 ; i < relation.length ;i++){
            String[] row = relation[i];
            for (int j = 0 ; j < row.length; j++){
            }
        }
        return answer;
    }
    private static void comb(String[] row, int , int r, String key){
    }
}