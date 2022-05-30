package algorithm.programers;
import java.util.*;

class Solution {
	class ParkingInfo implements Comparable<ParkingInfo>{
		private String time;
		private String carNum;
		private String inout;
		public ParkingInfo(String time, String carNum, String inout) {
			this.time = time;
			this.carNum = carNum;
			this.inout = inout;
		}
		@Override
		public int compareTo(ParkingInfo o) {			
			if(!this.carNum.equals(o.carNum)) {
				return this.carNum.compareTo(o.carNum);
			}
				
			return this.time.compareTo(o.time);
		}
		@Override 
		public String toString() {
	        return String.format("%s-%s-%s",this.time, this.carNum, this.inout);
	    }
	}
    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};
        Map<String, Integer> mapCarRecordCnt = new TreeMap<>();
        Map<String, Integer> mapCarRecordTotalTime = new HashMap<>();
        List<ParkingInfo> arrSortRecords = new ArrayList<>();
        
        for(String record : records) {
        	String[] split = record.split(" ");
        	arrSortRecords.add(new ParkingInfo(split[0], split[1], split[2]));
        	if(mapCarRecordCnt.containsKey(split[1])) {
        		mapCarRecordCnt.put(split[1], mapCarRecordCnt.get(split[1])+1);
        		continue;
        	}
        	mapCarRecordCnt.put(split[1], 1);        		
        }
        //마지막 출차 보정
        for(String keySet : mapCarRecordCnt.keySet()) {
        	if((mapCarRecordCnt.get(keySet)%2) == 1) {
        		arrSortRecords.add(new ParkingInfo("23:59", keySet, "OUT"));
        	}
        }
        
        Collections.sort(arrSortRecords);
        
        //출력 테스트
        //for(ParkingInfo parkingInfo : arrSortRecords) {
        //	System.out.println(parkingInfo.toString());
        //}
        
        int numSortRecordsLength = arrSortRecords.size();
        for(int i = 0 ; i < numSortRecordsLength ; i += 2) {
        	String carNum = arrSortRecords.get(i).carNum;
        	int numMinute = changeTimeToMinute(arrSortRecords.get(i+1).time) - changeTimeToMinute(arrSortRecords.get(i).time);
        	System.out.println("carNum:"+carNum+" "+"numMinute:"+numMinute);
        	if(mapCarRecordTotalTime.containsKey(carNum)) {       		
        		mapCarRecordTotalTime.put(carNum, mapCarRecordTotalTime.get(carNum) + numMinute);
        		continue;
        	}
        	mapCarRecordTotalTime.put(carNum, numMinute);
        }
        
        answer = new int[mapCarRecordCnt.size()];
        int i = 0;
        for(String keySet : mapCarRecordCnt.keySet()) {
        	answer[i] = getTotalFee(fees, mapCarRecordTotalTime.get(keySet));
        	i++;
        }
        
        return answer;
    }
    public int changeTimeToMinute(String strTime) {
    	String[] split = strTime.split(":");
    	
    	return (Integer.parseInt(split[0])*60) + Integer.parseInt(split[1]);
    }
    public int getTotalFee(int[] fees, int numMinute) {
    	if(numMinute<=fees[0])
    		return fees[1];
    	
    	return fees[1] + ((int)Math.ceil((double)(numMinute - fees[0])/fees[2]) * fees[3]);
    }
}

public class ParkingFeeCalculation {

	public static void main(String[] args) {
		Solution solution = new Solution();
		int[] fees = {180, 5000, 10, 600};//기본시간(분), 기본요금(원), 단위시간(분), 단위시간요금(원)
		String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
		int[] answer = solution.solution(fees, records);
		for(int totalfee : answer) {
			System.out.println(totalfee);
		}
	}

}
