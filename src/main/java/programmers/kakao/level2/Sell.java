package programmers.kakao.level2;


import java.util.*;

public class Sell {
    public static void main(String[] args) {
        solution(new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"}, new String[]{"young", "john", "tod", "emily", "mary"}, new int[]{12, 4, 2, 5, 10});
    }

    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        Map<String, Integer> indexMap = new HashMap<>();
        Map<String, String> referralMap = new HashMap<>();

        for (int i = 0; i < enroll.length; i++) {
            indexMap.put(enroll[i], i);
            referralMap.put(enroll[i], referral[i]);
        }

        for (int i = 0; i < seller.length; i++) {
            int earning = amount[i] * 100;
            String currSeller = seller[i];

            while (!currSeller.equals("-") && earning > 0) {
                int idx = indexMap.get(currSeller);
                int referralEarning = earning / 10;
                int sellerEarning = earning - referralEarning;

                answer[idx] += sellerEarning;
                earning = referralEarning;
                currSeller = referralMap.get(currSeller);
            }
        }
        return answer;
    }
}