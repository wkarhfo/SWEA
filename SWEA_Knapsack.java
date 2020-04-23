import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_Knapsack {
	static int N, W;
	static int[] w;
	static int[] v;
	static int[][] K;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			w = new int[N + 1];
			v = new int[N + 1];
			K = new int[w.length][W + 1];
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				w[i] = Integer.parseInt(st.nextToken());
				v[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 1; i < w.length; i++) { // 물건의 종류
				for (int j = 0; j < w[i]; j++) { // 임시 배낭의 무게가 물건의 무게보다 적으면 이전차수에서 값을 가져옴
					K[i][j] = K[i - 1][j];
				}
				for (int j = w[i]; j <= W; j++) {
					int now = K[i - 1][j - w[i]] + v[i];// 물건을 선택을 고려한 경우
					int pre = K[i - 1][j];// 물건을 고려하지 않은 경우
					K[i][j] = now >= pre ? now : pre;
				}
			}
			System.out.println("#"+tc+" "+K[N][W]);
		}
	}
}
