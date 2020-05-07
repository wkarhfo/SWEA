import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_베이비진 {
	static int[] people1;
	static int[] people2;
	static int[] numCount1;
	static int[] numCount2;
	static boolean[] visit1;
	static boolean[] visit2;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		int TC = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= TC; tc++) {
			people1 = new int[6];
			people2 = new int[6];
			numCount1 = new int[10]; // 0~9까지 숫자 판단
			numCount2 = new int[10]; // 0~9까지 숫자 판단
			visit1 = new boolean[10]; // 연속된 숫자 판단
			visit2 = new boolean[10]; // 연속된 숫자 판단
			Arrays.fill(numCount1, 3); // 해당 숫자 카운트하기 위해서
			Arrays.fill(numCount2, 3); // 해당 숫자 카운트하기 위해서

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 6; i++) {
				people1[i] = Integer.parseInt(st.nextToken());
				people2[i] = Integer.parseInt(st.nextToken());

			}
			int result=0;
			outer: for (int i = 0; i < 6; i++) {
				numCount1[people1[i]]--;
				numCount2[people2[i]]--;
				visit1[people1[i]] = true;
				visit2[people2[i]] = true;

				for (int j = 0; j < 8; j++) {
					int count1 = 0;
					int count2 = 0;
					for (int k = 0; k < 3; k++) {
						if (visit1[j + k])
							count1++;
						if (visit2[j + k])
							count2++;
					}
					if (count1 == 3) {
						result=1;
						break outer;
					} else if (count2 == 3) {		
						result=2;
						break outer;
					}
				}

				if (numCount1[people1[i]] == 0) {
					result=1;
					break;
				} else if (numCount1[people1[i]] == 0) {
					result=2;
					break;
				}
			}
			sb.append("#"+tc+" "+result+"\n");

		}
		System.out.println(sb);

	}
}
