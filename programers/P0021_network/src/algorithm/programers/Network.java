package algorithm.programers;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        int[] visited = new int[n];
        
        for(int i = 0 ; i < computers.length ; i++) {
        	if(visited[i]==0) {
        		dfs(computers, visited, i);
        		answer++;
        	}
        }
        return answer;
    }
    public void dfs(int[][] computers, int[] visited, int start) {
    	visited[start] = 1;
    	for(int end = 0 ; end < computers.length; end++) {
    		if(computers[start][end]==1 && visited[end]==0) {
    			dfs(computers, visited, end);
    		}
    	}
    }
}

public class Network {

	public static void main(String[] args) {
		int n =3;
		int[][] computers = {{1,1,0},{1,1,0},{0,0,1}};
		int answer = 0;
		Solution solution = new Solution();
		answer = solution.solution(n, computers);
		System.out.println(answer);
	}

}
