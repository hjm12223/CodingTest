package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj4256 {
	static int[] preorder, inorder, inorderIdx;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			preorder = new int[N + 1];
			inorder = new int[N + 1];
			inorderIdx = new int[N + 1];
			sb = new StringBuilder();

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				preorder[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				inorder[i] = Integer.parseInt(st.nextToken());
				inorderIdx[inorder[i]] = i;
			}
			postOrder(0, N - 1, 0, N - 1);
			System.out.println(sb.toString().trim());
		}

	}

	private static void postOrder(int preStart, int preEnd, int inStart, int inEnd) {
		if (preStart > preEnd || inStart > inEnd) return;

		int root = preorder[preStart];
		int rootIdx = inorderIdx[root];
		int leftSize = rootIdx - inStart;

		postOrder(
			preStart + 1,
			preStart + leftSize,
			inStart,
			rootIdx - 1
		);

		postOrder(
			preStart + leftSize + 1,
			preEnd,
			rootIdx + 1,
			inEnd
		);

		sb.append(root).append(" ");
	}
}
