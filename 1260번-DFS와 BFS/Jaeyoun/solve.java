import java.io.*;
import java.util.StringTokenizer;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;


public class Main {
	static int n, m, v;
	static boolean[][] graph;
	static boolean[] visited;
	
	private static void DFS() {
		Stack<Integer> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		stack.push(v);
		while(!stack.isEmpty()) {
			int i = stack.pop();
			if(!visited[i]) {
				sb.append(i).append(" ");
				visited[i] = true;
				for (int j = n; j > 0; j--) {
					if(!visited[j] && (graph[i][j] || graph[j][i])) stack.push(j);
				}
			}
		}
		System.out.println(sb);
	}
	
	private static void BFS() {
		Queue<Integer> queue = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		queue.add(v);
		while(!queue.isEmpty()) {
			int i = queue.remove();
			if(!visited[i]) {
				sb.append(i).append(" ");
				visited[i] = true;
				for (int j = 1; j <= n; j++) {
					if(!visited[j] && (graph[i][j] || graph[j][i])) queue.add(j);
				}
			}
		}
		System.out.println(sb);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		v = Integer.parseInt(st.nextToken());
		graph = new boolean[n + 1][n + 1];
		visited = new boolean[n + 1];
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[a][b] = true;
			graph[b][a] = true;
		}
		
		DFS();
		
		visited = new boolean[n + 1];
		BFS();
	}

}