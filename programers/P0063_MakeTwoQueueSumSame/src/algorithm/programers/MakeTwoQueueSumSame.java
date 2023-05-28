package algorithm.programers;
import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        int minWork = -1;
        long sumAll = 0;
        long q1Sum = 0;
        long q2Sum = 0;

        for(int num : queue1){
            sumAll+=num;
            q1Sum+=num;
            q1.add(num);
        }
        for(int num : queue2){
            sumAll+=num;
            q2Sum+=num;
            q2.add(num);
        }

        minWork = GetMinWork(q1, q2, q1Sum, q2Sum, sumAll);
        return minWork;
    }

    public int GetMinWork(Queue<Integer> q1 , Queue<Integer> q2, long q1Sum, long q2Sum, long sumAll){
        int work = 0;
        int worstLoopSize = q1.size() * 3;//최악의 루프 사이즈

        //전체 합이 홀수인 경우는 두 큐의 합이 같을 수 없으므로 -1 리턴
        if(sumAll%2 == 1){
            return -1;
        }
        //이미 두 큐의 합이 같으면 작업을 할 필요가 없으므로 0 리턴
        if(q1Sum == q2Sum){
            return 0;
        }
        //최악의 루프가 실행될 때 시간초과를 빠져나오기 위한 조건 (테스트케이스11, 28)
        while(work <= worstLoopSize){
            //큐가 한개라도 비게 되면 두 큐의 합이 같을 수 없으므로 -1 리턴
            if(q1.isEmpty() || q2.isEmpty()){
                return -1;
            }
            //두 큐의 합이 같으면 작업을 리턴
            if(q1Sum == q2Sum){
                return work;
            }
            //두 큐 중 합이 더 큰 큐의 수를 뽑아 반대편 큐에 넣고 합을 계산하여 반영
            else if(q1Sum > q2Sum){
                int num = q1.poll();
                q2.add(num);
                q1Sum -= num;
                q2Sum += num;
                work++;
            }
            else if(q1Sum < q2Sum){
                int num = q2.poll();
                q1.add(num);
                q2Sum -= num;
                q1Sum += num;
                work++;
            }
        }
        return -1;
    }
}

public class MakeTwoQueueSumSame {
    public static void main(String[] args) throws Exception {
        var Solution = new Solution();
        //int[] queue1 = {3, 2, 7, 2}; int[] queue2 = {4, 6, 5, 1};
        //int[] queue1 = {1, 2, 1, 2}; int[] queue2 = {1, 10, 1, 2};
        //int[] queue1 = {1, 1, 1}; int[] queue2 = {1, 1, 1};
        int[] queue1 = {1, 1, 1, 1, 1, 2}; int[] queue2 = {1, 1, 1, 1, 1, 4};
        //시간초과 테스트 케이스 : q2 29만9999개가 모두 q1로 넘어가고 q2의 59만 9999개가 q1으로 넘어간 경우
        //int[] queue1 = {1, 1, 1, 1, 1, ... };/30만개
        //int[] queue2 = {1, 1, 1, 1, 1, ..., 599999 ,1};//30만개

        int answer = Solution.solution(queue1, queue2);
        System.out.println(answer);
    }
}
