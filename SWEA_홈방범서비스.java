import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_홈방범서비스 {
	static int N, M;
	static int[][] arr;
	static boolean[][] visit;
	static int[] dh = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int count;
	static int MAX;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			arr = new int[N][N];
			MAX=Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int k = 1; k <= N+1; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						count = 0;
						bfs(i, j, k);
						int result = count * M - (k * k + (k - 1) * (k - 1));
						if (result >= 0) {
							MAX = Math.max(MAX, count);
						}
					}
				}
			}
			System.out.println("#"+tc+" "+MAX);
		}
	}

	static void bfs(int h, int y, int limit) {
		visit = new boolean[N][N];
		Queue<Data> q = new LinkedList<>();
		q.add(new Data(h, y, 1));
		visit[h][y] = true;
		while (!q.isEmpty()) {
			Data tmp = q.poll();
			if (arr[tmp.h][tmp.y] == 1) {
				count++;
			}
			if (tmp.length == limit) {
				continue;
			}
			for (int k = 0; k < 4; k++) {
				int ah = tmp.h + dh[k];
				int ay = tmp.y + dy[k];
				if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length || visit[ah][ay])
					continue;
				q.add(new Data(ah, ay, tmp.length + 1));
				visit[ah][ay] = true;
			}
		}
	}

	static class Data {
		int h;
		int y;
		int length;

		public Data(int h, int y, int length) {
			super();
			this.h = h;
			this.y = y;
			this.length = length;
		}

	}
}
