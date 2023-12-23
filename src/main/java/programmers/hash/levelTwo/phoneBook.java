package programmers.hash.levelTwo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class phoneBook {
    public static void main(String[] args) {
        String[] phone_book = new String[]{"119","97674223","11955244421"};
        boolean solution = solution(phone_book);
        System.out.println("solution = " + solution);
    }
    public static boolean solution(String[] phone_book) {
        Map<String,Integer> map = new HashMap<>();
        Arrays.sort(phone_book);
     for (int i = 0 ; i < phone_book.length-1 ; i++) {
         String str = phone_book[i];
         if (!map.containsKey(str)) {
             map.put(str, 0);
         }
         String str2 = phone_book[i + 1];
         if (str2.length() > str.length()) {
             String sub2 = str2.substring(0, str.length());
             if (map.containsKey(sub2)) {
                 return false;
             }
         }
     }
     return true;
    }
}
