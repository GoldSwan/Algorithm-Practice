package algorithm.programers;

import java.util.LinkedList;
import java.util.Queue;
//2020-05-23 goldswan
class Solution {
	public int solution(int bridge_length, int weight, int[] truck_weights) {

		int answer = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		int time = 0;
		int total_truck_weight = 0;
		int truck_index = 0;

		while (true) {
			
			if (truck_index >= truck_weights.length && total_truck_weight == 0)
				break;
			
			time++;
			
			if(q.size() == bridge_length) {
				total_truck_weight -= q.peek();
				q.poll();
				
			}				
			if (truck_index < truck_weights.length && total_truck_weight + truck_weights[truck_index] <= weight) {
				q.offer(truck_weights[truck_index]);
				total_truck_weight += truck_weights[truck_index];
				truck_index++;
				continue;
			}
			
			q.offer(0);			
		}

		answer = time;

		return answer;
	}
}

public class BridgeCrossedTruck {

	public static void main(String[] args) {
		int bridge_length;
		int weight;
		int time;
		int[] truck_weights = new int[] { 7, 4, 5, 6 };
		//int[] truck_weights = new int[] {10};
		
		bridge_length = 2;
		weight = 10;

		Solution sol = new Solution();
		time = sol.solution(bridge_length, weight, truck_weights);
		System.out.println("time:" + time);
	}

}
