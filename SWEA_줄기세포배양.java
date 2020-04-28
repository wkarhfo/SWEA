import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_줄기세포배양 {
	static int N, M, K;
	static int[][] arr;
	static boolean[][] visit;
	static PriorityQueue<Data> q;
	static int[] dh = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		int TC = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			int tempK = K / 2;

			q = new PriorityQueue<Data>();
			arr = new int[tempK + N + tempK][tempK + M + tempK];
			visit = new boolean[arr.length][arr[0].length];

			for (int i = 0 + tempK; i < N + tempK; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0 + tempK; j < M + tempK; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if (arr[i][j] > 0) {
						q.add(new Data(i, j, arr[i][j], arr[i][j]));
						visit[i][j] = true;
					}
				}
			}

			result = 0;
			for (int v = 1; v <= K; v++) {
				int size = q.size();
				ArrayList<Data> list = new ArrayList<>();
				for (int i = 0; i < size; i++) {
					Data tmp = q.poll();
					if (tmp.timex == 0) {
						for (int k = 0; k < 4; k++) {
							int ah = tmp.h + dh[k];
							int ay = tmp.y + dy[k];
							if (visit[ah][ay])
								continue;
							visit[ah][ay] = true;
							arr[ah][ay] = tmp.x;
							list.add(new Data(ah, ay, tmp.x, tmp.x));
						}
						list.add(new Data(tmp.h,tmp.y,tmp.x,tmp.timex-1));

					} else {
						Data data = new Data(tmp.h, tmp.y, tmp.x, tmp.timex - 1);
						list.add(data);
					}
					if(list.get(list.size()-1).timex==-list.get(list.size()-1).x)
						list.remove(list.size()-1);
				}
				for (int i = 0; i < list.size(); i++) {
					q.add(list.get(i));
				}

			}
			result=q.size();
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}

	static class Data implements Comparable<Data> {
		int h;
		int y;
		int x; // 생명력
		int timex; // life

		public Data(int h, int y, int x, int timex) {
			super();
			this.h = h;
			this.y = y;
			this.x = x;
			this.timex = timex;
		}

		@Override
		public int compareTo(Data o) {
			return Integer.compare(o.x, this.x);
		}

	}
}
