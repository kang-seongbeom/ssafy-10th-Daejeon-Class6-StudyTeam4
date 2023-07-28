// (BigInteger 사용법 & 예제)
// [참고문헌]: https://coding-factory.tistory.com/604

// BigInteger형
// nCr = n! / (n-r)! * r!

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");

        int n = Integer.parseInt(inputs[0]);
        int m = Integer.parseInt(inputs[1]);

        BigInteger numerator = BigInteger.ONE; // 분자
        BigInteger denominator = BigInteger.ONE; // 분모
        
        // nCr = n! / (n-r)! * r!
        for (int i = 0; i < m; i++) {
            numerator = numerator.multiply(new BigInteger(String.valueOf(n - i)));
            denominator = denominator.multiply(new BigInteger(String.valueOf(i + 1)));
        }

        BigInteger answer = numerator.divide(denominator);

        System.out.println(answer);
    }
}
====================================================================================================
// 재귀함수(DFS) + memoization 
// nCr = n-1Cr-1 + n-1Cr
import java.io.*;
import java.math.BigInteger;
import java.util.*;
public class Main {
    static BigInteger[][] dy = new BigInteger[1000][1000];
    static int n,r;
    public BigInteger DFS(int n, int r) {
        if(dy[n][r]!=null && dy[n][r].compareTo(BigInteger.valueOf(0)) > 0) return dy[n][r]; // memoization
        if(n==r || r==0) return BigInteger.valueOf(1); // 언제 DFS가 끝나는지
        else return dy[n][r] = DFS(n-1, r-1).add(DFS(n-1,r)); // DFS 재귀부분
    }
    public static void main(String[] args) throws IOException {
        Main T = new Main();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        r = sc.nextInt();
        T.DFS(n, r);
        System.out.println(dy[n][r]);
    }
}
