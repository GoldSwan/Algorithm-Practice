package algorithm.programers;
//참고 : https://velog.io/@jii0_0/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-150367-%ED%91%9C%ED%98%84-%EA%B0%80%EB%8A%A5%ED%95%9C-%EC%9D%B4%EC%A7%84%ED%8A%B8%EB%A6%AC-Java
class Solution {
    private int result = 0;
    private boolean[] binary;
    public int[] solution(long[] numbers) {
        var answer = new int [numbers.length];
        int i = 0;
        for(long number : numbers){
            var strBinary = Long.toBinaryString(number);
            var binaryLen = strBinary.length();
            var binaryTreeLen = getBinaryTreeLength(strBinary.length());
            binary = new boolean[binaryTreeLen];
            var idx = binaryTreeLen - binaryLen;
            for(int j=0; j<binaryLen; j++) {
                binary[idx++] = strBinary.charAt(j) == '1';
            }
            result = 1;
            possible(0, binaryTreeLen-1, false);
            answer[i++] = result;
        }
        return answer;
    }

    public int getBinaryTreeLength(int binaryLen){
        int binaryTreeLen = 0;
        int exp = 1;
        do {
            binaryTreeLen = (int) Math.pow(2, exp++) - 1;//높이 H인 포화 이진트리 전체 노드 수
        } while (binaryTreeLen < binaryLen);
        return binaryTreeLen;
    }
    //이진 트리 표현 가능 여부 검증
    public void possible(int start, int end, boolean isDummy){
        int mid = (start+end) / 2;
        if(isDummy && binary[mid]) {//루트가 0이면 자식노드들에서 1이나오면 안됨
            result = 0;
            return;
        }
        if(start != end) {
            possible(start, mid-1, !binary[mid]);//왼쪽, 현재 루트가 더미이면 check = true
            possible(mid+1, end, !binary[mid]);//오른쪽
        }
    } 
}

public class RepresentableBinaryTree {
    public static void main(String[] args) throws Exception {
        var solution = new Solution();
        //long[] numbers = {7,42,5};
        long[] numbers = {63,111,95};
        var answer = solution.solution(numbers);
        for(int num : answer){
            System.out.println(num+" ");
        }
    }
}
