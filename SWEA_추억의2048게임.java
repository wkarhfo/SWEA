import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_추억의2048게임 {
	static int N;
	static String S;
	static int[][] arr;
	static int[][] result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			S = st.nextToken();
			arr = new int[N][N];
			result = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			if (S.equals("left")) {
				for (int i = 0; i < N; i++) { // 행
					int count = 0;
					ArrayList<Integer> list = new ArrayList<>();
					for (int j = 0; j < arr[0].length; j++) { // 열:0부터 차례대로
						if (arr[i][j] == 0)
							continue;
						if (list.isEmpty())
							list.add(arr[i][j]);
						else {
							if (list.contains(arr[i][j])) {
								int temp = list.remove(0);
								temp *= 2;
								result[i][count] = temp;
							} else {
								int temp = list.remove(0);
								result[i][count] = temp;
								list.add(arr[i][j]);
							}
							count++;
						}
					}
					while (!list.isEmpty()) {
						result[i][count] = list.remove(0);
						count++;
					}
				}
			} else if (S.equals("right")) {
				for (int i = 0; i < N; i++) { // 행
					int count = N-1;
					ArrayList<Integer> list = new ArrayList<>();
					for (int j = arr[0].length-1; j >=0; j--) { // 열:마지막부터 차례대로
						if (arr[i][j] == 0)
							continue;
						if (list.isEmpty())
							list.add(arr[i][j]);
						else {
							if (list.contains(arr[i][j])) {
								int temp = list.remove(0);
								temp *= 2;
								result[i][count] = temp;
							} else {
								int temp = list.remove(0);
								result[i][count] = temp;
								list.add(arr[i][j]);
							}
							count--;
						}
					}
					while (!list.isEmpty()) {
						result[i][count] = list.remove(0);
						count--;
					}
				}
			} else if (S.equals("up")) {
				for (int i = 0; i < N; i++) { // 열
					int count = 0;
					ArrayList<Integer> list = new ArrayList<>();
					for (int j = 0; j < arr.length; j++) {
						if (arr[j][i] == 0)
							continue;
						if (list.isEmpty())
							list.add(arr[j][i]);
						else {
							if (list.contains(arr[j][i])) {
								int temp = list.remove(0);
								temp *= 2;
								result[count][i] = temp;
							} else {
								int temp = list.remove(0);
								result[count][i] = temp;
								list.add(arr[j][i]);
							}
							count++;
						}
					}
					while (!list.isEmpty()) {
						result[count][i] = list.remove(0);
						count++;
					}
				}

			} else if (S.equals("down")) {
				for (int i = 0; i < N; i++) { // 열
					int count = N - 1;
					ArrayList<Integer> list = new ArrayList<>();
					for (int j = arr.length - 1; j >= 0; j--) { // 행: down이기 때문에 밑에서 위로 올라가야댐
						if (arr[j][i] == 0)
							continue;
						if (list.isEmpty())
							list.add(arr[j][i]);
						else {
							if (list.contains(arr[j][i])) {
								int temp = list.remove(0);
								temp *= 2;
								result[count][i] = temp;
							} else {
								int temp = list.remove(0);
								result[count][i] = temp;
								list.add(arr[j][i]);
							}
							count--;
						}
					}
					while (!list.isEmpty()) {
						result[count][i] = list.remove(0);
						count--;
					}
				}
			}
			System.out.println("#"+tc);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(result[i][j] + " ");
				}
				System.out.println();
			}

		}
	}
}
