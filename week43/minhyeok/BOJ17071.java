package week43.minhyeok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ17071 {
    static class Info {
        int x;
        int cnt;

        public Info(int x, int cnt) {
            this.x = x;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int MAX = 500000;
        boolean[][] visited = new boolean[2][MAX+1];
        visited[0][N] = true;
        PriorityQueue<Info> pq = new PriorityQueue<>((o1, o2)->o1.cnt - o2.cnt);
        Info in = new Info(N,0);
        pq.add(in);
        boolean flag = true;
        while (!pq.isEmpty()) {
            Info info = pq.poll();
            int x = info.x;
            int cnt = info.cnt;
            int bX = K + cnt*(cnt+1)/2;
            if (bX > MAX) break;
            if (visited[cnt%2][bX]) {
                System.out.println(cnt);
                flag = false;
                break;
            }
            int next = (cnt+1)%2;
            if (x-1 >= 0 && !visited[next][x-1]) {
                visited[next][x-1] = true;
                Info nInfo = new Info(x-1,cnt+1);
                pq.add(nInfo);
            }

            if (x+1 <= MAX && !visited[next][x+1]) {
                visited[next][x+1] = true;
                Info nInfo = new Info(x+1,cnt+1);
                pq.add(nInfo);
            }

            if (x*2 <= MAX && !visited[next][x*2]) {
                visited[next][x*2] = true;
                Info nInfo = new Info(x*2,cnt+1);
                pq.add(nInfo);
            }
        }
        if (flag) System.out.println(-1);
    }
}
