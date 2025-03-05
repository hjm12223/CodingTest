package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj20546 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int money = Integer.parseInt(br.readLine());
		int BNP = money;
		int[] stocks = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int cnt = 0;
		for (int i = 0; i < stocks.length; i++) {
			if (stocks[i] <= BNP) {
				cnt = BNP / stocks[i];
				BNP %= stocks[i];
			}
		}
		BNP += stocks[stocks.length - 1] * cnt;
		int timing = money;
		int day = 0;
		boolean haveStock = false;
		int stockCnt = 0;
		int upCnt = 0;
		int downCnt = 0;
		int prevStock = stocks[day];
		while (day++ < stocks.length - 1) {
			if (prevStock > stocks[day]) {
				downCnt++;
				upCnt = 0;
			} else if (prevStock < stocks[day]) {
				upCnt++;
				downCnt = 0;
			}
			prevStock = stocks[day];
			if (upCnt == 3) {
				timing += stocks[day] * stockCnt;
				stockCnt = 0;
				downCnt = 0;
				haveStock = false;
			} else if (downCnt >= 3) {
				stockCnt += timing / stocks[day];
				timing %= stocks[day];
				upCnt = 0;
				haveStock = true;
			}
		}

		if (stockCnt > 0 && haveStock) {
			timing += stocks[stocks.length - 1] * stockCnt;
		}
		if (BNP > timing) {
			System.out.println("BNP");
		} else if (BNP < timing) {
			System.out.println("TIMING");
		} else {
			System.out.println("SAMESAME");
		}
	}
}
