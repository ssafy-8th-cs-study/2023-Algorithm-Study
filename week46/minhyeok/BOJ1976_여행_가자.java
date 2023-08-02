package week46.minhyeok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ1976_여행_가자 {
    public static void main(String[] args) throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 노드 수
        int M = Integer.parseInt(br.readLine()); // 여행 루트
        ArrayList[] nodes = new ArrayList[N+1]; // 노드 연결 리스트
        HashSet<Integer> set = new HashSet<>(); // 여행 루트들의 집합
        for (int i = 1; i <= N; nodes[i++] = new ArrayList<Integer>());
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                if(st.nextToken().equals("1")) {
                    nodes[i].add(j);
                }
            }
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }
        // bfs
        boolean[] visited = new boolean[N+1];
        boolean isYes = false;
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                if (bfs(i,visited,set,nodes)) {
                    System.out.println("YES");
                    isYes = true;
                    break;
                }
            }
        }

        if (!isYes) System.out.println("NO");
    }

    private static boolean bfs(int i, boolean[] visited, HashSet<Integer> set, ArrayList[] nodes) {
        ArrayDeque<Integer> aq = new ArrayDeque<>();
        aq.add(i);
        visited[i] = true;
        HashSet<Integer> temp = new HashSet<>();
        temp.add(i);
        while(!aq.isEmpty()) {
            int node = aq.poll();
            ArrayList<Integer> arr = nodes[node];
            for (int nextNode : arr) {
                if (!visited[nextNode]) {
                    visited[nextNode] = true;
                    aq.add(nextNode);
                    temp.add(nextNode);
                }
            }
        }

        for (int node : set) {
            if (!temp.contains(node)) return false;
        }
        return true;
    }
}
