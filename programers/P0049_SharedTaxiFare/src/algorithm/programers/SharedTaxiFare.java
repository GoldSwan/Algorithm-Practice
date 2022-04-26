package algorithm.programers;

import java.util.*;

//정답 참고용
class Solution {
    ArrayList<ArrayList<Edge>> graph;
    class Edge implements Comparable<Edge>{
        int next;
        int cost;
        Edge(int next, int cost){
            this.next = next;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge e){
            return this.cost - e.cost;
        }
    }
    int[] getMinDistance(int start, int[] distance){
    	//우선순위 큐로 다익스트라 구현
        PriorityQueue<Edge> pq = new PriorityQueue<>(graph.get(start));

        for(Edge e : graph.get(start)) { 
        	distance[e.next] = e.cost;
        }    
        distance[start] = 0;
        
        while(!pq.isEmpty()){
            Edge e = pq.poll();
            if(distance[e.next] < e.cost) continue;
            for(Edge ne : graph.get(e.next)){
                if(distance[ne.next] > e.cost + ne.cost){
                	distance[ne.next] = e.cost + ne.cost;
                    pq.add(new Edge(ne.next, e.cost + ne.cost));
                }
            }
        }
        
        return distance;
    }
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int min = Integer.MAX_VALUE;

        graph = new ArrayList<>();
        
        for(int i = 0 ; i < n ; i ++) {
        	graph.add(new ArrayList<>());
        }
        
        for(int[] fare : fares){
            graph.get(fare[0] - 1).add(new Edge(fare[1] - 1, fare[2]));
            graph.get(fare[1] - 1).add(new Edge(fare[0] - 1, fare[2]));
        }
 
        int[] startS = new int[n];
        int[] startA = new int[n];
        int[] startB = new int[n];

        Arrays.fill(startS, 20000001);
        Arrays.fill(startA, 20000001);
        Arrays.fill(startB, 20000001);
        
        startS = getMinDistance(s - 1, startS);
        startA = getMinDistance(a - 1, startA);
        startB = getMinDistance(b - 1, startB);

        for(int i = 0 ; i < n ; i ++) {
        	min = Math.min(min,  startS[i] + startA[i] + startB[i]);
        }
        return min;
    }
}

//테스트 했는데 테스트 케이스를 통과 못하는게 있어서 잠시 보류...
/*
class Solution {
	//MAX : 지점 N이 200 이하이고 요금이 100000이하이므로 요금이 20000001을 넘을 수 없다.
	//Integer.MAX_VALUE로 설정하면 아래 계산과정에서 산출오버플로우가 발생하여 이상한 값이 계산되므로 적절한 MAX 선정 필요
    private final int MAX = 20000000;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int min = Integer.MAX_VALUE;
        int[][] graph = new int[n][n];
		int start = 0;
		int[] distanceS = {};
		int[] distanceA = {};
		int[] distanceB = {};
		
		for(int[] graphArray : graph) {
			Arrays.fill(graphArray, MAX);
		}
		
        for(int[] fare : fares) {
        	graph[fare[0]-1][fare[1]-1] = fare[2];
        	graph[fare[1]-1][fare[0]-1] = fare[2];
        }
        
        start = s-1;
        distanceS = getMinDistance(start, graph);
        start = a-1;
        distanceA = getMinDistance(start, graph);
        start = b-1;
        distanceB = getMinDistance(start, graph);
        
        //(S+X) + (X+A) + (X+B)가 최소인 X지점 찾기
        for(int i = 0 ; i < n ; i++) {
        	if(min > distanceS[i] +  distanceA[i] +  distanceB[i]) {
        		min = distanceS[i] +  distanceA[i] +  distanceB[i];
        	}
        }
        return min;
    }
    
    public int[] getMinDistance(int start, int[][] graph) {
    	int n = graph.length;
        int[] d = new int[n];
    	int[] visited = new int[n];
		visited[start] = 1;	
		Arrays.fill(d, MAX);
		d[start] = 0;

        //다익스트라 알고리즘
		while(n>0) {
			for(int i = 0 ;i < n ; i++) {
				if(visited[i] == 0 && d[i]>d[start]+graph[start][i]) {
					d[i] = d[start]+graph[start][i];
				}
			}

			int min = Integer.MAX_VALUE;
			for(int i = 0 ;i<d.length;i++) {
				 if(visited[i] == 0 && min > d[i]) {
					 min = d[i];
					 start = i;				 
				 }
			}
			visited[start] = 1;
			n--;
		}

		return d;
    }
}
*/
public class SharedTaxiFare {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		//예제1
		int n = 6;
		int s = 4;
		int a = 6;
		int b= 2;

		int[][] fares = {{4,1,10}
		                 ,{3,5,24}
		                 ,{5,6,2}
		                 ,{3,1,41}
		                 ,{5,1,24}
		                 ,{4,6,50}
		                 ,{2,4,66}
		                 ,{2,3,22}
		                 ,{1,6,25}};
        */
		/*
		//예제2
		int n = 7;
		int s = 3;
		int a = 4;
		int b= 1;

		int[][] fares = {{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}};
		*/
		
		int n = 5;
		int s = 1;
		int a = 2;
		int b= 5;
		
		int[][] fares = {{1, 3, 1}, {1, 2, 5}, {3, 2, 3} , {1,4,3}, {4,5,5}};
		
		Solution solution = new Solution();
		int min = solution.solution(n, s, a, b, fares);
		System.out.println(min);
	}

}
