package kr.ac.lecture.baekjoon.Num10001_20000;

import java.io.*;
import java.util.*;

public class Num11657 {
    private static final long MAX = 987_654_321;
    private static final StringBuilder sb = new StringBuilder();

    private static int n, m;

    private static List<List<Bus>> buses;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        n = Integer.parseInt(stz.nextToken());
        m = Integer.parseInt(stz.nextToken());

        buses = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            buses.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            stz = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(stz.nextToken()) - 1;
            int e = Integer.parseInt(stz.nextToken()) - 1;
            int w = Integer.parseInt(stz.nextToken());

            buses.get(s).add(new Bus(e, w));
        }

        int v = n;
        long[] dis = new long[n]; // int는 underflow가 발생할 수 있음
        Arrays.fill(dis, MAX);
        dis[0] = 0;

        boolean isUpdated = false;
        while (v-- > 1) { // 한 개의 도시는 최대 n-1개의 간선을 가질 수 있음
            isUpdated = false;

            for (int k = 0; k < n; k++) { // 0~n-1 도시에서 출발

                // 시작지점인 0과 연결되지 않는 것들의 탐색을 하지 않게 함
                // 애초에 0에서 부터 시작하므로 시작 탐색에서 시작과 연결된 것은 MAX에서 초기화 될 것임
                if(dis[k] == MAX) continue;

                for (int i = 0; i < buses.get(k).size(); i++) {
                    Bus nn = buses.get(k).get(i);

                    if (dis[nn.e] > dis[k] + nn.weight) {
                        dis[nn.e] = dis[k] + nn.weight;
                        isUpdated = true;
                    }
                }
            }

            if (!isUpdated)
                break;
        }

        if (isUpdated) {
            isUpdated = false;

            Loop1:
            for (int k = 0; k < n; k++) { // 0~n-1 도시에서 출발
                for (int i = 0; i < buses.get(k).size(); i++) {
                    Bus nn = buses.get(k).get(i);

                    if (dis[nn.e] > dis[k] + nn.weight) {
                        isUpdated = true; // 음의 사이클 발생
                        break Loop1;
                    }
                }
            }

        }

        if (isUpdated) {
            sb.append(-1);
        } else {
            for (int i = 1; i < n; i++) {
                long ans = (dis[i] < MAX) ? dis[i] : -1;
                sb.append(ans).append(System.lineSeparator());
            }
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static class Bus {
        int e, weight;

        public Bus(int e, int weight) {
            super();
            this.e = e;
            this.weight = weight;
        }
    }
}
