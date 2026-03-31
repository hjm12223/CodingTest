package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//

public class Boj23323 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			long cnt = 0;
			long n = Long.parseLong(st.nextToken());
			long m = Long.parseLong(st.nextToken());
			while (n > 0) {
				if (n == 1) {
					cnt += m;
					m = 0;
				}
				if (n % 2 != 0 && m > 0) {
					n += 1;
					m--;
				}
				n /= 2;
				cnt++;
			}
			sb.append(cnt + "\n");
		}
		System.out.println(sb);
	}
}
