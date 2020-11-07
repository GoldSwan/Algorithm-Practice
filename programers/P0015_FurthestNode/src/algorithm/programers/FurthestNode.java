package algorithm.programers;

import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
//이후에 풀었던 풀이코드
class Solution {
    int nodeCnt = 0;
    int maxNodeLength = 0;
    public int solution(int n, int[][] edge) {
    	ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        int[] visited = new int[n];
        
        for(int i =0;i<n;i++) {
        	graph.add(new ArrayList<Integer>());
        }
        
        for(int i = 0 ;i<edge.length;i++){          
            graph.get(edge[i][0]-1).add(edge[i][1]-1);           
            graph.get(edge[i][1]-1).add(edge[i][0]-1);
        }        
        
        bfs(graph, visited);

        return nodeCnt;
    }
    
    public void bfs(ArrayList<ArrayList<Integer>> graph, int[] visited){
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);//1노드부터 시작
        visited[0] = 1;
        while(!q.isEmpty()){
            int start = q.poll();      
            for(int i = 0 ;i<graph.get(start).size();i++){
                if(visited[graph.get(start).get(i)]==0){
                    q.offer(graph.get(start).get(i));
                    visited[graph.get(start).get(i)] = visited[start] + 1; 
                    
                    if(maxNodeLength < visited[graph.get(start).get(i)]){
                        maxNodeLength = visited[graph.get(start).get(i)];
                        nodeCnt = 1;
                        continue;
                    }
                    
                    nodeCnt = maxNodeLength == visited[graph.get(start).get(i)] ? ++nodeCnt : nodeCnt; 
                }
            }
        }
    }
}

//이전에 풀었던 풀이코드
/*
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

class Solution {

	int answer;
	int max_node_length;
	Queue<Integer> bfsQueue;
	Vector<Vector<Integer>> v;
	int[] visisted;

	public int solution(int n, int[][] edge) {

		answer = 0;
		max_node_length = 0;
		bfsQueue = new LinkedList<Integer>();
		v = new Vector<Vector<Integer>>();
		visisted = new int[n];

		for(int i=0;i<n;i++) {
			v.add(new Vector<Integer>());
		}

		for(int i=0;i<edge.length;i++) {
			int x = edge[i][0];
			int y = edge[i][1];

			v.get(x-1).add(y-1);
			v.get(y-1).add(x-1);
		}

		//인접 리스트 출력

		bfs_q();

		return answer;
	}

	public void bfs_q() {
		visisted[0] = 1;
		bfsQueue.offer(0);

		while(!bfsQueue.isEmpty()) {
			int p = bfsQueue.peek();
			bfsQueue.poll();

			for(int i=0;i<v.get(p).size();i++) {
				if(visisted[v.get(p).get(i)]==0)//만약 방문을 하지 않은 정점이라면
				{
					visisted[v.get(p).get(i)] = visisted[p]+1;
					bfsQueue.add(v.get(p).get(i));
					if(max_node_length<visisted[v.get(p).get(i)]) {
						max_node_length = visisted[v.get(p).get(i)];
						answer=1;
					}
					else if (max_node_length==visisted[v.get(p).get(i)]){
						answer++;
					}
				}
			}
		}
	}
}
*/
public class FurthestNode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution sol = new Solution();
		int n = 6;
		int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};

		int answer = sol.solution(n, edge);
		System.out.println("answer:"+answer);
	}

}
