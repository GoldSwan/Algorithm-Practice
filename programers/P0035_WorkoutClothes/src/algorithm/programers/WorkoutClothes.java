package algorithm.programers;

import java.util.Arrays;
//Problem solving time : 50min
//오래걸린 이유 : 
//문제의 제한사항을 간과함.
//여별옷을 가져왔는데 도난당한 학생들도 옆에서 빌려입으면 다른 학생에게 옷을 빌려줄 수 있다는 것으로 잘못 이해하고 예외처리를 안함.
//여별옷을 가져왔는데 도난당한 학생들은 옷을 빌려주지 못함.
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        boolean[] isLost = new boolean[n+1];
        boolean[] isWearExtraClothes = new boolean[n+1];//여별 체육복을 가져왔는데 체육복을 도난당한 학생이 체육복을 입는 경우 체크
        
        Arrays.sort(reserve);//왼쪽부터 빌려주어 최대값이 되도록 여별 체육복을 갖은 학생 정렬
        
        //도난당한 학생 체크
        for(int lostStudent : lost) 
        	isLost[lostStudent] = true;
        
        //여별 체육복을 가져왔는데 도난당한 학생 체크
        for(int reserveStudent : reserve) { 
        	if(isLost[reserveStudent] == true) {
        		isLost[reserveStudent] = false;
        		isWearExtraClothes[reserveStudent] = true;
        	}
        }
        
        for(int reserveStudent : reserve) { 
        	if(isWearExtraClothes[reserveStudent]==true)//여별 체육복을 가져왔는데 도난당한 학생은 제외
        		continue;
        	if(reserveStudent-1>=1 && reserveStudent-1<=n && isLost[reserveStudent-1] == true) {//여별 체육복 가져온 학생이 왼쪽을 빌려주는 경우
        		isLost[reserveStudent-1] = false;
        		continue;
        	}
        	if(reserveStudent+1>=1 && reserveStudent+1<=n && isLost[reserveStudent+1] == true) {//여별 체육복 가져온 학생이 오른쪽을 빌려주는 경우
        		isLost[reserveStudent+1] = false;        		
        	}
        }
        //체육복을 입은 학생 수 체크
        for(int i=1;i<isLost.length;i++) {
        	if(!isLost[i])
        		answer++;
        }
              
        return answer;
    }
}

public class WorkoutClothes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int n = 5;
		int[] lost = {3,4,5};
		int[] reserve = {2,3,4};
		
		
		//int n = 5;
		//int[] lost = {2, 4};
		//int[] reserve = {3};
		
		Solution solution = new Solution();
		int answer = solution.solution(n, lost, reserve);
		System.out.println(answer);
	}

}
