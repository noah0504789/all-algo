import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 과목 수
        int m = Integer.parseInt(st.nextToken()); // 사용 가능한 마일리지

        PriorityQueue<Integer> minMileageQueue = new PriorityQueue<>(); // 최소 마일리지를 저장하는 우선순위 큐

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int req = Integer.parseInt(st.nextToken()); // 신청자 수
            int limit = Integer.parseInt(st.nextToken()); // 수강 가능 인원

            st = new StringTokenizer(br.readLine());
            List<Integer> mileages = new ArrayList<>();

            for (int j = 0; j < req; j++) {
                mileages.add(Integer.parseInt(st.nextToken())); // 신청자의 마일리지
            }

            // 마일리지를 내림차순으로 정렬
            Collections.sort(mileages, Collections.reverseOrder());

            if (req < limit) {
                // 신청자 수가 수강 가능 인원보다 적으면 1만 넣어도 수강 가능
                minMileageQueue.offer(1);
            } else {
                // 신청자 수가 수강 가능 인원보다 많으면 필요한 최소 마일리지를 계산
                minMileageQueue.offer(mileages.get(limit - 1));
            }
        }

        int ans = 0;

        // 최소 마일리지부터 처리
        while (!minMileageQueue.isEmpty() && m > 0) {
            int minMileage = minMileageQueue.poll();
            if (m >= minMileage) {
                m -= minMileage;
                ans++;
            } else {
                break;
            }
        }

        bw.write(ans + "\n");
        bw.flush();
        br.close();
        bw.close();
    }
}
