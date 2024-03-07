package programmers.kakao.Level2;


import java.util.*;
/*
유저의 수 100
최대 이모티콘 7
이모티콘당 할인율 4
(100 * 7 * 4^7) = 11,468,800 완탐 가능

 */
public class Emoticon {
    public static void main(String[] args) {
//        int[] solution = solution(new int[][]{{40, 10000}, {25, 10000}}, new int[]{7000, 9000});
        int[] solution = solution(new int[][]{{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}}, new int[]{1300, 1500, 1600, 4900});
        System.out.println("solution = " + Arrays.toString(solution));
    }
    static int cnt = 1;
    static int[] percent = new int[]{10,20,30,40};
    static int total_join = 0;
    static int total_price= 0;
    static int min = Integer.MAX_VALUE;

    public static int[] solution(int[][] users, int[] emoticons) {
        for (int[] user : users) {
            min = Math.min(min,user[0]);
        }
        System.out.println("min = " + min);

        for (int i = 0; i < 4; i++) {
            if (min <= percent[i]) {
                min = i; // index
                break;
            }
        }

        // 중복조합 돌리기
        int[] discounts = new int[emoticons.length];
        comb(discounts, 0, emoticons.length, users, emoticons);
        int[] answer = {total_join, total_price}; // 가입 유저 수, 매출액
        return answer;
    }
    private static void comb(int[] discounts, int depth, int r, int[][] users, int[] emoticons) {
        System.out.println("depth = " + depth);
        if (depth == r){
            System.out.println("------");
            cal(users,emoticons,discounts);
            return;
        }
        for (int i = depth ; i < r ; i++){
            for (int j = min; j< 4 ; j++){
                System.out.println("j = " + j);
                discounts[i] = percent[j];
                System.out.println("discounts = " + Arrays.toString(discounts));
                comb(discounts,i+1,r,users,emoticons);
            }
        }
    }

    private static void cal(int[][] users, int[] emoticons, int[] discounts) {
        int join = 0;
        int price = 0;

        for (int[] user : users) {
            int userMinDiscount = user[0];
            int userMaxPay = user[1];
            int sum = 0;

            for (int i = 0 ; i< discounts.length; i++){
                if (discounts[i] < userMinDiscount) continue;
                sum+= sale(emoticons[i],discounts[i]);
            }

            if (userMaxPay <= sum) join++;
            else price += sum;
        }
        if (join > total_join){
            total_join = join;
            total_price = price;
        } else if (join == total_join && price> total_price) {
            total_price= price;
        }
    }

    private static int sale(int emoticon, int discount) {
        return (emoticon / 100) * (100 - discount);
    }
}
  /*
        반목문을 돌 경우 user 에게 저장된 minDis 보다 큰 할인율을 적용하여 이모티콘을 사게 만든다
        만약 현재 유저1 의 최소 할인율은 25 그러므로 30의 할인율을 적용해야 해당 유저는 이모티콘을 구매

        만약 두 상품 모두 30의 할인율을 적용하면 10,000을 초과하기 때문에 해당 유저는 이모티콘 플러스를 구독

        하지만 유저2 의 최소 할인율은 40이기떄문에 두 상품에 모두 40을 적용
        그럴경우 유저1 ,유저2 는 두 상품을 모두 구매하지만
        그럴경우 첫번째 요구조건인 이모티콘 플러스를 구독하지않음으로 요구조건을 충족시키지 못함

        그렇기 떄문에 한 상품에는 30의 할인율, 한 상품에는 40의 할인율을 적용시킴으로
        유저 1은 총 가격이 10,000이 넘기 때문에 이모티콘 플러스를 구독
        유저 2는 총 가격이 10,000이 넘지 않기 때문에 40의 할인율만 적용된 상품을 구매
         */

    /*
    이모티콘 플러스 서비스 가입자를 최대한 늘리는 것.
    이모티콘 판매액을 최대한 늘리는 것.


    이 문제에서 요구하는 점화식은
    User 들에게 주어진 할인율을 통해 최대한 많은 이모티콘 플러스 가입자와, 이모티콘을 판매하는것이다

    1번 이모티콘을 30% 할인하고 2번 이모티콘을 40% 할인한다면 결과는 다음과 같습니다.

     * 사용자	구매한 이모티콘	이모티콘 구매 비용	이모티콘 플러스 서비스 가입 여부
     * 1	        2	            5,400	                X
     * 2	        1, 2	        10,300	                O

    할인율은 10, 20 ,30 ,40 이 고정적이다

    해당 유저가 이모티콘 플러스 서비스를 가입 할 수 있는지 확인한다
    못할 경우
     */
/**
 * 카카오톡에서는 이모티콘을 무제한으로 사용할 수 있는 이모티콘 플러스 서비스 가입자 수를 늘리려고 합니다.
 * 이를 위해 카카오톡에서는 이모티콘 할인 행사를 하는데, 목표는 다음과 같습니다.
 *
 * 이모티콘 플러스 서비스 가입자를 최대한 늘리는 것.
 * 이모티콘 판매액을 최대한 늘리는 것.
 * 1번 목표가 우선이며, 2번 목표가 그 다음입니다.
 *
 * 이모티콘 할인 행사는 다음과 같은 방식으로 진행됩니다.
 *
 * n명의 카카오톡 사용자들에게 이모티콘 m개를 할인하여 판매합니다.
 * 이모티콘마다 할인율은 다를 수 있으며, 할인율은 10%, 20%, 30%, 40% 중 하나로 설정됩니다.
 * 카카오톡 사용자들은 다음과 같은 기준을 따라 이모티콘을 사거나, 이모티콘 플러스 서비스에 가입합니다.
 *
 * 각 사용자들은 자신의 기준에 따라 일정 비율 이상 할인하는 이모티콘을 모두 구매합니다.
 * 각 사용자들은 자신의 기준에 따라 이모티콘 구매 비용의 합이 일정 가격 이상이 된다면, 이모티콘 구매를 모두 취소하고 이모티콘 플러스 서비스에 가입합니다.
 * 다음은 2명의 카카오톡 사용자와 2개의 이모티콘이 있을때의 예시입니다.
 *
 * 사용자	비율	가격
 * 1	40	10,000
 * 2	25	10,000
 * 이모티콘	가격
 * 1	7,000
 * 2	9,000
 *
 * 1번 사용자는 40%이상 할인하는 이모티콘을 모두 구매하고,
 * 이모티콘 구매 비용이 10,000원 이상이 되면 이모티콘 구매를 모두 취소하고 이모티콘 플러스 서비스에 가입합니다.
 *
 * 2번 사용자는 25%이상 할인하는 이모티콘을 모두 구매하고,
 * 이모티콘 구매 비용이 10,000원 이상이 되면 이모티콘 구매를 모두 취소하고 이모티콘 플러스 서비스에 가입합니다.
 *
 * 1번 이모티콘의 가격은 7,000원, 2번 이모티콘의 가격은 9,000원입니다.
 *
 * 만약, 2개의 이모티콘을 모두 40%씩 할인한다면,
 * 1번 사용자와 2번 사용자 모두 1,2번 이모티콘을 구매하게 되고,
 * 결과는 다음과 같습니다.
 *
 *
 * 사용자	구매한 이모티콘	이모티콘 구매 비용	이모티콘 플러스 서비스 가입 여부
 * 1	        1, 2	        9,600                   	X
 * 2	        1, 2	        9,600	                    X
 *
 *
 * 하지만, 1번 이모티콘을 30% 할인하고 2번 이모티콘을 40% 할인한다면 결과는 다음과 같습니다.
 *
 * 사용자	구매한 이모티콘	이모티콘 구매 비용	이모티콘 플러스 서비스 가입 여부
 * 1	        2	            5,400	                X
 * 2	        1, 2	        10,300	                O
 */