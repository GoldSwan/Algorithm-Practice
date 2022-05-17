package algorithm.programers;
import java.util.*;

class Solution {
	private int[][] cost;
	private List<ArrayList<Integer>> sales_info;
	private int[] g_sales;
	private final int INF = Integer.MAX_VALUE;

	public int solution(int[] sales, int[][] links) {
        int n1 = sales.length;
        int n2 = links.length;
        cost = new int[n1+1][2];
        sales_info = new ArrayList<ArrayList<Integer>>();
        g_sales = new int[n1];


        for(int i=0; i<n1; i++	) {
        	g_sales[i] = sales[i];
        }

        for(int i=0; i<n1+1; i++) {
        	sales_info.add(new ArrayList<Integer>());
        }

        for(int i=0; i<n2; i++) {
        	sales_info.get(links[i][0]).add(links[i][1]);
        }


        backTraversal(1);

        //1번 팀장 o <-> 1번 팀장 x 비교
        return Math.min(cost[1][0], cost[1][1]);
    }

	//후위 순회 - 왼쪽 서브 트리부터 탐색
	private void backTraversal(int pos) {
		int numChild = sales_info.get(pos).size();

		cost[pos][0] = 0; // 참석 x
		cost[pos][1] = g_sales[pos-1]; // 참석 o

		//오직 팀원일 경우 탐색 종료
		if( numChild == 0 )return;

		int minCost = INF;
		//트리 탐색 시작
		for(int child : sales_info.get(pos)) {
			backTraversal(child);//자식 탐색
			//팀원 x
			if(cost[child][0] < cost[child][1]) {
				cost[pos][0] += cost[child][0]; // 팀장 x 팀원 x
				cost[pos][1] += cost[child][0]; // 팀장 o 팀원 x
				//팀장x 팀원x인 경우 팀원을 최소1명 포함해야 하므로 (팀원 o - 팀원 x)의 최소 비용을 구해둠
				//(팀원 o - 팀원 x) 이유 : 참여 했을 때와 미참여 했을 때의 격차가 적을수록 참여를 했을 때 매출 손실 비용이 최소화되기 때문
				minCost = Math.min(minCost, cost[child][1] - cost[child][0]);
			}
			// 팀원 o
			else {
				cost[pos][0] += cost[child][1];//팀장 x 팀원 o
				cost[pos][1] += cost[child][1];//팀장 o 팀원 o
				//팀원이 참석한 경우이므로 0으로 초기화
				minCost = 0;
			}
		}
		//팀장x 팀원x 경우 최소비용 팀원 추가 - 이를 위해 팀원 o인 경우 minCost을 0으로 초기화 해주는 것
		cost[pos][0] += minCost;
	}
}
/*
class Solution {
    public int solution(int[] sales, int[][] links) {
        int answer = 0;
        Map<Integer,ArrayList<Integer>> mapTeam = new HashMap<>();
        int salesLength = sales.length;
        int[] visited = new int[salesLength];
        int[] twoRole = new int[salesLength];
        int[] realSales = new int[salesLength];

        for(int[] link : links) {
        	mapTeam.computeIfAbsent(link[0]-1, key -> new ArrayList<>()).add(link[1]-1);
        }

        Set<Integer> setKeys = mapTeam.keySet();

        for(int key : setKeys) {
        	ArrayList<Integer> list = mapTeam.get(key);
        	for(int person : list) {
        		if(mapTeam.containsKey(person)) {
        			twoRole[person] = 1;
        		}
        	}
        }

        for(int role : twoRole) {
        	System.out.print(role + " ");
        }

        return answer;
    }
}
*/
public class MinimizeTheDeclineInSales {

	public static void main(String[] args) {
		int[] sales = {14, 17, 15, 18, 19, 14, 13, 16, 28, 17};
		int[][] links = {{10, 8}, {1, 9}, {9, 7}, {5, 4}, {1, 5}, {5, 10}, {10, 6}, {1, 3}, {10, 2}};
		int answer = 0;
		Solution solution = new Solution();

		answer = solution.solution(sales, links);
		System.out.println("answer:"+ answer);
	}

}
