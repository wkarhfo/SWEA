import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWE_디저트카페 {
	static int result;
	static int[][] arr;
	static int startH;
	static int startY;
	static int[] dh = { 1, 1, -1, -1 };
	static int[] dy = { 1, -1, -1, 1 };
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			result = 0;
			visit = new boolean[101];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					Arrays.fill(visit, false);
					startH = i;
					startY = j;
					dfs(i, j, 0, 1);
				}
			}
			if (result == 0)
				System.out.println("#" + tc + " " + -1);
			else
				System.out.println("#" + tc + " " + result);
		}
	}

	private static void dfs(int h, int y, int dir, int cnt) {
		visit[arr[h][y]] = true;
		for (int k = dir; k < 4; k++) {
			int ah = h + dh[k];
			int ay = y + dy[k];

			if (ah == startH && ay == startY && cnt >= 4) {
				result = Math.max(cnt, result);
				return;
			}
			
			if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length || visit[arr[ah][ay]] == true)
				continue;
	
			dfs(ah, ay, k, cnt + 1);
		}
		visit[arr[h][y]] = false;
	}
}
