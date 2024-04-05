package programmers.kakao.Level1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecretMap {
    public static void main(String[] args) {
        String[] solution = solution(5, new int[]{9, 20, 28, 18, 11}, new int[]{30, 1, 21, 17, 28});
        solution(6, new int[]{46, 33, 33 ,22, 31, 50},new int[]{27 ,56, 19, 14, 14, 10});
        System.out.println("solution = " + Arrays.toString(solution));
    }
    public static String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        StringBuilder str = new StringBuilder();
        List<String> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        getStringBuilder(arr1, str, list,n);
        str = new StringBuilder();
        getStringBuilder(arr2, str, list2,n);
        str = new StringBuilder();


        for (int i = 0 ; i <list.size() ; i++){
            String str1 = list.get(i);
            String str2 = list2.get(i);
            for (int j = 0 ; j < str1.length() ; j++) {
                if (str1.charAt(j) == '1' || str2.charAt(j) == '1'){
                    str.append("#");
                }else {
                    str.append(" ");
                }
            }
            answer[i] = str.toString();
            str = new StringBuilder();
        }
        return answer;
    }

    private static void getStringBuilder(int[] arr, StringBuilder str, List<String> list, int n) {
        for (Integer value : arr) {
            str.append(Integer.toBinaryString(value));
            if (str.length() < n){
                while (str.length() != n){
                    str.insert(0,0);
                }
            }
            list.add(str.toString());
            str = new StringBuilder();
        }
    }
}
