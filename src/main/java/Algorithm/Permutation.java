package Algorithm;

/**
 * 순열 알고리즘
 */
public class Permutation {
	public static int serialNum = 1; // 0에서 1로 변경

	public static void main(String[] args) {

		int[] arr = new int[] {1, 2, 3};

		boolean[] isVisited = new boolean[arr.length];
		for (int r = 1; r <= arr.length; r++) { // n이 1부터 시작하도록 변경
			int[] out = new int[r];
			permutation(arr, out, isVisited, r, 0);
		}
	}

	private static void permutation(int[] arr, int[] out, boolean[] isVisited, int r, int depth) {
		if (r == depth) {
			for (int num : out) {
				System.out.println("num = " + num + " ");
			}
			System.out.println();
			System.out.println("serialNum++ = " + serialNum++);
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			if (!isVisited[i]) {
				isVisited[i] = true;
				out[depth] = arr[i];
				permutation(arr, out, isVisited, r, depth + 1);
				isVisited[i] = false;
			}
		}
	}
}