import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWE_벽돌깨기 {
	static int result;
	static int[][] arr;
	static int[][] test;
	static int N;
	static int[] dh = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[] point;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int yeul = Integer.parseInt(st.nextToken());
			int hang = Integer.parseInt(st.nextToken());
			arr = new int[hang][yeul];
			test = new int[hang][yeul];

			for (int i = 0; i < hang; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < yeul; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			result = Integer.MAX_VALUE;
			point = new int[N];
			dfs(0);

			System.out.println("#" + tc + " " + result);
		}
	}

	private static void dfs(int cnt) {
		if (cnt == N) {
			for (int m = 0; m < arr.length; m++) {
				for (int n = 0; n < arr[0].length; n++) {
					test[m][n] = arr[m][n];
				}
			}

			for (int i = 0; i < point.length; i++) {
				explore(point[i]);
			}
			int sum = 0;
			for (int i = 0; i < test.length; i++) {
				for (int j = 0; j < test[0].length; j++) {
					if (test[i][j] != 0)
						sum++;
				}
			}
			result = Math.min(result, sum);
			return;
		}

		for (int i = 0; i < arr[0].length; i++) {
			point[cnt] = i;
			dfs(cnt + 1);
		}

	}

	private static void explore(int y) {
		for (int i = 0; i < test.length; i++) {
			if (test[i][y] > 1) {
				bfs(i, y);
				break;
			} else if (test[i][y] == 1) {
				test[i][y] = 0;
				break;
			}
		}
		//숫자 밑으로 내리기
		for (int i = 0; i < test[0].length; i++) {
			int sx = test.length - 1;
			for (int j = test.length - 1; j >= 0; j--) {
				if (test[j][i] != 0) {
					int temp = test[j][i];
					test[j][i] = 0;
					test[sx][i] = temp;
					sx--;
				}
			}
		}
	}

	private static void bfs(int h, int y) {
		Queue<Data> q = new LinkedList<>();
		q.add(new Data(h, y,test[h][y]));
		while (!q.isEmpty()) {
			Data temp = q.poll();
			int count = temp.cnt;
			test[temp.h][temp.y] = 0;
			for (int k = 0; k < 4; k++) {
				int ah=temp.h;
				int ay=temp.y;
				int count2=count;
				while (count2 > 1) {
					ah = ah + dh[k];
					ay = ay + dy[k];
					if (ah >= 0 && ah < test.length && ay >= 0 && ay < test[0].length && test[ah][ay] != 0) {
						q.add(new Data(ah, ay,test[ah][ay]));
						test[ah][ay] = 0;
					}
					count2--;
				}
			}
		}
	}

	static class Data {
		int h;
		int y;
		int cnt;
		public Data(int h, int y,int cnt) {
			super();
			this.h = h;
			this.y = y;
			this.cnt=cnt;
		}

	}
}
