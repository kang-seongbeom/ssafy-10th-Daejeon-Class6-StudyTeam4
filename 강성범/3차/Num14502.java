package kr.ac.lecture.baekjoon.Num10001_20000;

import java.io.*;
import java.util.*;

public class Num14502 {

    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};

    private static int n, m, wallCount;
    private static int[][] map;
    private static List<Point> virusPoint, emptySpacePoint;
    private static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        n = Integer.parseInt(stz.nextToken());
        m = Integer.parseInt(stz.nextToken());
        map = new int[n][m];

        virusPoint = new ArrayList<>();
        emptySpacePoint = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            stz = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(stz.nextToken());

                if(map[i][j] == 0){
                    emptySpacePoint.add(new Point(i, j));
                }else if(map[i][j] == 2){
                    virusPoint.add(new Point(i, j));
                }else{
                    wallCount++;
                }
            }
        }

        int a = emptySpacePoint.size();
        int b = 3;

        boolean[] visited = new boolean[a];
        combination(visited, a, b, 0);

        bw.write(String.valueOf(max));

        bw.flush();
        bw.close();
        br.close();
    }

    private static void combination(boolean[] visited, int a, int b, int depth) {
        if(b == 0){
            int[][] copiedMap = copyMap();
            for(int i=0; i<a; i++){
                if(visited[i]){
                    copiedMap[emptySpacePoint.get(i).x][emptySpacePoint.get(i).y] = 1;
                }
            }
            int virusArea = searchVirusArea(copiedMap);
            max = Math.max(max, (n*m - virusArea - wallCount - 3)); // 벽을 세우는 개수인 3개 더 뺌
            return;
        }

        if(depth == a) return;

        visited[depth] = true;
        combination(visited, a, b-1, depth+1);

        visited[depth] = false;
        combination(visited, a, b, depth+1);
    }

    private static int searchVirusArea(int[][] copiedMap) {
        boolean[][] visited = new boolean[n][m];
        int virusArea = virusPoint.size();

        Queue<Point> qu = new ArrayDeque<>(virusPoint);
        while (!qu.isEmpty()){
            Point cn = qu.poll();

            for(int d = 0; d<4; d++){
                int nx = cn.x + DX[d];
                int ny = cn.y + DY[d];

                if(!inRange(nx, ny) || visited[nx][ny] || copiedMap[nx][ny] != 0) continue;

                visited[nx][ny] = true;
                virusArea++;
                qu.add(new Point(nx, ny));
            }
        }
        return virusArea;
    }

    private static boolean inRange(int x, int y) {
        return (x>=0 && y>=0 && x<n && y<m);
    }

    private static int[][] copyMap() {
        int[][] copied = new int[n][m];
        for (int i = 0; i < n; i++) {
            copied[i] = Arrays.copyOf(map[i], m);
        }
        return copied;
    }

    private static class Point{
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
