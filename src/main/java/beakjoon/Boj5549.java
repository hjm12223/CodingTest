package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj5549 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		int[][] si = new int[N + 1][M + 1];
		int[][] sj = new int[N + 1][M + 1];
		int[][] so = new int[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			String line = br.readLine();
			for (int j = 1; j <= M; j++) {
				char c = line.charAt(j - 1);
				si[i][j] = si[i - 1][j] + si[i][j - 1] - si[i - 1][j - 1];
				sj[i][j] = sj[i - 1][j] + sj[i][j - 1] - sj[i - 1][j - 1];
				so[i][j] = so[i - 1][j] + so[i][j - 1] - so[i - 1][j - 1];

				if (c == 'I') si[i][j]++;
				else if (c == 'J') sj[i][j]++;
				else so[i][j]++;
			}
		}

		for (int i = 0; i < K; i++) {
			StringBuilder sb = new StringBuilder();
			st = new StringTokenizer(br.readLine());
			int sx = Integer.parseInt(st.nextToken());
			int sy = Integer.parseInt(st.nextToken());
			int ex = Integer.parseInt(st.nextToken());
			int ey = Integer.parseInt(st.nextToken());
			int siCnt = si[ex][ey] - si[sx - 1][ey] - si[ex][sy - 1] + si[sx - 1][sy - 1];
			int sjCnt = sj[ex][ey] - sj[sx - 1][ey] - sj[ex][sy - 1] + sj[sx - 1][sy - 1];
			int soCnt = so[ex][ey] - so[sx - 1][ey] - so[ex][sy - 1] + so[sx - 1][sy - 1];
			sb.append(sjCnt).append(" ").append(soCnt).append(" ").append(siCnt).append("\n");
			bw.write(sb.toString());
		}
		bw.flush();
		bw.close();
	}
}
