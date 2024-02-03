package programmers.kakao.Level1;

import java.util.*;

public class Personality {
    public static void main(String[] args) {
        StringBuilder solution = solution(new String[]{"AN", "CF", "MJ", "RT", "NA"}, new int[]{5, 3, 2, 7, 5});
        StringBuilder solution1 = solution(new String[]{"TR", "RT", "TR"}, new int[]{7, 1, 3});
    }
    public static StringBuilder solution(String[] survey, int[] choices) {
        Map<Character,Integer> map = new HashMap<>();
        StringBuilder str = new StringBuilder();
        List<Character>surveyList = new ArrayList<>();

        surveyList.add('R');
        surveyList.add('T');
        surveyList.add('C');
        surveyList.add('F');
        surveyList.add('J');
        surveyList.add('M');
        surveyList.add('A');
        surveyList.add('N');
        for (Character character : surveyList) {
            map.put(character,0);
        }
        // 라이언형 R , 튜브형 T
        // 콘형 C , 프로도형 F
        // 제이형 J 무지형 M
        // 어피치형 A , 네오형 N
        for (int i = 0; i < survey.length; i++){
            char leftChar = survey[i].charAt(0);
            char rightChar = survey[i].charAt(1);
            if (choices[i] > 4){
                map.put(rightChar,map.getOrDefault(rightChar,0) + choices[i]- 4);
            } else if (choices[i] < 4){
                map.put(leftChar,map.getOrDefault(leftChar,0) + 4 -choices[i]);
            }
        }

        for (int i = 0 ; i < surveyList.size(); i += 2){

            if (map.get(surveyList.get(i)) >  map.get(surveyList.get(i+1))){
                str.append(surveyList.get(i));

            } else if (map.get(surveyList.get(i)) <  map.get(surveyList.get(i+1))) {
                str.append(surveyList.get(i+1));

            }else {
                List<Character> list = new ArrayList<>();
                list.add(surveyList.get(i+1));
                list.add(surveyList.get(i));
                Collections.sort(list);
                str.append(list.get(0));
            }
        }
        return str;
}
}
/**
 * 선택지	성격 유형 점수
 * 매우 비동의	네오형 3점
 * 비동의	네오형 2점
 * 약간 비동의	네오형 1점
 * 모르겠음	어떤 성격 유형도 점수를 얻지 않습니다
 * 약간 동의	어피치형 1점
 * 동의	어피치형 2점
 * 매우 동의	어피치형 3점
 *
 * 이때 검사자가 질문에서 약간 동의 선택지를 선택할 경우 어피치형(A) 성격 유형 1점을 받게 됩니다.
 * 만약 검사자가 매우 비동의 선택지를 선택할 경우 네오형(N) 성격 유형 3점을 받게 됩니다.
 *
 * survey의 원소는 "RT", "TR", "FC", "CF", "MJ", "JM", "AN", "NA" 중 하나입니다.
 * survey[i]의  첫 번째 캐릭터는 i+1번 질문의 비동의 관련 선택지를 선택하면 받는 성격 유형을 의미합니다.
 * survey[i]의  두 번째 캐릭터는 i+1번 질문의 동의 관련 선택지를 선택하면 받는 성격 유형을 의미합니다.
 */