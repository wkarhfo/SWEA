import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_무선충전 {
	static int M, A;
	static int[] moveA;
	static int[] moveB;
	static ArrayList<Battery> list;
	static int[][] arr;
	static int[] dh = { 0, -1, 0, 1, 0 };// 이동x, 상, 우, 하,좌
	static int[] dy = { 0, 0, 1, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); // 이동 시간
			A = Integer.parseInt(st.nextToken()); // 배터리 갯수

			arr = new int[10 + 1][10 + 1];
			moveA = new int[M];
			moveB = new int[M];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				moveA[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				moveB[i] = Integer.parseInt(st.nextToken());
			}

			list = new ArrayList<>();
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				int h = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				list.add(new Battery(h, y, c, p, i + 1));
			}
			Data peopleA = new Data(1, 1);
			Data peopleB = new Data(10, 10);
			for (int i = 0; i <= M; i++) {
				if (i != 0) {
					peopleA = new Data(peopleA.h + dh[moveA[i - 1]], peopleA.y + dy[moveA[i - 1]]);
					peopleB = new Data(peopleB.h + dh[moveB[i - 1]], peopleB.y + dy[moveB[i - 1]]);
				}
				System.out.println(peopleB.h + "," + peopleB.y);
				ArrayList<Integer> listA = new ArrayList<>();
				ArrayList<Integer> listB = new ArrayList<>();
				for (int j = 0; j < list.size(); j++) {
					// A이동 좌표
					int ah = peopleA.h;
					int ay = peopleA.y;
					// B이동 좌표
					int bh = peopleB.h;
					int by = peopleB.y;

					boolean[][] visit = new boolean[10 + 1][10 + 1];
					Queue<Data> q = new LinkedList<>();
					int h = list.get(j).h;
					int y = list.get(j).y;
					int limit = list.get(j).c;
					int type = list.get(j).type;
					visit[h][y] = true;
					q.add(new Data(h, y, type, 0));
					while (!q.isEmpty()) {
						Data tmp = q.poll();
						if (tmp.cnt == limit) {
							continue;
						}
						for (int k = 1; k < 5; k++) {
							int temph = tmp.h + dh[k];
							int tempy = tmp.y + dy[k];
							if (temph <= 0 || temph >= arr.length || tempy <= 0 || tempy >= arr[0].length
									|| visit[temph][tempy])
								continue;
							if (temph == ah && tempy == ay) {
								listA.add(tmp.type);
							} else if (temph == bh && tempy == by) {
								listB.add(tmp.type);
							}
							visit[temph][tempy] = true;
							q.add(new Data(temph, tempy, tmp.type, tmp.cnt + 1));

						}
					} // end of bfs 충전 범위 체크
				}

			}
		}
	}

	static class Data {
		int h;
		int y;
		int type;
		int cnt;

		public Data(int h, int y, int type, int cnt) {
			super();
			this.h = h;
			this.y = y;
			this.type = type;
			this.cnt = cnt;
		}

		public Data(int h, int y) {
			super();
			this.h = h;
			this.y = y;
		}

	}

	static class Battery {
		int h;
		int y;
		int c;// 충전범위
		int p;// 처리량
		int type; // 배터리 종류 파악하기 위해서

		public Battery(int h, int y, int c, int p, int type) {
			super();
			this.h = h;
			this.y = y;
			this.c = c;
			this.p = p;
			this.type = type;
		}

	}
}
