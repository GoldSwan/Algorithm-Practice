package algorithm.programers;
import java.util.*;

//시도방법1 : info를 점수순으로 내림차순 정렬하여 찾기 - 효율성에서 실패! 더 좋은 방법을 찾아야 겠다...
/*
class Solution {
    public int[] solution(String[] info, String[] query) {
    	int[] arrSearchPersonCnt = {};
    	String[] arrCopyInfo = {};   	
    	
    	arrSearchPersonCnt = new int[query.length];
    	arrCopyInfo = info.clone();
    	//점수별로 내림차순 정렬
    	Arrays.sort(arrCopyInfo, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				String[] arrStr1 = o1.split(" ");
				String[] arrStr2 = o2.split(" ");
				return -Integer.compare(Integer.parseInt(arrStr1[4]), Integer.parseInt(arrStr2[4]));
			}
		});
    	
    	int i = 0;
    	for(String strQuery : query) {
    		String[] arrQuerySplit = strQuery.split(" ");//0 : 개발언어, 2 : 직군, 4 : 경력, 6 : 소울푸드, 7 : 점수
    		for(String strInfo : arrCopyInfo) {
    			String[] strInfoSplit = strInfo.split(" ");//0 : 개발언어, 1 : 직군, 2 : 경력, 3 : 소울푸드, 4 : 점수
    			
    			if(Integer.parseInt(arrQuerySplit[7]) > Integer.parseInt(strInfoSplit[4])) {
    				break;
    			}
    			
    			if((arrQuerySplit[0].equals("-") || arrQuerySplit[0].equals(strInfoSplit[0]))
    			  &&(arrQuerySplit[2].equals("-") || arrQuerySplit[2].equals(strInfoSplit[1]))
    			  &&(arrQuerySplit[4].equals("-") || arrQuerySplit[4].equals(strInfoSplit[2]))
    			  &&(arrQuerySplit[6].equals("-") || arrQuerySplit[6].equals(strInfoSplit[3]))
    			  &&(arrQuerySplit[7].equals("-") || Integer.parseInt(arrQuerySplit[7]) <= Integer.parseInt(strInfoSplit[4]))) {
    				arrSearchPersonCnt[i] += 1;
    			}
    		}
    		i++;
    	}
    	
    	return arrSearchPersonCnt;
    }
 }
*/
//시도방법2(외부참고) : 비트연산으로 조건별 조합을 만들어서 조건을 Key로 하고 각 점수 리스트를 Value로 갖고 있는 HashMap 만들기.
//만들어진 HashMap에 조건 Key를 대입하여 Return 하는 List에서 만족하는 점수 개수를 찾으면 인원 수가 구해진다!
class Solution {
    public int[] solution(String[] info, String[] query) {
        Map<String, List<Integer>> infos = new HashMap<>();
        for (String in : info) {
            String[] split = in.split(" ");
            int score = Integer.parseInt(split[4]);

            for (int i = 0; i < (1 << 4); i++) {
                StringBuilder key = new StringBuilder();
                for (int j = 0; j < 4; j++) {
                    if ((i & (1 << j)) > 0) key.append(split[j]);//비트연산을 이용하여 
                }
                infos.computeIfAbsent(key.toString(), s -> new ArrayList<>()).add(score);
            }
        }

        List<Integer> empty = new ArrayList<>();
        for (Map.Entry<String, List<Integer>> entry : infos.entrySet())
            entry.getValue().sort(null);//이분탐색으로 점수 조건을 찾기 위하여 오름차순 졍렬한다.

        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            String[] split = query[i].replaceAll("-", "").split(" and | ");
            String key = String.join("", split[0], split[1], split[2], split[3]);
            int score = Integer.parseInt(split[4]);
           
            List<Integer> list = infos.getOrDefault(key, empty);

            int s = 0, e = list.size();

            while (s < e) {
                int mid = (s + e) / 2;

                if (list.get(mid) < score) s = mid + 1;
                else e = mid;
            }

            answer[i] = list.size() - s;
        }

        return answer;
    }
}

public class RankingSearch {

	public static void main(String[] args) {
		String[] info = {"java backend junior pizza 150"
		                 ,"python frontend senior chicken 210"
		                 ,"python frontend senior chicken 150"
		                 ,"cpp backend senior pizza 260"
		                 ,"java backend junior chicken 80"
		                 ,"python backend senior chicken 50"};
		String[] query = {"java and backend and junior and pizza 100"
		                  ,"python and frontend and senior and chicken 200"
		                  ,"cpp and - and senior and pizza 250"
		                  ,"- and backend and senior and - 150"
		                  ,"- and - and - and chicken 100"
		                  ,"- and - and - and - 150"};
		int[] arrSearchPersonCnt = {};
		
		Solution solution = new Solution();
		arrSearchPersonCnt = solution.solution(info, query);
		for(int numSearchPerson : arrSearchPersonCnt) {
			System.out.println("numSearchPerson:"+numSearchPerson);
		}
	}

}
