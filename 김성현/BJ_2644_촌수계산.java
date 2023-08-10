package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 입력 첫줄 : 전체 사람 수 n 둘째: 촌수 계산해야 하는 두 사람의 번호 셋쨰: 부모 자식들간의 관계의 개수 m 넷째부터 : 부모 자식
 * 간의 관계 나타내는 x(y의 부모번호), y
 */
public class BJ_2644_촌수계산 {
	static int N; // 전체 사람 수
	static int p1, p2; // 두 사람의 번호
	static int T; // 부모 - 자식 관계 개수
	static ArrayList<ArrayList<Integer>> list;
	static boolean[] visited;
	static int cnt = -1; // 촌수 계산 못할 경우에는 -1 을 출력해야 함

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 전체 사람 수 N
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 비교해야 할 두 사람의 번호
		p1 = Integer.parseInt(st.nextToken());
		p2 = Integer.parseInt(st.nextToken());

		// ArrayList 틀 생성
		list = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<Integer>()); // ?!
		}

		// 관계 개수
		T = Integer.parseInt(br.readLine());

		// 양 방향 인접리스트 구현
		for (int test = 0; test < T; test++) {
			// x가 y 의 부모, y는 자식
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			// 양 방향으로 넣어주기
			list.get(x).add(y);
			list.get(y).add(x);
		}
		// 방문 배열 생성
		visited = new boolean[N + 1];
		DFS(p1, p2, 0);
		System.out.println(cnt);

	}

	private static void DFS(int start, int end, int depth) {
		visited[start] = true;
		for (int i : list.get(start)) {
			if (!visited[i]) {
				if (i == end) {
					cnt = depth + 1;
					return;
				}

				DFS(i, end, depth + 1);
			}
		}

	}
}
