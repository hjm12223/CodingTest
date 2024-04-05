package programmers.kakao.Level2;

import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;
	
public class Cache {
	public static void main(String[] args) {
		solution(3, new String[] {"Jeju", "Jeju", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"});
	}

	public static int solution(int cacheSize, String[] cities) {
		Queue<String> q = new LinkedList<>();
		int answer = 0;
		if (cacheSize == 0) {
			return cities.length * 5;
		}
		for (int i = 0; i < cities.length; i++) {
			String city = cities[i].toUpperCase();
			if (q.size() == cacheSize) {
				if (q.contains(city)) {
					answer += 1; // cache Hit
					q.remove(city);
				} else {
					answer += 5; // cache miss
					q.poll();
				}
			} else if (q.contains(city)) {
				answer++; // 다 안채웠지만 존재하는경우
				q.remove(city);
			} else {
				answer += 5; // 아직 안채웠을 경우
			}
			q.add(city);
		}
		return answer;
	}
}
