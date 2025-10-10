package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Boj19583 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] times = br.readLine().split(" ");

		Integer startTime = makeTime(times[0]);
		Integer endTime = makeTime(times[1]);
		Integer endStream = makeTime(times[2]);
		Map<String, Integer> map = new HashMap<>();
		int result = 0;
		String line;
		while ((line = br.readLine()) != null) {
			String[] str = line.split(" ");
			Integer time = makeTime(str[0]);
			String nickName = str[1];
			if (time <= startTime) {
				map.put(nickName, 0);
			}
			if (time >= endTime && time <= endStream) {
				if (map.containsKey(nickName)) {
					map.put(nickName, 1);
				}
			}
		}

		for (Integer value : map.values()) {
			if (value == 1) result++;
		}
		System.out.println(result);
	}

	private static Integer makeTime(String time) {
		String[] times = time.split(":");
		String hour = times[0];
		String minute = times[1];
		int result = Integer.parseInt(hour) * 100 + Integer.parseInt(minute);
		System.out.println(result);
		return result;

	}
}
