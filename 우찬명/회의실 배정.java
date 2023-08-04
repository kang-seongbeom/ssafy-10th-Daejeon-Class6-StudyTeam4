import java.io.*;
import java.util.*;
class Time implements Comparable<Time> {
    public int s, e;
    Time(int s, int e) {
        this.s = s;
        this.e = e;
    }
    @Override
    public int compareTo(Time o) {
        if(this.e==o.e) return  this.s-o.s;    // 끝나는 시간이 같다면 시작시간 기준으로 오름차순 정렬
        else return this.e-o.e;                // 끝나는 시간 기준 오름차순 정렬
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        Main T = new Main();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Time> arr = new ArrayList<>();
        for(int i=0; i<n; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            arr.add(new Time(s, e));
        }

        int cnt=0;
        Collections.sort(arr); // 재정의된 compareTo에 의해 arr정렬
        int et=0;
        for(Time ob : arr) {
            if(ob.s>=et) {
                cnt++;
                et=ob.e;
            }
        }
        System.out.println(cnt);
    }
}
