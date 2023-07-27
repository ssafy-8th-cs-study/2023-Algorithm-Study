package week44.seoyoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* BOJ5567 - 결혼식 */
public class BOJ5567 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        StringTokenizer st;
        boolean[][] friends = new boolean[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            friends[a][b] = friends[b][a] = true;
        }

        Queue<Integer> q = new LinkedList<Integer>();
        boolean[] visit = new boolean[n + 1];
        visit[1] = true;
        q.add(1);

        int cnt = 0, level = 0;
        while (level < 2) {
            int size = q.size();

            for (int s = 0; s < size; s++) {
                int cur = q.poll();

                for (int i = 1; i <= n; i++) {
                    if (!visit[i] && friends[cur][i]) {
                        visit[i] = true;
                        q.add(i);
                        cnt++;
                    }
                }
            }
            level++;
        }
        System.out.println(cnt);
    }
}