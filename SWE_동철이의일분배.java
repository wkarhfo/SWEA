import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWE_동철이의일분배 {
	static int result;
	static int[][] arr;
	static boolean[] visit;
	static int N;
	static double MAX;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			visit = new boolean[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			MAX = 0;
			dfs(0, 0, 1);
			System.out.printf("#%d %.6f\n", tc, MAX * 100);
		}

	}

	private static void dfs(int h, int y, double max) {

		if (max <= MAX)
			return;

		if (h == N) {
			MAX = Math.max(MAX, max);
			return;
		}
		for (int i = 0; i < N; i++) {
			if (!visit[i]) {
				if(arr[h][i]==0) {
					continue;
				}
				visit[i] = true;
				dfs(h + 1, i, max * arr[h][i] / 100.0);
				visit[i] = false;

			}
		}
	}

}
