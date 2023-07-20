package week43.minhyeok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ13913 {

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


        int MAX = 100000;
        boolean[] visited = new boolean[MAX+1];
        int[] nextArr = new int[MAX+1];
        nextArr[N] = -1;
        visited[N] = true;
        PriorityQueue<Info> pq = new PriorityQueue<>((o1,o2)->o1.cnt - o2.cnt);
        Info in = new Info(N,0);
        pq.add(in);
        while (!pq.isEmpty()) {
            Info info = pq.poll();
            int x = info.x;
            int cnt = info.cnt;
            if (x == K) {
                StringBuilder sb = new StringBuilder();
                sb.append(cnt).append("\n");
                int next = x;
                int[] arr = new int[cnt+1];
                for (int i = cnt; i >= 0; i--) {
                    arr[i] = next;
                    next = nextArr[next];
                }
                for (int a : arr) sb.append(a).append(" ");
                System.out.println(sb);
                break;
            }

            if (x-1 >= 0 && !visited[x-1]) {
                visited[x-1] = true;
                Info nInfo = new Info(x-1,cnt+1);
                nextArr[x-1] = x;
                pq.add(nInfo);
            }

            if (x+1 <= MAX && !visited[x+1]) {
                visited[x+1] = true;
                Info nInfo = new Info(x+1,cnt+1);
                nextArr[x+1] = x;
                pq.add(nInfo);
            }

            if (x*2 <= MAX && !visited[x*2]) {
                visited[x*2] = true;
                Info nInfo = new Info(x*2,cnt+1);
                nextArr[x*2] = x;
                pq.add(nInfo);
            }
        }


    }

}
