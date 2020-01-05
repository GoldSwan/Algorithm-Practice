import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Solution {
	static int inputLength;
	static String input;
	static Set<Integer> candidateNumbers;
	static boolean[] isUsed;
	
	public static int solution(String Numbers) {
		 
        input = Numbers;
		candidateNumbers = new HashSet<Integer>();
		
		inputLength = input.length();
		boolean[] isUsed = new boolean[inputLength];
		for(int numbersToChoose=1; numbersToChoose<=inputLength; numbersToChoose++) {
			chooseNumber(numbersToChoose, "", Arrays.copyOf(isUsed, inputLength), 1);
		}
		

		candidateNumbers.remove(1);
		candidateNumbers.remove(0);

		Iterator<Integer> it = candidateNumbers.iterator();
		
		
		int number;
		int count=0;
		
		while( it.hasNext() ) {
			number = it.next();
			for(int i=2; i<number; i++) {
				if( number%i==0 ) {
					count++;
					break;
				}
			}
		}
		
		return candidateNumbers.size() - count;
	}
	
	public static void chooseNumber(int numbersToChoose, String candidateNumber, boolean[] isUsed, int depth) {
		if(numbersToChoose <= 0) { return; }
		
		for(int i=0; i<inputLength; i++) {
			if(depth==1) Arrays.fill(isUsed, false);
			if(isUsed[i]==false) {
				isUsed[i] = true;
				String newNumber = candidateNumber + input.charAt(i);
				candidateNumbers.add( Integer.parseInt(newNumber) );
				chooseNumber(numbersToChoose-1, newNumber, Arrays.copyOf(isUsed, inputLength), depth+1);
			}
		}
	}
}
