package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2003 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 주어진 수
		int M = Integer.parseInt(st.nextToken()); // 타겟

		int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		int left = 0;
		int right = 0;
		int sum = 0;
		int count = 0;

		while (right < N) {
			sum += arr[right]; // 오른쪽 포인터 이동하면서 합 추가

			while (sum > M) { // 합이 M을 초과하면 왼쪽 포인터 이동
				sum -= arr[left];
				left++;
			}

			if (sum == M) { // 합이 M과 같으면 카운트 증가
				count++;
			}

			right++; // 오른쪽 포인터 증가
		}

		System.out.println(count);
	}
}
