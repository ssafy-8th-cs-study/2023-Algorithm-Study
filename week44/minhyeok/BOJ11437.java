package week44.minhyeok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ11437 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] nodes = new ArrayList[N+1];
        int[] parents = new int[N+1];
        for (int i = 1; i < N+1; nodes[i++] = new ArrayList());
        for (int i = 0; i < N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            nodes[from].add(to);
            nodes[to].add(from);
        }

        parents[1] = -1;
        int[] depth = new int[N+1];
        ArrayDeque<Integer> aq = new ArrayDeque();
        aq.add(1);
        int d = 0;
        while (!aq.isEmpty()) {
            d++;
            int size = aq.size();
            for (int s = 0; s < size; s++) {
                int node = aq.poll();
                for (int next : nodes[node]) {
                    if (parents[next] == 0) {
                        parents[next] = node;
                        depth[next] = d;
                        aq.add(next);
                    }
                }
            }
        }

        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            sb.append(LCA(n1,n2,parents,depth)).append("\n");
        }
        System.out.println(sb);
    }

    private static int LCA(int n1, int n2, int[] parents, int[] depth) {
        if (depth[n2] < depth[n1]) {
            int temp = n2;
            n2 = n1;
            n1 = temp;
        }
        int d = depth[n2];
        while (d != depth[n1]) {
            d--;
            n2 = parents[n2];
        }
        while (n1 != n2) {
            n1 = parents[n1];
            n2 = parents[n2];
        }
        return n1;
    }
}
