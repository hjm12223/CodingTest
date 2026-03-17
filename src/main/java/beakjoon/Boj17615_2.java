package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj17615_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		String s = br.readLine();
		int red = 0;
		int blue = 0;

		for (char c : s.toCharArray()) {
			if (c == 'R') red++;
			else blue++;
		}

		int ans = Integer.MAX_VALUE;

		int cnt = 0;
		int i = 0;
		while (i < N && s.charAt(i) == 'R') i++;
		for (; i < N; i++) if (s.charAt(i) == 'R') cnt++;
		ans = Math.min(ans, cnt);

		cnt = 0;
		i = N - 1;
		while (i >= 0 && s.charAt(i) == 'R') i--;
		for (; i >= 0; i--) if (s.charAt(i) == 'R') cnt++;
		ans = Math.min(ans, cnt);

		cnt = 0;
		i = 0;
		while (i < N && s.charAt(i) == 'B') i++;
		for (; i < N; i++) if (s.charAt(i) == 'B') cnt++;
		ans = Math.min(ans, cnt);

		cnt = 0;
		i = N - 1;
		while (i >= 0 && s.charAt(i) == 'B') i--;
		for (; i >= 0; i--) if (s.charAt(i) == 'B') cnt++;
		ans = Math.min(ans, cnt);

		System.out.println(ans);
	}
}
