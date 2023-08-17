import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int from, to, edge;
	static int [][]arr;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		arr=new int[N][N];
		
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		// 경로가 있냐 없냐만 판단하면 되므로 (i,j) 노드를 거치는 경우가 있는지만 체크
		for(int k=0; k<N; k++) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(arr[i][k]==1 && arr[k][j]==1) arr[i][j]=1;
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}

}