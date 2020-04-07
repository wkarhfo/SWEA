import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_창용마을무리의개수 {
	static int N, M;
	static int[] arr;
	static int count;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		int TC = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			arr = new int[N + 1];
			count=N;
			makeset();
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a, b);

			}
			System.out.println(Arrays.toString(arr));
			sb.append("#").append(tc).append(" ").append(count).append("\n");

		}
		System.out.println(sb);
	}

	private static void union(int a, int b) {
		a = findset(a);
		b = findset(b);
		if (a == b)
			return;
		count--;
		arr[b] = a;
	}

	private static int findset(int num) {
		if (arr[num] == num) {
			return num;
		}
		int temp = findset(arr[num]);
		arr[num] = temp;
		return temp;
	}

	private static void makeset() {
		for (int i = 1; i < arr.length; i++) {
			arr[i] = i;
		}
	}

}
