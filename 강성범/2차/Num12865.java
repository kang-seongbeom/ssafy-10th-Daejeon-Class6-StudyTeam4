package kr.ac.lecture.baekjoon.Num10001_20000;

import java.io.*;
import java.util.*;

public class Num12865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(stz.nextToken());
        int k = Integer.parseInt(stz.nextToken());

        // [0] : 무게
        // [1] : 가치
        int[][] arr = new int[n+1][2];

        int max = 0;
        for(int i=1; i < n+1; i++){
            stz = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(stz.nextToken());
            int value = Integer.parseInt(stz.nextToken());

            arr[i][0] = weight;
            arr[i][1] = value;
            max = Math.max(max, value);
        }

        Arrays.sort(arr, Comparator.comparingInt(o -> o[0]));

        int[][] dp = new int[n+1][k+1];

        for(int i=1; i< n + 1; i++){ // 물건
            int w = arr[i][0];
            int v = arr[i][1];

            for(int j=1; j< k+1; j++){ // 무게
                if(j-w >= 0){
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-w] + v);
                }else{
                    dp[i][j] = dp[i-1][j]; // j로서는 현재 물건의 무게를 담을 수 없을 때
                }
            }
        }

        bw.write(String.valueOf(dp[n][k]));

        bw.flush();
        bw.close();
        br.close();
    }
}
