package First;
import java.util.*;

public class solve_15663 {
		static int n=0, m=0, input=0;
		static List<Integer>list=new ArrayList<>(8);
		static int []result=new int[8];
		static boolean []visited=new boolean[8];
		
	public static void recursive(int cnt) {
		int flag=0;
		
		if(cnt==m) {
			for(int i=0; i<m; i++) {
				System.out.print(result[i]+" ");
			}
			System.out.println();
		}
		for(int i=0; i<n; i++) {
			if(list.get(i)!=flag && !visited[i]) {
				visited[i]=true;
				result[cnt]=list.get(i);
				flag=result[cnt];
				recursive(cnt+1);
				visited[i]=false;
			}
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		Arrays.fill(visited, false);
		n=sc.nextInt();
		m=sc.nextInt();
		
		
		
		for(int i=0; i<n; i++) {
			int input=sc.nextInt();
			list.add(i, input);
		}
		
		Collections.sort(list);
		
		
		recursive(0);
		
		sc.close();

	}

}
