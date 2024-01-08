package leetCode.DP.Midium;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

    public static void main(String[] args) {
        generateParenthesis(3);
    }
    public static String generateParenthesis(int n) {
        List<String> result = new ArrayList<>();

        if (n == 1){
            return "()";
        }

        char left = '(';
        char right = ')';
        makeBracket(3);
        return result.toString();


    }
    public static String makeBracket(int n){
        return "";
    }
    /**
     * ((())) 1 2 3 1 2 3
     * (())() 1 2 1 2 3 3
     * ()(()) 1 1 2 3 2 3
     * (()()) 1 2 1 3 2 3
     * ()()() 1 1 2 2 3 3
     */
}
