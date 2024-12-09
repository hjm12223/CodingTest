package programmers.kakao.Level2;

import java.util.ArrayList;
import java.util.List;

public class parcel {
	public static void main(String[] args) {
		long solution = solution(4, 5, new int[] {1, 0, 3, 1, 2}, new int[] {0, 3, 0, 4, 0});
		System.out.println(solution(2,7,new int[]{1, 0, 2, 0, 1, 0, 2}	,new int[]{0, 2, 0, 1, 0, 2, 0}));
		System.out.println(solution);
	}
	public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
		long distance = 0;
		List<House> houses = new ArrayList<>();

		for (int i = n ; i > 0 ; i--){
			House house = new House(i, pickups[i-1], deliveries[i-1]);
			houses.add(house);
		}

		int nowDelivery = 0;
		int nowPickup = 0;
		for (int i = 0; i < houses.size(); i++) {
			House curr = houses.get(i);
			int cnt = 0;
			if (curr.pickup == 0 && curr.delivery == 0) continue;
			while (nowPickup < curr.pickup || nowDelivery < curr.delivery) {
				cnt++;
				nowDelivery += cap;
				nowPickup += cap;
			}
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
}