package algorithm.programers;

//triangle[i][j]가 갈 수 있는 경로는 triangle[i+1][j], triangle[i+1][j+1] 두가지 존재
//위에서부터 완전 탐색을 하면 level 1의 3,8이 level2 의 1을 중복 계산하므로
//중복을 최소화하기 위해 아래서부터 triangle[i+1][j], triangle[i+1][j+1] -> triangle[i][j]로 거꾸로 추적하여 최대값 계산
//level : 삼각형의 층 레벨 (예 : level1 {7} level2 {3,8} level3 {8,1,0}...)
//index : 삼각형 층 별 인덱스
class Solution {
    public int solution(int[][] triangle) {
    	for(int level =triangle.length-2;level>=0;level--){
             for(int index=0;index<triangle[level].length;index++){
                 triangle[level][index]=(triangle[level+1][index]>triangle[level+1][index+1]) ? triangle[level][index]+triangle[level+1][index] : triangle[level][index]+ triangle[level+1][index+1];
             }
         }
    	
    	for(int[] row : triangle) {
    		for(int value : row)
    			System.out.print(value+" ");
    		System.out.println();
    	}
         return triangle[0][0];
    }  
}

public class IntegerTriangle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution solution = new Solution();
		int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
		int answer = solution.solution(triangle);
		System.out.println("answer:"+answer);
	}

}
