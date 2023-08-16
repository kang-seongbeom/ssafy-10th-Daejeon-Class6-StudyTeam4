import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	static int[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		arr = new int[n+1]; // 노드수만큼을 가지는 arr 생성 (0은 편의상 미사용)
		
		// 연결정보 초기화
        for (int i = 0; i < arr.length; i++)
            arr[i] =i;
        // input 초기화
        int networks[][] = new int[m][3];
        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine()," ");
            networks[i][0] = Integer.parseInt(st.nextToken()); // 노드 a
            networks[i][1] = Integer.parseInt(st.nextToken()); // 노드 b
            networks[i][2] = Integer.parseInt(st.nextToken()); // 가중치 cost
        }
        Arrays.sort(networks, Comparator.comparingInt(o->o[2])); // cost 낮은순 정렬, 2차원 배열을 특정 index 값을 기준으로 정렬
        
        int res=0; // 정답 result
        for(int[] network : networks) {
        	if(union(network[0],network[1])) { // 연결했으면
        		res+=network[2]; // 비용값 더함
        	}
        }
        bw.write(res+"\n");
        bw.flush();
        br.close();
        bw.close();
	}
	private static int find(int v) { // 연결 끝지점 탐색
		if(v==arr[v]) return v;
		return arr[v] = find(arr[v]); // 최적화 (값 최신화를 하지 않아도 무방함)
	}
	private static boolean union(int from, int to) {
		int a = find(from);
		int b = find(to);
		if(a!=b) { // 연결이 없으면 
			arr[a]=b; // 연결정보가 없는 끝 쪽에 갱신시켜야 연결이 끊어지지 않는다(중요)
			return true;
		}
		return false;
	}
	
}
