import java.io.*;
import java.util.*;
class BOJ_2014_소수의곱 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[K];
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			pq.add(arr[i]);
		}
		
		int idx = 0;
		
		while(!pq.isEmpty()) {
			int now = pq.poll(); // 가장 작은 수 뽑기
			
			if(now == idx) continue; // 기록해놓고 중복 처리
			idx = now;
			
			if(--N == 0) {
				System.out.print(now);
				return;
			}
			
			for(int a : arr) { // K개 소수 다 곱해서 넣어주기
				if(a * now < 0 || Integer.MAX_VALUE < a * now) continue;
				
				if(a * now <= Integer.MAX_VALUE) pq.add(a * now);
			}
		}
	}
}