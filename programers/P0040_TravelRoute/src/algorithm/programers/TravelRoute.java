package algorithm.programers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public String[] solution(String[][] tickets) {
    	ArrayList<String> answerList = new ArrayList<String>(); 
    	ArrayList<String> tempList = new ArrayList<String>(); 
        Map<String,Integer> map = new HashMap<String,Integer>(); 
        ArrayList<String> keyArray = new ArrayList<String>(); 
        
        int index = 0;
        
        for(String[] ticket : tickets) {
        	for(String strTicket : ticket) {
        		if(!map.containsKey(strTicket)) {
        			map.put(strTicket, index);
        			keyArray.add(strTicket);
        			index++;
        		}
        	}
        }       
        
        int[][] graph = new int[map.size()][map.size()];       
        int depth = tickets.length;
        
        //똑같은 경로로 왕복을 2번이상 할 수도 있기 때문에 +1씩하여 경로를 연결
        for(String[] ticket : tickets) {
        	graph[map.get(ticket[0])][map.get(ticket[1])] += 1;
        }
          
        tempList.add("ICN");//시작점    
        int n = tickets.length+1;//최종 간선의 수
        
        dfs("ICN", map, keyArray, graph, depth, answerList, tempList, n);

        String[] answer = new String[answerList.size()];
        for(int i=0;i<answer.length;i++) {
        	answer[i] = answerList.get(i);
        }
        return answer;
    }
    
    public void dfs(String start, Map<String,Integer> map, ArrayList<String> keyArray, int[][] graph, int depth, ArrayList<String> answerList, ArrayList<String> tempList, int n) {
    	if(depth==0) {   		
    		if(answerList.size()==n && answerList.size()==tempList.size()) {
    			//다른 경로를 찾았을 때 알파벳 순으로 더 빠르다면 해당 경로 저장
    			StringBuilder sb1 = new StringBuilder();
    			StringBuilder sb2 = new StringBuilder();
    			
    			for(int i = 0;i<tempList.size();i++){
    				sb1.append(tempList.get(i));
    			}  
    			
    			for(int i = 0;i<answerList.size();i++){
    				sb2.append(answerList.get(i));
    			}
    			
    			if(sb1.toString().compareTo(sb2.toString())<0) {
					for(int j=0;j<answerList.size();j++) {
						answerList.set(j,  tempList.get(j));
					}
				}
				return;
    		}
    		//제일 처음 탐색한 경로 저장
    		for(String str : tempList) {
    			answerList.add(str);
    		}
    		return;
    	}

    	for(int i = 0;i<graph.length;i++) {
    		if(graph[map.get(start)][i]>=1) {
    			String beforeStart = start;
    			graph[map.get(start)][i] -= 1;
    			start = keyArray.get(i);
    			tempList.add(start);
    			depth--;
    			dfs(start, map, keyArray, graph, depth, answerList, tempList, n);
    			//빽트레킹으로 돌아올 시 이전 간선 복구 및 가장 마지막 경로 삭제
    			start  = beforeStart;
    			graph[map.get(start)][i] += 1;
    			tempList.remove(tempList.size()-1);
    			depth++;   			
    		}
    	}
    }
}

public class TravelRoute {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[][] tickets = //{{"ICN","JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};	
		//{{"ICN","BBB"}, {"BBB", "CCC"}, {"CCC", "ICN"}, {"ICN", "CCC"},{"CCC","BBB"},{"BBB","ICN"}};	
	    //{{"ICN","SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};
		//{{"ICN","AAA"}, {"ICN", "CCC"}, {"AAA", "ICN"}, {"CCC", "ICN"},{"AAA","CCC"},{"CCC","AAA"}};
		{{"ICN","AAA"}, {"AAA","ICN"}, {"ICN","AAA"}, {"AAA","ICN"}};
		Solution solution = new Solution();
		String[] answer = solution.solution(tickets);
		
		for(String str : answer) {
			System.out.print(str+" ");
		}
		System.out.println();
	}

}
