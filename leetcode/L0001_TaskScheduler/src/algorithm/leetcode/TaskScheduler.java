package algorithm.leetcode;
import java.util.*;

class Solution {
    public int leastInterval(char[] tasks, int n) {
        var hashMap = createJobCntMap(tasks);
        var maxKeyList = new ArrayList<Character>(hashMap.keySet());
        Collections.sort(maxKeyList, (key1, key2) -> hashMap.get(key2).compareTo(hashMap.get(key1)));//내림차순
        int numMinHour = 0;
        
        numMinHour = (hashMap.get(maxKeyList.get(0))-1) * (n+1);
        
        int maxTask = hashMap.get(maxKeyList.get(0));

        for(char task : maxKeyList){
            int taskcnt = hashMap.get(task);
            if(taskcnt == maxTask)
                numMinHour += 1;
        }

        if(numMinHour < tasks.length)
            numMinHour = tasks.length;

        return numMinHour;
    }

    public Map<Character, Integer> createJobCntMap(char[] tasks){
        Map<Character, Integer> hashMap = new HashMap<>();

        for(char task : tasks){
            if(hashMap.containsKey(task)){
                hashMap.put(task, hashMap.get(task)+1);
                continue;
            }
            hashMap.put(task, 1);
        }
        return hashMap;
    }
}

public class TaskScheduler {
    public static void main(String[] args) throws Exception {
        var solution = new Solution();
        char[] tasks  = {'A','A','A','B','B','B'};
        int n = 2;
        int numMinHour = solution.leastInterval(tasks, n);
        System.out.println("numMinHour:"+numMinHour);
    }
}
