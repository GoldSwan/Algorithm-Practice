package algorithm.programers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.util.Comparator;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        HashMap<String,Integer> genersMap = new HashMap<String,Integer>();
        HashMap<Integer,String> playsMap = new HashMap<Integer,String>();  
        HashMap<String,Integer> genersCountMap = new HashMap<String,Integer>();
        ArrayList<Integer> playsList = new ArrayList<Integer>();
        ArrayList<Integer> answerList = new ArrayList<Integer>();
        
        for(int i=0;i<plays.length;i++)
        	playsList.add(i);
        
        for(int i=0;i<plays.length;i++)
        	playsMap.put(i, genres[i]);
        
        for(int i=0;i<genres.length;i++) 
        {
        	if(genersMap.containsKey(genres[i])) {
        		genersMap.put(genres[i], genersMap.get(genres[i])+plays[i]); 
        		genersCountMap.put(genres[i], 2);//장르가 2개 이상일 경우
        	}
        	else {
        		genersMap.put(genres[i], plays[i]);     
        		genersCountMap.put(genres[i], 1);//장르가 1개 일 경우
        	}
        }      
        
        Collections.sort(playsList ,new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				String o1Genere = playsMap.get(o1);
				String o2Genere = playsMap.get(o2);
				Integer o1Plays = plays[o1];
				Integer o2Plays = plays[o2];
				
				if(genersMap.get(o1Genere)<genersMap.get(o2Genere)) 
					return 1;				
				else if(genersMap.get(o1Genere)>genersMap.get(o2Genere)) 
					return -1;		
				else 
					return -(o1Plays.compareTo(o2Plays));
			}
		});
        
        for(int i=0;i<playsList.size();i++) {
        	//각각 장르별로 재생횟수가 높은 2개만을 앨범에 수록
        	if(genersCountMap.get(playsMap.get(playsList.get(i)))>0) {
        		answerList.add(playsList.get(i));
        		genersCountMap.put(playsMap.get(playsList.get(i)), genersCountMap.get(playsMap.get(playsList.get(i)))-1);
        	}
        }
        
        answer = new int[answerList.size()];
        
        for(int i=0;i<answer.length;i++)
        	answer[i] = answerList.get(i);
        
        return answer;
    }
}

public class BestAlbum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution solution = new Solution();
		String[] genres = {"classic", "pop", "classic", "classic", "pop"};
		int[] plays = {500, 600, 150, 800, 2500};
		
		int answer[] = solution.solution(genres, plays);
		for(int value : answer)
			System.out.print(value+" ");
	}

}
