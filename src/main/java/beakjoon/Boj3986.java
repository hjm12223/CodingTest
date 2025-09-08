package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Boj3986 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int result = 0;
		while (N-- > 0) {
			char[] arr = br.readLine().toCharArray();
			ArrayDeque<Character> stack = new ArrayDeque<>();
			char lastWord = arr[0];
			stack.push(arr[0]);
			for (int i = 1; i < arr.length; i++) {
				if (lastWord != arr[i]) {
					lastWord = arr[i];
					stack.push(arr[i]);
				} else {
					stack.poll();
					if (!stack.isEmpty()) {
						lastWord = stack.getFirst();
					} else {
						lastWord = 'Z';
					}
				}
			}
			if (stack.isEmpty()) {
				result++;
			}
		}
		System.out.println(result);
	}
}
