import java.io.*;
import java.util.*;
import java.math.*;

public class Main {        
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static BigDecimal a;
    private static int b;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());

        a = new BigDecimal(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        bw.write(a.pow(b).toPlainString());

        bw.flush();
    }
}