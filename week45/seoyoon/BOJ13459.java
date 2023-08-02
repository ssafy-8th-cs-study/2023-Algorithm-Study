package week45.seoyoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/* BOJ13459 - 구슬 탈출 */
public class BOJ13459 {

    static char[][] map;
    static boolean[][] visit;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visit = new boolean[N][M];
        Balls initBalls = new Balls(0, 0, 0, 0);

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'R') {
                    initBalls.Ry = i;
                    initBalls.Rx = j;
                    map[i][j] = '.';
                }
                else if (map[i][j] == 'B') {
                    initBalls.By = i;
                    initBalls.Bx = j;
                    map[i][j] = '.';
                }
            }
        }

        Queue<Balls> queue = new ArrayDeque<Balls>();
        queue.add(initBalls);

        int cnt = 0;
        boolean blue = false, red = false, success = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            cnt++;

            for (int s = 0; s < size; s++) {
                Balls ball = queue.poll();

                for (int d = 0; d < 4; d++) {
                    Balls newPos;

                    if (d == 0) {	// 상
                        if (ball.Ry > ball.By) {
                            newPos = move(d, ball, 'b');
                            newPos = move(d, newPos, 'r');
                        }
                        else {
                            newPos = move(d, ball, 'r');
                            newPos = move(d, newPos, 'b');
                        }
                    }
                    else if (d == 1) { // 우
                        if (ball.Rx > ball.Bx) {
                            newPos = move(d, ball, 'r');
                            newPos = move(d, newPos, 'b');
                        }
                        else {
                            newPos = move(d, ball, 'b');
                            newPos = move(d, newPos, 'r');
                        }
                    }
                    else if (d == 2) { // 하
                        if (ball.Ry > ball.By) {
                            newPos = move(d, ball, 'r');
                            newPos = move(d, newPos, 'b');
                        }
                        else {
                            newPos = move(d, ball, 'b');
                            newPos = move(d, newPos, 'r');
                        }
                    }
                    else { // 좌
                        if (ball.Rx > ball.Bx) {
                            newPos = move(d, ball, 'b');
                            newPos  = move(d, newPos, 'r');
                        }
                        else {
                            newPos = move(d, ball, 'r');
                            newPos = move(d, newPos, 'b');
                        }
                    }

                    if (newPos.Bx == 0 || cnt > 10) {
                        blue = true;
                    }
                    else if (newPos.Rx == 0 && cnt <= 10) {
                        red = true;
                        System.out.println("1");
                        return;
                    }
                    else if (newPos.Ry != ball.Ry || newPos.Rx != ball.Rx || newPos.By != ball.By || newPos.Bx != ball.Bx) {
                        queue.add(newPos);
                    }
                }
            }

        }
    }

    static Balls move(int dir, Balls curPos, char color) {
        int moveY, moveX;

        if (color == 'r') {
            moveY = curPos.Ry;
            moveX = curPos.Rx;
        }
        else {
            moveY = curPos.By;
            moveX = curPos.Bx;
        }

        while(true) {
            if (map[moveY + dy[dir]][moveX + dx[dir]] == 'O') {
                return color == 'r' ? new Balls(0, 0, curPos.By, curPos.Bx) : new Balls(curPos.Ry, curPos.Rx, 0, 0);
            }
            if (map[moveY + dy[dir]][moveX + dx[dir]] != '.' || (color == 'r' && visit[moveY + dy[dir]][moveX + dx[dir]])
                    || (color == 'r' && moveY + dy[dir] == curPos.By && moveX + dx[dir] == curPos.Bx) || (color == 'b' && moveY + dy[dir] == curPos.Ry && moveX + dx[dir] == curPos.Rx)) {
                return color == 'r' ? new Balls(moveY, moveX, curPos.By, curPos.Bx) : new Balls(curPos.Ry, curPos.Rx, moveY, moveX);
            }
            moveY += dy[dir];
            moveX += dx[dir];
            if (color == 'r') {
                visit[moveY][moveX] = true;
            }
        }
    }

    static class Balls {
        int Ry, Rx, By, Bx;

        public Balls(int ry, int rx, int by, int bx) {
            Ry = ry;
            Rx = rx;
            By = by;
            Bx = bx;
        }
    }
}
