package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1477_1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 휴게소의 개수
		int M = Integer.parseInt(st.nextToken()); // 더 짓고자 하는 휴게소
		int L = Integer.parseInt(st.nextToken()); // 최대 길이

		int[] arr = new int[N + 2];
		arr[0] = 1;
		arr[N + 1] = L;
		int[] array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		for (int i = 1; i < N; i++) {
			arr[i] = array[i - 1];
		}
		Arrays.sort(arr);

		int left = 1;
		int right = L - 1;
		int result = 0;

		while (left <= right) {
			int mid = (left + right) / 2;
			if (check(mid, arr, M)) {
				result = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		System.out.println(result);
	}

	private static boolean check(int mid, int[] arr, int m) {
		int cnt = 0;
		for (int i = 1; i < arr.length; i++) {
			int gap = arr[i] - arr[i - 1];
			if (gap > mid) {
				cnt += (gap - 1) / mid;
			}
		}
		return cnt <= m;
	}
}
