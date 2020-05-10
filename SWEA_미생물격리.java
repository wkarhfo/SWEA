import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_미생물격리 {
	static int N, M, K; // 배열크기, 격리시간, 미생물 군집갯수
	static int[][] count;
	static int[][] arrway;
	static int[][] sumBug;
	static ArrayList<Data> list;
	static int[] dh = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		int TC = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			list = new ArrayList<>();
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				list.add(new Data(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}

			for (int i = 0; i < M; i++) {
				int size = list.size();

				for (int j = 0; j < size; j++) {
					Data tmp = list.remove(0);
					int ah = tmp.h + dh[tmp.way - 1];
					int ay = tmp.y + dy[tmp.way - 1];
					// 1.가장자리에 갈 경우
					if (ah == 0 || ay == 0 || ah == N - 1 || ay == N - 1) {
						int tempway = 0;
						if (tmp.way == 1) { // 상
							tempway = 2;
						} else if (tmp.way == 2) { // 하
							tempway = 1;
						} else if (tmp.way == 3) {// 좌
							tempway = 4;
						} else if (tmp.way == 4) { // 우
							tempway = 3;
						}
						list.add(new Data(ah, ay, tmp.count / 2, tempway));
					} else {
						list.add(new Data(ah, ay, tmp.count, tmp.way));
					}
				}
				count = new int[N][N]; // 이동하는 지점 체크하는 배열
				arrway = new int[N][N]; // 방향 배열
				sumBug = new int[N][N];// 미생물 합계
				for (int m = 0; m < list.size(); m++) {
					int ah = list.get(m).h;
					int ay = list.get(m).y;
					int num = list.get(m).count;
					int tmpway = list.get(m).way;
					if (count[ah][ay] == 0) {
						count[ah][ay] = num;// 미생물 수 적기
						sumBug[ah][ay] = num;
						arrway[ah][ay] = tmpway;
					} else if (count[ah][ay] < num) {
						count[ah][ay] = num;
						sumBug[ah][ay] += num;
						arrway[ah][ay] = tmpway;
					} else {
						sumBug[ah][ay] += num;
					}
				}
				list = new ArrayList<>();
				for (int m = 0; m < N; m++) {
					for (int n = 0; n < N; n++) {
						if (count[m][n] != 0) {
							list.add(new Data(m, n, sumBug[m][n], arrway[m][n]));
						}
					}
				}
			}
			int result = 0;
			for (int i = 0; i < list.size(); i++) {
				result += list.get(i).count;
			}
			sb.append("#"+tc+" "+result+"\n");
		}
		System.out.println(sb);
	}

	static class Data {
		int h;
		int y;
		int count; // 미생물 수
		int way; // 이동방향

		public Data(int h, int y, int count, int way) {
			super();
			this.h = h;
			this.y = y;
			this.count = count;
			this.way = way;
		}

	}

}
