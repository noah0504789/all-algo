import java.io.*;

public class Main {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static String input;

    private static int[][] dpMin, dpMax;
    private static int n, opnCnt;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        input = br.readLine();

        opnCnt = n/2+1;

        dpMin = new int[opnCnt][opnCnt];
        dpMax = new int[opnCnt][opnCnt];

        for (int i = 0; i < opnCnt; i++) dpMin[i][i] = dpMax[i][i] = input.charAt(i*2) - '0';

        for (int size = 2; size <= opnCnt; size++) {
            for (int i = 0; i <= opnCnt - size; i++) {
                int j = i+size-1;

                dpMin[i][j] = Integer.MAX_VALUE;
                dpMax[i][j] = Integer.MIN_VALUE;

                for (int k = i; k < j; k++) {
                    if (input.charAt(k*2+1) == '*') {
                        dpMin[i][j] = Math.min(dpMin[i][j], dpMin[i][k] * dpMin[k+1][j]);
                        dpMin[i][j] = Math.min(dpMin[i][j], dpMin[i][k] * dpMax[k+1][j]);
                        dpMin[i][j] = Math.min(dpMin[i][j], dpMax[i][k] * dpMin[k+1][j]);
                        dpMin[i][j] = Math.min(dpMin[i][j], dpMax[i][k] * dpMax[k+1][j]);

                        dpMax[i][j] = Math.max(dpMax[i][j], dpMin[i][k] * dpMin[k+1][j]);
                        dpMax[i][j] = Math.max(dpMax[i][j], dpMin[i][k] * dpMax[k+1][j]);
                        dpMax[i][j] = Math.max(dpMax[i][j], dpMax[i][k] * dpMin[k+1][j]);
                        dpMax[i][j] = Math.max(dpMax[i][j], dpMax[i][k] * dpMax[k+1][j]);
                    } else if (input.charAt(k*2+1) == '+') {
                        dpMin[i][j] = Math.min(dpMin[i][j], dpMin[i][k] + dpMin[k+1][j]);
                        dpMax[i][j] = Math.max(dpMax[i][j], dpMax[i][k] + dpMax[k+1][j]);
                    } else if (input.charAt(k*2+1) == '-') {
                        dpMin[i][j] = Math.min(dpMin[i][j], dpMin[i][k] - dpMax[k+1][j]);
                        dpMax[i][j] = Math.max(dpMax[i][j], dpMax[i][k] - dpMin[k+1][j]);
                    }
                }
            }
        }

        bw.write(dpMax[0][opnCnt -1]+"");
        bw.flush();
    }
}
