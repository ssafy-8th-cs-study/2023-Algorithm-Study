package week45.minhyeok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ21758_꿀_따기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int max1 = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max1 += arr[i];
        }
        int max2 = max1;
        int max3 = max1;

        max1 -= arr[0];
        max1 -= arr[1];
        max1 *= 2;
        int temp = max1;
        for (int i = 2; i < N; i++) {
            temp = temp + arr[i-1] - arr[i]*2;
            if (temp > max1) {
                max1 = temp;
            }
        }

        max2 -= arr[N-1];
        max2 -= arr[N-2];
        max2 *= 2;
        temp = max2;
        for (int i = N-3; i >= 0; i--) {
            temp = temp + arr[i+1] - arr[i] * 2;;
            if (temp > max2) {
                max2 = temp;
            }
        }

        max3 -= arr[0];
        max3 -= arr[N-1];
        int m = 0;
        for (int i = 1; i < N-1; i++) {
            if (arr[i] > m) m = arr[i];
        }
        max3 += m;

        max1 = Math.max((Math.max(max1,max2)), max3);

        System.out.println(max1);
    }
}
