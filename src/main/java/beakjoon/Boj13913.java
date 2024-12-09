package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Boj13913 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Queue<Node> q = new LinkedList<>();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		q.offer(new Node(N, 0, new int[100_001]));
		boolean[] isVisited = new boolean[100_001];
		isVisited[N] = true;
		Stack<Integer> stack = new Stack<>();
		while (!q.isEmpty()) {
			Node curr = q.poll();
			if (curr.curr == M) {
				int temp = M;
				bw.write(curr.move + "\n");
				while (temp != N) {
					stack.push(temp);
					temp = curr.tracking[temp];
				}
				stack.push(N);
				break;
			}
			if (curr.curr * 2 < 100_001 && !isVisited[curr.curr * 2]) {
				isVisited[curr.curr * 2] = true;
				curr.tracking[curr.curr * 2] = curr.curr;
				q.offer(new Node(curr.curr * 2, curr.move + 1, curr.tracking));
			}
			if (curr.curr + 1 < 100_001 && !isVisited[curr.curr + 1]) {
				isVisited[curr.curr + 1] = true;
				curr.tracking[curr.curr + 1] = curr.curr;
				q.offer(new Node(curr.curr + 1, curr.move + 1, curr.tracking));
			}
			if (curr.curr - 1 >= 0 && !isVisited[curr.curr - 1]) {
				isVisited[curr.curr - 1] = true;
				curr.tracking[curr.curr - 1] = curr.curr;
				q.offer(new Node(curr.curr - 1, curr.move + 1, curr.tracking));
			}
		}
		while (!stack.isEmpty()) {
			bw.write(stack.pop() + " ");
		}
		bw.flush();
		bw.close();

	}

	private static class Node {
		int curr;
		int move;
		int[] tracking;

		public Node(int curr, int move, int[] tracking) {
			this.curr = curr;
			this.move = move;
			this.tracking = tracking;
		}
	}
}
