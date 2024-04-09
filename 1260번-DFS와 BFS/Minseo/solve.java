import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static boolean[] visited;
	
	static void DFS(int[][] array, int V) {
		int v = V-1;
		visited[v] = true;
		System.out.printf("%d ", V);
		for (int i = 0; i < array[v].length; i++) {
			if (array[v][i] == 1 && !visited[i]) {
				visited[i] = true;
				DFS(array, i+1);
			}
		}
	}
	
	static void BFS(int[][] array, int V) {
		StringBuilder bfs = new StringBuilder();
		Queue<Integer> q = new LinkedList<>();
		q.add(V-1);
		bfs.append(V).append(" ");
		visited[V-1] = true;
		while (!q.isEmpty()) {
			int v = q.remove();
			for (int i = 0; i < array[v].length; i++) {
				if (array[v][i] == 1 && !visited[i]) {
					bfs.append(i+1).append(" ");
					q.add(i);
					visited[i] = true;
				}
			}
		}
		System.out.println(bfs);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N];
		
		int array[][] = new int[N][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			array[v1-1][v2-1] = 1;
			array[v2-1][v1-1] = 1;
		}
		
		DFS(array, V);
		System.out.println();
		
		visited = new boolean[N];
		BFS(array, V);
	}
}