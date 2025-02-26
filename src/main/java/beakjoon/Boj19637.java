package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj19637 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] title = new String[N];
		int[] value = new int[N];
		for (int i = 0; i < N; i++) {
			String[] split = br.readLine().split(" ");
			title[i] = split[0];
			value[i] = Integer.parseInt(split[1]);
		}
		for (int i = 0; i < M; i++) {
			int val = Integer.parseInt(br.readLine());
			int index = binarySearch(value, val);
			bw.write(title[index]);
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}

	private static int binarySearch(int[] value, int val) {
		int left = 0;
		int right = value.length - 1;
		while (left < right) {
			int mid = (left + right) / 2;
			if (value[mid] >= val) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}
}