package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj16564 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		if (N == 1) {
			System.out.println(arr[0] + K);
			return;
		}
		Arrays.sort(arr);
		int fristValue = arr[0];
		int cnt = 1;

		for (int i = 1; i < arr.length; i++) {
			int cal = (arr[i] - fristValue) * cnt;
			if (cal <= K) {
				fristValue = arr[i];
				cnt++;
				K -= cal;
			} else {
				fristValue += K / cnt;
				K = 0;
				break;
			}
		}
		if (K > 0) {
			fristValue += K / cnt;
		}
		System.out.println(fristValue);
	}
}

/*
K는 최대 10억
K를 기반으로 계산하는것이 아닌 평균을 구해서 해당 평균값
N개의 수를 Sorting 하여 Sorting된 값을 기반으로 i+1 보다 큰값이
 */