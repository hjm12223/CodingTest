package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Boj22860 {
	static Map<String, Folder> folderMap = new TreeMap<>();
	static Set<String> fileSet;
	static int fileCount;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		folderMap.put("main", new Folder());

		for (int i = 0; i < N + M; i++) {
			st = new StringTokenizer(br.readLine());
			String P = st.nextToken();
			String F = st.nextToken();
			int C = Integer.parseInt(st.nextToken());

			folderMap.putIfAbsent(P, new Folder());
			Folder parent = folderMap.get(P);
			if (C == 1) {
				folderMap.putIfAbsent(F, new Folder());
				parent.subFolder.add(folderMap.get(F));
			} else {
				parent.files.add(F);
			}
		}
		int Q = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			String query = br.readLine();
			String[] path = query.split("/");
			String folderName = path[path.length - 1];

			Folder start = folderMap.get(folderName);
			fileSet = new HashSet<>();
			fileCount = 0;

			dfs(start);

			sb.append(fileSet.size()).append(" ").append(fileCount).append("\n");
		}
		System.out.println(sb);
	}

	private static void dfs(Folder folder) {
		for (String file : folder.files) {
			fileSet.add(file);
			fileCount++;
		}
		for (Folder sub : folder.subFolder) {
			dfs(sub);
		}
	}

	static class Folder {
		List<Folder> subFolder = new ArrayList<>();
		List<String> files = new ArrayList<>();

	}
}