package algorithm.concept;
import java.util.*;

public class DijkstraAlgorithmByPriorityQueue{
	public static class Edge implements Comparable<Edge>{
		private int next;
		private int cost;
		public Edge(int next, int cost){
			this.next = next;
			this.cost = cost;
		}
		@Override
		public int compareTo(Edge e) {
			return Integer.compare(this.cost, e.cost);
		}
	}
	public static void main(String[] args) {
		int MAX = 1000;
		int start = 0;
		int[][] graph = {
			    {MAX,10,30,15,MAX,MAX}
			    ,{MAX,MAX,MAX,MAX,20,MAX}
			    ,{MAX,MAX,MAX,MAX,MAX,5}
			    ,{MAX,MAX,5,MAX,MAX,20}
			    ,{MAX,MAX,MAX,MAX,MAX,20}
			    ,{MAX,MAX,MAX,20,MAX,MAX}
		};
		int GRAPH_LENGTH = graph.length;
		ArrayList<ArrayList<Edge>> listGraph = new ArrayList<>();
		int[] distance = new int[GRAPH_LENGTH];
		PriorityQueue<Edge> pq = new PriorityQueue<>();

		//2차원 배열그래프를 리스트로 변환
		for(int i = 0 ; i < GRAPH_LENGTH ; i++) {
			listGraph.add(new ArrayList<>());
		}
		for(int i = 0 ; i < GRAPH_LENGTH ; i++) {
			for(int j = 0 ; j < GRAPH_LENGTH ; j++) {
				if(graph[i][j] == MAX)
					continue;
				listGraph.get(i).add(new Edge(j, graph[i][j]));
			}
		}

		Arrays.fill(distance, MAX);
		distance[start] = 0;

		for(int cost : distance)
			System.out.print(cost+" ");
		System.out.println();
		//distance, 우선순위 큐 최초 초기화
		for(Edge e : listGraph.get(start)) {
			pq.add(e);
			distance[e.next] = e.cost;
		}
		//우선순위 큐를 이용한 다익스트라 알고리즘
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			if(distance[e.next] < e.cost) continue;
			for(Edge ne : listGraph.get(e.next)) {
				if(distance[ne.next] > ne.cost + e.cost) {
					distance[ne.next]  = ne.cost + e.cost;
					pq.add(new Edge(ne.next, ne.cost + e.cost));
				}
			}
		}

		for(int cost : distance)
			System.out.print(cost+" ");
		System.out.println();
	}
}
