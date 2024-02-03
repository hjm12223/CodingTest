package programmers.kakao.level2;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class QueueSum {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{3,2,7,2}, new int[]{4,6,5,1})); // 2
        System.out.println(solution(new int[]{1,2,1,2}, new int[]{1,10,1,2})); // 7
        System.out.println(solution(new int[]{1,1}, new int[]{1,5})); // -1
    }
    public static int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        long Q2sum = 0;
        long Q1sum = 0;

        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        for (int i : queue1) {
            Q1sum+=i;
            q1.add(i);
        }

        for (int i : queue2) {
            Q2sum+=i;
            q2.add(i);
        }
        long target = (Q1sum + Q2sum) /2;


        /*
        루프에 빠지면 해당 정답이 없는것
         */
        while (Q1sum != target || Q2sum !=target){
            if(answer > (queue1.length + queue2.length) * 2)
                return -1;
            if (Q1sum > Q2sum && Q1sum > target){
                while (Q2sum < Q1sum) {
                    int Q1CurrPoll = q1.poll();
                    Q1sum -= Q1CurrPoll;
                    Q2sum += Q1CurrPoll;
                    q2.add(Q1CurrPoll);
                    answer++;
                }
            }
            if (Q1sum < Q2sum && Q1sum < target){
                while (Q2sum > Q1sum) {
                    int Q2CurrPoll = q2.poll();
                    Q1sum += Q2CurrPoll;
                    Q2sum -= Q2CurrPoll;
                    q1.add(Q2CurrPoll);
                    answer++;
                }
            }
        }
        return  answer;

    }
}
/**
 * 길이가 같은 두 개의 큐가 주어집니다. 하나의 큐를 골라 원소를 추출(pop)하고, 추출된 원소를 다른 큐에 집어넣는(insert) 작업을 통해 각 큐의 원소 합이 같도록 만들려고 합니다. 이
 * 때 필요한 작업의 최소 횟수를 구하고자 합니다. 한 번의 pop과 한 번의 insert를 합쳐서 작업을 1회 수행한 것으로 간주합니다.
 *
 * 큐는 먼저 집어넣은 원소가 먼저 나오는 구조입니다. 이 문제에서는 큐를 배열로 표현하며, 원소가 배열 앞쪽에 있을수록 먼저 집어넣은 원소임을 의미합니다.
 * 즉, pop을 하면 배열의 첫 번째 원소가 추출되며, insert를 하면 배열의 끝에 원소가 추가됩니다. 예를 들어 큐 [1, 2, 3, 4]가 주어졌을 때,
 * pop을 하면 맨 앞에 있는 원소 1이 추출되어 [2, 3, 4]가 되며, 이어서 5를 insert하면 [2, 3, 4, 5]가 됩니다.
 *
 * 다음은 두 큐를 나타내는 예시입니다.
 *
 * queue1 = [3, 2, 7, 2]
 * queue2 = [4, 6, 5, 1]
 * 두 큐에 담긴 모든 원소의 합은 30입니다. 따라서, 각 큐의 합을 15로 만들어야 합니다. 예를 들어, 다음과 같이 2가지 방법이 있습니다.
 *
 * queue2의 4, 6, 5를 순서대로 추출하여 queue1에 추가한 뒤,
 * queue1의 3, 2, 7, 2를 순서대로 추출하여 queue2에 추가합니다.
 * 그 결과 queue1은 [4, 6, 5], queue2는 [1, 3, 2, 7, 2]가 되며,
 * 각 큐의 원소 합은 15로 같습니다.
 * 이 방법은 작업을 7번 수행합니다.
 *
 * queue1에서 3을 추출하여 queue2에 추가합니다.
 * 그리고 queue2에서 4를 추출하여 queue1에 추가합니다.
 * 그 결과 queue1은 [2, 7, 2, 4], queue2는 [6, 5, 1, 3]가 되며,
 * 각 큐의 원소 합은 15로 같습니다. 이 방법은 작업을 2번만 수행하며,
 * 이보다 적은 횟수로 목표를 달성할 수 없습니다.
 */