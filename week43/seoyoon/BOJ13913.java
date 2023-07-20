package week43.seoyoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/* BOJ 13913 - 숨바꼭질 4 */
public class BOJ13913 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // BFS
        boolean[] visited = new boolean[100001];
        int[] prev = new int[100001];

        Queue<Route> q = new LinkedList<Route>();
        q.add(new Route(N, 0));
        visited[N] = true;

        while (!q.isEmpty()) {
            Route rt = q.poll();

            if (rt.idx == K) {
                System.out.println(rt.time);

                Stack<Integer> stack = new Stack<Integer>();
                int cur = rt.idx;
                while (cur != N) {
                    stack.push(cur);
                    cur = prev[cur];
                }
                stack.push(N);

                StringBuilder sb = new StringBuilder();
                while (!stack.isEmpty()) {
                    sb.append(stack.pop() + " ");
                }
                System.out.println(sb.toString());

                return;
            }

            if (rt.idx - 1 >= 0 && !visited[rt.idx - 1]) {
                q.add(new Route(rt.idx - 1, rt.time + 1));
                visited[rt.idx - 1] = true;
                prev[rt.idx - 1] = rt.idx;
            }

            if (rt.idx + 1 <= 100000 && !visited[rt.idx + 1]) {
                q.add(new Route(rt.idx + 1, rt.time + 1));
                visited[rt.idx + 1] = true;
                prev[rt.idx + 1] = rt.idx;
            }

            if (rt.idx * 2 <= 100000 && !visited[rt.idx * 2]) {
                q.add(new Route(rt.idx * 2, rt.time + 1));
                visited[rt.idx * 2] = true;
                prev[rt.idx * 2] = rt.idx;
            }
        }
    }

    static class Route {
        int idx;
        int time;

        public Route(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }
    }
}
