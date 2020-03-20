import java.util.Scanner;
/********************************************************************
작성일 : 2019-12-21
작성자 : CHJ
문제 : 록 페스티벌
출저 : 알고스팟
TEST 결과 : 알고스팟-걸린 시간 5860ms
*********************************************************************/
public class Festival {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int C = sc.nextInt();			//테스트 케이스의 수
		double min_avg[]=new double[C];	//케이스별 최소 평균비용

		if(C>100 || C<1) {
			return;						//케이스 수 1~100 이외 입력 시 프로그램 종료
		}else {
			for(int i=0; i<C; i++) {	//케이스 수만큼 반복

				int N;					//공연장을 대여할 수 있는 날
				int L;					//섭외한 공연 팀의 수

				while(true) {
					N = sc.nextInt();
					L = sc.nextInt();
					if(N>=1 && N<=1000 && L>=1 && L<=1000 && L<=N) {
						break;
					}else {
						System.out.println("N, L을 다시 입력해주세요");
					}
				}

				int cost[] = new int[N];	//대여 비용
				for(int j=0; j<cost.length; j++) {
					cost[j] = sc.nextInt();
				}
				/******************** 입력 끝 **********************/

				min_avg[i] = 200;	//최소평균비용 초기화
				int rent = L;		//'대여 일' 담을 변수.   L<=대여일<=N

				while(rent<=N) {
					int idx = 0;
					while(rent+idx<=N) {
						double sum = 0;
						double avg=1;

						for(int k=idx; k<rent+idx; k++) {	//0~2, 1~3, 2~4, 3~5
							sum += cost[k];					//0~3, 1~4, 2~5
						}									//0~4, 1~5  //0~5

						avg = sum/rent;
						if(min_avg[i] > avg) {		//구한 평균값이 최소값이라면 갱신
							min_avg[i] = avg;
						}
						idx++;		//날짜 증가
					}
					rent++;			//대여일 증가
				}
			}
			//결과값 출력
			for(int i=0; i<min_avg.length; i++) {
				System.out.println(min_avg[i]);
			}
		}
	}
}
