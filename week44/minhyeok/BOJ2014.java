package week44.minhyeok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2014 {
    public static void main(String[] args) throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[] prime = new int[K];
        st = new StringTokenizer(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < K; i++) {
             prime[i] = Integer.parseInt(st.nextToken());
             pq.add(prime[i]);
        }
        
        int ans = 0;
        // 가장 작은 수를 뽑아 모든 소수와 곱해줌 -> N번째 가장 작은 수가 N번째 작은 수
        for (int i = 0; i < N; i++) {
            long min = pq.peek();
            for (int j = 0; j < N; j++) {
                long value = min * prime[j];
                if (value < Integer.MAX_VALUE) pq.add((int)value);
                if (min % prime[j] == 0) break; // 중복 제거
            }
            ans = pq.poll();
        }
        System.out.println(ans);
    }
}
