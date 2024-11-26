import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        long[] num = new long[N+1];
        long[] sum = new long[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=1;i<=N;i++){
            num[i] = Long.parseLong(st.nextToken());
            sum[i] = sum[i-1] + num[i];
        }

        long[][] dp = new long[N+1][4];
        long count = 0;

        if(sum[N] %4 ==0){
            long target = sum[N]/4;

            dp[0][0] = 1;   //dp[1][1]이 가능한 경우을 위한 값

            for(int i=1;i<=N;i++){
                dp[i][0] = 1;   //dp[n][1]이 가능한 경우를 위한 값
                for(int j=1;j<=3;j++){
                    dp[i][j] = dp[i-1][j];  //이전까지 가능했던 경우의 수를 가져옴

                    if(sum[i] == target*j){ //현재까지의 누적합으로 부분 수열의 합이 가능하면
                        //이전 크기의 부분 수열이 가능한 만큼 추가로 되는 경우의 수이다.
                        dp[i][j] += dp[i-1][j-1];
                    }
                }
            }

            count = dp[N-1][3];
        }

        System.out.println(count);
    }
}
