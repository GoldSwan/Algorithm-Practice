/********************************************************************
작성일 : 2020-01-05
작성자 : 김동한
문제 : 프로그래머스 - 소수 찾기(3_findPrimeNumbers)
출저 : programers
풀이 : List, Set
예상 시간복잡도 : N^2
TEST 결과 :  모두통과
*********************************************************************/
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Solution {

	//https://programmers.co.kr/learn/courses/30/lessons/42839?language=java
	
		public int solution(String numbers) {
        int answer = 0;
        List<String> numList = new ArrayList<String>();
        Set<Integer> resultSet = new HashSet<Integer>();	        
        
        for(int i=0; i<numbers.length(); i++) {	        	
        	numList.add(String.valueOf(numbers.charAt(i)));
        }
        
        //순열생성
        perm(numList, resultSet, 0);
        
        //소수 확인
		Iterator<Integer> itr = resultSet.iterator();
		while (itr.hasNext()) {
			if(isPrime(itr.next())) {
				answer++;
			}
		}
		
		return answer;
	}
	
	//참고 (순열 생성) 
	public List<String> perm(List<String> list, Set<Integer> resultSet,int pivot) {
		if (pivot == list.size()) {
			//System.out.println(list);
			makeResultSet(list, resultSet);
		}
		 
		for (int i = pivot; i < list.size(); i++) {
			swap(list, i, pivot);
			perm(list, resultSet, pivot + 1);
			swap(list, i, pivot);
		}
		return list;
	}
	
	public void swap(List<String> list, int i, int j) {
		String a = list.get(i);
		String b = list.get(j);
		list.set(i, b);
		list.set(j, a);
		
	}
	
	// 순열별 경우의수
	public Set<Integer> makeResultSet(List<String> numList,Set<Integer> resultSet){
		// prefix 를 정하고 뒤를 순서대로 붙임
		// 다음 프리픽스로 사용한 인덱스를 제거하고 반복하면서 리절트셋에 에드
		for(int k=0; k<numList.size() ; k++) {
			
			int fixIdx = k;
			resultSet.add(Integer.valueOf(numList.get(k)));
			List<String> tempNumList = new ArrayList<String>();
	
			//고정 값을 제외하 나머지 요소를 담을  리스트를 생성합니다.
			for(int i=fixIdx+1; i<numList.size();i++) {
				tempNumList.add(numList.get(i));
			}
			
			for(int i=0; i< tempNumList.size();i++) {
				// 두번째로 배치할 인덱스 == i
				// 두번째 배치할 요소를 고려해서 큐를 생성합니다.
				Queue<String> q = new LinkedList<String>();
				List<String> tempNumList2 = new ArrayList<String>();
				String resultNum = numList.get(fixIdx);
					   resultNum += tempNumList.get(i);
				
				for(int j=0; j<tempNumList.size(); j++) {
					if(i != j) {
						resultNum += tempNumList.get(j);
					}
				}
				
				resultSet.add(Integer.valueOf(resultNum));
				makeResultSet(tempNumList,resultSet);
			}		
		}
		return resultSet;
	}

	private boolean isPrime(int num) {
		if (num < 2) return false;
	    if (num == 2) return true;
	    if (num % 2 == 0) return false;
	
	    /**
	     * num 이 p * q 라고 할때 한 수는 항상 sqrt(num) 이하의 값을 갖는다. (ex, num = 24, p = [1, 2, 3, 4], q = [6, 8, 12, 24])
	     * 따라서 num 이 sqrt(num) 이하의 값중 하나로 나눠지는지 체크한다. (ex, 24 가 4 이하의 숫자로 나눠지는지 체크,, 1,2 는 예외)
	     */
	    // 참고
	    int sqrtn = (int) Math.sqrt(num);
	    for (int i = 3; i <= sqrtn; i += 2) {
	        if (num % i == 0) return false;
	    }
	    return true;
	}
	
	public static void main(String[] args) {
		Solution obj = new Solution();
		
		System.out.println("result:"+obj.solution("17"));
		//System.out.println("result:"+obj.solution("011"));
		//System.out.println("result:"+obj.solution("101"));
		//System.out.println("result:"+obj.solution("12345"));
		//System.out.println("result:"+obj.solution("123"));
		//System.out.println("result:"+obj.solution("11"));
		//System.out.println("result:"+obj.solution("33"));
	}
	
	
}


public class findPrimeNumbers_kdh {

	public static void main(String[] args) {
		String numbers = "17";//7,17,71
		Solution sol = new Solution();
		int answer = sol.solution(numbers);
		System.out.println(answer);
	}

}