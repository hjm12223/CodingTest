package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1681 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		String L = st.nextToken();
		int cnt = 0;
		int result = 0;
		for (int i = 1; i <= Integer.MAX_VALUE - 1; i++) {
			if (String.valueOf(i).contains(L)) continue;
			else cnt++;
			if (cnt == N) {
				result = i;
				break;
			}
		}
		System.out.println(result);

	}
}
