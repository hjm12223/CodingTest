package programmers.kakao.Level2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
오전 10:02
오전 10:38
 */
public class Cache2 {
	public static void main(String[] args) {
		// solution(2,new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"});
		solution(0, new String[] {"jeju", "jeju", "jeju", "jeju", "jeju", "jeju", "jeju"});
	}

	public static int solution(int cacheSize, String[] cities) {

		if (cacheSize == 0) {
			return cities.length * 5;
		}

		int answer = 5;

		Queue<String> q = new LinkedList<>(); // 캐시
		
		String[] lowerCities = Arrays.stream(cities)
			.map(String::toLowerCase)
			.toArray(String[]::new);

		for (int i = 0; i < lowerCities.length; i++) {
			if (!q.contains(lowerCities[i])) { // 캐시 미스
				if (q.size() >= cacheSize) { // 캐시 미스이면서 사이즈가 꽉 찼을 경우
					q.poll();
					q.offer(lowerCities[i]);
				} else { // 아닐 경우
					q.offer(lowerCities[i]);
				}
				answer += 5; // 캐시 미스로 인해 +5
			} else {  // 캐시 히트
				q.remove(lowerCities[i]); // 삭제해주고
				q.offer(lowerCities[i]); // 교체
				answer += 1;
			}
		}
		return answer;
	}
}
