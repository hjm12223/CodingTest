package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Boj2877 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());

		Queue<String> q = new ArrayDeque<>();

		q.offer("4");
		q.offer("7");

		int cnt = 0;
		while (!q.isEmpty()) {
			String curr = q.poll();
			cnt++;
			if (cnt == K) {
				System.out.println(curr);
				return;
			}
			q.offer(curr + "4");
			q.offer(curr + "7");
		}
	}
}
