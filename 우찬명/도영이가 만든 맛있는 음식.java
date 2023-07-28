// [참고문헌]: https://subin-programming.tistory.com/293
import java.io.*;
import java.util.*;
public class Main {
    static int N;
    static int favor = Integer.MAX_VALUE;
    static int[] S;
    static int[] B;
    static boolean[] check;
    // N개 중 중복과 순서에 상관없이 M개 고르기 nCm
    public static void DFS(int L) { // Level
        if(L == N) { // 깊이가 음식의 개수와 같아진다면
            int foodcnt = 0;
            int sour = 1; int bitter = 0;
            for(int i=0; i<N; i++) {
                if(check[i]) { // 선택했던 음식들의 신맛과 단맛을 계산
                    foodcnt++;
                    sour *= S[i];
                    bitter += B[i];
                }
            }
            if(foodcnt==0) return; // 재료는 적어도 하나 사용
            favor = Math.min(favor, Math.abs(sour-bitter)); // 최소값 갱신
        }
        else {
            // 현재 음식을 선택한 경우 탐색
            check[L]=true;
            DFS(L+1);
            // 현재 음식을 선택하지 않은 경우 탐색
            check[L]=false;
            DFS(L+1);
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        S = new int[N];
        B = new int[N];
        check = new boolean[N];

        for(int i=0; i<N; i++) {
            S[i] = sc.nextInt();
            B[i] = sc.nextInt();
        }

        DFS(0);
        System.out.println(favor);
    }
}
