import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//그리디하게 정렬한후 차례대로 높은 순서를 향해 정하면서 가면 가장 적절한 값을 구함

public class SWEA_성수의프로그래밍강좌시청 {
	static int T;
	static double result;
	static int N, K;
	static int[] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			result = 0.0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			arr = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr);

			for (int i = arr.length-K; i < arr.length; i++) {

				result = (result + arr[i]) / 2;

			}

			System.out.println("#" + tc + " " + result);
		}

	}

}
