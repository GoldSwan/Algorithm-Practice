/********************************************************************
작성일 : 2019-12-21
작성자 : GoldSwan
문제 : 모의고사
출저 : 프로그래머스
풀이 : 완전탐색
예상 시간복잡도 : O(N)
TEST 결과 :  모두통과
*********************************************************************/

import java.util.Arrays;
import java.util.Vector;

//풀이법1
class Solution {
    int[] supoza1 = {1,2,3,4,5};
    int[] supoza2 = {2,1,2,3,2,4,2,5};
    int[] supoza3 = {3,3,1,1,2,2,4,4,5,5};
    int[] correctAnswerCount = {0,0,0};
    int[] correctAnswerCountSort = {0,0,0};
    int[] answer;
    Vector<Integer> vAnswers = new Vector<Integer>();

    public int[] solution(int[] answers) {

    	//각 수포자별 정답수 카운트
        for(int i=0;i<answers.length;i++)
        {
            if(supoza1[i%(supoza1.length)]==answers[i]){
                correctAnswerCount[0]++;
            }
            if(supoza2[i%(supoza2.length)]==answers[i]){
                correctAnswerCount[1]++;
            }
            if(supoza3[i%(supoza3.length)]==answers[i]){
                correctAnswerCount[2]++;
            }
        }

        for(int i=0;i<correctAnswerCount.length;i++)
        {
            //각 수표자별 정답수 복사
            correctAnswerCountSort[i] = correctAnswerCount[i];
        }
        //정렬
        Arrays.sort(correctAnswerCountSort);//퀵정렬 시간복잡도 N*lnN

        //정렬하였으므로 가장 마지막 인덱스 값이 최고 점수가 됨. 동점자가 있을 수 있으므로 마지막 인덱스 값과 일치하는 수포자 번호를 vAnswers 에 넣음.
        for(int i=0;i<correctAnswerCount.length;i++)
        {
            if(correctAnswerCountSort[correctAnswerCountSort.length-1]==correctAnswerCount[i]){
            	vAnswers.add(i+1);
            }
        }

        //정답 배열 생성 및 복사
        answer = new int[vAnswers.size()];

        for(int i=0;i<answer.length;i++)
        {
            answer[i] = vAnswers.get(i);
        }

        return answer;
    }
}

//풀이법2
class Solution2 {

	int[] supoza1 = {1,2,3,4,5};
    int[] supoza2 = {2,1,2,3,2,4,2,5};
    int[] supoza3 = {3,3,1,1,2,2,4,4,5,5};
    int correctAnswerCount1 = 0;
    int correctAnswerCount2 = 0;
    int correctAnswerCount3 = 0;
    int answerLenth;
    int[] answer = new int[3];
    int[] realAnswer;

    public int[] solution(int[] answers) {
    	answerLenth = 0;

    	//각 수포자별 정답수 카운트
        for(int i=0;i<answers.length;i++){
            if(supoza1[i%(supoza1.length)]==answers[i]){
                correctAnswerCount1++;
            }
            if(supoza2[i%(supoza2.length)]==answers[i]){
                correctAnswerCount2++;
            }
            if(supoza3[i%(supoza3.length)]==answers[i]){
                correctAnswerCount3++;
            }
        }

        //모든 경우수를 구하여 최대 점수 수포자 구하기

        //수포자1이 젤 많이 맞출 경우
        if(correctAnswerCount1>correctAnswerCount2 && correctAnswerCount1>correctAnswerCount3) {
        	answer[0] = 1;
        	answerLenth++;
        }
        //수포자2가 젤 많이 맞출 경우
        if(correctAnswerCount2>correctAnswerCount3 && correctAnswerCount2>correctAnswerCount1) {
        	answer[0] = 2;
        	answerLenth++;
        }
        //수포자3가 젤 많이 맞출 경우
        if(correctAnswerCount3>correctAnswerCount2 && correctAnswerCount3>correctAnswerCount1) {
        	answer[0] = 3;
        	answerLenth++;
        }
        //수포자1, 수포자2가 동점이고 수포자3이 아래일 경우
        if(correctAnswerCount1==correctAnswerCount2 && correctAnswerCount1>correctAnswerCount3 && correctAnswerCount2>correctAnswerCount3) {
        	answer[0] = 1;
        	answer[1] = 2;
        	answerLenth++;
        	answerLenth++;
        }
        //수포자2, 수포자3이 동점이고 수포자1이 아래일 경우
        if(correctAnswerCount2==correctAnswerCount3 && correctAnswerCount2>correctAnswerCount1 && correctAnswerCount3>correctAnswerCount1) {
        	answer[0] = 2;
        	answer[1] = 3;
        	answerLenth++;
        	answerLenth++;
        }
        //수포자1, 수포자3이 동점이고 수포자2이 아래일 경우
        if(correctAnswerCount1==correctAnswerCount3 && correctAnswerCount1>correctAnswerCount2 && correctAnswerCount3>correctAnswerCount2) {
        	answer[0] = 1;
        	answer[1] = 3;
        	answerLenth++;
        	answerLenth++;
        }
        //수포자1,2,3 이 동점일 경우
        if(correctAnswerCount1==correctAnswerCount2 && correctAnswerCount2==correctAnswerCount3) {
        	answer[0] = 1;
        	answer[1] = 2;
        	answer[2] = 3;
        	answerLenth++;
        	answerLenth++;
        	answerLenth++;
        }

        //정답 배열 생성 및 복사
        realAnswer = new int[answerLenth];

        for(int i=0;i<realAnswer.length;i++) {
        	realAnswer[i] = answer[i];
        }
        return realAnswer;
    }
}


public class mockTest {

	public static void main(String[] args) {
		//int[] answers = {1,2,3,4,5};
		int[] answers = {1,3,2,4,2};

		Solution sol = new Solution();
		int answer[] = sol.solution(answers);

		Solution2 sol2 = new Solution2();
		int answers2[] = sol2.solution(answers);

		//풀이법1
		for(int i=0;i<answer.length;i++) {
			System.out.print(answer[i]+" ");
		}
		System.out.println();
		//풀이법2
		for(int i=0;i<answer.length;i++) {
			System.out.print(answers2[i]+" ");
		}
	}
}
