import java.io.*;
import java.util.*;
class Main {
	static int N, M, answer;
	static int[] rDir = {0, 1, 0, -1};
	static int[] cDir = {1, 0, -1, 0};
	static char[][] map;
	static Info red, blue, whole;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		red = new Info(0,0);
		blue = new Info(0,0);
		whole = new Info(0,0);
		
		for (int r = 0; r < N; r++) {
			String str = br.readLine();	
			for (int c = 0; c < M; c++) {
				char ch = str.charAt(c);
				map[r][c] = ch == '#' ? '#' : '.'; // 벽 기록
				
				if(ch == 'R') red = new Info(r,c); // 빨간 구슬 위치
				else if(ch == 'B') blue = new Info(r,c); // 파란 구슬 위치
				else if(ch == 'O') whole = new Info(r,c); // 구멍 위치
			}
		}
		
		bfs();
		System.out.print(answer);
	}
	
	static void bfs() {
		boolean[][][][] visit = new boolean[N][M][N][M]; // 각 좌표별 빨간공 행/열, 파란공 행/열 방문 체크
		visit[red.r][red.c][blue.r][blue.c] = true;
		
		ArrayDeque<Info> q = new ArrayDeque<>(); // 큐
		q.add(new Info(red.r, red.c, blue.r, blue.c, 0));
		
		while(!q.isEmpty()) {
			Info el = q.poll();
			
			for (int d = 0; d < 4; d++) {
				if(checkMove(el, d)) { // 굴리기 테스트, 파란 구슬이 구멍에 빠지지 않는지
					Info next = move(el, d); // 굴리기
					if(next == null) { // 구멍 찾았을 때
						answer = 1;
						return;
					}
					if(el.cnt+1 == 10) continue; // 10번 초과 방지
					
					if(!visit[next.rRed][next.cRed][next.rBlue][next.cBlue]) {
						visit[next.rRed][next.cRed][next.rBlue][next.cBlue] = true;
						next.cnt++;
						q.add(next);
					}
				}
			}
		}
	}
	
	static boolean checkMove(Info el, int d) {
		int rBlue = el.rBlue + rDir[d];
		int cBlue = el.cBlue + cDir[d];
		
		while(map[rBlue][cBlue] != '#') {
			if(rBlue == whole.r && cBlue == whole.c) return false;
			rBlue += rDir[d];
			cBlue += cDir[d];
		}
		
		return true;
	}
	
	static Info move(Info el, int d) {
		Info next = new Info(el.rRed, el.cRed, el.rBlue, el.cBlue, el.cnt);
		
		next.rRed += rDir[d];
		next.cRed += cDir[d];
		
		boolean find = false;
		while(map[next.rRed][next.cRed] != '#') {
			if(next.rRed == el.rBlue && next.cRed == el.cBlue) find = true;
			if(next.rRed == whole.r && next.cRed == whole.c) return null; // find whole
			next.rRed += rDir[d];
			next.cRed += cDir[d];
		}
		if(find) {
			next.rRed -= rDir[d];
			next.cRed -= cDir[d];
		}
		next.rRed -= rDir[d];
		next.cRed -= cDir[d];
		
		next.rBlue += rDir[d];
		next.cBlue += cDir[d];
		find = false;
		while(map[next.rBlue][next.cBlue] != '#') {
			if(next.rBlue == el.rRed && next.cBlue == el.cRed) find = true;
			next.rBlue += rDir[d];
			next.cBlue += cDir[d];
		}
		if(find) {
			next.rBlue -= rDir[d];
			next.cBlue -= cDir[d];
		}
		next.rBlue -= rDir[d];
		next.cBlue -= cDir[d];

		return next;
	}
	
	static class Info {
		int r, c;
		int rRed, cRed, rBlue, cBlue, cnt;
		Info(int r, int c) {
			this.r = r;
			this.c = c;
		}
		Info(int rRed, int cRed, int rBlue, int cBlue, int cnt) {
			this.rRed = rRed;
			this.cRed = cRed;
			this.rBlue = rBlue;
			this.cBlue = cBlue;
			this.cnt = cnt;
		}
	}
}
