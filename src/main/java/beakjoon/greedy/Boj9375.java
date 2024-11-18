package beakjoon.greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Boj9375 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken()); // 테스트케이스
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int k = 0; k < T; k++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); // 옷의 가지수
			Map<String, Integer> clothe = new TreeMap<>();
			int result = 1;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				String name = st.nextToken();
				String type = st.nextToken();
				clothe.put(type, clothe.getOrDefault(type, 0) + 1);
			}
			if (clothe.size() == 1) {
				for (Integer value : clothe.values()) {
					bw.write(value + "\n");
				}
			} else {
				for (Integer value : clothe.values()) {
					result *= value + 1;
				}
				bw.write(result - 1 + "\n");
			}
		}
		bw.flush();
		bw.close();
	}
}
