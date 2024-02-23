package programmers.kakao.Level1;

import javax.lang.model.SourceVersion;
import java.sql.SQLType;
import java.util.Stack;

public class breadAndPruits {
    public static void main(String[] args) {
        int solution = solution(new int[]{1, 3, 2, 1, 2, 1, 3, 1, 2});
        System.out.println(solution(new int[]{1,2,1,2,1,3,1}));
        System.out.println(solution(new int[]{1,2,3,1,2,3,1,1}));
        System.out.println("args = " + solution);
    }
    /*
    1 빵 , 2야채 3고기 1빵 순으로 쌓아야함
    야채 고기
    빵 빵

    */
    public static int solution(int[] ingredient) {
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        for (int i = 0 ; i< ingredient.length-1;i++){
            if (ingredient[i] == 1 ){
                if (stack.contains(1) && stack.contains(2) && stack.contains(3)){
                    stack.removeElement(1);
                    stack.removeElement(2);
                    stack.removeElement(3);
                    result++;
                }else {
                    stack.add(ingredient[i]);
                }
            } else if (stack.contains(1) && ingredient[i] == 2) {
                result++;
                stack.pop();
            }
        }
        return result;
    }

}
