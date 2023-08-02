package week45.minhyeok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13459_구슬_탈출 {
    static int N, M;
    static char[][] map;
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    static int ry, rx;
    static int by, bx;
    static int ey, ex;
    static boolean ans;
    static int min = 11;
    static boolean[][][][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M][N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                // find red, blue, exit
                if (map[i][j] == 'R') {
                    ry = i;
                    rx = j;
                } else if (map[i][j] == 'B') {
                    by = i;
                    bx = j;
                } else if (map[i][j] == 'O') {
                    ey = i;
                    ex = j;
                }
            }
        }
        visited[ry][rx][by][bx] = true;
        move(0);
        System.out.println(ans ? 1 : 0);
    }

    private static void move(int cnt) {
        if (cnt >= min) return;
        if (cnt == 10) return;

        char[][] temp = new char[N][M];
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                temp[y][x] = map[y][x];
            }
        }
        int cry = ry;
        int crx = rx;
        int cby = by;
        int cbx = bx;

        for (int d = 0; d < 4; d++) {
            if (d == 0) up();
            else if (d == 1) down();
            else if (d == 2) left();
            else right();
            if (isRedEnter() && !isBlueEnter()) {
                ans = true;
                min = Math.min(min,cnt+1);
            }
            if (!isBlueEnter() && !visited[ry][rx][by][bx]) {
                visited[ry][rx][by][bx] = true;
                move(cnt+1);
                visited[ry][rx][by][bx] = false;
            }
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    map[y][x] = temp[y][x];
                }
            }
            ry = cry;
            rx = crx;
            by = cby;
            bx = cbx;
        }
    }

    private static boolean isBlueEnter() {
        if (by == ey && bx == ex) {
            return true;
        }
        return false;
    }

    private static boolean isRedEnter() {
        if (ry == ey && rx == ex) {
            return true;
        }
        return false;
    }

    private static void right() {
        if (rx >= bx) {
            moveMarble(ry,rx,3,'R');
            moveMarble(by,bx,3,'B');
        } else {
            moveMarble(by,bx,3,'B');
            moveMarble(ry,rx,3,'R');
        }
    }

    private static void left() {
        if (bx >= rx) {
            moveMarble(ry,rx,2,'R');
            moveMarble(by,bx,2,'B');
        } else {
            moveMarble(by,bx,2,'B');
            moveMarble(ry,rx,2,'R');
        }
    }

    private static void down() {
        if (ry >= by) {
            moveMarble(ry,rx,1,'R');
            moveMarble(by,bx,1,'B');
        } else {
            moveMarble(by,bx,1,'B');
            moveMarble(ry,rx,1,'R');
        }
    }

    private static void up() {
        if (by >= ry) {
            moveMarble(ry,rx,0,'R');
            moveMarble(by,bx,0,'B');
        } else {
            moveMarble(by,bx,0,'B');
            moveMarble(ry,rx,0,'R');
        }
    }

    private static void moveMarble(int y, int x, int d, char m) {
        while (true) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            if (map[ny][nx] == '.' || map[ny][nx] == 'O') {
                map[y][x] = '.';
                y = ny;
                x = nx;
                if (map[y][x] == '.') map[y][x] = m;
                else break;
            } else break;
        }

        if (m == 'R') {
            ry = y;
            rx = x;
        } else {
            by = y;
            bx = x;
        }
    }

}
