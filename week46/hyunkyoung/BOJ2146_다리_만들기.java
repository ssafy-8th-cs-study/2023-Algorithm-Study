import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2146_다리_만들기 {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); //지도의 크기
        map = new int[N][N]; //지도

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //섬 구분
        int num = 0;
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !visited[i][j]) mark(i, j, ++num);
                if (result == 1) {
                    System.out.println(result);
                    return;
                }
            }
        }

        //다리 잇기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] < 0) check(i, j);
            }
        }

        System.out.println(result);
    }

    static void mark(int i, int j, int v) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{i, j});
        visited[i][j] = true;
        map[i][j] = v;

        while (!queue.isEmpty()) {
            int[] p = queue.poll();

            for (int d = 0; d < 4; d++) {
                int x = p[0] + dx[d];
                int y = p[1] + dy[d];

                if (0 <= x && x < N && 0 <= y && y < N) {
                    if (visited[x][y]) continue;

                    if (map[x][y] < 0 && map[x][y] != -v) {
                        result = 1;
                        return;
                    }

                    if (map[x][y] == 1) {
                        queue.add(new int[]{x, y});
                        visited[x][y] = true;
                        map[x][y] = v;
                    }

                    if (map[x][y] == 0) map[x][y] = -v;
                }
            }
        }
    }

    static void check(int i, int j) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        queue.add(new int[]{i, j});
        visited[i][j] = true;
        int dist = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                int[] p = queue.poll();

                if (map[p[0]][p[1]] < 0 && map[p[0]][p[1]] != map[i][j]) {
                    result = Math.min(result, dist);
                    return;
                }

                for (int d = 0; d < 4; d++) {
                    int x = p[0] + dx[d];
                    int y = p[1] + dy[d];

                    if (0 <= x && x < N && 0 <= y && y < N) {
                        if (map[x][y] <= 0 && map[x][y] != map[i][j] && !visited[x][y]) {
                            queue.add(new int[]{x, y});
                            visited[x][y] = true;
                        }
                    }
                }
            }

            dist++;
        }
    }
}
