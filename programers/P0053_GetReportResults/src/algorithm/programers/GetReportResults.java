package algorithm.programers;
import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
    	int numIdCnt = id_list.length;
    	int[] arrReportCnt = new int[numIdCnt];
        Map<String, Integer> mapReportCnt = new HashMap<>();
        Map<String, ArrayList<String>> mapReportIds = new HashMap<>();
        
        for(String strId : id_list) {
        	mapReportIds.put(strId, new ArrayList<>());
        }
        for(String strReport : report) {
        	String[] arrReportSplit = strReport.split(" ");
        	if(mapReportIds.containsKey(arrReportSplit[0])){
            	boolean isReportLog = false;
        		for(String strId : mapReportIds.get(arrReportSplit[0])) {
        			if(strId.equals(arrReportSplit[1])) {
        				isReportLog = true;
        				break;
        			}
        		}
        		if(isReportLog) continue;
        	}      	
        	
        	mapReportIds.get(arrReportSplit[0]).add(arrReportSplit[1]);
        	
        	if(mapReportCnt.containsKey(arrReportSplit[1])) {
        		mapReportCnt.put(arrReportSplit[1], mapReportCnt.get(arrReportSplit[1])+1);
        	}
        	else {
        		mapReportCnt.put(arrReportSplit[1], 1);
        	}
        }
        
        
        
        for(int i = 0 ; i < numIdCnt ; i++) {
        	int numReportCnt = 0;
        	for(String strId :mapReportIds.get(id_list[i])) {
        		if(mapReportCnt.get(strId) >= k) 
        			numReportCnt++;    		
        	}
        	arrReportCnt[i] = numReportCnt;
        }
        
        return arrReportCnt;
    }
}

public class GetReportResults {

	public static void main(String[] args) {
		//String[] id_list = {"muzi", "frodo", "apeach", "neo"};
		String[] id_list = {"con", "ryan"};
		//String[] report = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
		String[] report = {"ryan con", "ryan con", "ryan con", "ryan con"};
		int k = 2;
		Solution solution = new Solution();
		int[] answer = solution.solution(id_list, report, k);
		
		for(int cnt : answer)
			System.out.println(cnt);

	}

}
