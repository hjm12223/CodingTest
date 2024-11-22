package Algorithm;

import java.util.ArrayList;
import java.util.List;

public class Permu2 {

	public static void main(String[] args) {
		int[] nums = {1, 2, 3};
		List<List<Integer>> permutations = new ArrayList<>();
		backtrack(nums, new ArrayList<>(), new boolean[nums.length], permutations);

		// 결과 출력
		for (List<Integer> perm : permutations) {
			System.out.println(perm);
		}
	}

	private static void backtrack(int[] nums,
		List<Integer> current,
		boolean[] used,
		List<List<Integer>> permutations) {
		// 현재 순열의 길이가 원본 배열의 길이와 같다면 결과에 추가
		if (current.size() == nums.length) {
			permutations.add(new ArrayList<>(current));
			return;
		}

		// 모든 숫자에 대해 순열 생성
		for (int i = 0; i < nums.length; i++) {
			// 이미 사용된 숫자는 건너뛰기
			if (used[i]) continue;

			// 현재 숫자 선택
			current.add(nums[i]);
			used[i] = true;

			// 재귀적으로 다음 순열 생성
			backtrack(nums, current, used, permutations);

			// 백트래킹: 마지막에 추가된 요소 제거
			current.remove(current.size() - 1);
			used[i] = false;
		}
	}
}
