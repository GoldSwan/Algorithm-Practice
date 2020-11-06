package algorithm.programers;

class Solution {
    public int solution(String s) {
        int minLength = s.length();//문자열을 잘라 압축하여 표현한 문자열 중 가장 짧은 것의 길이
        int cutUnit = 1;//문자열을 자르는 단위
        String[] sSplitArray = s.split("");        
        
        while(cutUnit <= s.length()/2){                    
            String sCutByUnit = getStrByCutUnit(sSplitArray, cutUnit);
            String[] sCutByUnitSplitArray = sCutByUnit.split(" ");                                       
            String compressionS = getCompressionStr(sCutByUnitSplitArray);        
            minLength = getMinLength(minLength, compressionS);        
            ++cutUnit;
        }
        
        return minLength;
    }
    //문자열 단위로 잘라서 잘릴 곳마다 " "를 추가하는 문자열 반환
    public String getStrByCutUnit(String[] sSplitArray, int cutUnit){
        StringBuilder sb = new StringBuilder();
            
        for(int i =0;i<sSplitArray.length;i++){            
            sb.append(sSplitArray[i]);
            if((i+1)%cutUnit == 0){
                    sb.append(" ");
            }
        }
        
        return sb.toString();
    }
    //getStrByCutUnit에서 구한 문자열을 통해 실제로 압축된 문자열 반환
    public String getCompressionStr(String[] sCutByUnitSplitArray){
        int num = 1;
        StringBuilder sb = new StringBuilder();         
        for(int i =0;i<sCutByUnitSplitArray.length;i++){
            if(i+1< sCutByUnitSplitArray.length && sCutByUnitSplitArray[i].equals(sCutByUnitSplitArray[i+1])){
                ++num;
                continue;
            }
            if(num>1){
                sb.append(num);
                num = 1;
            }
            sb.append(sCutByUnitSplitArray[i]);
        }
        return sb.toString();
    }
    
    //압축문자열중 가장 짧은 문자열 길이 구하기 
    public int getMinLength(int minLength, String compressionS){
        if(minLength>compressionS.length()){
            minLength = compressionS.length();
        }
        return minLength;
    }
}

public class StringCompression {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution sol = new Solution();
		int answer = sol.solution("aabbaccc");
		System.out.println("answer:"+answer);

	}

}
