package beakjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Boj1541 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str = br.readLine();

		StringBuilder sb = new StringBuilder();
		Queue<Integer> value = new LinkedList<>();
		Deque<Character> operations = new ArrayDeque<>();

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (!isOperation(c)) {
				sb.append(c);
			} else {
				if (!operations.isEmpty()) {
					value.offer(Integer.parseInt(operations.poll() + sb.toString()));
				} else {
					value.offer(Integer.parseInt(sb.toString()));
				}
				operations.offerLast(c);
				sb = new StringBuilder();
			}
		}
		if (operations.isEmpty()) {
			System.out.println(sb.toString());
			return;
		}
		value.offer(Integer.parseInt(operations.poll() + sb.toString()));
		int result = 0;

		while (!value.isEmpty()) {
			Integer curr = value.poll();
			if (curr < 0) {
				result += curr;
				while (!value.isEmpty() && value.peek() > 0) {
					result -= value.poll();
				}
			} else {
				result += curr;
			}
		}
		System.out.println(result);
	}

	static boolean isOperation(Character str) {
		if (str.equals('-') || str.equals('+') || str.equals('/') || str.equals('*')) return true;
		else return false;
	}
}