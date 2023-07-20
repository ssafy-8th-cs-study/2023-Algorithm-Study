package week44.minhyeok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ5567 {
    public static void main(String[] args) throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[] visited = new boolean[N+1];
        ArrayList<Integer>[] friends = new ArrayList[N+1];
        for (int i = 1; i < N+1; friends[i++] = new ArrayList<Integer>());
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            friends[from].add(to);
            friends[to].add(from);
        }
        // solution - Graph Search, BFS
        ArrayDeque<Integer> aq = new ArrayDeque();
        aq.add(1);
        visited[1] = true;
        int total = 0;
        int depth = 0;
        while (!aq.isEmpty()) {
            int size = aq.size();
            for (int i = 0; i < size; i++) {
                int node = aq.poll();
                for (int next : friends[node]) {
                    if (!visited[next]) {
                        visited[next] = true;
                        aq.add(next);
                        total++;
                    }
                }
            }
            depth++;
            if (depth == 2) break;
        }
        System.out.println(total);
    }
}
