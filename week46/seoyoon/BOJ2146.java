package week46.seoyoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/* BOJ2146 - 다리 만들기 */
public class BOJ2146 {
    static int N, min;
    static int[][] map;
    static boolean[][] visit;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        // 섬 라벨링
        int islandIdx = 1;
        visit = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!visit[i][j] && map[i][j] == 1) {
                    labelingIsland(i, j, islandIdx);
                    islandIdx++;
                }
            }
        }

        // 최단 길이 다리 구하기
        min = N * N;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] != 0) {
                    findShortestWay(i, j, map[i][j]);
                }
            }
        }
        System.out.println(min);
    }

    public static void labelingIsland(int y, int x, int islandIdx) {
        Queue<Point> q = new ArrayDeque<Point>();
        q.add(new Point(y, x));

        map[y][x] = islandIdx;
        visit[y][x] = true;

        while (!q.isEmpty()) {
            Point pos = q.poll();

            for(int d = 0; d < 4; d++) {
                if (0 <= pos.y + dy[d] && pos.y + dy[d] < N && 0 <= pos.x + dx[d] && pos.x + dx[d] < N && !visit[pos.y + dy[d]][pos.x + dx[d]] && map[pos.y + dy[d]][pos.x + dx[d]] == 1) {
                    q.add(new Point(pos.y + dy[d], pos.x + dx[d]));
                    map[pos.y + dy[d]][pos.x + dx[d]] = islandIdx;
                    visit[pos.y + dy[d]][pos.x + dx[d]] = true;
                }
            }
        }
    }

    public static void findShortestWay(int y, int x, int islandIdx) {
        boolean[][] curVisit = new boolean[N][N];
        curVisit[y][x] = true;

        Queue<Point> q = new ArrayDeque<Point>();
        q.add(new Point(y, x));

        int cnt = 0;
        while (!q.isEmpty()) {
            int size = q.size();

            for (int s = 0; s < size; s++) {
                Point pos = q.poll();

                for(int d = 0; d < 4; d++) {
                    if (0 <= pos.y + dy[d] && pos.y + dy[d] < N && 0 <= pos.x + dx[d] && pos.x + dx[d] < N && !curVisit[pos.y + dy[d]][pos.x + dx[d]] && map[pos.y + dy[d]][pos.x + dx[d]] != islandIdx) {
                        if (map[pos.y + dy[d]][pos.x + dx[d]] != 0 && map[pos.y + dy[d]][pos.x + dx[d]] != islandIdx) {
                            min = Math.min(min, cnt);
                            return;
                        }
                        q.add(new Point(pos.y + dy[d], pos.x + dx[d]));
                        curVisit[pos.y + dy[d]][pos.x + dx[d]] = true;
                    }
                }
            }
            cnt++;
        }
    }

    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
