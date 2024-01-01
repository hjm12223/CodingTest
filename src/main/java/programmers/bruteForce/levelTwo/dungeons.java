package programmers.bruteForce.levelTwo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class dungeons {
    public static void main(String[] args) {
        int solution = solution(40, new int[][]{{40, 20}, {10, 10}, {10, 10}, {10, 10}, {10, 10}});
        System.out.println("solution = " + solution);
    }

    public static int solution(int k, int[][] dungeons) {
        int result = 0;
        Arrays.sort(dungeons, ((o1, o2) -> {
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            else return o2[0] - o1[0];
        }));
        Queue<int[]> queue = new LinkedList<>(Arrays.asList(dungeons));

        Arrays.sort(dungeons, ((o1, o2) -> {
            return o1[0] - o2[0];
        }));
        Queue<int[]> queue2 = new LinkedList<>(Arrays.asList(dungeons));

        int tempK = k;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int[] peek = queue.peek();

            if (peek != null && poll[0] <= (k - peek[1]) && peek[0] <= k) {
                int[] temp = queue.poll();
                k -= poll[1];
                k -= temp[1];
                result += 2;
            } else if (poll[0] <= k) {
                k -= poll[1];
                result++;
            } else if (poll[0] <= k && k >= poll[1]) {
                result += 1;
            }
        }

        k = tempK; // 초기값으로 다시 설정
        int result2 = 0;

        while (!queue2.isEmpty()) {
            int[] poll = queue2.poll();
            int[] peek = queue2.peek();

            if (peek != null && poll[0] <= (tempK - peek[1]) && peek[0] <= tempK) {
                int[] temp = queue2.poll();
                tempK -= poll[1];
                tempK -= temp[1];
                result2 += 2;
            } else if (poll[0] <= tempK) {
                tempK -= poll[1];
                result2++;
            }
        }

        return Math.max(result, result2);
    }
}
/**
     * dungeons의 세로(행) 길이(즉, 던전의 개수)는 1 이상 8 이하입니다.
     * dungeons의 가로(열) 길이는 2 입니다.
     * dungeons의 각 행은 각 던전의 ["최소 필요 피로도", "소모 피로도"] 입니다.
     * "최소 필요 피로도"는 항상 "소모 피로도"보다 크거나 같습니다.
     * "최소 필요 피로도"와 "소모 피로도"는 1 이상 1,000 이하인 자연수입니다.
     * 서로 다른 던전의 ["최소 필요 피로도", "소모 피로도"]가 서로 같을 수 있습니다.
     */


    /**
     * 로직을 어떻게 해야하나?
     *
     * 첫번째 최소 피로도를 만족 시켜야한다 그렇기 때문에 최소피로도가 가장 높은 순서대로 정렬
     *
     * 만약 다음 최소피로도가 그다음에 있는 최소 피로도의 피로도 을 적용했을 때 보다 높거나 같을경우?
     *
     * 다음으로 점프한다음 이후의 작업을 진행
     *
     */