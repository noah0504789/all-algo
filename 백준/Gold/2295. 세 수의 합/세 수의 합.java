import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];

        // 입력받기
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        // 오름차순 정렬
        Arrays.sort(nums);

        // 모든 두 수의 합을 저장할 집합
        Set<Integer> sumSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                sumSet.add(nums[i] + nums[j]); // 두 수의 합 계산
            }
        }

        // 큰 k 탐색
        for (int i = n - 1; i >= 0; i--) { // c 선택
            for (int j = i - 1; j >= 0; j--) { // k - c = a + b 조건 확인
                int diff = nums[i] - nums[j];
                if (sumSet.contains(diff)) { // k - c가 존재하는 경우
                    System.out.println(nums[i]); // 가장 큰 k 출력
                    return;
                }
            }
        }
    }
}
