package kr.ac.lecture.baekjoon.Num1001_10000;

import java.io.*;
import java.util.*;

public class Num1759 {

    private static int l, c;
    private static Set<String> answer = new LinkedHashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        l = Integer.parseInt(stz.nextToken());
        c = Integer.parseInt(stz.nextToken());

        char[] inputs = new char[c];
        stz = new StringTokenizer(br.readLine());
        for(int i=0; i<c; i++){
            inputs[i] = stz.nextToken().charAt(0);
        }

        Arrays.sort(inputs);

        boolean[] visited = new boolean[c];
        backtracking(inputs, visited, 0, "");

        for (String ans : answer) {
            bw.write(ans + System.lineSeparator());
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void backtracking(char[] inputs, boolean[] visited, int depth, String str) {
        // 탐색하되, 가지치기
        if (!isPossible(inputs, visited, depth, str)) return;

        if (str.length() == l) {
            answer.add(str);
            return;
        }

        // 모든 경우 탐색
        for (int i = depth; i < c; i++) {
            visited[i] = true;
            backtracking(inputs, visited, i + 1 ,str + inputs[i]);

            visited[i] = false;
            backtracking(inputs, visited, i + 1, str);
        }
    }

    private static boolean isPossible(char[] inputs, boolean[] visited, int depth, String str) {
        int currentConsonantCount = 0;
        int currentVowelCount = 0;

        int nextConsonantCount = 0;
        int nextVowelCount = 0;

        for (int i = 0; i < c; i++) {
            boolean isConsonant = isConsonant(inputs[i]);

            if (i < depth) {
                if(visited[i]) {
                    if (isConsonant) currentConsonantCount++;
                    else currentVowelCount++;
                }
            } else {
                if(str.length() == l) break;

                if (isConsonant) nextConsonantCount++;
                else nextVowelCount++;
            }
        }

        // 모음은 최소 한 개, 자음은 최소 두 개
        return (currentConsonantCount + nextConsonantCount >= 1) && (currentVowelCount + nextVowelCount >= 2);
    }

    private static boolean isConsonant(char chr) {
        return (chr == 'a' || chr == 'e' || chr == 'i' || chr == 'o' || chr == 'u');
    }
}
