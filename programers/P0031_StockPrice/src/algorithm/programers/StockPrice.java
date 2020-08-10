package algorithm.programers;
//Problem solving time : 23min
class StockPriceSolution {
    public int[] solution(int[] prices) {
        int[] termArray = new int[prices.length];
        
        for(int i=0;i<termArray.length;i++) {
        	for(int j=i+1;j<prices.length;j++) {
        		if(j!=prices.length) {
        			termArray[i]++;
        			if(prices[i]<=prices[j])
        				continue;
        		}
        		break;        		
        	}
        }
        
        return termArray;
    }
}

public class StockPrice {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] prices = {1, 2, 3, 2, 3};
		int[] answer;
		StockPriceSolution stockPriceSolution = new StockPriceSolution();
		answer = stockPriceSolution.solution(prices);
		
		for(int value : answer)
			System.out.print(value + " ");
	}
}
