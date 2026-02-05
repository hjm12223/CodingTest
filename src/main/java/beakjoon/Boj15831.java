package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj15831 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());

		char[] arr = br.readLine().toCharArray();

		int bCnt = 0;
		int wCnt = 0;
		int left = 0;
		int result = 0;
		for (int right = 0; right < N; right++) {
			if (arr[right] == 'B') bCnt++;
			else wCnt++;

			while (bCnt > B) {
				if (arr[left] == 'B') bCnt--;
				else wCnt--;
				left++;
			}
			if (wCnt >= W) {
				result = Math.max(result, right - left + 1);
			}
		}
		System.out.println(result);
	}
}