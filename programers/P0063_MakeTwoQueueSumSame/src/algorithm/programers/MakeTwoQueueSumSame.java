package algorithm.programers;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int minWork = -1;//최소 작업
        int sumAll = 0;//전체 합
        int sumTarget = 0;//목표 합

        for(int num : queue1){
            sumAll+=num;
        }
        for(int num : queue2){
            sumAll+=num;
        }

        if(sumAll%2 == 1){
            return minWork;
        }
        
        sumTarget = sumAll/2;

        //생각
        //1.두 큐의 합을 구한다.
        //2.합이 큰 쪽 큐를 뽑아서 작은 쪽 큐에 넣는다.
        //4.이 작업을 반복한다.
        //무한정 작업할 수 없으니 중단 시점을 어떻게 정할지? 생각 필요
        //중단 CASE 1. 하나의 숫자가 너무 커서 큐를 옮겨도 하나의 숫자 합이 더 큰경우

        return minWork;
    }
}

public class MakeTwoQueueSumSame {
    public static void main(String[] args) throws Exception {
        var Solution = new Solution();
        int[] queue1 = {3, 2, 7, 2};
        int[] queue2 = {4, 6, 5, 1};

        int answer = Solution.solution(queue1, queue2);
        System.out.println(answer);
    }
}
