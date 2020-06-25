import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_프로세서연결하기 {
	static int N;
	static int[][] arr;
	static ArrayList<Data> list;
	static int[] dh = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int[] dy = { 0, 0, -1, 1 };
	static int MAX;
	static int MIN;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			arr = new int[N][N];
			list = new ArrayList<>();
			MAX = Integer.MIN_VALUE;
			MIN = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());

					if (i > 0 && i < N - 1 && j > 0 && j < N - 1 && arr[i][j] == 1) { // 태두리 영역 안에 있는 코어 리스트에 넣기
						list.add(new Data(i, j));
					}

				}
			}

			dfs(0, 0);
			System.out.println("#"+tc+" "+MIN);
		}
	}

	static void dfs(int depth, int coreCount) {
		if (MAX < coreCount) {
			MAX = coreCount;
			MIN = getInstallCount();
		} else if (MAX == coreCount) {
			MIN = Math.min(MIN, getInstallCount());
		}

		for (int i = depth; i < list.size(); i++) {
			int ah = list.get(i).h;
			int ay = list.get(i).y;

			for (int j = 0; j < 4; j++) { // 4방향으로 전선만들기
				if (isAvailable(ah, ay, j)) {
					install(ah, ay, j, 2); // 전선 깔기
					dfs(i + 1, coreCount + 1);
					install(ah, ay, j, 0); // 전선 다시 복귀하기
				}
			}

		}
	}

	static int getInstallCount() {
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 2)
					count++;
			}
		}
		return count;
	}

	static void install(int ah, int ay, int way, int num) {
		int h = ah;
		int y = ay;
		while (true) {
			h += dh[way];
			y += dy[way];
			if (h < 0 || h >= N || y < 0 || y >= N)
				break;
			arr[h][y] = num; // 전선 설치
		}
	}

	static boolean isAvailable(int ah, int ay, int way) {
		int h = ah;
		int y = ay;

		while (true) {
			h += dh[way];
			y += dy[way];
			if (h < 0 || h >= N || y < 0 || y >= N)
				break;
			if (arr[h][y] >= 1)
				return false;
		}
		return true;
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
