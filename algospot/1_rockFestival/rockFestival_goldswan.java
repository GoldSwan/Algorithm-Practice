/********************************************************************
작성일 : 2019-12-21
작성자 : GoldSwan
문제 : 록 페스티벌
출저 : 알고스팟
풀이 : 메모리제이션 이용
예상 시간복잡도 : O(N^2)
TEST 결과 :  380ms~420ms
*********************************************************************/

import java.util.Scanner;
import java.util.Vector;
import java.util.HashMap;

public class rockFestival {

	private static int[] CostArray;//비용배열
	static Scanner sc = new Scanner(System.in);
	static Vector<Double> vAnswers = new Vector<Double>();//답이 저장된 벡터

	public static void main(String[] args) {

		//테스트 케이스 수 C
		int C = sc.nextInt();

		for (int i = 0; i < C; ++i) {

			//공연장을 대여할 수 있는 날들의 수 N ( 1 <= N <= 1000)
			int N = sc.nextInt();

			//이미 섭외한 공연 팀의 수 L (1 <= L <= 1000, L <= N)
			int L = sc.nextInt();

			CostArray = new int[N];//비용 배열 CostArray

			for (int k = 0; k < N; ++k) {
				int Cost = sc.nextInt();//모든 비용은 100 이하 자연수 입력
				CostArray[k] = Cost;
			}

			// 알고리즘 동작
			solution(N, L, CostArray);
		}

		//정답 출력 소수점 11자리 까지
		for(int i = 0; i < vAnswers.size(); ++i) {
			System.out.format("%.11f",vAnswers.get(i));
			System.out.println();
		}
	}
	//최소 평균비용을 구하는 알고리즘
	public static void solution(int N, int L, int[] CostArray) {

		//첫번째 원소 포함하고 연속된 인덱스로 이루어진 비용 부분합 해시맵
		HashMap<Integer, Integer> firstPartialSumMap = new HashMap<Integer, Integer>();

		int firstPartialSumArray = 0;
		int minPartialSum;
		int partialSum;
		double avgCost;
		double minAvgCost = Double.MAX_VALUE;

		//비용 배열 CostArray의 첫번째 인덱스인 0인덱스 값을 포함하고 연속된 인덱스로 이루어진 부분배열의 합을 미리 구하여 해시에 저장
		for (int i = 0; i < N; i++) {
			firstPartialSumArray += CostArray[i];
			firstPartialSumMap.put(i + 1, firstPartialSumArray);
		}

		//L 길이를 갖는 평균 비용 ~ N 길이를 갖는 비용 평균 비용 계산
		while (L <= N) {

			minPartialSum = Integer.MAX_VALUE;

			//비교할 첫번째 부분 배열합 저장
			partialSum = firstPartialSumMap.get(L);
			minPartialSum = Math.min(partialSum, minPartialSum);

			for (int i = L+1; i <= N; i++) {
				//해시맵으로 미리 계산된 값을 이용하여 부분합의 다음 인덱스의 값을 더하고 첫번째 부분합 인덱스의 값을 빼서 각 구간 부분합 계산
				partialSum = partialSum + CostArray[i - 1] - CostArray[i - L - 1];
				//L 길이를 갖는 부분합 중 최소 부분합 비교
				minPartialSum = Math.min(partialSum, minPartialSum);
			}

			//평균 비용 계산
			avgCost = (double) minPartialSum / L;
			//최소 평균 비용 비교
			minAvgCost = Math.min(avgCost, minAvgCost);

			L++;
		}
		//답안 추가
		vAnswers.add(minAvgCost);
	}
}
