/********************************************************************
작성일 : 2019-12-21
작성자 : 최희진
문제 : 모의고사
출처 : 프로그래머스
TEST 결과 : 성공!
*********************************************************************/
class Solution {
	private int[] mem1 = {1, 2, 3, 4, 5};
	private int[] mem2 = {2, 1, 2, 3, 2, 4, 2, 5};
	private int[] mem3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

	public int[] solution(int[] answers) {
		int[] ans1 = new int[answers.length];
		int[] ans2 = new int[answers.length];
		int[] ans3 = new int[answers.length];

		int[] correct = {0,0,0};		//정답 카운트

		for(int i=0; i<answers.length; i++) {
			//수포자들의 답 만들기
			ans1[i] = mem1[i%mem1.length];
			ans2[i] = mem2[i%mem2.length];
			ans3[i] = mem3[i%mem3.length];

			//정답과 비교
			if(ans1[i] == answers[i]) {
				correct[0]++;
			}
			if(ans2[i] == answers[i]) {
				correct[1]++;
			}
			if(ans3[i] == answers[i]) {
				correct[2]++;
			}
		}

		int max=0;
		int result_len=1;

		//최대값 뽑기   			//8,15,3	//8,8,3
		for(int i=0; i<correct.length; i++) {
			if(max<correct[i]) {
				max=correct[i];	//최대값 갱신, 결과값길이 초기화
				result_len=1;
			}else if(max==correct[i]) {
				result_len++;	//현재 최대값과 같은점수라면 결과값길이 증가
			}
		}

		int[] result = new int[result_len];
		int k=0;
		for(int i=0; i<correct.length; i++) {
			if(max==correct[i]) {
				result[k] = i+1;
				k++;
			}
		}
		return result;
	}
}

public class MockTest {
	public static void main(String[] args) {
		int[] answers = {1,3,2,4,2};
		Solution sol = new Solution();
		int[] result = sol.solution(answers);

		for(int i=0; i<result.length; i++) {
			System.out.print(result[i]+" ");
		}
	}
}
