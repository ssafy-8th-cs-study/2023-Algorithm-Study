package week46.seoyoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/* BOJ1976 - 여행 가자 */
public class BOJ1976 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        ArrayList<Integer>[] adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<Integer>();
        }

        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                if (st.nextToken().equals("1") || i == j) {
                    adjList[i].add(j);
                    adjList[j].add(i);
                }
            }
        }

        int[] route = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            route[i] = Integer.parseInt(st.nextToken());
        }

        // BFS
        Queue<Trip> q = new ArrayDeque<Trip>();
        q.add(new Trip(route[0], 0, new boolean[N + 1]));

        while (!q.isEmpty()) {
            Trip trip = q.poll();

            if (trip.idx == M - 1) {
                // YES
                System.out.println("YES");
                return;
            }

            for (Integer link : adjList[trip.city]) {
                if (!trip.visit[link]) {
                    trip.visit[link] = true;

                    if (route[trip.idx + 1] == link) {
                        q.add(new Trip(link, trip.idx + 1, new boolean[N + 1]));
                    }
                    else {
                        q.add(new Trip(link, trip.idx, trip.visit));
                    }
                }
            }
        }
        System.out.println("NO");
        return;
    }

    static class Trip {
        int city;
        int idx;
        boolean[] visit;

        public Trip(int city, int idx, boolean[] visit) {
            this.city = city;
            this.idx = idx;
            this.visit = visit;
        }
    }
}
