import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA_파핑파핑지뢰찾기 {
	static char[][] arr;
	static boolean[][] visit;
	static int[] dh = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= TC; tc++) {
			int size = Integer.parseInt(br.readLine().trim());
			arr = new char[size][size];
			visit = new boolean[size][size];
			for (int i = 0; i < size; i++) {
				arr[i] = br.readLine().toCharArray();
			}
			result=0;
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (arr[i][j] == '.' && visit[i][j] == false && isOkay(i, j)) {
						bfs(i, j);
						result++;
					}
				}
			}

			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if(arr[i][j]=='.')
						result++;
				}
			}
			System.out.println("#"+tc+" "+result);
		}
	}

	private static void bfs(int h, int y) {
		Queue<Data> q = new LinkedList<>();
		visit[h][y] = true;
		arr[h][y] = 'O';
		q.add(new Data(h, y));
		while (!q.isEmpty()) {
			Data tmp = q.poll();
			for (int k = 0; k < 8; k++) {
				int ah = tmp.h + dh[k];
				int ay = tmp.y + dy[k];
				if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length || visit[ah][ay] == true
						|| arr[ah][ay] == '*')
					continue;
				if (!isOkay(tmp.h, tmp.y)) {
					arr[tmp.h][tmp.y] = 'O';
				} else {
					visit[ah][ay] = true;
					arr[ah][ay] = 'O';
					q.add(new Data(ah, ay));
				}
			}

		}
	}

	private static boolean isOkay(int h, int y) {
		for (int k = 0; k < 8; k++) {
			int ah = h + dh[k];
			int ay = y + dy[k];
			if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length)
				continue;
			if (arr[ah][ay] == '*')
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
