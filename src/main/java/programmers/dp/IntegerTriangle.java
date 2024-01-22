package programmers.dp;

import java.util.*;

public class IntegerTriangle {
    public static void main(String[] args) {
        int solution = solution(new int[][]{{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}});
        System.out.println(solution);
    }
    public static int solution(int[][] triangle) {

        int answer = 0;
        List<List<Integer>> tr = new LinkedList<>();
        int[][] dp = new int[triangle.length][];

        int max = 0;
        for (int i = 0; i < triangle.length; i++) {
            tr.add(new LinkedList<>());
            dp[i] = new int[triangle[i].length];
        }
        tr.get(0).add(triangle[0][0]);

        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                tr.get(i).add(triangle[i][j]);
            }
        }
        dp[0][0] = tr.get(0).get(0);
        for (int i = 0; i < triangle.length - 1; i++) {
            for (int j = 0; j < tr.get(i).size(); j++) {
                int LValue = dp[i][j] + tr.get(i + 1).get(j);
                int RValue = dp[i][j] + tr.get(i + 1).get(j + 1);

                if (dp[i + 1][j] < LValue) {
                    dp[i + 1][j] = LValue;
                }

                dp[i + 1][j + 1] = RValue;
                max = Math.max(LValue,RValue);
                answer =Math.max(answer,max);
            }
        }

        return answer;
    }
}
/**
 * 위와 같은 삼각형의 꼭대기에서 바닥까지 이어지는 경로 중,
 * 거쳐간 숫자의 합이 가장 큰 경우를 찾아보려고 합니다.
 * 아래 칸으로 이동할 때는 대각선 방향으로 한 칸 오른쪽 또는 왼쪽으로만 이동 가능합니다.
 * 예를 들어 3에서는 그 아래칸의 8 또는 1로만 이동이 가능합니다.
 *
 * 삼각형의 정보가 담긴 배열 triangle이 매개변수로 주어질 때, 거쳐간 숫자의 최댓값을 return 하도록 solution 함수를 완성하세요.
 *
 *
 * [[7], 1
 * [3, 8], 1 2
 * [8, 1, 0],  1 2 3
 * [2, 7, 4, 4], 1 2 3 4
 * [4, 5, 2, 6, 5]] 1 2 3 4 5
 * 제한사항
 *
 * 효율성 테스트 2개 실패
 */