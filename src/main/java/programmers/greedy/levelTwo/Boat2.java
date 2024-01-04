package programmers.greedy.levelTwo;

import java.util.Arrays;

public class Boat2 {
    public static void main(String[] args) {
        int[] people = new int[]{80, 20, 10, 30, 40};
        int limit = 100;
        int solution = solution(people, limit);
        System.out.println("solution = " + solution);
    }
    public static int solution(int[] people, int limit) {
        Arrays.sort(people);
        int answer = 0;
        int left = 0, right = people.length - 1;

        while (left <= right) {
            // 가장 무거운 사람과 가장 가벼운 사람을 비교하여 무게 제한 내에서 최대한 많은 사람을 태우기
            if (people[left] + people[right] <= limit) {
                left++;
            }
            right--;
            answer++;
        }

        return answer;
    }
}