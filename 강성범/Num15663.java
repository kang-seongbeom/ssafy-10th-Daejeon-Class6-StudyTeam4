package kr.ac.lecture.baekjoon.Num10001_20000;

import java.io.*;
import java.util.*;

public class Num15663 {

    private static int n, m;
    private static final Set<String> permus = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        n = Integer.parseInt(stz.nextToken());
        m = Integer.parseInt(stz.nextToken());

        int[] arr = new int[n];
        stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stz.nextToken());
        }

        // nPm
        permutation(arr, 0);

        // 사전순에 맞춰 정렬
        List<String> answer = new ArrayList<>(permus);
        answer.sort((o1, o2) -> {
            String[] sp1 = o1.split(" ");
            String[] sp2 = o2.split(" ");
            for(int i=0; i < sp1.length; i++){
                int a = Integer.parseInt(sp1[i]);
                int b = Integer.parseInt(sp2[i]);

                if(a == b) continue;
                return (a > b) ? 1 : -1;
            }
            return 0;
        });

        for (String ans : answer) {
            bw.write(ans);
            bw.write(System.lineSeparator());
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void permutation(int[] arr, int depth) {
        if(depth == m){
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<m; i++){
                sb.append(arr[i]).append(" ");
            }
            permus.add(sb.toString());
        }

        for(int i=depth; i<n; i++){
            swap(arr, depth, i);
            permutation(arr, depth+1);
            swap(arr, depth, i);
        }
    }

    private static void swap(int[] arr, int depth, int i) {
        int tmp = arr[depth];
        arr[depth] = arr[i];
        arr[i] = tmp;
    }
}
