import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SWEA_보물상자비밀번호 {
	static int N, K;
	static char[] arr;
	static char[] temp;
	static String result="";

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		int TC = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			arr = new char[N];
			temp = new char[N];
			arr = br.readLine().toCharArray();
			for (int i = 0; i < N; i++)
				temp[i] = arr[i];
			int size = N / 4;
			TreeSet<String> tree = new TreeSet<String>(new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					return o2.compareTo(o1);
				}
			});

			while (true) {

				// size만큼씩 짤라서 treeset에 넣기(넣으면서 정렬)
				String word = "";
				for (int i = 1; i <= N; i++) {
					word += arr[i - 1];
					if (i % size == 0) {
						tree.add(word);
						word = "";
					}

				}

				// 한칸씩 이동하기
				char last = arr[N - 1];
				for (int i = N - 1; i > 0; i--) {
					arr[i] = arr[i - 1];
				}
				arr[0] = last;


				if (isEq()) {
					break;
				}

			}
			int count=1;
			while(!tree.isEmpty()) {
				String s=tree.pollFirst();
				if(count==K)
					result=s;
				count++;
			}
			int lastRe=Integer.parseInt(result,16);
			sb.append("#").append(tc).append(" ").append(lastRe).append("\n");
		}
		System.out.println(sb);
	}

	static boolean isEq() {
		for (int i = 0; i < N; i++) {
			if (arr[i] != temp[i])
				return false;
		}
		return true;
	}
}
