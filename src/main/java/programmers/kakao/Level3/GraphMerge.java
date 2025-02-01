package programmers.kakao.Level3;

import java.util.ArrayDeque;
import java.util.Arrays;

public class GraphMerge {
	static int pointer, N;
	static boolean[] list;

	public static void main(String[] args) {
		// String solution = solution(8, 2, new String[] {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"});
		String solution1 = solution(8, 2,
			new String[] {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"});
		// System.out.println("solution = " + solution);
		System.out.println("solution1 	= " + solution1);
	}

	private static String solution(int n, int k, String[] cmd) {
		// n = 표의 행 개수  k = 처음 선택된 행의 번호 cmd = 커맨드의 수,
		N = n;
		ArrayDeque<Integer> deleted = new ArrayDeque<>();
		list = new boolean[N];
		Arrays.fill(list, true);
		pointer = k;
		for (int i = 0; i < cmd.length; i++) {
			String command = cmd[i];
			if (command.length() > 1) {
				move(command);
			} else if (command.equals("C")) {
				deleted.addLast(pointer);
				list[pointer] = false;
				N--;
				boolean found = false;
				for (int j = pointer + 1; j < list.length; j++) {
					if (list[j]) {
						pointer = j;
						found = true;
						break;
					}
				}
				if (!found) {
					for (int j = pointer - 1; j >= 0; j--) {
						if (list[j]) {
							pointer = j;
							break;
						}
					}
				}
			} else if (command.equals("Z")) {
				N++;
				Integer curr = deleted.pollLast();
				list[curr] = true;
			}
		}

		StringBuilder sb = new StringBuilder();
		for (boolean b : list) {
			if (b) {
				sb.append("O");
			} else {
				sb.append("X");
			}
		}
		return sb.toString();
	}

	private static void move(String command) {
		String[] split = command.split(" ");
		String instruction = split[0];
		int value = Integer.parseInt(split[1]);
		if (instruction.equals("U")) {
			int moved = 0;
			for (int i = pointer - 1; i >= 0 && moved < value; i--) {
				if (list[i]) {
					moved++;
					if (moved == value) {
						pointer = i;
						break;
					}
				}
			}
		} else if (instruction.equals("D")) {
			int moved = 0;
			for (int i = pointer + 1; i < list.length && moved < value; i++) {
				if (list[i]) {
					moved++;
					if (moved == value) {
						pointer = i;
						break;
					}
				}
			}
		}
	}
}
