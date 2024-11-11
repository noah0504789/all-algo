import java.io.*;
import java.util.*;

public class Main {
    private static StringBuilder sb;

    private static PriorityQueue<Long> files;
    private static long ans, sum;
    private static int tc, k;

    public static void main(String... args) throws IOException {
        sb = new StringBuilder();

        tc = (int) readLong();

        files = new PriorityQueue<>();

        for (int i = 0; i < tc; i++) {
            k = (int) readLong();

            files.clear();

            for (int j = 0; j < k; j++) files.offer(readLong());

            ans = sum = 0;

            while (files.size() > 1) {
                ans += sum = files.poll() + files.poll();
                files.offer(sum);
            }

            sb.append(ans).append("\n");
        }

        System.out.print(sb);
    }

    public static long readLong() throws IOException {
        long r = 0, c = System.in.read();

        while (c <= ' ') c = System.in.read();
        while (c >= '0' && c <= '9') {
            r *= 10;
            r += c - '0';
            c = System.in.read();
        }

        return r;
    }
}

        // 소설
        // - 여러 chapter로 구성됨
        // - 각 chapter당 여러 file로 구성됨
        // -> file을 합쳐 chapter를 완성해야 함
        
        // file merge
        // - 2개씩 가능
        // - 합치는 비용 제공됨
        
        // c1, c2, c3, c4
// 크기  // 40, 30, 30, 50
        // 