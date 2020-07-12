import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_벽돌깨기 {
	static int N, W, H;
	static int[][] arr;
	static int[][] temp;
	static boolean[][] visit;
	static int[] dh = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int count = 0;
	static int[] loc;
	static int MIN;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken()); // 열
			H = Integer.parseInt(st.nextToken()); // 행
			arr = new int[H][W];
			temp = new int[H][W];
			MIN=Integer.MAX_VALUE;
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					arr[i][j] = temp[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			loc = new int[N]; // 블록깰 위치 저장할 배열
//			dfs(temp, 0);
			dfs(0);
			System.out.println("#"+tc+" "+MIN);
		}
	}

	static void dfs(int depth) {
		if (depth == N) {
			for (int i = 0; i < N; i++) {
				doBreak(loc[i]);
				sort();

			}

			int count = 0;
			for (int m = 0; m < H; m++) {
				for (int n = 0; n < W; n++) {
					if(arr[m][n]!=0)
						count++;
				}
			}
			
			
			MIN=Math.min(MIN, count);
			//다시 초기화
			for (int m = 0; m < H; m++) {
				for (int n = 0; n < W; n++) {
					arr[m][n]=temp[m][n];
				}
			}
			
			
			return;
		}
		for (int i = 0; i < W; i++) {
			loc[depth] = i;
			dfs(depth + 1);
		}
	}

//	static void dfs(int[][] temp, int depth) {
//
//		if (depth == N) {
//
//			if(count==2)
//				System.exit(0);
//			return;
//		}
//		for (int i = 0; i < W; i++) {
//			if (!isHave(i)) // 해당 열에 숫자가 존재하지 않을 경우
//				continue;
//
//			// 해당 열의 구슬파괴
//			doBreak(i);
//			// 정렬하기
//			sort();
//			dfs(arr, depth + 1);
//			// 다시 복구
//			back(temp);
//			for(int l=0;l<H;l++) {
//				for(int m=0;m<W;m++) {
//					System.out.print(arr[l][m]);
//				}
//				System.out.println();
//			}
//		}
//	}

	static void sort() {
		for (int j = 0; j < W; j++) {
			ArrayList<Integer> list = new ArrayList<>();
			for (int i = 0; i < H; i++) {
				if (arr[i][j] != 0) {
					list.add(arr[i][j]);
					arr[i][j] = 0;
				}
			}
			int idx = H - 1;
			for (int i = list.size() - 1; i >= 0; i--) {
				arr[idx][j] = list.get(i);
				idx--;
			}
		}
	}

	static void doBreak(int y) {
		int idx = 0;
		for (int k = 0; k < H; k++) {
			if (arr[k][y] != 0) {
				idx = k;
				break;
			}
		}
		visit = new boolean[H][W];
		Queue<Data> q = new LinkedList<>();
		visit[idx][y] = true;
		q.add(new Data(idx, y));
		while (!q.isEmpty()) {
			Data tmp = q.poll();
			int size = arr[tmp.h][tmp.y];
			arr[tmp.h][tmp.y] = 0;

			for (int k = 0; k < 4; k++) {
				int ah = tmp.h;
				int ay = tmp.y;
				for (int m = 1; m < size; m++) {
					ah += dh[k];
					ay += dy[k];
					if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length || visit[ah][ay])
						continue;
					q.add(new Data(ah, ay));
					visit[ah][ay] = true;
				}
			}
		}
	}

	static void back(int[][] reset) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				arr[i][j] = reset[i][j];
			}
		}

	}

	static boolean isHave(int i) {
		for (int k = 0; k < H; k++) {
			if (arr[k][i] != 0)
				return true;
		}
		return false;
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
