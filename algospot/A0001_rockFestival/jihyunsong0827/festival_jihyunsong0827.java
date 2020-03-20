package algorithmStudy;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int test = sc.nextInt(); 
		for (int p = 0; p < test; p++) {
			int L = sc.nextInt(); 
			int N = sc.nextInt(); 
			int ia[] = new int[L];
			double result = 1000; 
			for (int i = 0; i < L; i++) {
				ia[i] = sc.nextInt(); 
			}
			for (int i = 0; i < L; i++) {
			}
			for (int k = 0; k <= (L - N); k++) {
				for (int i = N + k; i <= L; i++) {
					int sum = 0;
					double ave = 0;
					for (int j = k; j < i; j++) {
						sum = sum + ia[j];
						ave = (double) sum / (i - k);
					}
					if (ave <= result) {
						result = ave;
					}
				}
			}
			System.out.printf("%.11f\n", result);
		}

	}

}