package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Boj20551_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		Map<Integer, Integer> map = new HashMap<>();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(br.readLine());

		Arrays.sort(arr);
		for (int i = 0; i < N; i++) {
			if (!map.containsKey(arr[i])) {
				map.put(arr[i], i);
			}
		}
		for (int i = 0; i < M; i++) {
			int key = Integer.parseInt(br.readLine());
			if (map.containsKey(key)) {
				sb.append(map.get(key)).append("\n");
			} else {
				sb.append(-1).append("\n");
			}
		}
		System.out.println(sb);
	}
}
