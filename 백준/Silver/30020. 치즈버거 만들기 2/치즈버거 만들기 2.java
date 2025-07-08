import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int pattyCnt, cheeseCnt, hamburgerCnt;

    public static void main(String... args) throws IOException {
        sb = new StringBuilder();

        pattyCnt = readInt();
        cheeseCnt = readInt();
        hamburgerCnt = pattyCnt - cheeseCnt;

        if (hamburgerCnt <= 0 || cheeseCnt < hamburgerCnt) {
            System.out.print("NO");
            return;
        }

        sb.append("YES").append("\n");
        sb.append(hamburgerCnt+"").append("\n");

        int[] cheeseArr = new int[hamburgerCnt];
        Arrays.fill(cheeseArr, 1);

        int remainCheese = cheeseCnt - hamburgerCnt;

        for (int i = 0; i < hamburgerCnt && remainCheese > 0; i++) {
            int add = Math.min(remainCheese, 100); // 적당히 한 번에 많이 줘도 됨(여기서는 단순히 remain 모두 줘도 됨)

            cheeseArr[i] += add;
            remainCheese -= add;
        }

        for (int cheese : cheeseArr) {
            for (int i = 0; i < cheese; i++) sb.append("ab");
            sb.append("a").append("\n");
        }

        System.out.print(sb);
    }

    public static int readInt() throws IOException {
        int r = 0, c = System.in.read();

        while (c <= ' ') c = System.in.read();
        while (c >= '0' && c <= '9') {
            r *= 10;
            r += c - '0';
            c = System.in.read();
        }

        return r;
    }
}
