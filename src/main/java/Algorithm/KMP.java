package Algorithm;

public class KMP {

	// LPS 배열 생성
	public static int[] computeLPSArray(String pattern) {
		int length = 0; // 접두사와 접미사가 일치하는 길이
		int i = 1;
		int[] lps = new int[pattern.length()];
		lps[0] = 0; // 첫 번째 값은 항상 0

		while (i < pattern.length()) {
			if (pattern.charAt(i) == pattern.charAt(length)) {
				length++;
				lps[i] = length;
				i++;
			} else {
				if (length != 0) {
					length = lps[length - 1];
				} else {
					lps[i] = 0;
					i++;
				}
			}
		}
		return lps;
	}

	// KMP 알고리즘으로 텍스트에서 패턴 검색
	public static void KMPSearch(String text, String pattern) {
		int[] lps = computeLPSArray(pattern);
		int i = 0; // 텍스트 인덱스
		int j = 0; // 패턴 인덱스

		while (i < text.length()) {
			if (text.charAt(i) == pattern.charAt(j)) {
				i++;
				j++;
			}

			if (j == pattern.length()) {
				System.out.println("패턴이 텍스트에서 발견된 위치: " + (i - j));
				j = lps[j - 1];
			} else if (i < text.length() && text.charAt(i) != pattern.charAt(j)) {
				if (j != 0) {
					j = lps[j - 1];
				} else {
					i++;
				}
			}
		}
	}

	public static void main(String[] args) {
		String text = "abbbaaba";
		String pattern = "aaba";

		System.out.println("텍스트: " + text);
		System.out.println("패턴: " + pattern);

		KMPSearch(text, pattern);
	}
}
