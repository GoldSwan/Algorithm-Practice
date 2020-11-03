package algorithm.programers;

import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int[][] routes) {
    	int cnt = 0; //카메라의 개수
    	int camera = -30001;//차량이 빠져나간 지점 중 카메라를 설치할 기준점

    	//차량이 빠져나간 지점인 route[i][1] 기준으로 오름차순 정렬
    	Arrays.sort(routes, new Comparator<int[]>() 
    	{
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[1], o2[1]);
			}   		
    	}); 
    	
    	for (int[] route : routes) { 
    		//진입지점이 카메라가 설치된 기준점보다 앞에 있을 경우 카메라를 설치 후 기준점을 바꿔준다.
    		if (camera < route[0]) 
    		{ 
    			camera = route[1]; 
    			cnt++; 
    		} 
    	} 

    	return cnt;
    }
}

public class ControlCamera {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution sol = new Solution();
		int[][] routes = {{-20,15}, {-14,-5}, {-18,-13}, {-5,-3}};
		int answer = sol.solution(routes);
		System.out.println("answer:"+answer);
	}

}
