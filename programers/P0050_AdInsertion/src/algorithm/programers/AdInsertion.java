package algorithm.programers;

class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
		int play_len = timeToNum(play_time);
		int adv_len = timeToNum(adv_time);
		
		int[] arrAd = new int[360000];
		
		for(String log : logs) {
			String[] arrLog = log.split("-");
			int numStart = timeToNum(arrLog[0]);
			int numEnd = timeToNum(arrLog[1]);
			for(int i = numStart ; i < numEnd ; i++) {
				arrAd[i] += 1;
			}
		}		
		
		int numMaxIdx = searchMaxIdxBySlidingWindow(play_len, adv_len, arrAd);
		
		return timeToString(numMaxIdx);
    }
    //문자시간 -> 숫자시간 변환
    private int timeToNum(String strTime) {
		String[] arrTimes = strTime.split(":");
		int numSec = 3600;
		int numTotalTime = 0;
		for(String time : arrTimes) {
			int num = Integer.parseInt(time);
			numTotalTime += num*numSec;
			numSec /= 60;
		}
		return numTotalTime;
	}
    //숫자시간 -> 문자시간 변환
    private String timeToString(int numTime) {
    	StringBuilder sb = new StringBuilder();
		int numHour = numTime/3600;		
		numTime %= 3600;
		if(numHour <10) sb.append("0").append(numHour).append(":");
		else sb.append(numHour).append(":");
		
		int numMinute = numTime/60;
		numTime %= 60;
		if(numMinute <10) sb.append("0").append(numMinute).append(":");
		else sb.append(numMinute).append(":");
		
		int numSecond = numTime;
		if(numSecond <10) sb.append("0").append(numSecond);
		else sb.append(numSecond);
		
		return sb.toString();
	}
    //슬라이딩윈도우 알고리즘 사용하여 구간합 MAX인 구간의 첫번째 인덱스 구하기
    private int searchMaxIdxBySlidingWindow(int play_len, int adv_len, int[] arrAd) {  	
		int numMaxIdx = 0;
		long numMaxSum = 0;
		long sum =0;
		
		for(int i=0; i<adv_len; i++) {
			sum += arrAd[i];
		}
		
		numMaxSum = sum;
		
		for(int i = adv_len; i < play_len ; i++) {
			sum += arrAd[i] - arrAd[i - adv_len];
			if(sum > numMaxSum) {
				numMaxSum = sum;
				numMaxIdx = i - adv_len + 1;
			}
		}		
    	return numMaxIdx;
    }
}

public class AdInsertion {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String play_time = "02:03:55";
		String adv_time = "00:14:15";
		String[] logs = {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"};
		String result =  "";
		
		Solution solution = new Solution();
		result = solution.solution(play_time, adv_time, logs);
		System.out.println("result: "+result);
	}

}
