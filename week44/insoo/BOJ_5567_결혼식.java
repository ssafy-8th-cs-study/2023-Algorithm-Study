import java.io.*;
import java.util.*;
class BOJ_5567_결혼식 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		ArrayDeque<Integer>[] qList = new ArrayDeque[n+1]; // 인접 리스트
		for (int i = 0; i < n+1; qList[i++] = new ArrayDeque<>());
		
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			qList[from].add(to); // 양방향 연결
			qList[to].add(from);
		}
		
		boolean[] visit = new boolean[n+1]; // 방문 체크
		visit[1] = true; // 시작점
		
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.add(1);
		
		int cnt = 0;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			for(int next : qList[now]) { // 연결된 친구들
				if(!visit[next]) { // 방문 안 했다면
					visit[next] = true; // 방문 기록
					cnt++; // 카운트 + 1
					if(now==1) q.add(next); // 친구의 친구는 큐에 안 넣기
				}
			}
		}
		
		System.out.print(cnt);
	}
}