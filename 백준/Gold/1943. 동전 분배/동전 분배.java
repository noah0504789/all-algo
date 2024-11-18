import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br;
    private static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int tc = 3; // 세 번의 테스트 케이스
        for (int i = 0; i < tc; i++) {
            int n = Integer.parseInt(br.readLine());
            List<int[]> coins = new ArrayList<>();
            int totalSum = 0;

            // 동전 정보 읽기 및 총합 계산
            for (int j = 0; j < n; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int type = Integer.parseInt(st.nextToken());
                int count = Integer.parseInt(st.nextToken());
                coins.add(new int[]{type, count});
                totalSum += type * count;
            }

            // 총합이 홀수면 반으로 나눌 수 없다
            if (totalSum % 2 != 0) {
                bw.write("0\n");
                continue;
            }

            int target = totalSum / 2;
            boolean[] dp = new boolean[target + 1];
            dp[0] = true; // 0은 항상 만들 수 있음

            // 각 동전 타입과 개수로 DP 업데이트
            for (int[] coin : coins) {
                int value = coin[0];
                int count = coin[1];

                for (int j = target; j >= value; j--) {
                    for (int k = 1; k <= count && k * value <= j; k++) {
                        if (dp[j - k * value]) {
                            dp[j] = true;
                        }
                    }
                }
            }

            // 결과 출력
            bw.write(dp[target] ? "1\n" : "0\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
