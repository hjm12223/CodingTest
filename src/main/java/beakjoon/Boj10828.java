package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Boj10828 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Deque<Integer> stack = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		while (N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String commands = st.nextToken();
			if (!commands.equals("push")) {
				switch (commands) {
					case "top":
						if (stack.isEmpty()) sb.append("-1\n");
						else sb.append(stack.peekLast() + "\n");
						break;
					case "pop":
						if (stack.isEmpty()) sb.append("-1\n");
						else sb.append(stack.pollLast() + "\n");
						break;
					case "size":
						sb.append(stack.size() + "\n");
						break;
					case "empty":
						if (stack.isEmpty())
							sb.append("1\n");
						else
							sb.append("0\n");
						break;
				}
			} else {
				int value = Integer.parseInt(st.nextToken());
				stack.addLast(value);
			}
		}
		System.out.println(sb);
	}
}
