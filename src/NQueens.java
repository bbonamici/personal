import java.util.*;

public class NQueens { 

	public static void main(String[] arg) { 
		NQueens nq = new NQueens();
		nq.solve(4);
	}	
	public void solve(int n) { 
		LinkedList<QNode> linkedList = new LinkedList<QNode>();
		
		int level = 0;
		int row;
		
		//initializing data
				
		for(row = 0; row != n; row++){
		
			QNode q = new QNode();
			q.level = level;
			q.row = row;
			q.parent = null;
			q.childList = new ArrayList<QNode>();
			linkedList.addLast(q);
		}
		
		while(!linkedList.isEmpty()) {
			QNode parent = linkedList.removeFirst();
			level = parent.level+1;	
			for(row = 0; row != n; row++) {			
				if(isValidPosition(row, level,parent)) {  
					//initializing node;
					QNode q = new QNode();
					q.level = level;
					q.row = row;
					q.parent = parent;
					q.childList = new ArrayList<QNode>();
					parent.childList.add(q);

					if(q.level+1 == n) 						
						reconstruct(q, n);
					else
						linkedList.addLast(q);
						
				}
			}
		}
	}

	private void reconstruct(QNode node, int n) { 
		if(node == null) {
			System.out.println("\n");
			return;
		}
		System.out.println("");
		for(int a = 0; a != n; a++) {
			if(node.row == a) 
				System.out.print("Q");
			else
				System.out.print("#");
						
		}
		reconstruct(node.parent, n);
	}
		
	
	//checks if the proposed row is valid.
	private boolean isValidPosition(int row, int level, QNode parent) { 
		if(parent == null) 
			return true;
		else if(parent.row == row) 
			return false;
		
		int diff = level - parent.level; 

//		if((diff + row  == parent.row || row-diff == parent.row) &&
//				(diff + level == parent.level || level-diff == parent.level))
		if(Math.abs(level - parent.level) == Math.abs(row - parent.row))
			return false;
		
		
		return isValidPosition(row, level, parent.parent);
	}


}
class QNode { 
	int level;
	int row;
	QNode parent;
	List<QNode> childList;
	
	public String toString() { 
		return "[" + level + "][" + row + "]";
		
	}
}
