package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class NCR2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		

		BigInteger bigSon = new BigInteger("1");
		for (int i = n; i >= n - r + 1; i++) {
			bigSon = bigSon.multiply(new BigInteger(String.valueOf(i)));
		}

		BigInteger bigMom = new BigInteger("1");
		for (int i = 1; i <= r; i++) {
			bigMom = bigMom.multiply(new BigInteger(String.valueOf(i)));
		}
		
		System.out.println(bigSon.divide(bigMom));
	}
}
             