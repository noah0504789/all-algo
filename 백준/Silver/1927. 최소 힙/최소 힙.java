import java.io.*;
import java.util.*;

public class Main {
    private static StringBuilder sb;

    private static MinHeap minHeap;
    private static int n, num;

    public static void main(String... args) throws IOException {
        sb = new StringBuilder();

        n = readInt();        
        minHeap = new MinHeap(n);
        
        for (int i = 0; i < n; i++) {
            num = readInt();
            if (num == 0) {
                sb.append(minHeap.pop()).append("\n");
                continue;
            }
            
            minHeap.push(num);
        }

        System.out.print(sb);
    }
    
    static class MinHeap {        
        final int size;
        int cnt;
        final int[] arr;
        
        MinHeap(int size) {
            this.size = size;
            this.cnt = 0;
            this.arr = new int[size];
        }
        
        public int pop() {
            if (cnt == 0) return 0;
            
            int top = arr[0];
            
            arr[0] = arr[--cnt];
            bubbleDown(0);
            
            return top;
        }
        
        public void push(int node) {
            if (cnt == size) return;
            
            arr[cnt] = node;
            
            bubbleUp(cnt);
            cnt++;
        }
        
        private void heapify() {
            for (int i = (cnt-2)/2; i >= 0; i--) bubbleDown(i);
        }
        
        private void bubbleUp(int cIdx) {
            if (cIdx <= 0) return;
            
            int pIdx = (cIdx-1) / 2;
            
            if (arr[pIdx] <= arr[cIdx]) return;
            
            swap(cIdx, pIdx);
            bubbleUp(pIdx);
        }
        
        private void bubbleDown(int pIdx) {
            int cIdx = pIdx * 2 + 1, rcIdx = cIdx + 1;
            
            if (cIdx >= cnt) return;
            if (rcIdx < cnt && arr[cIdx] > arr[rcIdx]) cIdx = rcIdx;
            if (arr[pIdx] <= arr[cIdx]) return;
            
            swap(cIdx, pIdx);
            bubbleDown(cIdx);
        }
        
        private void swap(int src, int dest) {
            int tmp = arr[src];
            arr[src] = arr[dest];
            arr[dest] = tmp;
        }
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
