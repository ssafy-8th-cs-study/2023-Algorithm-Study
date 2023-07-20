package week43.minhyeok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ11725 {
    static int N;
    static ArrayList[] nodes;
    static boolean[] visited;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nodes = new ArrayList[N+1];
        for (int i=1; i<N+1; nodes[i++] = new ArrayList<Integer>());
        visited = new boolean[N+1];

        for (int i = 0; i < N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            nodes[from].add(to);
            nodes[to].add(from);
        }

        parent = new int[N+1];
        visited[1] = true;
        dfs(1);
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < N+1; i++) {
            sb.append(parent[i]).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int i) {
        ArrayList<Integer> childNodes = nodes[i];
        for (int node : childNodes) {
            if (!visited[node]) {
                visited[node] = true;
                parent[node] = i;
                dfs(node);
            }
        }
    }
}
