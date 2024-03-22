package programmers.kakao.Level2;

import java.util.*;
/*
BigO(2^4 * n * + q * log n)
 */
public class SearchRank {
    public static void main(String[] args) {
        solution(new String[]{"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"}, new String[]{"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"});
        ;
    }

    private static int[] solution(String[] info, String[] query) {
        Map<String,List<Integer>> map = new HashMap<>();

        for (String s : info) {
            String[] split = s.split(" ");
            int score = Integer.parseInt(split[4]);
            comb(split,0,"",info,map,score);
        }
        for (Map.Entry<String, List<Integer>> stringListEntry : map.entrySet()) {
            Collections.sort(stringListEntry.getValue());
        }
        int[] answer= new int[query.length];
        for (int i = 0 ; i< query.length; i++){
            String[] querys = query[i].replaceAll(" and ", " ").split(" ");
            String key = querys[0] + querys[1] + querys[2] + querys[3];

            List<Integer> list = map.getOrDefault(key,new ArrayList<>());
            int score = Integer.parseInt(querys[4]);

            int cnt = list.size() - binarySearch(list,score);
            answer[i] = cnt;
        }
        return answer;
    }

    private static int binarySearch(List<Integer> key, int score) {
        int left =0;
        int right = key.size();
        while (left<right){
            int mid = (left + right) /2;
            if (key.get(mid) >=  score){
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static void comb(String[] split, int depth, String key, String[] info, Map<String, List<Integer>> map, int score) {
        if (depth == 4){
            List<Integer> list = map.getOrDefault(key,new ArrayList<>());
            list.add(score);
            map.put(key,list);
            return;
        }
        comb(split, depth+1, key + "-", info, map, score);
        comb(split, depth+1, key + split[depth], info, map, score);
    }
}
/**
 * [본 문제는 정확성과 효율성 테스트 각각 점수가 있는 문제입니다.]
 *
 * 카카오는 하반기 경력 개발자 공개채용을 진행 중에 있으며 현재 지원서 접수와 코딩테스트가 종료되었습니다.
 * 이번 채용에서 지원자는 지원서 작성 시 아래와 같이 4가지 항목을 반드시 선택하도록 하였습니다.
 *
 * 코딩테스트 참여 개발언어 항목에 cpp, java, python 중 하나를 선택해야 합니다.
 * 지원 직군 항목에 backend와 frontend 중 하나를 선택해야 합니다.
 * 지원 경력구분 항목에 junior와 senior 중 하나를 선택해야 합니다.
 * 선호하는 소울푸드로 chicken과 pizza 중 하나를 선택해야 합니다.
 * 인재영입팀에 근무하고 있는 니니즈는 코딩테스트 결과를 분석하여 채용에 참여한 개발팀들에 제공하기 위해 지원자들의 지원 조건을 선택하면 해당 조건에 맞는 지원자가 몇 명인 지 쉽게 알 수 있는 도구를 만들고 있습니다.
 * 예를 들어, 개발팀에서 궁금해하는 문의사항은 다음과 같은 형태가 될 수 있습니다.
 * 코딩테스트에 java로 참여했으며, backend 직군을 선택했고, junior 경력이면서, 소울푸드로 pizza를 선택한 사람 중 코딩테스트 점수를 50점 이상 받은 지원자는 몇 명인가?
 *
 * 물론 이 외에도 각 개발팀의 상황에 따라 아래와 같이 다양한 형태의 문의가 있을 수 있습니다.
 */