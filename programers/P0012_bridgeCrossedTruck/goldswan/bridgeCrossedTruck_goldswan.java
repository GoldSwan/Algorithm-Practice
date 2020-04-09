import java.util.LinkedList;
import java.util.Queue;

/*
 * 작성일 : 2019-11-17
 * 문제 : 프로그래머스 - 다리를지나는트럭
 * 내용 : 다리가 일정무게 이상 견딜수 있고 트럭이 1초마다 움직일 수 있을때 모든 트럭이 지나가는 시간 구하기
 * 풀이법 : Queue 이용
 * 테스트 통과 유무 : 모두통과
 * 실패 원인 파악 :
 */

class Solution {

	int i=0;
	int time = 0;
	Queue<Integer> q;
	int nu_truck_weight = 0;

	public int solution(int bridge_length, int weight, int[] truck_weights) {
		int time = 0;
		q = new LinkedList<Integer>();

		while(true)
		{
			if(i>=truck_weights.length&&nu_truck_weight==0)//truck_weights 에 있는 트럭들이 전부 다리를 건너가고 누적 트럭 무게가 0이 되었을 경우
				break;

			if(q.size()<bridge_length && nu_truck_weight<=weight)
			{
				if(i>=truck_weights.length)//마지막 트럭이 다리를 건너고 있을 경우
				{
					q.offer(0);//0일 경우 트럭이 없는 다리의 빈공간
					time++;
				}
				else if(nu_truck_weight+truck_weights[i]<=weight) //만약 누적 트럭 무게가 weight 를 넘지 않을 경우
				{
				q.offer(truck_weights[i]);//다리에 트럭 추가
				nu_truck_weight = nu_truck_weight+truck_weights[i];//누적 트럭 무게 추가
				time++;//시간증가
				i++;//다음 트럭을 가리킴
				}
				else if(time<=bridge_length)//만약 누적 트럭 무게가 weight를 넘고 time이 bridge_lenght 일 경우
				{
					q.offer(0);//0일 경우 트럭이 없는 다리의 빈공간
					time++;
				}
			}
			else if(q.size()==bridge_length)//다리위에 트럭수가 다리위 최대허용갯수가 될 경우
			{
				int front_truck_weight = q.peek();
				q.poll();//다리 위를 빠져나간 트럭 제거
				nu_truck_weight = nu_truck_weight - front_truck_weight;//누적 트럭 무게에서 나가는 트럭 무게를 빼줌
				if(i>=truck_weights.length)//마지막 트럭이 다리를 건너고 있을 경우
				{
					q.offer(0);//0일 경우 트럭이 없는 다리의 빈공간
					time++;//시간 증가
				}
				else if(nu_truck_weight+truck_weights[i]<=weight) //만약 누적 트럭 무게가 weight 를 넘지 않을 경우
				{
				q.offer(truck_weights[i]);//새로 들어 오는 트럭 추가
				nu_truck_weight = nu_truck_weight+truck_weights[i];//누적 트럭 무게 추가
				time++;
				i++;
				}
				else//만약 누적 트럭 무게가 weight를 넘을 경우
				{
					q.offer(0);//0일 경우 트럭이 없는 다리의 빈공간
					time++;//시간 증가
				}
			}
		}

		return time;
	}
}

public class bridgeCrossedTruck_goldswan {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int bridge_length;
		int weight;
		int time;
		int[] truck_weights = new int[] {7,4,5,6};

		bridge_length = 2;
		weight = 10;

		Solution sol = new Solution();
		time = sol.solution(bridge_length, weight, truck_weights);
		System.out.println("time:"+time);
	}

}
