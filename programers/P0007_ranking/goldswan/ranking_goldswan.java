/********************************************************************
 * 작성일 : 2020-04-04
 * 작성자 : GoldSwan
 * 문제 : 프로그래머스 - 순위 (ranking)
 * 출저 : programers
 * 풀이 : 플로이드-워셜 알고리즘
 * Big-O  : O(N^3)
 * TEST 결과 : 모두통과
 *********************************************************************/
 
import java.util.Arrays;

class Solution{
    public int solution(int n, int[][] results) {
    	int answer = 0;
    	int [][] ranks = new int[n+1][n+1];
    	boolean [] isConnect = new boolean[n+1];

    	int INF = 987654321;

    	for(int[] rank:ranks)
    		Arrays.fill(rank, INF);

    	for(boolean flag:isConnect)
    		Arrays.fill(isConnect, true);

    	for(int i=1;i<=n;i++)
    		for(int j=1;j<=n;j++)
    	        if(i==j) ranks[i][j] = 0;

    	for(int i=0;i<results.length;i++) {
    		ranks[results[i][0]][results[i][1]]=1;//[A,B] A가 B를 이겼을 경우: A->B 인 랭킹 그래프 생성
    	}

		for(int k=1;k<=n;k++)
			for(int i=1;i<=n;i++)
				for(int j=1;j<=n;j++)
    				if(ranks[i][j]>ranks[i][k]+ranks[k][j])
    					ranks[i][j]=ranks[i][k]+ranks[k][j];

    	//정점 i 와 j를 잇는 경로가 n-1이 되야 순위를 결정할 수 있다.
    	for(int i=1;i<=n;i++)
    	{
    		for(int j=1;j<=n;j++) {
    			if(i==j)
    				continue;
    			//정점 i에서 j로 가는 경로와 j에서 i로 가는 경로가 없는 경우 정점 i와 j를 잇는 경로가 n-1보다 감소하므로 이는 순위를 결정할 수 없게 되는 정점이 되므로 false 처리
    	        if(ranks[i][j]==INF && ranks[j][i]==INF)
    	        	isConnect[i]=false;
    		}
    	}

    	for(int i=1;i<=n;i++)
    		if(isConnect[i])
    			answer++;

    	return answer;
    }
}

public class ranking_goldswan {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution sol = new Solution();
		int n = 5;
		int[][] results = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};
		//int[][] results = {{4, 3}, {3, 2}};
		int answer = sol.solution(n, results);
		System.out.println("answer:"+answer);
	}

}
