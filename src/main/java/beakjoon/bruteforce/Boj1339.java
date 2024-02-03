package beakjoon.bruteforce;

import java.util.*;

public class Boj1339 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] strings = new String[n];
        for (int i = 0 ; i< n ; i++){
            strings[i] = sc.next();
        }

        int[] alphabet = new int[26];
        for (String string : strings) {
            int length = string.length()-1;
            for (char chars :string.toCharArray()) {
                alphabet[ chars-'A'] += (int) Math.pow(10,length);
                length--;
            }
        }
        Arrays.sort(alphabet);

        int i = alphabet.length-1;
        int sum = 0;
        int range = 9;
        while (alphabet[i] != 0 && range >= 0){
            sum += alphabet[i] * range;
            range--;
            i--;
        }
        System.out.println(sum);
    }
}