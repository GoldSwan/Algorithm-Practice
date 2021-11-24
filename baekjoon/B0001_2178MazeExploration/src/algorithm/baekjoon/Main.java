package algorithm.baekjoon;
import java.io.*;
import java.util.*;

class Solution {
	int N;
	int M;
	int [][] maze = {};
	int [][] distance = {};
	int [][] visit = {};
	int [] xMove = { 1, 0, -1, 0 };
	int [] yMove = { 0, 1, 0, -1 };

	class Point {
	    private Integer x;
	    private Integer y;

	    Point(Integer x, Integer y) {
	        this.x = x;
	        this.y = y;
	    }

	    public Integer getX(){
	        return x;
	    }

	    public Integer getY(){
	        return y;
	    }
	}
	
	public void init() {
		String strNM;
		String strMaze;
		StringTokenizer st;
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			strNM = br.readLine();
			st = new StringTokenizer(strNM, " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			maze = new int[N][M];
			distance = new int[N][M];
			visit = new int[N][M];

			for (int i = 0; i < N; ++i) {
				strMaze = br.readLine();
				for (int j = 0; j < M; ++j) {			
					maze[i][j] = Integer.parseInt(Character.toString(strMaze.charAt(j)));				
				}
			}
		}		
		catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void solution() {		
		bfs(0,0);
		System.out.println(distance[N - 1][M - 1] + 1);
	}
	
	public void bfs(int startX, int startY) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(startX,startY));
		visit[startX][startY] = 1;
		
		while (!q.isEmpty())
		{
			int x = q.peek().getX();
			int y = q.peek().getY();

			q.poll();

			for (int i = 0; i < 4; i++) 
			{
				int nextX = x + xMove[i];
				int nextY = y + yMove[i];

				if (0 <= nextX && nextX < N && 0 <= nextY && nextY < M && maze[nextX][nextY] == 1 && visit[nextX][nextY] == 0)
				{					
					distance[nextX][nextY] = distance[x][y] + 1;
					visit[nextX][nextY] = 1;
					q.add(new Point(nextX, nextY));
				}
			}
		}
	}
}

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
		solution.init();
		solution.solution();
	}

}
