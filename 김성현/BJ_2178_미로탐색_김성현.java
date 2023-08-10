package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제 설명 N * M 미로 1은 이동할 수 있는 칸 0은 이동 불가 (1,1)에서 출발하여 (N,M)까지 지나야 하는 최소 칸 수 서로
 * 인접한 칸으로만 이동 가능 칸 셀 때에는 시작, 도착 위치 포함
 * 
 * 입력: 첫 줄 : 두 정수 N, M 다음 N 개의 줄 : 미로 주어짐 -> 각 수들은 붙어서 주어짐
 * 
 * 생각나는 풀이 사방 탐색을 위해 벡터는 무조건 필요함 경계에 도달하거나 가는 방향에 1이 없다면 방향 전환
 */
public class BJ_2178_미로탐색_김성현 {

	static boolean[][] visited; // 방문 배열
	static int maze[][]; // 미로판

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static Queue<Node> que = new LinkedList<>();

	static int N, M;
	static int newR, newC;
	static int direction;

	static class Node {
		int r;
		int c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 열 길이
		M = Integer.parseInt(st.nextToken()); // 행 길이

		visited = new boolean[N][M];
		maze = new int[N][M];
		// maze 배열 채우기
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				maze[i][j] = Character.getNumericValue(str.charAt(j));
			}
		}

		BFS(0, 0);
		System.out.println(maze[N - 1][M - 1]);
	}

	private static void BFS(int r, int c) {
		que.offer(new Node(r, c)); // Node(x, y) 집어넣기
		visited[r][c] = true; // 방문 체크

		while (!que.isEmpty()) { // que가 빌 때까지
			Node node = que.poll(); // 큐에서 꺼냄

			for (int d = 0; d < 4; d++) {
				// 이동할 위치 변수
				newR = node.r + dr[d];
				newC = node.c + dc[d];

				if (isInRange(newR, newC) && visited[newR][newC] == false && maze[newR][newC] == 1) {
					// 조건에 만족하면 큐에 새노드를 집어넣음
					que.offer(new Node(newR, newC));
					visited[newR][newC] = true;

					// 거리 누적하기
					maze[newR][newC] = maze[node.r][node.c] + 1;

					if (visited[N - 1][M - 1] == true) {
						return;
					}
				}
			}
		}

	}

	private static boolean isInRange(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < M;
	}

}
