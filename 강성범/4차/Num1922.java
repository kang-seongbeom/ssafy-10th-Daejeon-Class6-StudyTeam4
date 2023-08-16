package kr.ac.lecture.baekjoon.Num1001_10000;

import java.io.*;
import java.util.*;

public class Num1922 {

    private static int n, m;
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        parents = new int[n];
        for(int i=0; i<n; i++){
            parents[i] = i;
        }

        List<Computer> computers = new ArrayList<>();

        for(int i=0; i<m; i++){
            stz = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stz.nextToken()) - 1;
            int end = Integer.parseInt(stz.nextToken()) - 1;
            int weight = Integer.parseInt(stz.nextToken());

            computers.add(new Computer(start, end, weight));
        }

        computers.sort(Computer::compareTo);

        int answer = 0;
        for(Computer c : computers){
            if(union(c.start, c.end)){
                answer += c.weight;
            }
        }

        bw.write(String.valueOf(answer));

        bw.flush();
        bw.close();
        br.close();
    }

    private static int find(int num){
        if(num == parents[num]){
            return num;
        }
        return parents[num] = find(parents[num]);
    }

    private static boolean union(int a, int b){
        int root1 = find(a);
        int root2 = find(b);

        if(root1 == root2) return false;

        if(root1 > root2){
            parents[root1] = root2;
        }else {
            parents[root2] = root1;
        }
        return true;
    }

    private static class Computer implements Comparable<Computer>{
        int start, end, weight;

        public Computer(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Computer o) {
            return this.weight - o.weight;
        }
    }
}
