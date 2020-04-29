import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 
 * 배열을 거꾸로 정렬하는 방법
 * 배열을 선언을 int가 아니라 Integer로 선언한 후
 * Arrays.sort(arr, Collections.reverseOrder()); --> 거꾸로 정렬 된다. 
 * 
 *
 */
public class SWEA_재관이의대량할인 {
	static int N;
	static Integer[] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		int TC = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			arr = new Integer[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr,Collections.reverseOrder());
			int result = 0;
			for (int i =1; i <=N; i++) {
				if (i % 3 == 0)
					continue;
				else
					result += arr[i-1];
			}
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
}
