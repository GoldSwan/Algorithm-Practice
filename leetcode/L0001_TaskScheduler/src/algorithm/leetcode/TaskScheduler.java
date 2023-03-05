package algorithm.leetcode;
import java.util.*;

class Solution {
    public int leastInterval(char[] tasks, int n) {
        var taskCntMap = createTaskCntMap(tasks);
        int maxTaskCnt = 0;

        for(var task : taskCntMap.keySet())
            maxTaskCnt = Math.max(maxTaskCnt, taskCntMap.get(task));

        int minTaskHour = (maxTaskCnt-1) * (n+1);

        int remain = 0;//가장 개수가 많은 task와 동일한 개수의 task를 찾아 나머지에 더해준다.
        for(char task : taskCntMap.keySet())
            remain = taskCntMap.get(task) == maxTaskCnt ? ++remain : remain;

        minTaskHour += remain;

        //remain에 가장 많은 task가 아닌 다른 task가 배치될 경우 tasks.length을 반환. 아래 input2 참고
        return minTaskHour = minTaskHour < tasks.length ? tasks.length : minTaskHour;
    }

    public Map<Character, Integer> createTaskCntMap(char[] tasks){
        Map<Character, Integer> taskCntMap = new HashMap<>();

        for(char task : tasks)
            taskCntMap.put(task, taskCntMap.getOrDefault(task, 0)+1);

        return taskCntMap;
    }
}

public class TaskScheduler {
    public static void main(String[] args) throws Exception {
        var solution = new Solution();
        char[] tasks  = {'A','A','A','B','B','B'}; int n = 2;//input1
        //char[] tasks  = {'A','A','A','B','B','B', 'C', 'C', 'D', 'E', 'F', 'G', 'H'}; int n = 4;//input2
        int numMinHour = solution.leastInterval(tasks, n);
        System.out.println("numMinHour:"+numMinHour);
    }
}
