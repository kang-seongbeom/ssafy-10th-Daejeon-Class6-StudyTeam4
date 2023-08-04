package kr.ac.lecture.baekjoon.Num1001_10000;

import java.io.*;
import java.util.*;

public class Num2636 {
    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};

    private static final int CHEESE = 1;
    private static final int AIR = 0;

    private static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        n = Integer.parseInt(stz.nextToken());
        m = Integer.parseInt(stz.nextToken());
        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            stz = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(stz.nextToken());
            }
        }

        int[] answer = removeCheese(map);

        System.out.println(answer[0]);
        System.out.println(answer[1]);

        bw.flush();
        bw.close();
        br.close();
    }

    private static int[] removeCheese(int[][] map) {
        int time = 0;

        while (true) {
            int cheeseCount = 0; // 현재 시간의 치즈 개수 저장
            boolean[][] airs = getAirLocation(map);
            List<int[]> contactAirCheese = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == AIR) continue;
                    cheeseCount++;

                    // 외곽선 치즈일 경우 제거할 수 있도록 리스트에 담아둠
                    if (isBorderCheese(map, airs, i, j)) {
                        contactAirCheese.add(new int[]{i, j});
                    }
                }
            }

            // 치즈를 전부 제거할 수 있다면 (정답이라면)
            if (cheeseCount == contactAirCheese.size()) {
                // while 마지막에 time을 증가하므로 중간에 정답일 경우 +1해 주어야 함
                // 또, 치즈가 존재하되 한 번에 전부 없앨 수 있을 경웅도 +1
                if (time > 0 || (time == 0 && cheeseCount > 0)) time++;

                return new int[]{time, cheeseCount};
            }

            time++;
            removeCheese(map, contactAirCheese);
        }
    }

    // (0,0) 은 무조건 공기임(문제 조건에 의해 판의 가장자리는 공기라 해도 무방함)
    // (0, 0)에서 부터 탐섹을 시작해 치즈 밖의 공기 위치 확인
    private static boolean[][] getAirLocation(int[][] map) {
        boolean[][] airs = new boolean[n][m];
        Queue<int[]> qu = new LinkedList<>();
        qu.add(new int[]{0, 0});
        airs[0][0] = true;

        while (!qu.isEmpty()) {
            int[] cn = qu.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cn[0] + DX[d];
                int ny = cn[1] + DY[d];

                if (!inRange(nx, ny) || airs[nx][ny] || map[nx][ny] == CHEESE) continue;

                airs[nx][ny] = true;
                qu.add(new int[]{nx, ny});
            }
        }
        return airs;
    }

    // 치즈의 외곽선 확인
    // 치즈를 확인해 인접 네 방향중 하나라도 공기(밖)이면 true 반환
    private static boolean isBorderCheese(int[][] map, boolean[][] airs, int i, int j) {
        for (int d = 0; d < 4; d++) {
            int nx = i + DX[d];
            int ny = j + DY[d];

            if (!inRange(nx, ny) || map[nx][ny] == CHEESE) continue;

            if (airs[nx][ny]) {
                return true;
            }
        }
        return false;
    }

    // 치즈 제거
    // contactAirCheese는 치즈의 외곽선을 저장함
    private static void removeCheese(int[][] map, List<int[]> contactAirCheese) {
        for (int[] lo : contactAirCheese) {
            map[lo[0]][lo[1]] = 0;
        }
    }

    private static boolean inRange(int x, int y) {
        return (x >= 0 && y >= 0 && x < n && y < m);
    }
}
