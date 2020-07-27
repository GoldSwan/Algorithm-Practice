package algorithm.programers;

class Solution {

    public int solution(String begin, String target, String[] words) {
        int answer = 987654321;        
    	boolean[] visit = new boolean[words.length];
        int depth = 0;

        answer = dfs(begin,target,begin,visit,words, depth, answer);
    	
        if(answer == 987654321)
        	answer = 0;
        
        return answer;
    }
    
    public int dfs(String begin, String target, String beforeChange, boolean[] visit, String[] words, int depth, int answer) {
    	
    	if(begin.equals(target)) {
    		//System.out.println("depth:"+depth);
    		answer = Math.min(depth, answer);
    	}
    	
    	for(int i=0;i<words.length;i++) {	
    		if(!visit[i] && canChange(begin, words[i])) {
    			beforeChange = begin;
    			begin = words[i];
    			visit[i] = true;
    			depth += 1;
    			//System.out.print(beforeChange+ "->" +begin+" ");
    			
    			answer = dfs(begin, target, beforeChange, visit, words, depth, answer);
    			
    			begin = beforeChange;
    			visit[i] = false;
    			depth += -1;
    		}
    	}  	
    	return answer;
    }
    
    public boolean canChange(String word, String compareWord) {
    	
    	String[] wordSplitArray = word.split("");
    	String[] compareWordSplitArray = compareWord.split("");
    	int count = 0;
    	
    	for(int i=0;i<wordSplitArray.length;i++) {
    		if(wordSplitArray[i].equals(compareWordSplitArray[i]))
    			count++;
    	}  	
    	
    	if(count == wordSplitArray.length-1)
    		return true;
    	
    	return false;
    }
}

public class WordChange {

	public static void main(String[] args) {	
		Solution sol = new Solution();
		String begin = "hit";
		String target = "cog";
		String[] words = {"hot", "dot", "dog", "lot", "log"};
		//String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
		int answer = sol.solution(begin, target, words);
		System.out.println(answer);
		
	}

}
