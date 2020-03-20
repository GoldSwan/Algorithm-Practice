/********************************************************************
작성일 : 2020-01-04
작성자 : GoldSwan
문제 : 알고스팟 - 게임판 덮기(3_boardCover)
출저 : algospot
풀이 : 탐색 재귀호출 이용
예상 시간복잡도 : n^2 * 4^a a은 흰색칸의 수 
TEST 결과 :  76~108ms
*********************************************************************/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

class Solution {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int C, H, W;
	int [][] gameBoardArray;
	int [][][] coverType= //왼쪽 위 흰칸에서부터 덮을경우 가능한 덮는 방법의 Type 상대적 좌표
			{
			 {{0,0},{1,0},{0,1}},
			 {{0,0},{0,1},{1,1}},
			 {{0,0},{1,0},{1,1}},
			 {{0,0},{0,1},{-1,1}},
			};
	Vector<Integer> vAnswer = new Vector<Integer>();

	//초기 입력을 담당하는 init 메소드
	public void init() throws NumberFormatException, IOException {
		// 테스트 케이스수
		C = Integer.parseInt(br.readLine());

		for (int i = 0; i < C; ++i) {
			String strHW = br.readLine();

			StringTokenizer st = new StringTokenizer(strHW, " ");

			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			gameBoardArray = new int [H][W];
			String gameBoardShape = "";
			for(int k=0;k<H;k++) {
				gameBoardShape = br.readLine();//EX : #.....#
				String [] gameBoardShapeSplit = gameBoardShape.split("");
				for(int l=0;l<gameBoardShapeSplit.length;l++){
					if(gameBoardShapeSplit[l].equals("#"))
						gameBoardArray[k][l] = 1;
					else
						gameBoardArray[k][l] = 0;
				}
			}
			//알고리즘 시작
			solution(gameBoardArray);
		}
		//답 출력
		for(int i=0;i<vAnswer.size();i++) {
			System.out.println(vAnswer.get(i));
		}
	}

	public void solution(int [][] gameBoardArray) {
		int answer = 0;
		answer = cover(gameBoardArray);
		vAnswer.add(answer);
	}

	//덮을 수 있는지 탐색하는 cover 메소드
	public int cover(int [][] gameBoardArray) {
		int count = 0;//경우의 수 count
		int findX = -1;
		int findY = -1;
		//가장 왼쪽 위의 흰칸 찾기
		loop : for(int i=0;i<gameBoardArray.length;i++) {
				for(int k=0;k<gameBoardArray[0].length;k++) {
					if(gameBoardArray[i][k]==0) {
						findY = i;
						findX = k;
						break loop;//해당하는 흰칸 X,Y 좌표를 발견하면 loop문을 빠져나온다.
					}
				}
		}

		//기저사실 : 모두 덮여있다면 1 return
		if(findY == -1)
		return 1;

		//각 덮는 type 경우수를 대입하기
		for(int type=0;type<4;type++) {
			//덮을 수 있는지 판별
			if(canCover(findY, findX, type, gameBoardArray, 1)) {
				count = count + cover(gameBoardArray);
			}
			//checkValue를 -1로 설정하여 원래대로 되돌리기
			canCover(findY, findX, type, gameBoardArray, -1);
		}
		return count;
	}
	//각각의 Type으로 덮을 수 있는지 판별하는 canCover 메소드
	public boolean canCover(int findY, int findX, int type, int[][] gameBoardArray, int checkValue) {
		boolean checkCanCover = true;

		for(int i=0;i<3;i++) {
			int nextY = findY + coverType[type][i][1];
			int nextX = findX + coverType[type][i][0];

			//게임판을 벗어나지 않않는지 체크
			if(nextY<0 || nextX<0 || nextY >= gameBoardArray.length ||  nextX >= gameBoardArray[0].length) {
				checkCanCover = false;
			}
			//검은칸과 겹치는지 체크 - 겹치는 경우 1을 넘으므로 false 리턴, 전부 순회하고 checkValue를 -1로 설정하여 원래대로 되돌리기
			else if((gameBoardArray[nextY][nextX] += checkValue)>1) {
				checkCanCover = false;
			}
		}

		return checkCanCover;
	}
}

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		Solution sol = new Solution();
		sol.init();
	}

}
