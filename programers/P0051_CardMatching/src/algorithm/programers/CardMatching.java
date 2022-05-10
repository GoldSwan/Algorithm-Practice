package algorithm.programers;
import java.util.*;

class Pair{
	int x;
	int y;
	int move;
	
	public Pair(int x, int y, int move){
		this.x =x;
		this.y =y;
		this.move = move;
	}
}

class Solution {
	private List<String> orders;
	private int[] dx = {1,-1,0,0};
	private int[] dy = {0,0,1,-1}; 
	public int solution(int[][] board, int r, int c) {		 
		 int numCardCount =0;
		 int[] arrCards = {};
		 
		 numCardCount =  getNumCardCount(board);
		 arrCards = getArrCards(numCardCount);

		 //순서를 고려한 카드 조합(순열) 경우의 수 구하기
		 orders = new ArrayList<>();
		 permutation("", 0, arrCards);
		 
		 int min = Integer.MAX_VALUE;
		 for(String comb : orders) {
			 String[] order = comb.split("");
			 
			 int total_move =0;
			 int[] pos = new int[2];
			 pos[0] =r;
			 pos[1] =c;			
			 
			 int[][] copy_board  = new int[4][4];
			 for(int i=0; i<4; i++) {
				 for(int j=0; j<4; j++) {
					 copy_board[i][j] = board[i][j];
				 }
			 }
			 
			 for(String target_card : order) {
				 int card_num = Integer.parseInt(target_card);

				 total_move += cardSearch(pos, card_num, copy_board);				 
				 copy_board[pos[0]][pos[1]] = 0;
				 total_move += 1;

				 total_move += cardSearch(pos, card_num, copy_board);
				 copy_board[pos[0]][pos[1]] = 0;
				 
				 total_move += 1;
			 }
			 
			 min = Math.min(min, total_move);
		 }
		 
		 return min;
	}

	private int getNumCardCount(int[][] board) {
		int numCardCount = 0;
		 for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(board[i][j] != 0) {
					numCardCount ++;
				}
			}	
		 }
		return numCardCount/2;
	}
	
	private int[] getArrCards(int numCardCount) {
		 int[] arrCards = new int[numCardCount];
		 for(int i=0; i<numCardCount; i++) {
			 arrCards[i] = i+1;
		 }
		 return arrCards;
	}
	
	private void permutation(String comb, int numDepth,int[] arrCards) {
		if(arrCards.length == numDepth) {
			orders.add(comb);
			return ;
		}
		
		for(int i=0; i<arrCards.length; i++) {
			int num = arrCards[i];
			if(!comb.contains(""+num)) {
				permutation(comb+num, numDepth+1, arrCards);
			}
		}
		
	}
	
	private int cardSearch(int[] pos, int target_card, int[][] copy_board) {
		Queue<Pair> q = new LinkedList<>();
		boolean[][] check = new boolean[4][4];
		int x= pos[0];
		int y= pos[1];
		
		check[x][y] = true;
		q.add(new Pair(x,y,0));
		while(!q.isEmpty()) {
			Pair next = q.poll();
			int px = next.x;
			int py = next.y;
			int move =next.move;
			
			if(copy_board[next.x][next.y] == target_card) {
				pos[0] = next.x;
				pos[1] = next.y;
				return move;
			}
			
			for(int i=0; i<4; i++) {
				int nx = px + dx[i];
				int ny = py + dy[i];
				
				if(nx<0 || ny<0 || nx>3 || ny>3) continue;
				if(check[nx][ny]) continue;
				
				check[nx][ny] = true;
				q.add(new Pair(nx,ny, move+1));
			}
			
			for(int i=0; i<4; i++) {
				Pair res = checkRoute(px,py, i, copy_board);
				int nx = res.x, ny =res.y;
				
				if(nx==x && ny ==y) continue;
				if(check[nx][ny]) continue;
				
				check[nx][ny] = true;
				q.add(new Pair(nx,ny, move+1));
			}
		}
		return 0;
		
		
		
	}

	private Pair checkRoute(int x, int y, int direction, int[][] copy_board) {
		x += dx[direction];
		y += dy[direction];
		
		while(x >= 0 && x < 4 && y >= 0 && y < 4) {
			if(copy_board[x][y] != 0) return new Pair(x,y,0);
			
			x += dx[direction];
			y += dy[direction];
		}

		return new Pair(x-dx[direction],y-dy[direction],0);	
		
	}
}

public class CardMatching {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution solution = new Solution();
		int[][] board = {{1,0,0,3},{2,0,0,0},{0,0,0,2},{3,0,1,0}};
		int r = 1;
		int c = 0;
		int answer = 0;
		 
		answer = solution.solution(board, r, c);
		System.out.println("answer:"+ answer);
	}

}
