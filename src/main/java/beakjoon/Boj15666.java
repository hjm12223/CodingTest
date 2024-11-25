package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj15666 {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		Arrays.sort(arr);

		int[] out = new int[M];
		dfs(arr, out, 0, 0, M);
		bw.flush();
		bw.close();
	}

	private static void dfs(int[] arr, int[] combination, int start, int depth, int r) throws IOException {
		if (depth == r) {
			// 결과 출력
			for (int num : combination) {
				bw.write(num + " ");
			}
			bw.write("\n");
			return;
		}

		int lastUsed = -1; // 중복된 숫자를 건너뛰기 위해 마지막 사용 숫자를 기록
		for (int i = start; i < arr.length; i++) {
			if (arr[i] != lastUsed) { // 중복 제거
				combination[depth] = arr[i];
				lastUsed = arr[i];
				dfs(arr, combination, i, depth + 1, r); // 같은 숫자를 다시 선택 가능 (start=i)
			}
		}
	}
}