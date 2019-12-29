/********************************************************************
작성일 : 2019-12-28
작성자 : GoldSwan
문제 : 괄호변환
출저 : programers
풀이 : 분할정복, 재귀적 탐색 이용
예상 시간복잡도 : NlgN
TEST 결과 :  모두통과
*********************************************************************/

package algorithm.programers;

import java.util.Stack;

class Solution {
	String answer = "";
	Stack<String> stack = new Stack<String>();// 올바른 괄호 문자열 인지 체크
	boolean checkCorrectP = false;
	boolean checkCorrectU = false;

	public String solution(String p) {

		// p가 올바른 괄호 문자열인지 체크
		checkCorrectP = CorrectBracket(p);

		if (checkCorrectP) {
			// 올바른 괄호 문자열 일 경우 그대로 리턴
			answer = p;
		} else {
			// 균형잡힌 괄호 문자열일 경우 계산
			answer = Transformation(p);
		}
		return answer;
	}

	public String Transformation(String p) {

		if (p.equals(""))
			return "";

		// 균형잡힌 괄호 문자열체크
		int left = 0;
		int right = 0;
		int index = 0;
		String[] pSplitArray = p.split("");

		for (index = 0; index < pSplitArray.length; index++) {
			if (pSplitArray[index].equals("(")) {
				left++;
			}
			if (pSplitArray[index].equals(")")) {
				right++;
			}
			if (left == right) {
				break;
			}
		}

		String u = p.substring(0, index+1);
		String v = p.substring(u.length());

		// u가 올바른 괄호 문자열 인지 체크
		checkCorrectU = CorrectBracket(u);

		// u가 옮바른 괄호 문자열일 경우
		if (checkCorrectU) {
			return  u + Transformation(v);
		} // u가 올바른 괄호 문자열이 아닐경우
		else {
			String uChange = "";

			u = u.substring(1, u.length() - 1);

			String[] uSplitArray = u.split("");

			for(int i=0;i<u.length();i++) {
				if (uSplitArray[i].equals("(")) {
					uChange += ")";
				}
				if (uSplitArray[i].equals(")")) {
					uChange += "(";
				}
			}

			return "(" + Transformation(v) + ")" + uChange;
		}
	}

	public boolean CorrectBracket(String p) {
		// 올바른 괄호 문자열 인지 체크
		String[] pSplitArray = p.split("");

		stack.push(pSplitArray[0]);// 비교할 첫 인덱스 괄호 값 push

		for (int i = 1; i < pSplitArray.length; i++) {
			if (stack.isEmpty()) {//비어있는 경우 비교할 인덱스 값 하나 대입
				stack.push(pSplitArray[i]);
			} else if (stack.peek().equals("(") && pSplitArray[i].equals(")")) {//괄호쌍이 맞을 경우 제거
				stack.pop();
			} else {//괄호 쌍이 맞지 않을 경우 대입
				stack.push(pSplitArray[i]);
			}
		}

		if (stack.isEmpty()) {//올바른 괄호 문자열이면 스택이 비어있으므로 true 리턴
			return true;
		} else {//올바른 괄호 문자열이 아니면 스택이 비어있지 않으므로 false 리턴
			stack.clear();// 다음 계산을 위해 스택 초기화
			return false;
		}
	}
}

public class bracketTransformation {

	public static void main(String[] args) {
		// String p = "(()())()";
		// String p = ")(";
		// String p = ")()()(";
		String p = "()))((()";
		Solution sol = new Solution();
		String answer = sol.solution(p);
		System.out.println(answer);
	}

}
