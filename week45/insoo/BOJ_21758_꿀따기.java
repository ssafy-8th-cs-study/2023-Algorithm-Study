import java.io.*;
import java.util.*;
public class Main {
	static int N;
	static int[] arr, accArr;
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        arr = new int[N];
        accArr = new int[N+1];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
        
        getAccSum(); // 누적합 구하기
        
        int max = 0;
        
        for (int i = 1; i < N-1; i++) { // 1 벌, N-1 벌통, 1<벌<N-1
			int A = getSum(1, N-1, i);
			int B = getSum(i+1, N-1, -1);
			max = Math.max(max, A+B);
		}
        
        for (int i = 1; i < N-1; i++) { // 1 벌통, N-1 벌, 1<벌<N-1
        	int A = getSum(0, N-2, i);
        	int B = getSum(0, i-1, -1);
        	max = Math.max(max, A+B);
        }
        
        for (int i = 1; i < N-1; i++) { // 1 벌, N-1 벌, 1<벌통<N-1
        	int A = getSum(1, i, -1);
        	int B = getSum(i, N-2, -1);
        	max = Math.max(max, A+B);
        }
        
        System.out.print(max);
    }
	
	static void getAccSum() {
		int acc = 0;
		
		for (int i = 0; i < N; i++) {
			acc += arr[i];
			accArr[i] = acc;
		}
	}
	
	static int getSum(int from, int to, int another) {
		int sum = 0;
		
		sum = accArr[to] - accArr[from-1 < 0 ? N : from-1];
		
		if(another != -1) {
			sum -= arr[another];
		}
		
		return sum;
	}
}
