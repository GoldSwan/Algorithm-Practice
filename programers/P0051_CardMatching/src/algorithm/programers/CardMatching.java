package algorithm.programers;
import java.util.*;

class Solution {
	private List<String> arrListCases;
	private final int[][] arrXY = {{1,0},{-1,0},{0,1},{0,-1}};
	private class Point{
		private int x;
		private int y;
		private int move;

		public Point(int x, int y, int move){
			this.x =x;
			this.y =y;
			this.move = move;
		}
	}

	public int solution(int[][] board, int r, int c) {
		 int numCardCount =0;
		 int[] arrCards = {};
		 int min = Integer.MAX_VALUE;

		 numCardCount =  getNumCardCount(board);
		 arrCards = getArrCards(numCardCount);

		 //순서를 고려한 카드 조합(순열) 경우의 수 구하기
		 arrListCases = new ArrayList<>();
		 permutation("", 0, arrCards);

		 for(String strCase : arrListCases) {
			 String[] arrCase = strCase.split("");

			 int numTotalMove =0;
			 int[] pos = new int[2];
			 pos[0] =r;
			 pos[1] =c;

			 int[][] arrCopyBoard  = new int[4][4];

			 for(int i=0; i<4; i++) {
				 System.arraycopy(board[i], 0, arrCopyBoard[i], 0, 4);
			 }

			 for(String strTargetCard : arrCase) {
				 int numCard = Integer.parseInt(strTargetCard);

				 numTotalMove += cardSearch(pos, numCard, arrCopyBoard);
				 arrCopyBoard[pos[0]][pos[1]] = 0;
				 numTotalMove += 1;

				 numTotalMove += cardSearch(pos, numCard, arrCopyBoard);
				 arrCopyBoard[pos[0]][pos[1]] = 0;

				 numTotalMove += 1;
			 }

			 min = Math.min(min, numTotalMove);
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

	private void permutation(String strCase, int numDepth, int[] arrCards) {
		if(arrCards.length == numDepth) {
			arrListCases.add(strCase);
			return;
		}

		for(int i=0; i<arrCards.length; i++) {
			int num = arrCards[i];
			if(!strCase.contains(""+num)) {
				permutation(strCase+num, numDepth+1, arrCards);
			}
		}
	}

	private int cardSearch(int[] pos, int numTargetCard, int[][] arrCopyBoard) {
		Queue<Point> q = new LinkedList<>();
		boolean[][] check = new boolean[4][4];
		int x= pos[0];
		int y= pos[1];

		check[x][y] = true;
		q.add(new Point(x,y,0));
		while(!q.isEmpty()) {
			Point next = q.poll();
			int px = next.x;
			int py = next.y;
			int move =next.move;

			if(arrCopyBoard[next.x][next.y] == numTargetCard) {
				pos[0] = next.x;
				pos[1] = next.y;
				return move;
			}

			for(int i=0; i<4; i++) {
				int nx = px + arrXY[i][0];
				int ny = py + arrXY[i][1];

				if(nx<0 || ny<0 || nx>3 || ny>3) continue;
				if(check[nx][ny]) continue;

				check[nx][ny] = true;
				q.add(new Point(nx,ny, move+1));
			}

			for(int i=0; i<4; i++) {
				Point res = checkRoute(px,py, i, arrCopyBoard);
				int nx = res.x, ny =res.y;

				if(nx==x && ny ==y) continue;
				if(check[nx][ny]) continue;

				check[nx][ny] = true;
				q.add(new Point(nx,ny, move+1));
			}
		}
		return 0;



	}

	private Point checkRoute(int x, int y, int direction, int[][] copyBoard) {
		x += arrXY[direction][0];
		y += arrXY[direction][1];

		while(x >= 0 && x < 4 && y >= 0 && y < 4) {
			if(copyBoard[x][y] != 0) return new Point(x,y,0);

			x += arrXY[direction][0];
			y += arrXY[direction][1];
		}

		return new Point(x-arrXY[direction][0],y-arrXY[direction][1],0);
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
