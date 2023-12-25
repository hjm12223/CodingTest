package programmers.hash.levelThree;

import java.util.*;

public class BestAlbum {
    public static void main(String[] args) {
        String[] genres=  {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500,600,150,800,2500};

        int[] solution = solution(genres, plays);
        System.out.println("solution = " + Arrays.toString(solution));
    }
    public static int[] solution(String[] genres, int[] plays) {
        if (genres.length ==1){
            return new int[]{1};
        }
        /*
        Flow =
        장르를 먼저 수록 -> 장르 내에서 많이 재생된 노래를 먼저 수록 -> 장르내에서 재생횟수가 같으면 노래중에서 고유 번호가 낮은 노래를 먼저 수록
        HashMap 에 장르를 수록 -> 장르내에서 많이 재생된 노래를 내림차순 하여 정렬 만약 정렬값이 같으면 고유번호(Index) 값을 기준으로 다시 정렬
         */
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        Map<String, Map<Integer,Integer>> music = new HashMap<>();
        for (int i = 0 ; i< genres.length ; i++){ /*  i = 해당 앨범의 고유번호*/
            if (!map.containsKey(genres[i])) {
                map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
                Map<Integer,Integer> temp = new HashMap<>();
                temp.put(i, plays[i]);
                music.put(genres[i], temp);
            }else {
                music.get(genres[i]).put(i,plays[i]);
                map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
            }
        }
        List<String> keySet = new ArrayList<>(map.keySet());
        keySet.sort((s1, s2) -> map.get(s2) - map.get(s1));


        for (String key : keySet) {
            Map<Integer, Integer> map2 = music.get(key);
            List<Integer> genre_key = new ArrayList<>(map2.keySet());

            genre_key.sort((s1, s2) -> map2.get(s2) - map2.get(s1));
            answer.add(genre_key.get(0));
            if (genre_key.size() > 1){
                answer.add(genre_key.get(1));
            }
        }
        return answer.stream().mapToInt(i -> i).toArray();
    }
}
