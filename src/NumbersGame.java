public class NumbersGame { 

	public static void main(String[] arg) { 
		GameState<Integer> state = new GameState(1, 2, 3);
		
		System.out.println(state.n1);		
	}

	public static void solve(GameState state) { 
		
		
	}
	
}

class GameState<T> { 	
	
	boolean first;
	T n1;
	T n2;
	T n3;
		
	public GameState(T n1, T n2, T n3) { 
		this.n1 = n1;
		this.n2 = n2;
		this.n3 = n3;	
	}
	
	public boolean isValidMove(short move) {
		T result;
		T original;
		switch(move) { 
			case 0:
				result = calculateMove(n2, n3);
				original = n1;
				break;
			case 1:
					
				result = calculateMove(n1, n3);
				original = n2;
				break;
			case 2:
	
				result = calculateMove(n1, n2);
				original = n3;
		}

			
		if(result.equals(original)) 
			return false;
		else
			return true;
	}
	
	public T calculateMove( T n2, T n3) { 
		return (n2 + n3) / 2;
		
	}
	
}
