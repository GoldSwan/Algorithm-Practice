package k_number;

import java.util.Arrays;
/*
 * 작성일 : 2019-12-10
 * 문제 : 프로그래머스 - K번째 수
 * 풀이법 : Arrays.copyOfRange, JAVA Sort API 이용
 * 테스트 통과 유무 : 모두통과
 * 실패 원인 파악 :
 */
class Solution {
	    public int[] answer;

    public int[] solution(int[] array, int[][] commands) {
        answer = new int [commands.length];

        for(int i=0; i<commands.length ; i++) {
        	//Arrays.copyOfRange(배열,시작 인덱스, 끝 인덱스) 시작 인덱스 포함부터 끝 인덱스 전까지 복사
        	int[] copyArray = Arrays.copyOfRange(array, commands[i][0]-1, commands[i][1]);
        	Arrays.sort(copyArray);//JAVA Sort API 퀵정렬
        	answer[i] = copyArray[commands[i][2]-1];
        };

        return answer;
    }
}

public class kNumber_goldswan {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {1,5,2,6,3,7,4};
		int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
		Solution sol = new Solution();
		 int[] answer = sol.solution(array, commands);
		for(int i=0;i<answer.length;i++)
		System.out.print(answer[i]+" ");
	}

}
