package algorithm.programers;

import java.util.ArrayList;
import java.util.LinkedList;

class Solution {
	public int solution(int[][] baseball) {
		int answer = 0;
		ArrayList<String> answerList = new ArrayList<String>();	
		LinkedList<String> permutationList = new LinkedList<String>();	
		String[] strArray = {"1","2","3","4","5","6","7","8","9"};
		int[] perCheck = new int[strArray.length];
		int strikeCount = 0;
		int ballCount = 0;
		
		permutation(strArray.length, 3, permutationList, perCheck, strArray,answerList);		
		
		for(int[] question: baseball) {
			
			String strQuestion = Integer.toString(question[0]);
			String[] strSplitQuestion = strQuestion.split("");
			for(int i=0;i<answerList.size();i++) {
				String[] strSplitAns = answerList.get(i).split("");

				for(int j =0;j<strSplitAns.length;j++) {
					if(strSplitQuestion[j].equals(strSplitAns[j])) 
						strikeCount++;
					else if(strQuestion.contains(strSplitAns[j]))
						ballCount++;
				}
				
				if(question[1]!=strikeCount || question[2]!=ballCount) {
					answerList.remove(i);
					i--;
				}
			
				strikeCount=0;
				ballCount=0;
			}	
		}
		
		answer = answerList.size();
		return answer;
	}

	private static void permutation(int n, int r, LinkedList<String> permutationList, int[] perCheck, String[] strArray, ArrayList<String> answerList) {
		if (permutationList.size() == r) {
			StringBuilder sb = new StringBuilder();
			for (String i : permutationList) {
				sb.append(i);
			}
			answerList.add(sb.toString());
			return;
		}
		for (int i = 0; i < n; i++) {
			if (perCheck[i] == 0) {
				permutationList.add(strArray[i]);
				perCheck[i] = 1;
				permutation(n, r, permutationList, perCheck, strArray, answerList);
				perCheck[i] = 0;
				permutationList.removeLast();
			}
		}
	}
}

public class NumberBaseball {

	public static void main(String[] args) {	
		int answer = 0;
		int[][] baseball = { { 123, 1, 1 }, { 356, 1, 0 }, { 327, 2, 0 }, { 489, 0, 1 } };
		
		Solution sol = new Solution();
		answer = sol.solution(baseball);
		
		System.out.println(answer);
	}

}
