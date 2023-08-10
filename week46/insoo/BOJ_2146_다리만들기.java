package week46.insoo;
import java.io.*;
import java.util.*;
class BOJ_2146_다리만들기 {
	static int N, bridgeLen = Integer.MAX_VALUE;
	static int[] rDir = {0, 1, 0, -1};
	static int[] cDir = {1, 0, -1, 0};
	static int[][] map, bridgeNumMap, bridgeLenMap;
	static boolean[][] visit;
	static ArrayDeque<Info> edgeQ = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		drawIsland(); // 섬 구분 BFS
		
		drawBridge(); // 섬 가장자리 BFS
		
		System.out.print(bridgeLen);
	}
	
	static void drawIsland() {
		ArrayDeque<Info> q = new ArrayDeque<>(); // islandQ
		visit = new boolean[N][N];
		bridgeNumMap = new int[N][N]; // 다리 구분 기록 2차원 배열
		bridgeLenMap = new int[N][N]; // 다리 길이 기록 2차원 배열
		
		int islandNum = 1;
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if(map[r][c] == 1 && !visit[r][c]) {
					q.add(new Info(r, c));
					visit[r][c] = true;
					
					while(!q.isEmpty()) {
						Info now = q.poll();
						map[now.r][now.c] = islandNum; 
						
						for (int d = 0; d < 4; d++) {
							int nr = now.r + rDir[d];
							int nc = now.c + cDir[d];
							
							if(0<=nr && nr<N && 0<=nc && nc<N && !visit[nr][nc]) {
								if(map[nr][nc] == 1) { // 섬 구분 BFS
									q.add(new Info(nr, nc));
									visit[nr][nc] = true;
								}
								else {
									edgeQ.add(new Info(nr, nc, islandNum, 1)); // 섬 가장자리 BFS위해 저장
									visit[nr][nc] = true;
									bridgeNumMap[nr][nc] = islandNum;
									bridgeLenMap[nr][nc] = 1;
								}
							}
						}
					}
					
					islandNum++;
				}
			}
		}
	}
	
	static void drawBridge() {
		while(!edgeQ.isEmpty()) {
			Info now = edgeQ.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = now.r + rDir[d];
				int nc = now.c + cDir[d];
				
				if(0<=nr && nr<N && 0<=nc && nc<N) {
					if(map[nr][nc] != 0 && map[nr][nc] != now.num) { // 다른 섬 만나면 길이 반환
						bridgeLen = now.cnt;
						return;
					}
					
					if(map[nr][nc] == 0) {
						if(visit[nr][nc] && bridgeNumMap[nr][nc] != now.num) { // 다리 연결
							bridgeLen = Math.min(bridgeLen, now.cnt + bridgeLenMap[nr][nc]);
						}
						
						if(bridgeLen != Integer.MAX_VALUE) continue; // 다리 연결 시 더 이상 늘리기 방지
						
						if(!visit[nr][nc]) {
							edgeQ.add(new Info(nr, nc, now.num, now.cnt+1)); // 다리 늘리며 BFS
							bridgeLenMap[nr][nc] = now.cnt+1;
							bridgeNumMap[nr][nc] = now.num;
							visit[nr][nc] = true;
						}
					}
				}
			}
		}
	}
	
	static class Info {
		int r, c, num, cnt;
		Info(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		Info(int r, int c, int num, int cnt) {
			this.r = r;
			this.c = c;
			this.num = num;
			this.cnt = cnt;
		}
	}
}