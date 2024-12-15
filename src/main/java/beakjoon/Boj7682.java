package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj7682 {
	static boolean isOkay;
	static int oResult;
	static int xResult;
	static int oCnt;
	static int xCnt;

	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			isOkay = false;
			oResult = 0;
			xResult = 0;
			String str = br.readLine();
			if (str.equals("end") || str.isEmpty()) break;
			String[] arr = str.split("");
			String[][] takTok = makeTikTakTok(arr);
			if (!countTakTok(takTok)) {
				bw.write("invalid");
				bw.newLine();
				continue;
			}
			if (checkTakTok(takTok)) {
				bw.write("valid");
			} else {
				bw.write("invalid");
			}
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}

	private static boolean checkTakTok(String[][] takTok) {
		boolean xWins = false;
		boolean oWins = false;

		for (int i = 0; i < 3; i++) {
			if (takTok[i][0].equals(takTok[i][1]) && takTok[i][1].equals(takTok[i][2]) && !takTok[i][0].equals(".")) {
				if (takTok[i][0].equals("X")) xWins = true;
				if (takTok[i][0].equals("O")) oWins = true;
			}
			if (takTok[0][i].equals(takTok[1][i]) && takTok[1][i].equals(takTok[2][i]) && !takTok[0][i].equals(".")) {
				if (takTok[0][i].equals("X")) xWins = true;
				if (takTok[0][i].equals("O")) oWins = true;
			}
		}

		// 대각선 검사
		if (takTok[0][0].equals(takTok[1][1]) && takTok[1][1].equals(takTok[2][2]) && !takTok[0][0].equals(".")) {
			if (takTok[0][0].equals("X")) xWins = true;
			if (takTok[0][0].equals("O")) oWins = true;
		}
		if (takTok[0][2].equals(takTok[1][1]) && takTok[1][1].equals(takTok[2][0]) && !takTok[0][2].equals(".")) {
			if (takTok[0][2].equals("X")) xWins = true;
			if (takTok[0][2].equals("O")) oWins = true;
		}

		if (xWins && oWins) return false;

		if (xWins && xCnt != oCnt + 1) return false;
		if (oWins && xCnt != oCnt) return false;

		if (!xWins && !oWins && isOkay) return true;

		if (!xWins && !oWins && !isOkay) return false;

		return true;
	}

	private static boolean countTakTok(String[][] takTok) {
		oCnt = 0;
		xCnt = 0;

		for (String[] row : takTok) {
			for (String cell : row) {
				if (cell.equals("O")) oCnt++;
				else if (cell.equals("X")) xCnt++;
			}
		}

		if (xCnt < oCnt || xCnt - oCnt > 1) {
			return false; // 개수 규칙 위반
		}

		isOkay = (xCnt + oCnt == 9);
		return true;
	}

	private static String[][] makeTikTakTok(String[] arr) {
		String[][] temp = new String[3][3];
		for (int i = 0; i < arr.length; i++) {
			temp[i / 3][i % 3] = arr[i];
		}
		return temp;
	}
}
