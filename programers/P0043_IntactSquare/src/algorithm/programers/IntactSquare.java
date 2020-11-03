package algorithm.programers;

class Solution {
    private class PatternNumber{
        //PatternNumber : 최소로 패턴을 분할한 클래스
        long pw;//패턴에서 넓이 w
        long ph;//패턴에서 높이 h
        long maxCommonNumber;//최대공약수
        public PatternNumber(long pw, long ph, long maxCommonNumber){
            this.pw = pw;
            this.ph = ph;
            this.maxCommonNumber = maxCommonNumber;
        }
    }
    
    public long solution(int w, int h) {
        PatternNumber patternNumber = findPatternNumber(w, h);
        return ((long)w * (long)h) - ((patternNumber.pw + patternNumber.ph - 1) * patternNumber.maxCommonNumber);
    }
    
    public PatternNumber findPatternNumber(int w, int h){
        long min = Math.min(w,h);
        long maxCommonNumber = 1;
        PatternNumber patternNumber;
        
        while(true){           
            boolean isNotCommonNumber = true;
            for(int i = 2; i<=min ; i++){
                if(w%i == 0 && h%i == 0){
                    w = w/i; 
                    h = h/i;
                    maxCommonNumber *= i;
                    min = Math.min(w,h);
                    isNotCommonNumber = false;
                    break;
                }                
            }
            if(isNotCommonNumber){
                patternNumber = new PatternNumber(w,h,maxCommonNumber);
                break;
            }               
        }      
        return patternNumber;
    }
}

public class IntactSquare {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution sol = new Solution();
		int w = 12;
		int h = 8;
		long answer = sol.solution(w,h);
		System.out.println("answer:"+answer);
	}
}
