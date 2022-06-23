package algorithm.programers;
import java.util.*;

class Solution {
	
	private ArrayList<ArrayList<Integer>> childs;
	private int[] Info;
	private int maxSheepCnt = 0;
	
    public int solution(int[] info, int[][] edges) {
		Info = info;
		childs = new ArrayList<>();
		int infoLength = info.length;
		for(int i = 0 ; i < infoLength ; i++) {
			childs.add(new ArrayList<>());
		}
		for (int[] edge : edges) {
			int parent = edge[0];
			int child = edge[1];
			childs.get(parent).add(child);
		}
 
		List<Integer> list = new ArrayList<>();
		list.add(0);
		dfs(0, 0, 0, list);
		return maxSheepCnt;
    }
    
	private void dfs(int index, int sheepCnt, int wolfCnt, List<Integer> listNextEdges) {
		if (Info[index] == 0) sheepCnt++;
		else wolfCnt++;
 
		if (wolfCnt >= sheepCnt) return;
		maxSheepCnt = Math.max(sheepCnt, maxSheepCnt);
   
		List<Integer> list = new ArrayList<>();
		list.addAll(listNextEdges);
		list.remove(Integer.valueOf(index));
		
		if (childs.get(index) != null) {
			for (int child : childs.get(index)) {
				list.add(child);
			}
		}
        
		for (int next : list) {
			dfs(next, sheepCnt, wolfCnt, list);
		}
	}
}

public class SheepAndWolf {

	public static void main(String[] args) {

		Solution solution = new Solution();
		int[] info = {0,0,1,1,1,0,1,0,1,0,1,1};
		int[][] edges = {{0,1},{0,2},{1,3},{1,4},{2,5},{2,6},{3,7},{4,8},{6,9},{9,10}};
		int maxSheepCnt = solution.solution(info, edges);
		System.out.println(maxSheepCnt);
	}

}
