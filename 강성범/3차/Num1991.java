package kr.ac.lecture.baekjoon.Num1001_10000;

import java.io.*;
import java.util.*;
public class Num1991 {

    private static final Map<Character, char[]> map = new HashMap<>();
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz;


        int n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++){
            stz = new StringTokenizer(br.readLine());
            char a = stz.nextToken().charAt(0);
            char b = stz.nextToken().charAt(0);
            char c = stz.nextToken().charAt(0);

            map.put(a, new char[]{b, c});
        }

        char start = 'A';
        preOrder(start);
        sb.append(System.lineSeparator());

        midOrder(start);
        sb.append(System.lineSeparator());

        postOrder(start);

        bw.write(sb.toString());

        bw.flush();
        bw.close();
        br.close();
    }

    private static void preOrder(char current) {
        sb.append(current);

        char[] children = map.get(current);
        if(children[0] != '.') preOrder(children[0]);
        if(children[1] != '.') preOrder(children[1]);
    }

    private static void midOrder(char current) {

        char[] children = map.get(current);
        if(children[0] != '.') midOrder(children[0]);
        sb.append(current);
        if(children[1] != '.') midOrder(children[1]);
    }

    private static void postOrder(char current) {

        char[] children = map.get(current);
        if(children[0] != '.') postOrder(children[0]);
        if(children[1] != '.') postOrder(children[1]);
        sb.append(current);
    }
}
