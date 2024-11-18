package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Boj1620 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(st.nextToken()); // 포켓몬의 개수 N개
		int M = Integer.parseInt(st.nextToken()); // 맞춰야 하는 문제의 개수
		Map<Integer, String> index = new HashMap<>(100_001);
		Map<String, Integer> string = new HashMap<>(100_001);
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();

			index.put(i, name);
			string.put(name, i);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			boolean isNumber = checkType(command);
			if (isNumber) {
				bw.write(index.get(Integer.parseInt(command)) + "\n");
			} else {
				bw.write(string.get(command) + "\n");
			}
		}
		bw.flush();
		bw.close();
	}

	public static boolean checkType(String command) {
		try {
			Integer.parseInt(command);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
