package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Boj4659 {
	static char[] mo = new char[] {'a', 'e', 'i', 'o', 'u'};
	static int aCnt, iCnt, uCnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			String str = br.readLine();
			if (str.equals("end")) break;
			int jaCnt = 0;
			int moCnt = 0;
			Map<Character, Integer> map = new TreeMap<>();
			for (char c : mo) {
				map.put(c, 0);
			}
			boolean isImportedMo = false;
			boolean isContinuous = false;
			boolean cntContinuous = false;

			for (int i = 0; i < str.length(); i++) {
				char key = str.charAt(i);
				if (moCnt >= 3 || jaCnt >= 3) {
					cntContinuous = true;
					break;
				}
				if (map.containsKey(key)) {
					moCnt++;
					isImportedMo = true;
					jaCnt = 0;
				} else {
					jaCnt++;
					moCnt = 0;
				}
				if (key != 'e' && key != 'o') {
					if (i < str.length() - 1 && key == str.charAt(i + 1)) {
						isContinuous = true;
						break;
					}
				}
			}
			if (moCnt >= 3 || jaCnt >= 3 || !isImportedMo || isContinuous || cntContinuous) {
				sb.append("<").append(str).append(">").append(" is not acceptable.\n");
				continue;
			}
			sb.append("<").append(str).append(">").append(" is acceptable.\n");
		}
		System.out.println(sb);
	}
}
