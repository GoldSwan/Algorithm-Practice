package algorithm.programers;

import java.util.Arrays;

class Solution {
	public int solution(String name) {
		int answer = 0;
		int currentOffset = 0;//현재 위치한 커서 위치
		int optimumOffset = 0;//알파벳을 변환하기 위해 최적으로 움직인 커서 위치
		int optimumOffsetMovemontCnt = 0;//현재의 커서 위치에서 최적의 커서 위치로 이동하기 위한 최소의 조작 횟수
		int optimumChangeAlpabetCnt = 0;//알파벳을 구하기 위한 최소의 조작횟수

		char[] nameArray = name.toCharArray();
		char[] resultArray = new char[name.length()];
		Boolean[] visit = new Boolean[name.length()];
		
		Arrays.fill(visit, false);
		Arrays.fill(resultArray, 'A');

		while (true) {
			
			//기저사실 : 완성해야하는 이름배열 nameArray와 결과배열 resultArray 가 동일하면 반복문 종료 
			if(String.copyValueOf(nameArray).equals(String.copyValueOf(resultArray)))
				break;
		
			optimumOffset = findOptimumOffset(currentOffset, nameArray, visit);			
			optimumOffsetMovemontCnt = getOptimumOffsetMovemontCnt(currentOffset, optimumOffset, nameArray);			
			optimumChangeAlpabetCnt = getOptimumChangeAlpabetCnt(optimumOffset, nameArray, resultArray);
			
			visit[optimumOffset] = true;//최적의 커서 위치를 구할 때 이미 방문한 커서는 제외되도록 설정	
			currentOffset = optimumOffset;//움직여서 알파벳을 변환했으므로 현재 커서 위치를 최적의 커서 위치로 변경	
			
			answer += (optimumOffsetMovemontCnt + optimumChangeAlpabetCnt);//조작횟수 가산
		}
		return answer;
	}
	public int findOptimumOffset(int currentOffset, char[] nameArray, Boolean[] visit) {
	//현재의 커서에서 단어를 변환하기 위해 최적의 커서 위치를 구하는 함수
		int offset1 = 0;
		int offset2 = 0;
		
		for (int i = 0; i < nameArray.length; i++) {
			if (nameArray[i] != 'A' && !visit[i]) {		
				offset1 = i;
				break;
			}
		}

		for (int i = nameArray.length - 1; i > -1; i--) {
			if (nameArray[i] != 'A' && !visit[i]) {
				offset2 = i;
				break;
			}
		}
		
		return Math.min(offset1, offset2);
	}
	public int getOptimumOffsetMovemontCnt(int currentOffset, int optimumOffset, char[] nameArray) {
	//현재의 커서 위치에서 최적의 커서 위치로 이동하기 위한 최소의 조작 횟수를 구하는 함수
		return Math.min(nameArray.length - (Math.abs(optimumOffset - currentOffset)), Math.abs(optimumOffset - currentOffset));
	}

	public int getOptimumChangeAlpabetCnt(int index, char[] nameArray, char[] resultArray) {
	//알파벳을 구하기 위한 최소의 조작횟수를 구하는 함수	
		resultArray[index] = nameArray[index];//조작결과를 결과배열에 반영
		
		return Math.min((nameArray[index] - 65), (90 + 1 - nameArray[index]));
	}
}

public class joyStick_goldswan {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution sol = new Solution();
		int answer = 0;
		String name = "JAN";
		//String name = "AAAZZ";
		answer = sol.solution(name);
		System.out.println(answer);
	}

}
