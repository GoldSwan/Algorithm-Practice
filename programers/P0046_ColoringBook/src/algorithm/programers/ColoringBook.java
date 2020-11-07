package algorithm.programers;

class Solution {
	//완전탐색 이용
    int[][] move ={{-1,0},{1,0},{0,-1},{0,1}};//(x,y)상하좌우 움직임
    int SizeOfOneArea = 0;
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        int[][] pictureCopy = new int[m][n];
       for(int i = 0; i<m;i++){
            for(int j = 0; j<n;j++){
                pictureCopy[i][j] = picture[i][j];
            }
       }
        for(int i = 0; i<m;i++){
            for(int j = 0; j<n;j++){
                if(pictureCopy[i][j] > 0){
                    SizeOfOneArea = 1;
                    int areaNumber = pictureCopy[i][j];
                    pictureCopy[i][j] = 0;
                    getSizeOfOneArea(m, n, j, i, pictureCopy, areaNumber);
                    ++numberOfArea;
                    if(SizeOfOneArea>maxSizeOfOneArea)
                        maxSizeOfOneArea = SizeOfOneArea;
                }
            }
        }        
        
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    public void getSizeOfOneArea(int m, int n, int x, int y, int[][] pictureCopy, int areaNumber){
        //영역 탐색
        for(int i = 0;i<4;i++){
            int xNext = x + move[i][1];
            int yNext = y + move[i][0];            
            if(isCanArea(m, n, xNext, yNext) && pictureCopy[yNext][xNext] == areaNumber){
                pictureCopy[yNext][xNext] = 0;
                ++SizeOfOneArea;
                getSizeOfOneArea(m, n, xNext, yNext, pictureCopy, areaNumber);
            }
        }
    }
    
    public boolean isCanArea(int m, int n, int x, int y){
        return ((x<0) || (x>=n) || (y<0) || (y>=m)) ? false : true;
    }
}

public class ColoringBook {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution sol = new Solution();
		int m = 13;
		int n =16;
		int[][] picture = {
				{0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0},
				{0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0},
				{0,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0},
				{0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
				{0,1,1,1,1,2,1,1,1,1,2,1,1,1,1,0},
				{0,1,1,1,2,1,2,1,1,2,1,2,1,1,1,0},
				{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
				{0,1,3,3,3,1,1,1,1,1,1,3,3,3,1,0},
				{0,1,1,1,1,1,2,1,1,2,1,1,1,1,1,0},
				{0,0,1,1,1,1,1,2,2,1,1,1,1,1,0,0},
				{0,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0},
				{0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0}
				};

		int[] answer = sol.solution(m, n, picture);
		
		System.out.println("answer:"+answer[0]+" "+answer[1]);
	}

}
