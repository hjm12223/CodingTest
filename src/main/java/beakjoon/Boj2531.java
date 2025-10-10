package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Boj2531 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		Map<Integer, Integer> map = new HashMap<>();
		int cnt = 0;
		for (int i = 0; i < k; i++) {
			if (map.getOrDefault(arr[i], 0) == 0) cnt++;
			map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
		}

		int result = cnt + (map.getOrDefault(c, 0) == 0 ? 1 : 0);

		for (int i = 1; i < N; i++) {
			int remove = arr[i - 1];
			map.put(remove, map.get(remove) - 1);
			if (map.get(remove) == 0) cnt--;

			int add = arr[(i + k - 1) % N];
			if (map.getOrDefault(add, 0) == 0) cnt++;
			map.put(add, map.getOrDefault(add, 0) + 1);

			result = Math.max(result, cnt + (map.getOrDefault(c, 0) == 0 ? 1 : 0));
		}

		System.out.println(result);
	}
}
