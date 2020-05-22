import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_보호필름 {
	static int D, W, K;
	static int[][] arr;
	static int[][] reset;
	static int[] temp;
	static int[] num;
	static int MIN;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken()); // 보호필름 두께
			W = Integer.parseInt(st.nextToken()); // 가로크기
			K = Integer.parseInt(st.nextToken()); // 합격 기준
			arr = new int[D][W];
			reset = new int[D][W];
			// 0:A , 1:B
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					arr[i][j] = reset[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			MIN = Integer.MAX_VALUE;
			System.out.print("#" + tc + " ");
			// 0번 실행해도 될때
			if (ischeck()) {
				System.out.println(0);
			} else {
				// 그 외에 투입 해야 할때
				for (int i = 1; i <= D; i++) {
					temp = new int[i];
					dfs(0, 0, i);
				}
				System.out.println(MIN);
			}
		}
	}

	static void dfs(int start, int depth, int dest) {
		if (depth == dest) {
			num = new int[temp.length];
			if (MIN <= temp.length)
				return;
			dfs2(0);
			return;
		}
		for (int i = start; i < D; i++) {
			temp[depth] = i;
			dfs(i + 1, depth + 1, dest);
		}
	}

	static void dfs2(int depth) {
		if (depth == temp.length) {
			for (int k = 0; k < temp.length; k++) {
				for (int j = 0; j < arr[0].length; j++) {
					arr[temp[k]][j] = num[k];
				}
			}
			// 열에서 연속 3개씩 잇는 체크
			if (ischeck()) {
				MIN = Math.min(MIN, temp.length);
			}
			// 다시 초기화
			reset();
			return;
		}
		for (int i = 0; i <= 1; i++) {
			num[depth] = i;
			dfs2(depth + 1);
		}
	}

	static void reset() {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				arr[i][j] = reset[i][j];
			}
		}
	}

	static boolean ischeck() {
		// 열하나씩 검사
		int count = 0;
		for (int i = 0; i < arr[0].length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (j + K > arr.length) {
					continue;
				}
				int cn = arr[j][i];
				boolean flag = true;
				for (int k = 1; k < K; k++) {
					if (cn != arr[j + k][i]) {
						flag = false;
						break;
					}
				}
				if (flag) {
					count++;
					break;
				}
			}
		}
		if (count == arr[0].length)
			return true;
		else
			return false;
	}
}
