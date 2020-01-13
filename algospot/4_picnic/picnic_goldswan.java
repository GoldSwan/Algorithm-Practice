/********************************************************************
작성일 : 2020-01-11
작성자 : GoldSwan
문제 : 알고스팟 - 소풍(picnic)
출저 : algospot
풀이 : 완전탐색
TEST 결과 : 260~280ms
20200113 수정 : 중복으로 조합을 세는 것을 제거하여 속도를 개선함
수정 TEST 결과 : 80~88ms
*********************************************************************/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

class Solution {

	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private int [][] mateArray;
	private int C;//테스트 케이스수
	private int count;
	private int visited[];
	private Vector<Integer> v = new  Vector<Integer>();

	public void init() throws NumberFormatException, IOException {

		C = Integer.parseInt(br.readLine());
		int N;//학생의 수
		int M;//친구쌍의 수
		String strNM;
		String strMate;
		StringTokenizer st;
		int x;
		int y;

		for (int i = 0; i < C; ++i) {

			strNM = br.readLine();

			st = new StringTokenizer(strNM, " ");

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			strMate = br.readLine();

			mateArray = new int [N][N];
			visited  = new int [N];

			st = new StringTokenizer(strMate, " ");

			while(st.hasMoreTokens()) {
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());

				//20200113 GoldSwan : 코드 개선을 위해 주석처리
				//mateArray[x][y] = 1;

				//20200113 GoldSwan : 작은 값을 x 좌표로 두기위해 크기 비교하여 처리 if문 추가
				/*****************************************************************/
				if(x>y)
				mateArray[y][x] = 1;
				else
				mateArray[x][y] = 1;
				/*****************************************************************/
			}

			count=0;

			makeMate(mateArray, -1);
			//20200113 GoldSwan : 코드 개선을 위해 주석처리
			/*
			//순서쌍 조합수 -> 순서를 고려하지 않은 조합수로 변환
			for(int k=N/2;k>0;k--) {
				count = count/k;
			}
			*/
			v.add(count);
		}

		for(int i=0;i<v.size();i++) {
			System.out.println(v.get(i));
		}
	}

	public void makeMate(int [][] mateArray, int depth) {

		//20200113 GoldSwan : 중복으로 세는 것을 제거하기 위한 개선 코드
		/*****************************************************************/
		depth++;
		if(depth==(mateArray.length/2)) {
			count++;
			return;
		}

		//방문하지 않은 학생중 가장 적은 숫자인 학생을 찾는다.
		int firstStudent = -1;
		for(int i=0;i<visited.length;i++) {
			if(visited[i]==0) {
				firstStudent = i;
				break;
			}
		}
		//그 다음 짝지을 학생을 찾아 방문했다고 visited 에 체크후 재귀 탐색
		for(int friendStudent=firstStudent+1;friendStudent<mateArray.length;friendStudent++) {
			if(visited[firstStudent]==0 && visited[friendStudent]==0 && mateArray[firstStudent][friendStudent]==1) {
				visited[firstStudent]=1;
				visited[friendStudent]=1;
				makeMate(mateArray, depth);
				visited[firstStudent]=0;
				visited[friendStudent]=0;
			}
		}
		/*****************************************************************/

		//20200113 GoldSwan : 코드 개선을 위해 주석처리
		/*
		int findX = -1;
		int findY = -1;
		depth++;
		if(depth==(mateArray.length/2)) {
			count++;
			return;
		}
		for(int i=0;i<mateArray.length;i++) {
			for(int k=0;k<mateArray[0].length;k++) {
				if(mateArray[i][k]==1 && visited[i]==0 && visited[k]==0) {
					findY = i;
					findX = k;

					mateArray[findY][findX]=0;
					visited[findY]=1;
					visited[findX]=1;

					makeMate(mateArray, depth);

					mateArray[findY][findX]=1;
					visited[findY]=0;
					visited[findX]=0;
				}
			}
		}

		//기저사실 : 가능한 짝이 존재하지 않는다면 return
		if(findY == -1)
		return;
		*/
	}
}

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		Solution sol = new Solution();
		sol.init();
	}

}
