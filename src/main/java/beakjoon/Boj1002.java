package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1002 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int result = 0;
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int r1 = Integer.parseInt(st.nextToken());

			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());

			int dx = x1 - x2;
			int dy = y1 - y2;
			int distSq = dx * dx + dy * dy;

			int rSum = (r1 + r2) * (r1 + r2);
			int rDiff = (r1 - r2) * (r1 - r2);

			if (distSq == 0) {
				if (r1 == r2) sb.append(-1 + "\n");
				else sb.append(0 + "\n");
			} else if (distSq == rSum || distSq == rDiff) {
				sb.append(1 + "\n");
			} else if (distSq < rSum && distSq > rDiff) {
				sb.append(2 + "\n");
			} else {
				sb.append(0 + "\n");
			}
		}
		System.out.println(sb.toString());
	}
}
