package week44.minhyeok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ11438 {
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] nodes = new ArrayList[N+1];
        for (int i = 1; i < N+1; nodes[i++] = new ArrayList());
        for (int i = 0; i < N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            nodes[from].add(to);
            nodes[to].add(from);
        }

        max = 0;
        int tmp = 1;
        while (tmp < N + 1) {
            tmp <<= 1;
            max++;
        }


        int[][] parents = new int[N+1][max];
        int[] depth = new int[N+1];
        ArrayDeque<Integer> aq = new ArrayDeque();
        aq.add(1);
        depth[1] = 1;
        int d = 1;
        while (!aq.isEmpty()) {
            d++;
            int size = aq.size();
            for (int s = 0; s < size; s++) {
                int node = aq.poll();
                for (int next : nodes[node]) {
                    if (depth[next] == 0) {
                        parents[next][0] = node;
                        depth[next] = d;
                        aq.add(next);
                    }
                }
            }
        }
        for (int i = 1; i < max; i++) {
            // 2^K 번째 조상 노드 저장
            for (int j = 1; j <= N; j++) {
                parents[j][i] = parents[parents[j][i - 1]][i - 1];
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

    private static int LCA(int n1, int n2, int[][] parents, int[] depth) {
        if (depth[n1] < depth[n2]) {
            int temp = n1;
            n1 = n2;
            n2 = temp;
        }
        for (int i = max - 1; i >= 0; i--) {
            if (Math.pow(2, i) <= depth[n1] - depth[n2]) {
                // 높이 차이 만큼 a 높이 올리기
                n1 = parents[n1][i];
            }
        }
        if (n1 == n2)
            return n1;

        for (int i = max-1; i>=0; i--) {
            if (parents[n1][i] != parents[n2][i]) {
                n1 = parents[n1][i];
                n2 = parents[n2][i];
            }
        }
        return parents[n1][0];
    }
}
