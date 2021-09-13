import java.util.HashMap;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] arrAnswer = new int[2];
        HashMap<Integer, Integer> mapWinNums = new HashMap<Integer, Integer>();

        for(int win_num : win_nums){
			mapWinNums.put(win_num,1);
        }

        arrAnswer[0] = getMaxRank(lottos, mapWinNums);
        arrAnswer[1] = getMinRank(lottos, mapWinNums);

        return arrAnswer;
    }
    //최고순위
    private int getMaxRank(int[] lottos, HashMap<Integer, Integer> mapWinNums){
        int cnt = 0;
        for(int num : lottos){
			if(mapWinNums.containsKey(num) || num==0)
                cnt++;
        }
        return (cnt == 6) ? 1 : (cnt == 5) ? 2 : (cnt == 4) ? 3 : (cnt == 3) ? 4 : (cnt == 2) ? 5 : 6;
    }
    //최저순위
    private int getMinRank(int[] lottos, HashMap<Integer, Integer> mapWinNums){
         int cnt = 0;
         for(int num : lottos){
			if(mapWinNums.containsKey(num))
                cnt++;
         }
        return (cnt == 6) ? 1 : (cnt == 5) ? 2 : (cnt == 4) ? 3 : (cnt == 3) ? 4 : (cnt == 2) ? 5 : 6;
    }
}
