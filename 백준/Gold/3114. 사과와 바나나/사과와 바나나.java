import java.io.*;
import java.util.*;

public class Main {
    
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static String[] input;
    private static int n, m;
    private static int[][] banana, apple, dp;
    
    public static void main(String... args) throws IOException {
        input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        
        // 여유 인덱스 포함 (j+1, m-j+2까지 접근하므로 m+2 정도는 있어야 안전)
        banana = new int[n + 2][m + 3];
        apple  = new int[n + 2][m + 3];
        
        // 입력: 1-based 인덱스로 맞추기
        for (int i = 1; i <= n; i++) {
            input = br.readLine().split(" ");
            
            for (int j = 1; j <= m; j++) {
                String val = input[j - 1];
                int cnt = Integer.parseInt(val.substring(1)); // A10, B3 같은 것도 안전하게 처리
                
                if (val.charAt(0) == 'B') banana[i][j] = cnt;
                else apple[i][j] = cnt;
            }
        }
               
        // apple: 왼쪽 -> 오른쪽 prefix sum
        // banana: 오른쪽 -> 왼쪽 suffix sum (wookje 코드와 동일)
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                apple[i][j] += apple[i][j - 1];
                banana[i][m - j + 1] += banana[i][m - j + 2];
            }            
        }
        
        dp = new int[n + 2][m + 3];
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 1행 또는 1열은 예외 처리 (wookje: if (i == 1 || j == 1))
                if (i == 1 || j == 1) {
                    dp[i][j] = dp[i - 1][j] + banana[i][j + 1];
                    continue;
                }
                
                // 위/대각선에서 오는 경우: 새 열 j를 처음 "정식으로" 확정
                int up   = dp[i - 1][j]     + apple[i][j - 1] + banana[i][j + 1];
                int diag = dp[i - 1][j - 1] + apple[i][j - 1] + banana[i][j + 1];
                
                // 왼쪽에서 오는 경우: 같은 행에서 열만 j-1 -> j로 옮기는 것이라
                // 이 열에서 위쪽 바나나 기여분을 b[i][j] -> b[i][j+1]로 수정
                int left = dp[i][j - 1] - banana[i][j] + banana[i][j + 1];
                
                dp[i][j] = Math.max(up, Math.max(diag, left));
            }
        }
        
        // 정답은 (n, m)
        System.out.print(dp[n][m]);
    }
}
