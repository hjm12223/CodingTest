package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj16401 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int M = Integer.parseInt(st.nextToken()); // 조카의 수 1,000,000
		int N = Integer.parseInt(st.nextToken()); // 과자의 갯수 1,000,000
		// N*M  = 1,000,000^2 완탐불가, 최대 NLogN -> PQ, BinarySearch , DP등
		// 과자는 여러개가 존재하며 해당 과자들은 분해할 수 있다
		// 하지만 해당 과자를 합쳐서 조카들에게는 나눠줄 수 없다
		// 과자의 개수가 조카의 수 보다 많은경우는  N - M 의 존재하는 과자가 최대값이 된다
		// 조카의 수가 과자보다 많을경우는?
		long[] arr = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
		long left = 1;
		long right = 1000000000;
		long result = 0;
		// 미드 값을 토대로 해당 배열에 존재하는
		while (left <= right) {
			long mid = left + (right - left) / 2;
			if (isMatch(mid, arr, M)) {
				result = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		System.out.println(result);
	}

	private static boolean isMatch(long mid, long[] arr, int M) {
		long cnt = 0;
		for (long bread : arr) {
			cnt += bread / mid;
		}
		return cnt >= M;
	}
}
