package wk4;

import java.util.*;

public class Main {

	/*n명의 친구관계 변수*/
	private static boolean[][] areFriends;
	
	public static void main(String[] ars){
	
		Scanner sc = new Scanner(System.in);
		// 테스트케이스 수 C
		int C = sc.nextInt();
		
		// 짝이 생겼는가
		boolean[] isPaired;
		
		// 각 테스트 케이스별 방법의 수
		int[] solution = new int[C];
		
		//테스트 케이스 만큼 loop
		
		while(C-- > 0){
			// 학생수
			int n = sc.nextInt();
			// 친구쌍의 수
			int m = sc.nextInt();
			
			isPaired = new boolean[n];
			//00 ~ nn 까지 전체 경우의 수 
			areFriends = new boolean[n][n];
			
		for(int a= 0; a < m; a++){
			int y = sc.nextInt();
			int z = sc.nextInt();
			areFriends[y][z] = true;
			areFriends[z][y] = true;
		}
		
		solution[C] = countParsings(isPaired);
		
		}
		
		
		for(int i = solution.length-1; i>=0; i--){
			System.out.println(solution[i]);
		}
		
	}

	private static int countParsings(boolean[] isPaired) {
		
		// 학생수
		int n = isPaired.length;
		// 짝이 지어지지 않은 학생 중 가장 빠른 번호의 학생을 담을 변수
		int pickFirst = -1;
		//테스트케이스 경우의 수
		int resultCount = 0 ;
		
		
		
		for(int i = 0; i < n; ++i){
			// 개별학생이 짝이 있는가(가장 빠른 학생을 담음, 중복제거)
			if(!isPaired[i]){
				pickFirst = i;	// i 학생 pick
				break;
			}
		}
		
		// 모든 학생이 짝이 있을 떄 
		if(pickFirst == -1){
			return 1;
		}
		
		
		
		// 짝이 없는 학생(pickFirst)의 다음학생부터 시작
		for(int pairWith = pickFirst+1; pairWith < n; ++pairWith){
			//짝이 없는 학생의 다음번째 학생이 짝이 없고, 짝이 없는 학생과 친구관계일때
			if(!isPaired[pairWith] && areFriends[pickFirst][pairWith]){
				isPaired[pickFirst] = true;
				isPaired[pairWith] = true;

				// 경우의 수 추가
				resultCount += countParsings(isPaired);
				
				// 복구
				isPaired[pickFirst] = false;
				isPaired[pairWith] = false;
			}
		}
		
		return resultCount;
		
	}
	
}
