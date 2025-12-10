package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj13413 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			char[] init = br.readLine().toCharArray();
			char[] target = br.readLine().toCharArray();
			int wCnt = 0;
			int bCnt = 0;
			for (int i = 0; i < N; i++) {
				if (init[i] != target[i]) {
					if (init[i] == 'W') bCnt++;
					else wCnt++;
				}
			}
			bw.write(Math.max(bCnt, wCnt) + "\n");
		}
		bw.flush();
		bw.close();
	}
}
