package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Boj12919 {
	static String target,start;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		start = br.readLine();
		target = br.readLine();

		if (dfs(target)) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
	}

	private static boolean dfs(String current) {
		if (start.length() == current.length()){
			return start.equals(current);
		}

		if (current.endsWith("A")) {
			String substring = current.substring(0, current.length() - 1);
			if (dfs(substring)){
				return true;
			}
		}
		if (current.startsWith("B")){
			String string = new StringBuilder(current).reverse().deleteCharAt(current.length() - 1).toString();
			System.out.println(string);
			if (dfs(string)){
				return true;
			}
		}
		return false;
	}
}