package beakjoon.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1074 {
	static int count;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		int line = (int)Math.pow(2,N);

		Queue<String> queue = new LinkedList<>();
		PriorityQueue<String> q = new PriorityQueue<>(
			new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return o1.length() - o2.length();
				}
			}
		);


		func(line, row, col);
		System.out.println(count);
	}

	// 재귀적으로 Z 탐색하는 함수
	private static void func(int size, int row, int col) {
		if (size == 1) {
			// 크기가 1일 때는 탐색이 완료됨
			return;
		}

		// 배열을 4개의 사분면으로 나누기 위해 중간점을 계산
		int half = size / 2;

		// 1사분면: 좌상
		if (row < half && col < half) {
			func(half, row, col);  // 해당 사분면으로 탐색
		}
		// 2사분면: 우상
		else if (row < half && col >= half) {
			count += half * half;  // 1사분면의 탐색 크기 더하기
			func(half, row, col - half);  // 좌표 조정
		}
		// 3사분면: 좌하
		else if (row >= half && col < half) {
			count += 2 * half * half;  // 1사분면과 2사분면의 탐색 크기 더하기
			func(half, row - half, col);  // 좌표 조정
		}
		// 4사분면: 우하
		else {
			count += 3 * half * half;  // 앞의 3사분면의 탐색 크기 더하기
			func(half, row - half, col - half);  // 좌표 조정
		}
	}
}
