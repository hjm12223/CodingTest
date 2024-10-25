package programmers.kakao.Level2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LastSong {
	public static void main(String[] args) {
		String solution = solution("ABC#D", new String[] {"12:00,12:03,NAME,ABC#D"});
		// String solution = solution("CDCDCD", new String[] {"12:00,12:10,NAME,CDCDCDE", "12:30,12:50,NA,CD"});
		// String solution = solution("CC#BCC#BCC#BCC#B",
		// 	new String[] {"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"});
		// String solution = solution("A", new String[] {"12:00,12:01,Sing,A", "12:00,12:01,Song,A"});
		// String solution = solution("A", new String[] {"12:00,12:01,Song,A"});
		System.out.println("solution = " + solution);
	}

	public static String solution(String m, String[] musicinfos) {
		List<Music> musicList = new ArrayList<>();

		for (String musician : musicinfos) { // N 번만큼 반복
			int order = 1;
			String[] arr = musician.split(",");
			String[] startTimeSplit = arr[0].split(":");
			String[] endTimeSplit = arr[1].split(":");
			Integer startTime =
				(Integer.parseInt(startTimeSplit[0]) * 60) + Integer.parseInt(startTimeSplit[1]);
			Integer endTime =
				(Integer.parseInt(endTimeSplit[0]) * 60) + Integer.parseInt(endTimeSplit[1]);
			String author = arr[2];
			String[] songSplit = replace(arr[3]).split("");
			// 전처리 단계
			int size = m.length();
			int total = endTime - startTime;

			for (int i = 0; i < total; i++) { // M번 만큼 반복

				boolean isFind = false;
				StringBuilder str = new StringBuilder();
				int j = i;
				int count = 0;

				while (true) { // 모듈러 연산을 통해 최대 네오가 기억하는 길이만큼 문자열 연산을 해줌
					str.append(songSplit[j++ % songSplit.length]);
					if (str.toString().equals(replace(m))) { // 만약 해당 곡의 정보가 네오가 기억하는 곡의 정보와 같을경우 더해줌
						musicList.add(new Music(total, author, order));
						order++;
						isFind = true;
						break;
					}
					if (++count == total || count == size) break;
					// 1. 만약 음악 재생시간 보다 작을경우 break
				}
				if (isFind) break;
				// 곡이 존재한다면 해당 N번째 반복문을 종료
			}
		}
		Collections.sort(musicList);

		if (musicList.isEmpty()) {
			return "(None)"; // 네오가 기억하는 곡이 없을 경우
		} else {
			return musicList.get(0).title; // 존재할 경우
		}
	}

	public static String replace(String melody) {
		return melody.replace("C#", "c")
			.replace("D#", "d")
			.replace("F#", "f")
			.replace("G#", "g")
			.replace("A#", "a");
		/*
		"ABC", "C#DEFGAB" 이 경우 ABC# 이기 떄문에 해당 #을 포함한 문자를 소문자로 변환하여 처리
		 */
	}

	public static class Music implements Comparable<Music> {
		int songSize;
		String title;
		Integer order;

		public Music(int songSize, String title, Integer order) {
			this.songSize = songSize;
			this.title = title;
			this.order = order;
		}

		@Override
		public int compareTo(Music o) {
			if (o.songSize == this.songSize) {
				return this.order - o.order;
			}
			return o.songSize - this.songSize;
		}
	}
	/**
	 1. 네오가 기억한 음표 m은 1439 이하의 문자열로 제공
	 2. musicInfo 는 100 개 이하의 곡정보를 담은배열로 {시작시간, 끝시간, 제목, 음표정보} 가 담김



	 1.C, C#, D, D#, E, F, F#, G, G#, A, A#, B 12개의 음이 사용됌
	 2.음악의 시간보다 길게 재생된경우 반복해서 재생되는거임 -> 슬라이딩 윈도우?
	 3.00:00을 넘겨서 재생되는 음악은 없다
	 4.같은 음이 존재하는경우 음악의 길이가 가장 긴 음악을 반환
	 5.없을 경우 None 을 반환

	 제한사항
	 1. 곡의 길이만큼만 재생
	 i.ABCD 3분 일 경우 ABC 만큼 재생
	 2. 만약 일치하는 곡이 여러개 존재하는 경우 해당 곡의 길이기준으로 내림차순 정렬
	 i. 같을 경우 먼저 입력된 음악의 순서로 정렬


	 */
}