package algorithm.programers;
import java.util.*;

public class BFSByQueue {

	public static void main(String[] args) {
		int[][] vertexs = {{1, 3}, {1, 2}, {3, 2} , {1,4}, {4,5}, {2,6}};
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		int vertex_cnt = 6;
		int vertexs_length = vertexs.length;
		int[] visited = new int[vertexs_length+1];
		int start = 1;
		
		for(int i = 0 ; i <= vertex_cnt ; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0 ; i < vertexs_length ; i++) {
			graph.get(vertexs[i][0]).add(vertexs[i][1]);
		}
		
		dfs(graph, visited, start);
		
		System.out.print("깊이 : ");
		for(int i = 1 ; i <=vertex_cnt ; i++) {
			System.out.print((visited[i]-1)+" ");
		}
		System.out.println();
	}
	private static void dfs(ArrayList<ArrayList<Integer>> graph, int[] visited, int start) {
		Queue<Integer> q = new LinkedList<>();
		int depth = 0;
		q.add(start);
		visited[start] = 1;
		System.out.print("탐색경로 : " + start + " ");
		while(!q.isEmpty()) {
			int vs = q.poll();
			for(Integer ve : graph.get(vs)) {
				if(visited[ve] == 0) {
					System.out.print(ve + " ");
					q.add(ve);
					visited[ve] = visited[vs] + 1;//깊이 기록
				}
			}
		}
		System.out.println();
	}

}
