package algorithm.programers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
//Problem solving time : 35~40min
class Solution {
	public int[] solution(int[] progresses, int[] speeds) {
		// progresses : 현재 진도 배열
		// speeds : 작업의 개발 속도 배열
		ArrayList<Integer> release = new ArrayList<Integer>();// 각 배포시 몇개의 기능이 배포되는지 나타내는 배열
		int[] answer = {};
		Queue<Integer> WorkDayQueue = new LinkedList<Integer>();//작업 완료에 걸리는 시간(일)을 담은 큐

		if (progresses.length == 1) {
			answer = new int[1];
			answer[0] = 1;
			return answer;
		}

		for (int i = 0; i < progresses.length; i++) {
			int progress = progresses[i];
			int workDay = getWorkDay(progress, speeds[i]);
			WorkDayQueue.offer(workDay);
		}

		while (WorkDayQueue.isEmpty() == false) {
			int workDay = WorkDayQueue.poll();
			release.add(getReleaseCount(workDay, WorkDayQueue));
		}

		answer = new int[release.size()];

		for (int i = 0; i < answer.length; i++) {
			answer[i] = release.get(i);
		}
		return answer;
	}
	
	public int getWorkDay(int progress, int speed) {
		int workDay = 0;		
		while (progress < 100) {
			progress += speed;
			workDay++;
		}
		return workDay;
	}
	
	public int getReleaseCount(int workDay, Queue<Integer> WorkDayQueue) {
		int count = 1;
		while (!WorkDayQueue.isEmpty() == true && workDay >= WorkDayQueue.peek()) {
			WorkDayQueue.poll();
			count++;
		}
		return count;
	}
}

public class FunctionalDevelopment {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] progresses = { 1, 2, 3 };
		int[] speeds = { 1, 30, 5 };

		Solution solution = new Solution();
		int[] answer = solution.solution(progresses, speeds);

		for (int value : answer)
			System.out.print(value + " ");
	}

}
