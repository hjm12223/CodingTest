package programmers.bruteForce.levelTwo;

import java.util.Stack;

public class 괄호_회전하기 {
	public static void main(String[] args) {
		int solution = solution("}]()[{");
		System.out.println("solution = " + solution);
	}
	public static int solution(String s) {
		int answer = 0;
		for(int i = 0; i<s.length(); i++) {
			String line = s.substring(i, s.length()) + s.substring(0, i);
			if (isCorrect(line)){
				answer+=1;
			}
		}
		return answer;
	}

	/*
	괄호 회전
	[()] 은정답
	[(]) 은 올바르지않은 괄호
	이럴 경우에는 어떻게 하는게 좋을까?
	[ +1
	( +1
	 */
	public static boolean isCorrect(String s) {
		Stack<Character> duplicateStack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			char push = s.charAt(i);

			if (push == '[' || push == '{' || push == '(') {
				duplicateStack.add(push);
			} else if (!duplicateStack.empty()) {
				if (push == ']' && duplicateStack.peek() == '[') {
					duplicateStack.pop();
				}
				else if (push == '}' && duplicateStack.peek() == '{') {
					duplicateStack.pop();
				}
				else if (push == ')' && duplicateStack.peek() == '(') {
					duplicateStack.pop();
				}
			} else {
				return false;
			}
		}
		return duplicateStack.empty();
	}
}
