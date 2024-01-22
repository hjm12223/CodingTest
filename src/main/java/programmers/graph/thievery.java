package programmers.graph;

import java.util.Arrays;

public class thievery {
    public static void main(String[] args) {
        solution(new int[]{10, 20, 30, 40,5040});
    }
    public static int solution(int[] money) {
        int[][] df = new int[2][money.length];
        df[0][0] = money[0];
        df[0][1] = money[0];

        df[1][0] = 0;
        df[1][1] = money[1];
        // 전처리

        /**
         * 단순하게 생각했을떄는
         * 짝수, 홀수 집을 모두 턴다는 과정하에
         * 둘중에서 가장 큰값을 넣어주면 된다
         *
         * 하지만 그 집을 털수도 있고 안털 수 도 있다
         *
         * 왜냐하면 다음집의 금액이 현재집 보다 클 경우
         *
         * 그럼 그집의 도둑을 들어
         */
        for (int i = 2 ; i< money.length; i++){
            df[1][i] = Math.max(df[1][i-2] + money[i], df[1][i-1]);
            df[0][i] = Math.max(df[0][i-2] + money[i], df[0][i-1]);
            }
        System.out.println("df = " + Arrays.deepToString(df));
        return Math.max(df[0][money.length-2], df[1][money.length-1]);
    }
}
/**
 * 문제 설명
 * 도둑이 어느 마을을 털 계획을 하고 있습니다. 이 마을의 모든 집들은 아래 그림과 같이 동그랗게 배치되어 있습니다.
 *
 * 각 집들은 서로 인접한 집들과 방범장치가 연결되어 있기 때문에 인접한 두 집을 털면 경보가 울립니다.
 *
 * 각 집에 있는 돈이 담긴 배열 money가 주어질 때, 도둑이 훔칠 수 있는 돈의 최댓값을 return 하도록 solution 함수를 작성하세요.
 *
 * 제한사항
 * 이 마을에 있는 집은 3개 이상 1,000,000개 이하입니다.
 * money 배열의 각 원소는 0 이상 1,000 이하인 정수입니다.
 * 입출력 예
 * money	return
 * [1, 2, 3, 1]	4
 */

/*
일단 첫번째로 연속으로 집을 못텀 -> 그러면 첫번째집을 무조건 터는 과정과 아닌 과정을 선택
그럼 df 라는 array 안에 여태까지 훔친 것들을 저장
 */