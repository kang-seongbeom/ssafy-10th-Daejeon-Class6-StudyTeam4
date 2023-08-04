package kr.ac.lecture.baekjoon.Num1001_10000;

import java.io.*;
import java.util.*;

public class Num7576 {

    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};
    private static final int RIPE = 1; // 익은 토마토
    private static final int NOT = -1; // 빈공간
    private static final int MAX = 987_654_321;

    private static int n, m;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        m = Integer.parseInt(stz.nextToken());
        n = Integer.parseInt(stz.nextToken());
        map = new int[n][m];

        // 익은 토마토 저장
        List<Point> ripes = new ArrayList<>();
        int[][] ages = new int[n][m];
        for(int i=0; i<n; i++){
            stz = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(stz.nextToken());

                if(map[i][j] == RIPE) ripes.add(new Point(i, j));

                ages[i][j] = (map[i][j] == 0) ? MAX : map[i][j];
            }
        }

        bw.write(String.valueOf(whatDay(ripes, ages)));

        bw.flush();
        bw.close();
        br.close();
    }

    private static int whatDay(List<Point> ripes, int[][] ages) {
        Queue<Point> qu = new LinkedList<>(ripes);

        while (!qu.isEmpty()){
            Point cn = qu.poll();

            for(int d = 0;d<4; d++){
                int nx = cn.x + DX[d];
                int ny = cn.y + DY[d];

                // 빈공간이거나 최소로 갱신할 수 없을 때
                if(!inRange(nx, ny) || map[nx][ny] == NOT || ages[nx][ny] <= ages[cn.x][cn.y]+1) continue;

                // 최소 갱신
                ages[nx][ny] = ages[cn.x][cn.y] + 1;
                qu.add(new Point(nx, ny));
            }
        }

        int day = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                day = Math.max(day, ages[i][j]);
            }
        }

        // 시작위치를 1초로 잡기 때문에 1을 뺌 (한 번 전파되면 2초가 되므로 1빼기)
        return day == MAX ? -1 : day-1;
    }

    private static boolean inRange(int x, int y) {
        return (x>=0 && y>=0 && x<n && y<m);
    }

    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }
}
