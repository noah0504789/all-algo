import java.io.*;
import java.util.*;

public class Main {
    private static Meeting[] list;
    private static int n;

    public static void main(String... args) throws IOException {
        n = readInt();

        list = new Meeting[n];
        for (int i = 0; i < n; i++) list[i] = new Meeting(readInt(), readInt());

        Arrays.sort(list);

        int cnt = 0, minIdx = 0, maxEnd = 0;

        while (minIdx < n) {
            if (list[minIdx].start >= maxEnd) {
                maxEnd = list[minIdx].end;
                cnt++;
            }

            minIdx++;
        }

        System.out.print(cnt);
    }

    private static int readInt() throws IOException {
        int result = 0, c = System.in.read();

        while (c <= ' ') c = System.in.read();

        while (c >= '0' && c <= '9') {
            result *= 10;
            result += c - '0';
            c = System.in.read();
        }

        return result;
    }
    
    static class Meeting implements Comparable<Meeting> {
        final int start, end;

        Meeting (int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(Meeting other) {
            return this.end == other.end ? Integer.compare(this.start, other.start) : Integer.compare(this.end, other.end);
        }
    }
}
