package algorithm.programers;

import java.util.Arrays;

class Solution { 
	int[] tree;
	public int solution(int n, int[][] costs) {
        int answer = 0;
        int minCost = 0;
        //크리스컬 알고리즘(Kruskal’s algorithm) 을 사용하여 최소 비용 신장 트리(MST : Minimum Spanning Tree)을 만들어 최소 비용을 구한다.
        Arrays.sort(costs, (o1,o2)->{return Integer.compare(o1[2], o2[2]);});//Comparator의 compare 메소드(람다로 무명메서드 표현)를 오버라이딩하여 최소 비용으로 오름차순 정렬
        tree = new int[n];
        
        for (int i = 0; i < n; i++) {
        	tree[i] = i;
        }
        
        for(int[] cost : costs) {
        	//Union-Find 알고리즘을 이용하여 최소 신장트리 최소 비용 구하기
        	//연결되어 있지 않으면 노드 집합 형성하여 연결
        	if(isNotConnect(find(cost[0]),find(cost[1]))) {
        		union(cost[0], cost[1]);
        		minCost += cost[2];
        	}
        }     
        
        answer = minCost;
        
        return answer;
    }
    
	boolean isNotConnect(int node1, int node2) {
		return (node1==node2) ? false : true;
	}
	
    int find(int x) {
        //루토 노드와 자식 노드가 동일할 경우 어떤 집합에도 속해있지 않은 상태
        if (tree[x] == x) {
            return x;
        }        
        //부모 노드를 찾아 올라가기 
        return find(tree[x]);
        
    }

    void union(int x, int y){
        // 각 노드의 루트 노드를 찾기
        x = find(x);
        y = find(y);
        // 그 후 부모, 자식 간 집합 형성하기
        tree[y] = x;
    }
}

public class ConnectingIsles {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution sol = new Solution();
		int n = 4;
		int[][] costs = {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};
		//int[][] costs = {{0,1,7},{1,2,7},{2,3,8},{3,4,8},{4,5,8}};
		//int[][] costs = {{0,1,1},{2,3,8}};
		int answer = sol.solution(n,costs);

		System.out.println("answer:" + answer);
	}

}
