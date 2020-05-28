import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_탈주범검거 {
	static int N, M, R, C, L;
	static int[][] arr;
	static int[] dh = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int[] dy = { 0, 0, -1, 1 };
	static boolean[][] visit;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 행
			M = Integer.parseInt(st.nextToken()); // 열
			R = Integer.parseInt(st.nextToken()); // 맨홀뚜껑위치 행
			C = Integer.parseInt(st.nextToken()); // 맨홀뚜껑위치 열
			L = Integer.parseInt(st.nextToken()); // 탈출 후 소오된 시간
			arr = new int[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			visit = new boolean[N][M];
			///// 입력끝
			Queue<Data> q = new LinkedList<>();
			q.add(new Data(R, C, 1));
			visit[R][C] = true;
			while (!q.isEmpty()) {
				Data tmp = q.poll();
				if (tmp.cnt == L) {
					break;
				}
				int num = arr[tmp.h][tmp.y];
				for (int k = 0; k < 4; k++) {
					int ah = tmp.h + dh[k];
					int ay = tmp.y + dy[k];
					if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length || visit[ah][ay]
							|| arr[ah][ay] == 0)
						continue;
					if (num == 1) {
						// 상하좌우 다들어감.
					} else if (num == 2) { // 상하
						if (k == 2 || k == 3)
							continue;
					} else if (num == 3) {
						if (k == 0 || k == 1)
							continue;
					} else if (num == 4) {
						if (k == 1 || k == 2)
							continue;
					} else if (num == 5) {
						if (k == 0 || k == 2)
							continue;
					} else if (num == 6) {
						if (k == 0 || k == 3)
							continue;
					} else if (num == 7) {
						if (k == 1 || k == 3)
							continue;
					}
					if (k == 0) { // 위로 갈수 있을때 다음거 체크
						if (arr[ah][ay] == 3 || arr[ah][ay] == 4 || arr[ah][ay] == 7)
							continue;
					} else if (k == 1) { // 아래로 갈수 있을때 다음거 체크
						if (arr[ah][ay] == 3 || arr[ah][ay] == 5 || arr[ah][ay] == 6)
							continue;
					} else if (k == 2) { // 왼쪽으로 갈 수 있을 때 다음거 체크
						if (arr[ah][ay] == 2 || arr[ah][ay] == 6 || arr[ah][ay] == 7)
							continue;
					} else if (k == 3) { // 오른쪽으로 갈수 있을 때 다음거 체크
						if (arr[ah][ay] == 2 || arr[ah][ay] == 4 || arr[ah][ay] == 5)
							continue;
					}

					visit[ah][ay] = true;
					q.add(new Data(ah, ay, tmp.cnt + 1));
				}
			}
			int count = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (visit[i][j])
						count++;
				}
			}
			System.out.println("#" + tc + " " + count);
		}
	}

	static class Data {
		int h;
		int y;
		int cnt;

		public Data(int h, int y, int cnt) {
			super();
			this.h = h;
			this.y = y;
			this.cnt = cnt;
		}

	}
}
