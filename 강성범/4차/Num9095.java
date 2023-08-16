package kr.ac.lecture.baekjoon.Num1001_10000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Num9095 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        int[] arr = new int[t];
        for(int testCase = 1; testCase <= t; testCase++) {
            int n = Integer.parseInt(br.readLine());
            arr[testCase-1] = n;
        }

        // 순서 저장
        int[] copy = Arrays.copyOf(arr, t);

        Arrays.sort(arr);

        // 가장 큰 값을 구해 한 번에 계산
        int max = arr[arr.length-1] + 1;

        long[] dp = new long[max];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        /**
         * [i-3] : 3 + (3개 전)과 같음
         * [i-2] : 2 + (2개 전)과 같음
         * [i-1] : 1 + (1개 전)과 같음
         * */
        for(int i=4; i < max; i++){
            dp[i] = dp[i-3] + dp[i-2] + dp[i-1];
        }

        for(int n : copy){
            System.out.println(dp[n]);
        }

        br.close();
    }
}
