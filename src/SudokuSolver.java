import java.util.*;

public class SudokuSolver {
	public static void main(String[] arg) {

		Sudoku su = new Sudoku(3);

		su.data[0][0] = 8;

		su.data[1][2] = 3;
		su.data[1][3] = 6;

		su.data[2][1] = 7;
		su.data[2][4] = 9;
		su.data[2][6] = 2;

		su.data[3][1] = 5;
		su.data[3][5] = 7;

		su.data[4][4] = 4;
		su.data[4][5] = 5;
		su.data[4][6] = 7;

		su.data[5][3] = 1;
		su.data[5][7] = 3;

		su.data[6][2] = 1;
		su.data[6][7] = 6;
		su.data[6][8] = 8;

		su.data[7][2] = 8;
		su.data[7][3] = 5;
		su.data[7][7] = 1;

		su.data[8][1] = 9;
		su.data[8][6] = 4;

		su.print();
//		Scanner scan = new Scanner(System.in);
//		scan.nextLine();

		long startTime = System.nanoTime();

		solve(su);

		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration / 1000000);
	}

	public static void solve(Sudoku initialState) {
		int n = initialState.data.length / 3;
		SudokuNode root = new SudokuNode(initialState, null);

		LinkedList<SudokuNode> linkedList = new LinkedList<SudokuNode>();

		linkedList.add(root);
		
		int counter = 0;
			
		while (!linkedList.isEmpty()) {
			SudokuNode parent = linkedList.removeFirst();
			Sudoku sudoku = parent.sudoku;

			for (int a = 0; a < (n * n); a++) {
			
				for (int b = 0; b < (n * n); b++) {

					if (sudoku.data[a][b] == 0) {
						for (int v = 1; v <= (n * n); v++) {
							if (sudoku.isValid(a, b, v)) {

								Sudoku suNode = new Sudoku(n);

								suNode.copyFrom(sudoku);
								suNode.insert(a, b, v);
								//
								SudokuNode node = new SudokuNode(suNode, parent);
								parent.childList.add(node);

								if (a == (n * n) - 1 && a == b
										&& suNode.solved()) {
									System.out.println("\nSolution Found!\n");
									// reconstructSolution(node);
									node.sudoku.print();

								}
				
								
								linkedList.addFirst(node);
							}
						}
						// System.out.println(a + ", " + b);
						if (!linkedList.isEmpty()) {
							parent = linkedList.removeFirst();
							sudoku = parent.sudoku;
							a = sudoku.x;
							b = sudoku.y;
						}
					}
				}

			}
		}
	}

	static void reconstructSolution(SudokuNode node) {
		if (node.parent == null) {
			node.sudoku.print();
			return;
		}
		reconstructSolution(node.parent);
	}
}

class SudokuNode {
	Sudoku sudoku;
	List<SudokuNode> childList;
	SudokuNode parent;

	public SudokuNode(Sudoku sudoku, SudokuNode parent) {
		this.sudoku = sudoku;
		this.parent = parent;
		childList = new ArrayList<SudokuNode>();
	}
}

class Sudoku {
	int data[][];
	int n;
	//last insertion values
	int x;
	int y;

	Sudoku(int n) {
		data = new int[n * n][n * n];
		this.n = n;
	}
	
	void insert(int x, int y, int v) { 
		data[x][y] = v;
		this.x = x;
		this.y = y;
	}

	boolean solved() {
		for (int a = 0; a != (n * n); a++) {
			for (int b = 0; b != (n * n); b++) {
				if (data[a][b] == 0)
					return false;
			}
		}
		return true;

	}

	void copyFrom(Sudoku su) {
		data = new int[su.data.length][su.data.length];

		for (int a = 0; a != su.data.length; a++) {
			for (int b = 0; b != su.data.length; b++) {
				data[a][b] = su.data[a][b];
			}
		}
	}

	void print() {

		for (int a = 0; a != (n * n); a++) {
			for (int b = 0; b != (n * n); b++) {
				System.out.print("|" + data[a][b]);
			}
			System.out.print("|\n");

		}
	}

	boolean isValid(int x, int y, int value) {
		// checking row
		for (int a = 0; a != (n * n); a++) {
			if (a != y && data[x][a] == value)
				return false;
		}

		// checking column
		for (int a = 0; a != (n * n); a++) {
			if (a != x && data[a][y] == value)
				return false;
		}

		int subX = x / n;
		int subY = y / n;
		for (int a = (subX * n); a != (subX * n) + n; a++) {
			for (int b = (subY * n); b != (subY * n) + n; b++) {
				if (!(x == a && y == b)) {
					if (data[a][b] == value)
						return false;
				}
			}
		}
		return true;
	}
}
