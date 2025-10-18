package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj11501_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(st.nextToken());

		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int maxDay = 0;
			int[] stocks = new int[N];
			st = new StringTokenizer(br.readLine());
			int result = 0;

			for (int i = 0; i < N; i++) {
				stocks[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = N - 1; i >= 0; i--) {
				if (stocks[i] > maxDay) {
					maxDay = stocks[i];
				} else {
					result += maxDay - stocks[i];
				}
			}
			bw.write(result + "\n");
		}
		bw.flush();
		bw.close();
	}
}
