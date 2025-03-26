package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16235 {
	static int[][] moves = new int[][] {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

	// (r-1, c-1), (r-1, c), (r-1, c+1), (r, c-1), (r, c+1), (r+1, c-1), (r+1, c), (r+1, c+1)
	/*
	나무는 총 100개
	100_000* 100 = 1_000_000

	가장 처음에 양분은 모든 칸에 5만큼 들어있다.
	봄에는 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가한다.
	각각의 나무는 나무가 있는 1×1 크기의 칸에 있는 양분만 먹을 수 있다.
	하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹는다.
	만약, 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽는다.
	여름에는 봄에 죽은 나무가 양분으로 변하게 된다.
	각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다. 소수점 아래는 버린다.
	가을에는 나무가 번식한다. 번식하는 나무는 나이가 5의 배수이어야 하며,
	인접한 8개의 칸에 나이가 1인 나무가 생긴다.
	어떤 칸 (r, c)와 인접한 칸은 (r-1, c-1), (r-1, c), (r-1, c+1), (r, c-1), (r, c+1), (r+1, c-1), (r+1, c), (r+1, c+1) 이다.
	상도의 땅을 벗어나는 칸에는 나무가 생기지 않는다.
    겨울에는 S2D2가 땅을 돌아다니면서 땅에 양분을 추가한다. 각 칸에 추가되는 양분의 양은 A[r][c]이고, 입력으로 주어진다.
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 판의 크기 N * N
		int M = Integer.parseInt(st.nextToken()); // 상도가 심은 나무의 정보의 갯수
		int K = Integer.parseInt(st.nextToken()); // K년 이후 나무의 갯수
		int[][] initTree = new int[N][N];
		for (int i = 0; i < N; i++)
			Arrays.fill(initTree[i], 5);

		int[][] plusTree = new int[N][N];

		for (int i = 0; i < N; i++) {
			plusTree[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		List<Tree> trees = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int old = Integer.parseInt(st.nextToken());
			trees.add(new Tree(x, y, old));
		}

		trees.sort((o1, o2) -> o1.old - o2.old);

		while (K-- > 0) {
			List<Tree> aliveTrees = new ArrayList<>();
			List<Tree> deadTrees = new ArrayList<>();
			Queue<Tree> newTrees = new ArrayDeque<>();

			// 봄
			for (Tree tree : trees) {
				if (initTree[tree.x][tree.y] >= tree.old) {
					initTree[tree.x][tree.y] -= tree.old;
					aliveTrees.add(new Tree(tree.x, tree.y, tree.old + 1)); // 나이 증가 후 저장
				} else {
					deadTrees.add(tree); // 먹지 못한 나무는 죽음
				}
			}

			// 여름
			for (Tree tree : deadTrees) {
				initTree[tree.x][tree.y] += tree.old / 2;
			}

			// 가을
			for (Tree tree : aliveTrees) {
				if (tree.old % 5 == 0) { // 5의 배수일 때 번식
					for (int[] move : moves) {
						int nx = tree.x + move[0];
						int ny = tree.y + move[1];
						if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
							newTrees.offer(new Tree(nx, ny, 1)); // 번식된 나무 추가
						}
					}
				}
			}

			while (!newTrees.isEmpty()) {
				aliveTrees.add(newTrees.poll());
			}

			aliveTrees.sort((o1, o2) -> o1.old - o2.old);

			// 겨울
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					initTree[i][j] += plusTree[i][j];
				}
			}

			trees = aliveTrees;
		}
		System.out.println(trees.size());
	}

	private static class Tree {
		int x;
		int y;
		int old;

		public Tree(int x, int y, int old) {
			this.x = x;
			this.y = y;
			this.old = old;
		}

		@Override
		public String toString() {
			return "Tree{" +
				"x=" + x +
				", y=" + y +
				", old=" + old +
				'}';
		}
	}
}
