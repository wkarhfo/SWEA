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
	static int sum;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); // 이동 시간
			A = Integer.parseInt(st.nextToken()); // 배터리 갯수

			sum = 0;

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
				int y = Integer.parseInt(st.nextToken());
				int h = Integer.parseInt(st.nextToken());
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
				ArrayList<Battery> listA = new ArrayList<>();
				ArrayList<Battery> listB = new ArrayList<>();
				for (int j = 0; j < list.size(); j++) {
					// A이동 좌표
					int ah = peopleA.h;
					int ay = peopleA.y;
					// B이동 좌표
					int bh = peopleB.h;
					int by = peopleB.y;
					if ((Math.abs(ah - list.get(j).h) + Math.abs(ay - list.get(j).y)) <= list.get(j).c) {
						listA.add(list.get(j));
					}
					if ((Math.abs(bh - list.get(j).h) + Math.abs(by - list.get(j).y)) <= list.get(j).c) {
						listB.add(list.get(j));
					}

				}
				int tempSum = 0;
				int max = Integer.MIN_VALUE;
				if (listA.size() >= 1 && listB.size() == 0) {
					for (int m = 0; m < listA.size(); m++) {
						max = Math.max(max, listA.get(m).p);
					}
					sum += max;
				} else if (listA.size() == 0 && listB.size() >= 1) {
					for (int m = 0; m < listB.size(); m++) {
						max = Math.max(max, listB.get(m).p);
					}
					sum += max;
				} else if (listA.size() == 1 && listB.size() == 1) {
					if(listA.get(0).type==listB.get(0).type) {
						tempSum=listA.get(0).p;
					}else {
						tempSum = listA.get(0).p + listB.get(0).p;						
					}
					sum += tempSum;
					
				} else if (listA.size() > 1 || listB.size() > 1) {
					for (int m = 0; m < listA.size(); m++) {
						for (int n = 0; n < listB.size(); n++) {
							if (listA.get(m).type == listB.get(n).type) {
								tempSum = listA.get(m).p;
							} else {
								tempSum = listA.get(m).p + listB.get(n).p;
							}
							max = Math.max(max, tempSum);
						}
					}
					sum += max;
				}
			}
			System.out.println("#"+tc+" "+sum);
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
