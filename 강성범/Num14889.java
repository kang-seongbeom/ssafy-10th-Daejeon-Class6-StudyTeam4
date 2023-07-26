package kr.ac.lecture.baekjoon.Num10001_20000;

import java.io.*;
import java.util.*;

public class Num14889 {

    private static int[][] synergy;
    private static int different = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz;

        int n = Integer.parseInt(br.readLine());
        synergy = new int[n][n];

        for (int i = 0; i < n; i++) {
            stz = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                synergy[i][j] = Integer.parseInt(stz.nextToken());
            }
        }

        boolean[] visited = new boolean[n];
        combination(visited, n, n/2, 0);

        bw.write(String.valueOf(different));

        bw.flush();
        bw.close();
        br.close();
    }

    private static void combination(boolean[] visited, int n, int r, int depth) {
        if(r == 0){
            List<Integer> teamA = new ArrayList<>();
            List<Integer> teamB = new ArrayList<>();

            for(int i=0; i<n; i++) {
                if(visited[i]){
                    teamA.add(i);
                }else{
                    teamB.add(i);
                }
            }

            int teamATotalScore = calScore(teamA, n);
            int teamBTotalScore = calScore(teamB, n);

            different = Math.min(different, Math.abs(teamATotalScore - teamBTotalScore));
            return;
        }

        if(depth == n) return;

        visited[depth] = true;
        combination(visited, n, r-1, depth+1);

        visited[depth] = false;
        combination(visited, n, r, depth+1);
    }

    private static int calScore(List<Integer> team, int n) {
        int totalScore = 0;
        for (int i = 0; i < team.size(); i++) {
            for (int j = i+1; j < team.size(); j++) {
                totalScore += synergy[team.get(i)][team.get(j)];
                totalScore += synergy[team.get(j)][team.get(i)];
            }
        }
        return totalScore;
    }
}
