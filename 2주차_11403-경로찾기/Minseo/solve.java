import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static boolean[][] D;
	
	static void findPath(int[][] G) {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < D.length; i++) {
			for (int j = 0; j < D.length; j++) {
				D[i][j] = G[i][j] == 1 ? true : false;
			}
		}
		
		for (int k = 0; k < G.length; k++) {
			for (int i = 0; i < G.length; i++) {
				for (int j = 0; j < G.length; j++) {
					D[i][j] = D[i][j] || (D[i][k] && D[k][j]);
				}
			}
		}
		
		for (boolean[] line : D) {
			for (boolean b : line) {
				sb.append(b ? 1 : 0).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[][] G = new int[N][N];
		
		D = new boolean[N][N];
		
		for (int i = 0; i < G.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < G[i].length; j++) {
				G[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		findPath(G);
	}
}