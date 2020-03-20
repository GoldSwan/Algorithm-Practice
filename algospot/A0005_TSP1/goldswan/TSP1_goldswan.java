/********************************************************************
작성일 : 2020-01-18
작성자 : GoldSwan
문제 : 알고스팟 - Traveling Salesman Problem 1 (TSP1)
출저 : algospot
풀이 : 완전탐색
예상 시간복잡도 : O(N!)
TEST 결과 : 380ms
*********************************************************************/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

class Solution {

	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private double [][] cityDistanceArray;
	private int C;//테스트 케이스수
	private int N;//도시의 수
	private double shortestDistance;
	private int visited[];
	private Vector<Double> v = new  Vector<Double>();
	private Vector<Integer> savePathV;
	public void init() throws NumberFormatException, IOException {

		C = Integer.parseInt(br.readLine());
		String strN;
		String strDistence;//각 도시간 거리
		StringTokenizer st;

		for (int i = 0; i < C; ++i) {


			strN = br.readLine();

			st = new StringTokenizer(strN, "  ");

			N = Integer.parseInt(st.nextToken());

			cityDistanceArray = new double [N][N];
			visited  = new int [N];

			st = new StringTokenizer(strN, " ");

			for(int x=0;x<N;x++) {

				strDistence = br.readLine();
				st = new StringTokenizer(strDistence, " ");

				int y=0;
				while(st.hasMoreTokens()) {

					cityDistanceArray[x][y] = Double.parseDouble(st.nextToken());
					y++;
				}
			}

			shortestDistance = 987654321;

			for(int k=0;k<N;k++) {
				savePathV = new  Vector<Integer>();
				savePathV.add(k);
				visited[k]=1;
				double canShortestDistance = findShortestDistence(savePathV, 0);
				shortestDistance = Math.min(canShortestDistance, shortestDistance);
				visited[k]=0;
			}
			v.add(shortestDistance);
		}

		for(int i=0;i<v.size();i++) {
			System.out.format("%.10f",v.get(i));
			System.out.println();
		}
	}

	public double findShortestDistence(Vector<Integer> savePathV, double currentLenth) {

		if(savePathV.size()==N)
		return currentLenth;

		double answer = 987654321;

		for(int next=0;next<N;next++) {
			if(visited[next]==1) continue;
			int here = savePathV.lastElement();
			savePathV.add(next);
			visited[next]=1;
			double candAnswer = findShortestDistence(savePathV, currentLenth+cityDistanceArray[here][next]);

			answer = Math.min(candAnswer, answer);
			visited[next]=0;
			savePathV.remove(savePathV.size()-1);
		}

		return answer;
	}
}


public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		Solution sol = new Solution();
		sol.init();
	}
}
