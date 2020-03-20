import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class programmers42839 {
	static int inputLength;
	static String input;
	static Set<Integer> candidateNumbers;
	static boolean[] isUsed;
	
	public static void main(String args[]) {
		Scanner s = new Scanner(System.in);
		input = s.next();
		
		candidateNumbers = new HashSet<Integer>();
		
		inputLength = input.length();
		boolean[] isUsed = new boolean[inputLength];
		
		for(int howManyToAppend=1; howManyToAppend<=inputLength; howManyToAppend++) {
			generateNumber(howManyToAppend, "", Arrays.copyOf(isUsed, inputLength), 1);
		}
		

		candidateNumbers.remove(1);
		candidateNumbers.remove(0);

		Iterator<Integer> it = candidateNumbers.iterator();
		
		int candidateNumber;
		int noSosuCount=0;
		
		while( it.hasNext() ) {
			candidateNumber = it.next();
			if( !isPrime(candidateNumber) ) 
				noSosuCount++;
		}
		
		System.out.print( candidateNumbers.size() - noSosuCount);
	}
	
	public static void generateNumber(int howManyToAppend, String numberBeforeAppend, boolean[] isUsed, int depth) {
		if(howManyToAppend < 1)  return; 
		
		for(int i=0; i<inputLength; i++) {
			if(isUsed[i]==false) {
				isUsed[i] = true;
				String numberAfterAppend = numberBeforeAppend + input.charAt(i);
				candidateNumbers.add( Integer.parseInt(numberAfterAppend) );
				generateNumber(howManyToAppend-1, numberAfterAppend, Arrays.copyOf(isUsed, inputLength), depth+1);
				isUsed[i] = false;
			}
		}
	}
	
	public static boolean isPrime(int number) {
		for(int i=2; i*i<=number; i++)
			if( number%i==0 ) 
				return false;
		return true;
	}
}
