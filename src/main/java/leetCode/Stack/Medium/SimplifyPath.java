package leetCode.Stack.Medium;

import java.util.Arrays;
import java.util.Stack;

public class SimplifyPath {
    public static void main(String[] args) {
        String path = "/a//b////c/d//././/..";
        String result = simplifyPath(path);
        System.out.println("result = " + result);
    }

    /**
     * /. 일 경우 현재 디렉토리를 선택
     * /../ 일 경우 상위 디렉토리 선택
     * 만약 /a/./b 일 경우 /b 디렉토리가 선택
     * 만약 /a/../b 일 경우 /a 디렉토리가 선택
     * /a/./b/../../c 일 경우 /c 가 선택
     */

    public static String simplifyPath(String path) {
    Stack<String> stack = new Stack<>();
    String[] dictionary = path.split("/");
        for (String str : dictionary) {
            if (str.equals(".") || str.isEmpty()){
                continue;
            }else if (str.equals("..")){
                if (!stack.isEmpty()){
                    stack.pop();
                }
                }else {
                stack.push(str);
            }
        }
        return "/" + String.join("/",stack);
    }
}