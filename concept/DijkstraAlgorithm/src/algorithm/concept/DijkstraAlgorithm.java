package algorithm.concept;

import java.util.Arrays;

public class DijkstraAlgorithm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int MAX = 1000;
		int[][] graph = {			
			    {MAX,10,30,15,MAX,MAX}
			    ,{MAX,MAX,MAX,MAX,20,MAX}
			    ,{MAX,MAX,MAX,MAX,MAX,5}
			    ,{MAX,MAX,5,MAX,MAX,20}
			    ,{MAX,MAX,MAX,MAX,MAX,20}
			    ,{MAX,MAX,MAX,20,MAX,MAX}
		};
		int[] d = new int[graph.length];
		int[] visited = new int[graph.length];
		Arrays.fill(d, MAX);
		d[0] = 0;
		int n = graph.length-1;
		int start = 0;
		visited[0] = 1;
		for(int distance : d)
			System.out.print(distance+" ");
		System.out.println();
		
		while(n>0) {
			for(int i = 0 ;i < graph.length ; i++) {
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
		
		for(int distance : d)
			System.out.print(distance+" ");
		System.out.println();
		
	}

}
