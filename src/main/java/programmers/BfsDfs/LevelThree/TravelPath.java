package programmers.BfsDfs.LevelThree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class TravelPath {
	static boolean[] isVisited;
	static List<String> output;
	static Stack<Node> stack;

	public static void main(String[] args) {
		String[] solution = solution(
			new String[][] {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}});
		System.out.println("solution = " + Arrays.toString(solution));
	}

	public static String[] solution(String[][] tickets) {

		stack = new Stack<>(); // DFS를 구현하기 위한 Stack 자료구조
		isVisited = new boolean[tickets.length]; // 방문 표시

		for (String[] ticket : tickets) {
			stack.push(new Node(ticket[0], ticket[1])); // 노드에 삽입
		}
		output = new ArrayList<>(); // 출력물을 저장할 output

		dfs(0, stack.get(0).from, stack.get(0).from, tickets.length); // DFS
		Collections.sort(output); // 오름차순 정렬
		return output.get(0).split(" "); // 정답반환
	}

	private static void dfs(int depth, String now, String path, int r) {
		if (depth == r) {
			output.add(path); // dfs 종료
		}
		for (int i = 0; i < r; i++) {
			if (!isVisited[i] && now.equals(stack.get(i).from)) { // 방문을 안했으며 현재(INC) 이 stack.get(i) 의 from 과같으면?
				isVisited[i] = true; // 방문처리
				Node node = stack.get(i); // 노드를 빼서
				dfs(depth + 1, node.to, path + " " + node.to, r); // "ICN ATL" 과 같은형식의 문자열로 만들어준다
				isVisited[i] = false; // 방문처리 해제후 다시 올라간다
			}
		}
	}

	public static class Node {
		String from;
		String to;

		public Node(String from, String to) {
			this.from = from;
			this.to = to;
		}
	}
}
