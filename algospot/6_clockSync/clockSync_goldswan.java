import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/********************************************************************
 * 작성일 : 2020-02-02
 * 작성자 : GoldSwan
 * 문제 : 알고스팟 - 시계 맞추기 (clockSync)
 * 출저 : algospot
 * 풀이 : 완전탐색
 * Big-O  : O(4^N) N : 스위치 갯수
 * TEST 결과 : 	2316ms
 *********************************************************************/
class Solution {

	final int SWITCHES = 10;
	final int CLOCKS = 16;
	final int INF = 987654321;

	public void solution() throws NumberFormatException, IOException {

		int C;
		int answer;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[] clocks = new int [CLOCKS];
		int[][] switchs = {
				 {1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0}
				,{0,0,0,1,0,0,0,1,0,1,0,1,0,0,0,0}
				,{0,0,0,0,1,0,0,0,0,0,1,0,0,0,1,1}
				,{1,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0}
				,{0,0,0,0,0,0,1,1,1,0,1,0,1,0,0,0}
				,{1,0,1,0,0,0,0,0,0,0,0,0,0,0,1,1}
				,{0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,1}
				,{0,0,0,0,1,1,0,1,0,0,0,0,0,0,1,1}
				,{0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0}
				,{0,0,0,1,1,1,0,0,0,1,0,0,0,1,0,0}
		};
		C = Integer.parseInt(br.readLine());

		for(int i=0;i<C;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int k=0;k<CLOCKS;k++) {
				clocks[k] = Integer.parseInt(st.nextToken());
			}
			answer = solve(clocks, switchs, 0);

			answer = (answer == INF) ? -1 : answer;

			System.out.println(answer);
		}
	}

	//시계 CHECK 함수 구현
	public boolean checkClocks(int[] clocks) {

		int count=0;

		for(int i=0;i<clocks.length;i++) {
			if(clocks[i]==12) count++;
		}

		if(count==CLOCKS) return true;

		return false;
	}
	//스위치 PUSH 동작함수 구현
	public void push(int[] clocks, int[][] switchs, int swichNumber) {

		for(int i=0;i<clocks.length;i++) {
			if(switchs[swichNumber][i]==1) {
				clocks[i] += 3;

				if(clocks[i]==15)
					clocks[i] = 3;
			}
		}
	}

	//MIN CASE 함수 구현
	public int solve(int[] clocks, int[][] switchs, int swichNumber) {
		//기저사실 : 9번 스위치까지 1~4번 누른경우 모두 12시면 0을 반환하여 누른 수 반환되도록 아니면 매우 큰 수 반환
		if(swichNumber == SWITCHES) return checkClocks(clocks) ? 0 : INF;

		int answer = INF;

		for(int i=0;i<4;i++) {
			answer = Math.min(answer, i + solve(clocks, switchs, swichNumber+1));

			push(clocks, switchs, swichNumber);
		}

		return answer;
	}
}
public class clockSync_goldswan {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		Solution sol = new Solution();
		sol.solution();
	}

}
