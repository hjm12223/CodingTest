package programmers.kakao.Level2;

import java.util.ArrayList;
import java.util.List;

public class box {
    public static void main(String[] args) {
//        long solution = solution(4, 5, new int[]{1, 0, 3, 1, 2}, new int[]{0, 3, 0, 4, 0});// 결과값	16
        long solution = solution(2, 7, new int[]{1, 0, 2, 0, 1, 0, 2}, new int[]{0, 2, 0, 1, 0, 2, 0});
        System.out.println("solution = " + solution);
    }
    public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long distance = 0;
        List<House> houses = new ArrayList<>();
        // 맨 마지막 집부터 방문한다는 가정
        for (int i = n ; i > 0 ; i--){
            House house = new House(i, pickups[i-1], deliveries[i-1]);
            houses.add(house);
        }
        int nowDelivery = 0;
        int nowPickup = 0;

        for (int i = 0; i < houses.size(); i++) {
            House curr = houses.get(i);
            // 집에 방문해야할 횟수를 누적
            int cnt = 0;
            // 만약 Pickup 혹은 delivery할 대상이 없으면 그대로 진행
            if (curr.pickup == 0 && curr.delivery == 0) continue;
            //배달 혹은 수거 상자 갯수가 현재집의 갯수보다 커질때까지 반복
            while (nowPickup < curr.pickup || nowDelivery < curr.delivery) {
                // 방문했다는 cnt++
                    cnt++;
                    /*
                    배달 혹은 수거 상자 갯수를 누적
                     */
                    nowDelivery += cap;
                    nowPickup += cap;
            }
            // 현재 집에 배달과 수거를 진행 한 후 추가로 배달 수거 할 수 있는 상자의 갯수를 측정
                nowDelivery -= curr.delivery;
                nowPickup -= curr.pickup;
                distance += (long) curr.idx * cnt * 2;
            }
        return distance;
        }

    public static class House{
        int idx;
        int pickup;
        int delivery;


        @Override
        public String toString() {
            return "House{" +
                    "idx=" + idx +
                    ", pickup=" + pickup +
                    ", delivery=" + delivery +
                    '}';
        }

        public House(int idx, int pickup, int delivery) {
            this.idx = idx;
            this.pickup = pickup;
            this.delivery = delivery;
        }
    }
    /*
    가장 먼집 부터 배달 및 수거를 한다

     */
}
/**
 * 당신은 일렬로 나열된 n개의 집에 택배를 배달하려 합니다.
 * 배달할 물건은 모두 크기가 같은 재활용 택배 상자에 담아 배달하며,
 * 배달을 다니면서 빈 재활용 택배 상자들을 수거하려 합니다.
 *
 * 배달할 택배들은 모두 재활용 택배 상자에 담겨서 물류창고에 보관되어 있고,
 * i번째 집은 물류창고에서 거리 i만큼 떨어져 있습니다.
 * 또한 i번째 집은 j번째 집과 거리 j - i만큼 떨어져 있습니다. (1 ≤ i ≤ j ≤ n)
 *
 * 트럭에는 재활용 택배 상자를 최대 cap개 실을 수 있습니다.
 * 트럭은 배달할 재활용 택배 상자들을 실어 물류창고에서 출발해 각 집에 배달하면서, 빈 재활용 택배 상자들을 수거해 물류창고에 내립니다.
 * 각 집마다 배달할 재활용 택배 상자의 개수와 수거할 빈 재활용 택배 상자의 개수를 알고 있을 때,
 *
 * 트럭 하나로 모든 배달과 수거를 마치고 물류창고까지 돌아올 수 있는 최소 이동 거리를 구하려 합니다.
 * 각 집에 배달 및 수거할 때, 원하는 개수만큼 택배를 배달 및 수거할 수 있습니다.
 */