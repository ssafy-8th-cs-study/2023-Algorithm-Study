// BFS로 대륙을 먼저 가져오기
// 대륙을 가져오는데, 내륙은 가져오지 않기
// 대륙1 부터 2~3 비교하면서 거리 재기

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // BFS로 대륙 가져오기
        ArrayList<ArrayList<Point>> lists = new ArrayList<>();
        int islandNum = 2;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(map[i][j] != 1) continue;

                lists.add(getIsland(i, j, islandNum++));
            }
        }

        int min = Integer.MAX_VALUE;
        // 다리 놓기
        for(int i=0; i<lists.size(); i++){
            min = Math.min(connectBridge(lists.get(i), i+2), min);
        }

        System.out.println(min);
    }
    static int connectBridge(ArrayList<Point> listA, int islandIdx){
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        for(Point p : listA){
            q.add(p);
            visited[p.i][p.j] = true;
        }

        int dist = 0;
        while(!q.isEmpty()){
            int size = q.size();

            for(int s=0; s<size; s++){
                Point now = q.poll();
                //물만 연결
                for(int d=0; d<4; d++){
                    int nexti = now.i + di[d];
                    int nextj = now.j + dj[d];

                    if(nexti < 0 || nexti >= N || nextj < 0 || nextj >= N) continue;
                    if(map[nexti][nextj] == islandIdx) continue;
                    if(visited[nexti][nextj]) continue;

                    if(map[nexti][nextj] != 0) return now.d;

                    visited[nexti][nextj] = true;
                    q.add(new Point(nexti, nextj, now.d + 1));
                }
            }

            dist++;
        }
        return -1;
    }
    static int[] di = {1, -1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static ArrayList<Point> getIsland(int starti, int startj, int islandNum){
        ArrayList<Point> list = new ArrayList<>();

        Queue<Point> q = new LinkedList<>();
        map[starti][startj] = islandNum;
        q.add(new Point(starti, startj,0));

        while(!q.isEmpty()){
            Point now = q.poll();

            //물과 맞닿아 있는 부분
            for(int d=0; d<4; d++){
                int nexti = now.i + di[d];
                int nextj = now.j + dj[d];

                if(nexti < 0 || nexti >= N || nextj < 0 || nextj >= N) continue;
                if(map[nexti][nextj] == 0){
                    list.add(now);
                    break;
                }
            }

            for(int d=0; d<4; d++){
                int nexti = now.i + di[d];
                int nextj = now.j + dj[d];

                if(nexti < 0 || nexti >= N || nextj < 0 || nextj >= N) continue;
                if(map[nexti][nextj] == 0 || map[nexti][nextj] == islandNum) continue;

                map[nexti][nextj] = islandNum;
                q.add(new Point(nexti, nextj, 0));
            }
        }//end while

        return list;
    }
    static class Point {
        int i;
        int j;
        int d;

        public Point(int i, int j, int d) {
            this.i = i;
            this.j = j;
            this.d = d;
        }
    }
}