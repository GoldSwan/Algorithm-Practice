
/********************************************************************
작성일 : 2019-12-28
작성자 : GoldSwan
문제 : 보글게임
출저 : algospot
풀이 : 메모이제이션(memoization) 이용
예상 시간복잡도 : 중복 탐색될 경우를 제거하여 완전탐색일 때보다 현저히 줄어듬
TEST 결과 :  100~120ms
*********************************************************************/

package algorithm.algospot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class BOGGLE {
	final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static String[][] boogleGameBoard = new String[5][5];//보글 게임판
	static String[] knownWords;//알고있는 단어들
	static int[][][] memoryBoogleGameBoard;
	static int[] xMove = { -1, 1, 0, 0, -1, -1, 1, 1 };// x좌표 : 좌, 우, 상, 하, 왼족 대각선 위, 왼쪽 대각선 아래, 오른쪽 대각선 위,오른쪽 대각선 아래
	static int[] yMove = { 0, 0, 1, -1, 1, -1, 1, -1 };// y좌표 : 좌, 우, 상, 하, 왼족 대각선 위, 왼쪽 대각선 아래, 오른쪽 대각선 위,오른쪽 대각선 아래
	static Vector<String> VAnswer = new Vector<String>();

	public static void main(String[] args) throws NumberFormatException, IOException {

		//테스트 케이스수
		int C = Integer.parseInt(br.readLine());

		for (int i = 0; i < C; ++i) {

			// 보글 게임판 입력
			for (int k = 0; k < boogleGameBoard.length; ++k) {
				String word = br.readLine();
				String[] splitArray = word.split("");

				for (int j = 0; j < splitArray.length; ++j) {
					boogleGameBoard[k][j] = splitArray[j];
				}
			}

			int N = Integer.parseInt(br.readLine());

			// 알고 있는 단어들
			knownWords = new String[N];

			//알고 있는 단어 입력
			for (int k = 0; k < knownWords.length; k++) {
				String knownWord = br.readLine();
				knownWords[k] = knownWord;
				memoryBoogleGameBoard = new int[5][5][knownWord.length()];//순서대로 게임판 자체를 기억할 3차원 배열 생성
				//알고리즘 동작
				solution(boogleGameBoard, knownWord);
			}

		}
		// 답안출력
		for (int i = 0; i < VAnswer.size(); i++) {
			System.out.println(VAnswer.get(i));
		}
	}

	public static void solution(String[][] boogleGameBoard, String knownWord) {

		String[] splitKnownWord = knownWord.split("");
		boolean isWord = false;

		//보글 게임판 탐색
		loop: for (int i = 0; i < 5; i++) {
			for (int k = 0; k < 5; k++) {
				if (splitKnownWord[0].equals(boogleGameBoard[i][k])) {
					if (checkIsWord(i, k, 1, splitKnownWord) == 1) {
						isWord = true;//알고있는 단어 탐색에 성공한 경우 true, loop문을 빠져나온다.
						break loop;
					}
				}

			}
		}

		//단어가 존재할 경우 YES 존재하지 않을 경우 NO
		if (isWord)
			VAnswer.add(knownWord + " YES");
		else
			VAnswer.add(knownWord + " NO");
	}

	public static int checkIsWord(int y, int x, int offset, String[] splitKnownWord) {

		//모든 탐색이 이루어졌을 때 단어가 존재하는 것이므로 1 리턴
		if (offset == splitKnownWord.length)
			return 1;

		//탐색이 이미 이루어진 보글게임판 인덱스중 탐색에 실패했을 경우 -1 리턴, 탐색이 성공한 경우 1 리턴
		if (memoryBoogleGameBoard[y][x][offset] != 0)
			return memoryBoogleGameBoard[y][x][offset];

		//offset에 해당하는 글자를 찾은지 못찾은지 여부를 가려내는 find 변수 선언
		int find = -1;

		//각 경우의 수 구하기
		loop: for (int i = 0; i < 8; i++) {
			int xAfter = x + xMove[i];
			int yAfter = y + yMove[i];

			//각 경우의 수 x,y 가 보글게임판 인덱스를 벗어날 경우 무시
			if ((xAfter < 0) || (yAfter < 0) || (xAfter >= 5) || (yAfter >= 5)) {
				continue;
			}
			//offset에 해당하는 글자와 xAfter, yAfter 인덱스에 해당하는 보글게임판 글자가 일치하는 경우
			if (splitKnownWord[offset].equals(boogleGameBoard[yAfter][xAfter])) {
				//다음인덱스 탐색
				if (checkIsWord(yAfter, xAfter, offset + 1, splitKnownWord) == 1) {
					find = 1;
					break loop;//탐색에 성공한 경우 loop 문 전체를 빠져나온다.
				}
			}
		}
		//탐색 결과 return
		return memoryBoogleGameBoard[y][x][offset] = find;
	}
}
