/********************************************************************
작성일 : 2020-01-04
수정일 : 2020-07-28
수정내용 : 알고리즘 속도 개선 및 코드 개선
작성자 : GoldSwan
문제 : 프로그래머스 - 소수 찾기(3_findPrimeNumbers)
출저 : programers
풀이 : 완전탐색, 해시이용
TEST 결과 :  모두통과
*********************************************************************/

import java.util.ArrayList;

class Solution {

		int count=0;
		ArrayList<Integer> PrimeNumbersArray = new ArrayList<Integer>();

	public int solution(String numbers) {
        int answer = 0;
        String[] numbersSplitArray = numbers.split("");
        int maxNumber = makeMaxNumber(numbersSplitArray);

        for(int i=2;i<=maxNumber;i++) {
        	if(isPrimeNumbers(i))
        		PrimeNumbersArray.add(i);
        }

        for(int i=0;i<PrimeNumbersArray.size();i++) {
        	if(isCanMakeNumbers(Integer.toString(PrimeNumbersArray.get(i)).split(""),numbersSplitArray))
        		count++;

        }
        answer = count;

        return answer;
    }

	public int makeMaxNumber(String[] numbersSplitArray) {

		int[] cards = new int[10];
		int index = 9;
		StringBuffer sb = new StringBuffer();

		for(int i=0;i<numbersSplitArray.length;i++) {
			cards[Integer.parseInt(numbersSplitArray[i])]++;
		}

		while(index>=0) {

			if(cards[index]>0) {
				sb.append(Integer.toString(index));
				cards[index]--;
				continue;
			}

			index--;
		}

		return Integer.parseInt(sb.toString());
	}

	public boolean isPrimeNumbers(int number) {

		if(number==2)
			return true;

		int sqrtValue = (int) Math.sqrt(number);

		for(int i=2;i<=sqrtValue;i++) {
			if(number%i==0)
				return false;
		}

		return true;
	}

	public boolean isCanMakeNumbers(String[] primeNumberSplitArray, String[] numbersSplitArray) {

		int[] cards = new int[10];
		int count = 0;

		for(int i=0;i<numbersSplitArray.length;i++) {
			cards[Integer.parseInt(numbersSplitArray[i])]++;
		}

		for(int i=0;i<primeNumberSplitArray.length;i++) {
			if(cards[Integer.parseInt(primeNumberSplitArray[i])]>0) {
				count++;
				cards[Integer.parseInt(primeNumberSplitArray[i])]--;
			}
		}

		if(count==primeNumberSplitArray.length) {
			return true;
		}

		return false;
	}
}
//리팩터링 이전 코드
/*
import java.util.HashMap;
import java.util.Vector;

class Solution {
		HashMap<String, Integer> numbersMap;
		Vector<Integer> PrimeNumbersV = new Vector<Integer>();
		int count=0;

	public int solution(String numbers) {
        int answer = 0;
        HashMap<String, Integer> numbersMap;
        String[] numbersSplit = numbers.split("");

        numbersMap = makeNumbersMap(numbersSplit);
        HashMap<String, Integer> copyNumbersMap = new HashMap<String, Integer>(numbersMap);

        //조합할 수 있는 수중 최대 maxValue 구하기
        String maxValue = getMaxValue(numbers, numbersSplit, copyNumbersMap);

        findCanMakeNumbers(maxValue, numbersSplit);

        answer = count;

        return answer;
    }
	//Numbers 로 만들수 있는 숫자 집합을 구하는 메소드
	public void findCanMakeNumbers(String maxValue, String[] numbersSplit) {

		int maxValueToInt = Integer.parseInt(maxValue);
		//기저사실 : maxValueToInt 가 0 또는 1 인경우 - 소수가 존재하지 않으므로 count = 0 리턴
		if(maxValueToInt==0 || maxValueToInt==1) {
			return;
		}
		//maxValue 까지 모든 소수를 저장
		for(int i=2;i<=maxValueToInt;i++) {
			boolean checkPrimeNumbers = true;
			int sqrtValue = (int) Math.sqrt(i);
			numbersMap = makeNumbersMap(numbersSplit);

			loop : for(int k=2;k<=sqrtValue;k++) {
				if(i%k==0) {//나눠질 경우 소수가 아님
					checkPrimeNumbers = false;
					break loop;
				}
			}
			if(checkPrimeNumbers)
				PrimeNumbersV.add(i);
		}

		for(int i=0;i<PrimeNumbersV.size();i++) {
			String PrimeNumber = Integer.toString(PrimeNumbersV.get(i));
			String [] PrimeNumberSplit = PrimeNumber.split("");
	        HashMap<String, Integer> copyNumbersMap = new HashMap<String, Integer>(numbersMap);
	        boolean checkMakeNumber = true;
			//해당 소수가 Numbers 로 이루어진 종이들로 만들 수 있는지 판별
			for(int k=0; k<PrimeNumberSplit.length;k++) {
				if(copyNumbersMap.get(PrimeNumberSplit[k])==null) {
					checkMakeNumber = false;
					break;
				}
				else if(copyNumbersMap.get(PrimeNumberSplit[k])!=0 && PrimeNumber.contains(PrimeNumberSplit[k])) {
					copyNumbersMap.put(PrimeNumberSplit[k], copyNumbersMap.get(PrimeNumberSplit[k])-1);
				}
				else {
					checkMakeNumber = false;
					break;
				}
			}
			if(checkMakeNumber)
				count++;
		}

		//System.out.println("count:"+count);
	}

	//각 numbers 로 이루어진 숫자종이조각들의 갯수 정보를 담고있는 makeNumbersMap 만드는 메소드
    public HashMap<String, Integer> makeNumbersMap(String[] numbersSplit){

    	HashMap<String, Integer> numbersMap = new HashMap<String, Integer>();

        for(int i=0;i<numbersSplit.length;i++){

        	if(numbersMap.get(numbersSplit[i])==null)
        	numbersMap.put(numbersSplit[i], 1);
        	else
        	{
        		numbersMap.put(numbersSplit[i], numbersMap.get(numbersSplit[i])+1);
        	}
        }
    	return numbersMap;
    }

    public String getMaxValue(String numbers, String[] numbersSplit,  HashMap<String, Integer> copyNumbersMap) {
        String maxNumber = "";
        for(int i=0;i<numbers.length();i++) {
        	int maxValue = -1;
        	int index = -1;
        	for(int k=0;k<numbersSplit.length;k++){
        		int number = Integer.parseInt(numbersSplit[k]);

        		if(copyNumbersMap.get(numbersSplit[k])!=0 && maxValue<number) {
        			maxValue = number;
        			index = k;
        		}
        	}
        	copyNumbersMap.put(numbersSplit[index], copyNumbersMap.get(numbersSplit[index])-1);
        	maxNumber += Integer.toString(maxValue);
        }

        //System.out.println(maxNumber);

        return maxNumber;
    }

}
*/
public class FindPrimeNumbers {
	public static void main(String[] args) {
		String numbers = "011";//7,17,71
		Solution sol = new Solution();
		int answer = sol.solution(numbers);
		System.out.println("answer:"+answer);
	}
}
