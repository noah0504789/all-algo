
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author noah kim
 * @date 2024-02-19
 * @link https://www.acmicpc.net/problem/1992
 * @requirement 0과 1로 표현된 영상을 압축하여 표현하라.
 * @keyword
    [영상]
    - 크기: N*N
    - 칸: 0 or 1
    [압축]
    - 순서: 왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래
    - 방식: 재귀적으로 전체 압축
 * @input
    - N: 영상 크기(2의 제곱수. 1<=N<=64)
    - 영상 정보
 * @output
 * @time_complex
 * @perf
 */
public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuffer sb = new StringBuffer();
    private static int[][] movies, dp;
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        movies = new int[N][N];
        dp = new int[N][N];
        for (int r = 0; r < N; r++) movies[r] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();

        quad(0,0, N);

        System.out.println(sb);

        br.close();
    }

    private static void quad(int r, int c, int size) {
        if (isSame(r, c, size)) {
            sb.append(movies[r][c]);
            return;
        }

        sb.append("(");

        int newSize = size / 2;
        
        quad(r,c, newSize);
        quad(r,c+ newSize, newSize);
        quad(r+ newSize,c, newSize);
        quad(r+ newSize,c+ newSize, newSize);

        sb.append(")");
    }

    private static boolean isSame(int r, int c, int size) {
        int val = movies[r][c];

        for (int i = r; i < r+size; i++) {
            for (int j = c; j < c+size; j++) {
                if (movies[i][j] != val) return false;
            }
        }

        return true;
    }
}