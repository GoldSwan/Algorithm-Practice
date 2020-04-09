package graph_01_2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

/*
 * 작성일 : 2019-11-17
 * 문제 : 프로그래머스 - 가장먼노드
 * 내용 : 그래프 1번 노드에서 각 노드 사이의 거리를 최단거리라고 할 떄 가장 먼 노드들 갯수 구하기
 * 풀이법 : BFS - Queue 이용
 * 테스트 통과 유무 : 1~9번 전부 통과
 * 실패 원인 파악 : X
 */

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
/*		for(int i=0;i<v.size();i++) {
			System.out.print("v("+i+") ");
			for(int j=0;j<v.get(i).size();j++) {
				System.out.print(v.get(i).get(j)+" ");
			}
			System.out.println();
		}*/

		bfs_q();

/*		for(int i=0;i<visisted.length;i++) {
			System.out.println(visisted[i]+" ");
		}

		System.out.println("answer:" + answer);
		System.out.println("max_node_length:" + max_node_length);
		*/
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

public class furthestNode_goldswan {

	public static void main(String[] args) {
		int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
		Solution sol = new Solution();
		int answer = sol.solution(6, edge);
		System.out.println("answer:" + answer);
	}

}
