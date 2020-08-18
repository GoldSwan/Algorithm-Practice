package algorithm.programers;

import java.util.PriorityQueue;
//Problem solving time : 27min
class Solution {
	public int[] solution(String[] operations) {
		int[] answer = new int[2];
		PriorityQueue<Integer> minPrioritiyQueue = new PriorityQueue<Integer>();
		PriorityQueue<Integer> maxPrioritiyQueue = new PriorityQueue<Integer>((o1, o2) -> {return -Integer.compare(o1, o2);});//최대 우선순위큐가 되도록 Comparator사용(무명 메소드 - 람다식으로 표현)하여 내림차순으로 정렬 변경
		for (String operation : operations) {
			String[] operationsSplitArray = operation.split(" ");
			if(operationsSplitArray[0].equals("I")) {//삽입
				minPrioritiyQueue.add(Integer.parseInt(operationsSplitArray[1]));
				maxPrioritiyQueue.add(Integer.parseInt(operationsSplitArray[1]));
			}
			else if(operationsSplitArray[0].equals("D")&& !maxPrioritiyQueue.isEmpty() && !minPrioritiyQueue.isEmpty()) {//삭제 - 큐가 비어있을 시 무시
				if(operationsSplitArray[1].equals("1")){//최댓값 삭제
					int maxValue = maxPrioritiyQueue.peek();				
					maxPrioritiyQueue.remove(maxValue);
					minPrioritiyQueue.remove(maxValue);
				}
				else if(operationsSplitArray[1].equals("-1")){//최소값 삭제
					int minValue = minPrioritiyQueue.peek();				
					maxPrioritiyQueue.remove(minValue);
					minPrioritiyQueue.remove(minValue);
				}
			}				
		}
		
		answer[0] = maxPrioritiyQueue.isEmpty() ? 0 : maxPrioritiyQueue.peek();
		answer[1] = minPrioritiyQueue.isEmpty() ? 0 : minPrioritiyQueue.peek();
		
		return answer;
	}
}

public class DoublePriorityQueue {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] operations = { "I 16", "D 1" };
		Solution solution = new Solution();
		int[] answer = solution.solution(operations);

		for (int value : answer)
			System.out.print(value + " ");
	}

}
