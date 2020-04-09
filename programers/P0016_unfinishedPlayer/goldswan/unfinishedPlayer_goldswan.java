package hash_01;

import java.util.HashMap;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";

        HashMap<String, Integer> map = new HashMap<String, Integer>();

		for(int i=0;i<completion.length;i++) {

			if(map.get(completion[i])!=null&&map.get(completion[i])>=1)
			{//동명이인으로 이미 key가 존재할 경우
				map.put(completion[i],map.get(completion[i])+1);//동명이인 갯수 증가
			}
			else
			{
			map.put(completion[i],1);
			}
		}

		for(int i=0;i<participant.length;i++) {

			//완주를 못한자가 동명이인이 아닌경우
			if(map.get(participant[i])==null)
			{
				answer = participant[i];
				break;
			}
			//완주를 못한자가 동명이인일 경우
			else if(map.get(participant[i])==0)
			{
				answer = participant[i];
				break;
			}
			else {
			map.put(participant[i],map.get(participant[i])-1);
			}
		}

        return answer;
    }
}

public class unfinishedPlayer_goldswan {
	public static void main(String[] args) {
		//String[] participant	= new String[] {"marina", "josipa", "nikola", "vinko", "filipa"};
		//String[] completion = new String[] {"josipa", "filipa", "marina", "nikola"};
		String[] participant	= new String[] {"mislav", "stanko", "mislav", "ana"};
		String[] completion = new String[] {"stanko", "ana", "mislav"};

		Solution sol = new Solution();

		String answer = sol.solution(participant, completion);

		System.out.println("answer : " + answer);
	}
}
