package algorithm.programers;

class Solution {
    public int solution(int n, int k) {
        int cntPrimeNumbers = 0;
        String strDecimalNum = getKDecimalNum(n, k);
        //System.out.println(strDecimalNum);
        String[] arrSplit = strDecimalNum.split("0");
        for(String strNum : arrSplit) {
        	if(strNum.equals(""))
        		continue;
        	if(isPrimeNumber(Long.parseLong(strNum))) {
        		cntPrimeNumbers++;
        	}
        }

        return cntPrimeNumbers;
    }
    public String getKDecimalNum(int n, int k) {
    	StringBuilder sb = new StringBuilder();

    	while(n>0) {
    		int remain = n%k;
    		sb.append(remain);
    		n /= k;
    	}

    	return sb.reverse().toString();
    }

    public boolean isPrimeNumber(long num) {
    	if(num==1) return false;

    	for(long i = 2 ; i*i <= num ; i++) {
    		if(num % i == 0) return false;
    	}
    	return true;
    }
}

public class FindTheNumberOfDecimalsInKNumber {

	public static void main(String[] args) {
		Solution solution = new Solution();
		int n = 437674;
		int k = 3;
		int cntPrimeNumbers = solution.solution(n, k);
		System.out.println("cntPrimeNumbers:"+cntPrimeNumbers);
	}

}
