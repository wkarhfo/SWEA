import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class SWE_디저트카페풀이2 {
	static int N;
	static int max;
	static int sr;
	static int sc;
	static int[][] map;
	static boolean[] visit;
	static int[][] direction = { { 1, 1 }, { 1, -1 }, { -1, -1 }, { -1, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			max = 0;
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visit = new boolean[101]; // 먹은 디저트 체크, 방문체크

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sr = i;
					sc = j;
					Arrays.fill(visit, false);
					dfs(i, j, 0, 1);
				}
			}
			if(max==0)
				System.out.println("#" + tc + " " + -1);
			else
				System.out.println("#" + tc + " " + max);
		}
	}

	private static void dfs(int r, int c, int dir, int cnt) {
		visit[map[r][c]] = true;
		for (int d = dir; d < 4; d++) {
			int nr = r + direction[d][0];
			int nc = c + direction[d][1];

			if (nr == sr && nc == sc && cnt >= 4) {
				if (cnt > max) {
					max = cnt;
					return;
				}

			}
			if (nr > -1 && nr < N && nc > -1 && nc < N && !visit[map[nr][nc]]) {
				dfs(nr, nc, d, cnt + 1);
			}
		}
		visit[map[r][c]] = false;

	}

}
