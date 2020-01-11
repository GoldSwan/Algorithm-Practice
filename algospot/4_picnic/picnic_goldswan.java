/********************************************************************
작성일 : 2020-01-11
작성자 : GoldSwan
문제 : 알고스팟 - 소풍(picnic)
출저 : algospot
풀이 : 완전탐색
TEST 결과 : 260~280ms
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

				mateArray[x][y] = 1;
			}

			count=0;

			makeMate(mateArray, -1);
			//순서쌍 조합수 -> 순서를 고려하지 않은 조합수로 변환
			for(int k=N/2;k>0;k--) {
				count = count/k;
			}
			v.add(count);
		}

		for(int i=0;i<v.size();i++) {
			System.out.println(v.get(i));
		}
	}

	public void makeMate(int [][] mateArray, int depth) {

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
	}
}

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		Solution sol = new Solution();
		sol.init();
	}

}
