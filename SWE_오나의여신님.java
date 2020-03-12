import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWE_오나의여신님 {
	static char[][] arr;
	static boolean[][] suvisit;
	static boolean[][] devilvist;
	static int[] dh = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int hang = Integer.parseInt(st.nextToken());
			int yeul = Integer.parseInt(st.nextToken());
			arr = new char[hang][yeul];
			suvisit = new boolean[hang][yeul];
			devilvist = new boolean[hang][yeul];

			for (int i = 0; i < hang; i++) {
				st = new StringTokenizer(br.readLine());
				arr[i] = st.nextToken().toCharArray();
			}

			Queue<Data> q = new LinkedList<>();

			for (int i = 0; i < hang; i++) {
				for (int j = 0; j < yeul; j++) {
					if (arr[i][j] == '*') {
						q.add(new Data(i, j, 1));
					}
					
				}
			}
			for (int i = 0; i < hang; i++) {
				for (int j = 0; j < yeul; j++) {
					if (arr[i][j] == 'S') {
						q.add(new Data(i, j, 2, 0));
					}
				}
			}
			boolean flag = false;
			int result = 0;
			while (!q.isEmpty()) {
				int t = q.size();
				for (int i = 0; i < t; i++) {
					Data temp = q.poll();
					
					if (arr[temp.h][temp.y] == 'D') {
						result = temp.cnt;
						flag = true;
						break;
					}
					if (temp.type == 1) {
						devilvist[temp.h][temp.y] = true;
						for (int k = 0; k < 4; k++) {
							int ah = temp.h + dh[k];
							int ay = temp.y + dy[k];
							if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length || devilvist[ah][ay] == true
									|| arr[ah][ay] == 'X' || arr[ah][ay] == 'D')
								continue;
							arr[ah][ay] = '*';
							devilvist[ah][ay] = true;
							q.add(new Data(ah, ay, 1));

						}

					} else {
						suvisit[temp.h][temp.y] = true;
						for (int k = 0; k < 4; k++) {
							int ah = temp.h + dh[k];
							int ay = temp.y + dy[k];
							if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length || suvisit[ah][ay] == true
									|| arr[ah][ay] == 'X' || arr[ah][ay] == '*')
								continue;

							suvisit[ah][ay] = true;
							q.add(new Data(ah, ay, 2, temp.cnt + 1));

						}

					}
				}

			}
			if (flag) {
				System.out.println("#"+tc+" "+result);
			} else
				System.out.println("#"+tc+" GAME OVER");

		}
	}

	static class Data {
		int h;
		int y;
		int type;
		int cnt;

		public Data(int h, int y, int type, int cnt) {
			this.h = h;
			this.y = y;
			this.type = type;
			this.cnt = cnt;
		}

		public Data(int h, int y, int type) {
			this.h = h;
			this.y = y;
			this.type = type;
		}

	}

}
