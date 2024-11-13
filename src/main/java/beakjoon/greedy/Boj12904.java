package beakjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Boj12904 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine(); // 시작 문자열
		String T = br.readLine(); // 목적 문자열

		Stack<Character> stack = new Stack<>();

		char[] arr = S.toCharArray();

		System.out.println("stack = " + stack);

	}

	private void ruleOne(Stack<Character> stack) {

	}

	private void ruleTwo(Stack<Character> stack) {
	}
}
