package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Boj2812 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		String target = br.readLine();
		Deque<Character> stack = new ArrayDeque<>();

		int cnt = 0;

		for (int i = 0; i < target.length(); i++) {
			char curr = target.charAt(i);

			while (!stack.isEmpty() && cnt < K && stack.peek() < curr) {

				stack.pop();
				cnt++;
			}
			stack.push(curr);
		}

		while (cnt < K && !stack.isEmpty()) {
			stack.pop();
			cnt++;
		}

		StringBuilder sb = new StringBuilder();
		int length = stack.size();
		for (int i = 0; i < length; i++) {
			sb.append(stack.pollLast());
		}

		System.out.println(sb.toString());
	}

}
