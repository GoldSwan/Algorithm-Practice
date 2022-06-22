package algorithm.programers;

class Solution {
    private int[] res = { -1 };
    private int[] lion;
    private int max = -1;
    public int[] solution(int n, int[] info) {
        lion = new int[11];
        dfs(info,1,n);
        return res;
    }
    public void dfs(int[] info, int cnt, int n) {
        if(cnt == n + 1) {//라이언이 화살을 다 쏜 상태일 경우 점수 계산
            int apeach_point = 0;
            int lion_point = 0;
            for(int i = 0; i <= 10; i++) 
            {
                if(info[i] != 0 || lion[i] != 0) {
                    if(info[i] < lion[i]) 
                        lion_point += 10 - i;
                    else 
                        apeach_point += 10 - i;
                }
            }
            if(lion_point > apeach_point && (lion_point - apeach_point >= max)) {
                    res = lion.clone();//라이언이 화살 쏜 결과 복사
                    max = lion_point - apeach_point;//점수 차 갱신              
            }
            return;
        }
        for(int j = 0; j <= 10 && lion[j] <= info[j]; j++) {
        	//벡트레킹 가지치기
        	//lion[j] <= info[j] 조건 의미
        	//라이언은 최소로 화살을 쏴서 어비치가 쏜 점수를 넘겨야하므로 같은 점수 구간 내에서 라이언이 쏜 화살 갯수가 많은 경우 더 탐색할 거 없이 종료한다.
            lion[j]++;
            dfs(info, cnt + 1, n);
            lion[j]--;
        }
    }
}

public class ArcheryCompetition {

	public static void main(String[] args) {
		Solution solution = new Solution();
		int n = 5;
		int[] info = {2,1,1,1,0,0,0,0,0,0,0};
		//int n = 1;
		//int[] info = {1,0,0,0,0,0,0,0,0,0,0};
		int[] res = {};
		res = solution.solution(n, info);
		for(int num : res) {
			System.out.print(num+" ");
		}
	}

}
