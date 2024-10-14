package beakjoon.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Boj11729 {
	static int count = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Integer N = Integer.parseInt(br.readLine());

		hanoi(1,2,N);
		

	}

	private static void hanoi(int start, int target, Integer n) {
		if (n == 1 ) {
			System.out.println(start + " " + target);
			return;
		}
		hanoi(start,6-start-target, n-1);
		System.out.println(start + " " + target);
		hanoi(6-start-target , target, n-1);

	}
}
