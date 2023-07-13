package week43.minhyeok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11404 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[][] bus = new int[N][N];
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken())-1;
            int to = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken());
            if (bus[from][to] == 0) bus[from][to] = c;
            else bus[from][to] = Math.min(bus[from][to],c);
        }

        int MAX = 10000000;
        int[][] cost = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i==j) cost[i][j] = 0;
                else {
                    if (bus[i][j] == 0) cost[i][j] = MAX;
                    else cost[i][j] = bus[i][j];
                }
            }
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i==j) continue;
                    cost[i][j] = Math.min(cost[i][j],cost[i][k]+cost[k][j]);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(cost[i][j] == MAX ? 0 : cost[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
