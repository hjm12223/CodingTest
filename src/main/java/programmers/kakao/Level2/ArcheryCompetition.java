package programmers.kakao.Level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArcheryCompetition {
    public static void main(String[] args) {
//        System.out.println(Arrays.toString(solution(5, new int[]{2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0})));
//        System.out.println(Arrays.toString(solution(10, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3})));
        System.out.println(Arrays.toString(solution(3, new int[]{0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0})));
//        System.out.println(Arrays.toString(solution(1, new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0})));
//        System.out.println(Arrays.toString(solution(10, new int[]{5, 4, 1, 0 , 0 , 0, 0, 0, 0, 0})));
    }

    /**
     * 더 높은 점수가 아니라 더ㅓ 높은 차이를 내는게 이 문제에 주된 목적
     *
     * n개의 화살이 주어지고 info =어피치가 n개의 화살을 가지고 쏜 정보이다
     * 저 정보를 가지고 라이언은 n개의 화살과 info 를 가지고 제일 점수차이를 많이 낼 수 있는 경우를 찾아라
     * 만약 과녁판의 어피치가 2개 라이언이 2개를 맞췄을 시 어피치가 k점을 가지게 된다
     *
     *예제의 공통점을 보면 어피치가 제일 화살을 많이 소비한곳은 건너뛴다
     *
     * idx 0 번 부터 가장 높은
     */
    static  List<Apeach> apeach = new ArrayList<>();
    static  int[] scoreBoard = new int[]{10,9,8,7,6,5,4,3,2,1,0};
    static int methodScore1 = 0;
    static int methodScore2 = 0;
    static int method1ApeachScore = 0;
    static int method2ApeachScore = 0;
    public static int[] solution(int n, int[] info) {

        int[] result1 = method_1(info, n);
        int[] result2 = method_2(info,  n);
        int method1Df = methodScore1 - method1ApeachScore;
        int method2Df = methodScore2 - method2ApeachScore;
        if (method1Df > method2Df){
            if (method1Df < 1){
                return new int[]{-1};
            }
            return result1;
        }else {
            if (method2Df < 1){
                return new int[]{-1};
            }
            return result2;
        }
    }

    public static int[] method_1(int[] info,int n){
        methodScore1 = 0;
        method1ApeachScore = 0;
        int mostIdx = 0;
        int mostConsumeArrow = 0;
        int[] answer = new int[info.length];

        for (int i = 0 ; i< info.length ; i++){
            apeach.add(new Apeach(info[i],false));
            if (info[i] > 0) {
                method1ApeachScore += scoreBoard[i];
            }
            if (mostConsumeArrow <= info[i]){
                mostConsumeArrow = info[i];
                mostIdx = i;
            }
        }
        apeach.get(mostIdx).isMostArrow = true;

        for (int i = 0 ; i< info.length-1; i++) {
            Apeach ap1 = apeach.get(i);
            if (ap1.arrow == 0 && n != 0) {
                methodScore1 += scoreBoard[i];
                answer[i] = ap1.arrow + 1;
                n -= ap1.arrow + 1;
            }
        }

        for (int i = 0 ; i< info.length ; i++){
            if (n < 1) break;
            if (!apeach.get(i).isMostArrow && apeach.get(i).arrow < n && answer[i] == 0){
                answer[i] = apeach.get(i).arrow +1;
                n -= apeach.get(i).arrow +1;
                if (apeach.get(i).arrow != 0) {
                    method1ApeachScore -= scoreBoard[i];
                }
                methodScore1 += scoreBoard[i];
            }
        }
        if (n > 0){
            answer[answer.length-1] += n;
        }
        return answer;
    }
    public static int[] method_2(int[] info, int n){
        methodScore2 = 0;
        method2ApeachScore = 0;
        int mostIdx = 0;
        int mostConsumeArrow = 0;
        int[] answer = new int[info.length];

        for (int i = 0 ; i< info.length ; i++){
            apeach.add(new Apeach(info[i],false));
            if (info[i] > 0) {
                method2ApeachScore += scoreBoard[i];
            }
            if (mostConsumeArrow <= info[i]){
                mostConsumeArrow = info[i];
                mostIdx = i;
            }
        }
        apeach.get(mostIdx).isMostArrow = true;

        for (int i = 0 ; i< info.length ; i++){
            if (n < 1) break;
            if (!apeach.get(i).isMostArrow && apeach.get(i).arrow < n && answer[i] == 0){
                answer[i] = apeach.get(i).arrow +1;
                n -= apeach.get(i).arrow +1;
                if (apeach.get(i).arrow != 0) {
                    method2ApeachScore -= scoreBoard[i];
                }
                methodScore2 += scoreBoard[i];
            }
        }
        if (n > 0){
            answer[answer.length-1] += n;
        }

        return answer;

    }
    public static class Apeach{
        int arrow;
        boolean isMostArrow = false;

        public Apeach(int arrow, boolean isMostArrow) {
            this.arrow = arrow;
            this.isMostArrow = isMostArrow;
        }
    }
}
/**
 * 카카오배 양궁대회가 열렸습니다.
 * 라이언은 저번 카카오배 양궁대회 우승자이고 이번 대회에도 결승전까지 올라왔습니다. 결승전 상대는 어피치입니다.
 * 카카오배 양궁대회 운영위원회는 한 선수의 연속 우승보다는 다양한 선수들이 양궁대회에서 우승하기를 원합니다. 따라서,
 * 양궁대회 운영위원회는 결승전 규칙을 전 대회 우승자인 라이언에게 불리하게 다음과 같이 정했습니다.
 *
 * 1.어피치가 화살 n발을 다 쏜 후에 라이언이 화살 n발을 쏩니다.
 *
 * 2.점수를 계산합니다.
 *  1.과녁판은 아래 사진처럼 생겼으며 가장 작은 원의 과녁 점수는 10점이고 가장 큰 원의 바깥쪽은 과녁 점수가 0점입니다.
 *  2.만약, k(k는 1~10사이의 자연수)점을 어피치가 a발을 맞혔고 라이언이 b발을 맞혔을 경우 더 많은 화살을 k점에 맞힌 선수가 k 점을 가져갑니다.
 *   단, a = b일 경우는 어피치가 k점을 가져갑니다. k점을 여러 발 맞혀도 k점 보다 많은 점수를 가져가는 게 아니고 k점만 가져가는 것을 유의하세요.
 *   또한 a = b = 0 인 경우, 즉, 라이언과 어피치 모두 k점에 단 하나의 화살도 맞히지 못한 경우는 어느 누구도 k점을 가져가지 않습니다.
 *   예를 들어, 어피치가 10점을 2발 맞혔고 라이언도 10점을 2발 맞혔을 경우 어피치가 10점을 가져갑니다.
 *   다른 예로, 어피치가 10점을 0발 맞혔고 라이언이 10점을 2발 맞혔을 경우 라이언이 10점을 가져갑니다.
 *  3.모든 과녁 점수에 대하여 각 선수의 최종 점수를 계산합니다.
 *
 * 3. 최종 점수가 더 높은 선수를 우승자로 결정합니다. 단, 최종 점수가 같을 경우 어피치를 우승자로 결정합니다.
 *
 * 현재 상황은 어피치가 화살 n발을 다 쏜 후이고 라이언이 화살을 쏠 차례입니다.
 * 라이언은 어피치를 가장 큰 점수 차이로 이기기 위해서 n발의 화살을 어떤 과녁 점수에 맞혀야 하는지를 구하려고 합니다.
 *
 * 화살의 개수를 담은 자연수 n, 어피치가 맞힌 과녁 점수의 개수를 10점부터 0점까지 순서대로 담은 정수 배열 info가 매개변수로 주어집니다.
 * 이때, 라이언이 가장 큰 점수 차이로 우승하기 위해 n발의 화살을 어떤 과녁 점수에 맞혀야 하는지를 10점부터 0점까지 순서대로 정수 배열에 담아 return 하도록 solution 함수를 완성해 주세요. 만약,
 * 라이언이 우승할 수 없는 경우(무조건 지거나 비기는 경우)는 [-1]을 return 해주세요.
 *
 *
 * n	info	result
 * 5	[2,1,1,1,0,0,0,0,0,0,0]	[0,2,2,0,1,0,0,0,0,0,0]
 * 1	[1,0,0,0,0,0,0,0,0,0,0]	[-1]
 * 9	[0,0,1,2,0,1,1,1,1,1,1]	[1,1,2,0,1,2,2,0,0,0,0]
 * 10	[0,0,0,0,0,0,0,0,3,4,3]	[1,1,1,1,1,1,1,1,0,0,2]
 */