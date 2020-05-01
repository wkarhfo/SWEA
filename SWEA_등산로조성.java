import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_등산로조성 {
	static int N, K;
	static int[][] arr;
	static boolean[][] visit;
	static int[] dh = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static ArrayList<Data> list;
	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		int TC = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			arr = new int[N][N];

			int max = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if (max < arr[i][j])
						max = arr[i][j];
				}
			}

			list = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (max == arr[i][j])
						list.add(new Data(i, j));
				}
			}
			result = Integer.MIN_VALUE;
			for (int i = 0; i < list.size(); i++) {
				visit = new boolean[N][N];
				visit[list.get(i).h][list.get(i).y] = true;
				dfs(list.get(i).h, list.get(i).y, 1, K, false);
				visit[list.get(i).h][list.get(i).y] = false;
			}
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}

	static void dfs(int h, int y, int depth, int broke, boolean flag) {
		result = Math.max(result, depth);
//		System.out.println(h + " " + y + ":" + arr[h][y]);
		for (int k = 0; k < 4; k++) {
			int ah = h + dh[k];
			int ay = y + dy[k];
			if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length || visit[ah][ay])
				continue;
			if (arr[ah][ay] >= arr[h][y]) {
				if (flag) {
					continue;
				} else {
					for (int i = 1; i <= broke; i++) {
						int temp = arr[ah][ay] - i;
						if (temp < arr[h][y]) {
							arr[ah][ay] = arr[ah][ay] - i;
							visit[ah][ay] = true;
							dfs(ah, ay, depth + 1, broke, true);
							visit[ah][ay] = false;
							arr[ah][ay] = arr[ah][ay] + i;
						} else {
							continue;
						}

					}
				}
			} else {
			
				visit[ah][ay] = true;
				dfs(ah, ay, depth + 1, broke, flag);
				visit[ah][ay] = false;
			}

		}

	}

	static class Data {
		int h;
		int y;

		public Data(int h, int y) {
			super();
			this.h = h;
			this.y = y;
		}

	}
}
