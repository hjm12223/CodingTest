import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj15686 {
	static int N, result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		result = Integer.MAX_VALUE;
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<int[]> locations = new ArrayList<>();
		List<int[]> homes = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int value = Integer.parseInt(st.nextToken());
				if (value == 1) {
					homes.add(new int[] {i, j});
				} else if (value == 2) {
					locations.add(new int[] {i, j});
				}
			}
		}

		List<List<int[]>> comb = new ArrayList<>();
		comb(comb, locations, M, 0, new ArrayList<>());
		for (List<int[]> chickens : comb) {
			int tempResult = 0;
			for (int[] home : homes) {
				int temp = Integer.MAX_VALUE;
				for (int[] chicken : chickens) {
					temp = Math.min(Math.abs(home[0] - chicken[0]) + Math.abs(home[1] - chicken[1]), temp);
				}
				tempResult += temp;
			}
			result = Math.min(tempResult, result);
		}
		System.out.println(result);
	}

	private static void comb(List<List<int[]>> comb, List<int[]> locations, int r, int start, List<int[]> temp) {
		if (temp.size() == r) {
			comb.add(new ArrayList<>(temp));
			return;
		}
		for (int i = start; i < locations.size(); i++) {
			temp.add(locations.get(i));
			comb(comb, locations, r, i + 1, temp);
			temp.remove(temp.size() - 1);
		}
	}

}
