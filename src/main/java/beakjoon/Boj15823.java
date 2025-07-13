package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Boj15823 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());

		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int left = 1;
		int right = N;

		int result = 1;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (check(mid, arr, M)) {
				result = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		System.out.println(result);
	}

	private static boolean check(int mid, int[] arr, int m) {
		int cnt = 0;
		int idx = 0;
		while (idx + mid <= arr.length) {
			if (slideWindow(idx, idx + mid - 1, arr)) {
				cnt++;
				idx += mid;
			} else {
				idx++;
			}

			if (cnt >= m) return true;
		}
		return false;
	}

	private static boolean slideWindow(int start, int end, int[] arr) {
		Set<Integer> freq = new HashSet<>();
		for (int i = start; i <= end; i++) {
			if (!freq.add(arr[i])) {
				return false;
			}
		}
		return true;
	}
}

/*
1.N개의 카드가 한 줄로 진열되어 있으며, 카드의 자리는 바꿀 수 없다.
2.좌/우로 연속한 카드들을 묶어 하나의 '카드 팩'을 구성할 수 있다.
3.주띵이는 정확히 M개의 카드 팩을 구매해야 한다.
4.각 카드 팩을 구성하는 카드의 수는 일치해야 한다.
5.한 카드 팩안에 같은 종류의 카드가 두 장 이상 존재해서는 안 된다.
6.하나의 카드가 여러 카드팩에 속할 수 는 없다.
 */
