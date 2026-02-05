package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Boj14247_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] trees = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] grow = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		Queue<Tree> q = new PriorityQueue<>((o1, o2) ->
			o1.grow - o2.grow
		);
		for (int i = 0; i < N; i++) {
			q.offer(new Tree(trees[i], grow[i]));
		}
		long result = 0;
		for (int i = 0; i < N; i++) {
			Tree tree = q.poll();
			result += tree.height + (long)tree.grow * i;
		}
		System.out.println(result);
	}

	static class Tree {
		int height;
		int grow;

		public Tree(int height, int grow) {
			this.height = height;
			this.grow = grow;
		}
	}
}
