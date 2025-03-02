package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj20207 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int[] arr = new int[366];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			for (int s = start; s <= end; s++) {
				arr[s]++;
			}
		}
		int result = 0;
		int width = 0;
		int height = 0;
		for (int i = 1; i <= arr.length - 1; i++) {
			if (arr[i] > 0) {
				height = Math.max(arr[i], height);
				width++;
			} else {
				result += height * width;
				width = 0;
				height = 0;
			}
		}
		if (width != 0) {
			result += width * height;
		}
		System.out.println(result);

	}

	private static class Node {
		int start;
		int end;

		public Node(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			return "Node{" +
				"start=" + start +
				", end=" + end +
				'}';
		}
	}

}
