package algorithm.programers;

import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
	public int solution(int[][] jobs) {
		// int[][] jobs : [[작업이 요청되는 시점, 작업의 소요시간]]
		int answer = 0;//최소 총 소요 시간 평균
		int endWorkTimePoint = 0;//작업 실행 종료 시점
		int minTotalWorkTime = 0;//최소 총 소요 시간
		int jobsIndex = 0;//jobs 2차원 배열 인덱스
		int workRequestsPerformedCount = 0;//작업 요청 실행 횟수
		PriorityQueue<int[]> jobPrioritiyQueue = new PriorityQueue<int[]>((o1, o2) -> Integer.compare(o1[1], o2[1]));//작업 우선순위 큐 - 작업 소요시간이 최소인 순서대로 
		Arrays.sort(jobs, (o1, o2) -> Integer.compare(o1[0], o2[0]));//작업이 요청되는 시점 오름차순 정렬

		while (workRequestsPerformedCount < jobs.length) {

			while (jobsIndex < jobs.length && jobs[jobsIndex][0] <= endWorkTimePoint) {//작업 실행 종료 시점까지 수행할 수 있는 작업들의 소요 시간을 작업 우선순위 큐에 넣음
				jobPrioritiyQueue.add(jobs[jobsIndex++]);
			}

			if (jobPrioritiyQueue.isEmpty()) {//jobPrioritiyQueue가 비어있을 경우 작업 실행 종료 시점을 다음 작업 수행시간으로 맞춰줌.
				endWorkTimePoint = jobs[jobsIndex][0];
				continue;
			}
			
			int[] job = jobPrioritiyQueue.poll();//실행할 우선 순위 작업
			minTotalWorkTime += job[1] + endWorkTimePoint - job[0];//최소 총 소요 시간 = 최소 총 소요 시간 + 작업의 소요시간 + 작업시작시간 - 작업 실행 종료 시점
			endWorkTimePoint += job[1];//작업 실행 종료 시점 = 작업 실행 종료 시점 + 작업의 소요시간
			workRequestsPerformedCount++;

		}

		//System.out.println(minTotalWorkTime);
		answer = minTotalWorkTime / jobs.length;

		return answer;
	}
}

public class DiskController {

	public static void main(String[] args) {
		Solution sol = new Solution();
		int[][] jobs = { { 0, 3 }, { 1, 9 }, { 2, 6 } };

		int answer = sol.solution(jobs);

		System.out.println("answer:" + answer);
	}

}
