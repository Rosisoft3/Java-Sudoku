

import java.util.ArrayList;
import java.util.Collections;

public class Sudoku {

	private ArrayList<Integer> array = new ArrayList<Integer>();
	private int sudoku[][]= new int [9][9];
	private int solving_sudoku[][]= new int [9][9];
	boolean isSolved = false;	


	public Sudoku() {
		// To generate a new sudoku.
			generate();
		
		// To solve sudoku 
			
		//		sudoku[0][1]=2;sudoku[0][2]=9;sudoku[0][5]=6;sudoku[0][6]=5;
		//		sudoku[1][0]=4;sudoku[1][5]=3;sudoku[1][7]=1;
		//		sudoku[2][0]=1;sudoku[2][4]=4;sudoku[2][8]=2;
		//		sudoku[3][4]=3;sudoku[3][8]=5;
		//		sudoku[4][2]=8;sudoku[4][3]=2;sudoku[4][4]=7;sudoku[4][8]=1;
		//		sudoku[5][0]=6;sudoku[5][1]=3;sudoku[5][5]=4;sudoku[5][7]=7;
		//		sudoku[6][0]=7;sudoku[6][6]=4;
		//		sudoku[7][1]=5;sudoku[7][5]=2;sudoku[7][8]=3;
		//		sudoku[8][2]=3;sudoku[8][3]=4;sudoku[8][4]=5;sudoku[8][7]=2;
	}

	private  void generate(){
		for (int i = 0; i < 9; i++) 
			array.add((i+1));

		// Shuffle the array
		Collections.shuffle(array);

		for (int i = 0; i < sudoku.length; i++)	
			sudoku[0][i]=array.get(i);
	}


	public void printSudoku() {
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				System.out.print(sudoku[i][j]+" ");
				if(j==2)
					System.out.print("| ");
				if(j==5) System.out.print("| ");
			}
			System.out.print("\n");
			if(i==2) System.out.print("- - - - - - - - - - -\n");
			if(i==5) System.out.print("- - - - - - - - - - -\n");
		}
		System.out.println("\n");
	}

	public void sudokuSolver(){
		int i =0;
		int j =0;
		ArrayList<Integer> possible = new ArrayList<Integer>();		

		if (!isSolved) {
			if (isFull(sudoku)) {
				System.out.println("Sudoku Generated Successfully!");
				isSolved=true;			
				printSudoku();					
			}else{
				for (int x = 0; x < solving_sudoku.length; x++) 
					for (int y = 0; y < solving_sudoku.length; y++) {
						if (sudoku[x][y]==0) {
							i=x;
							j=y;
						}
					}
				possible = possibleEntries(i, j);

				for (int x = 0; x < possible.size(); x++) {
					if (possible.get(x)!=0) {
						sudoku[i][j]=possible.get(x);
						sudokuSolver();
					}

				}
				sudoku[i][j]=0;
			}
		}	

	}

	public int[] getRow(int x,int y){
		int[]row =new int [9];
		for (int a = 0; a <= x; a++) 
			for (int b = 0; b <= y; b++) 
				for (int i = 0; i < sudoku.length; i++) 
					row[i] = sudoku[a][i];
		return row;
	}

	public int[] getCol(int x,int y){
		int[]col =new int [9];
		for (int a = 0; a <= x; a++) 
			for (int b = 0; b <= y; b++) 
				for (int i = 0; i < sudoku.length; i++) 
					col[i]= sudoku[i][b];	
		return col;
	}


	public int getValue(int x,int y){
		int a= sudoku[x][y];
		return a;
	}

	public void setValue(int x,int y,int val){
		sudoku[x][y] = val;

	}

	public ArrayList<Integer> getBloc(int x ,int y){
		ArrayList<Integer> bloc = new ArrayList<Integer>();

		int bX =0;int bY =0;

		if ( x >= 6 )		
			bX = 9;
		else
			if (x < 3) 
				bX = 3;
			else 
				bX = 6;

		if ( y >= 6 )
			bY = 9;
		else
			if (y < 3) 
				bY = 3;
			else 
				bY = 6;

		for (int i = (bX-3); i < bX; i++) 
			for (int j = (bY-3); j < bY; j++) 
				bloc.add(sudoku[i][j]);
		return bloc;
	}

	public boolean isFull(int array[][]){
		ArrayList<Integer> arr= new ArrayList<Integer>();	
		for (int[] is : array) 
			for (int i : is) 
				arr.add(i);				
		if (arr.contains(0)) 
			return false;
		else 
			return true;	
	}

	public boolean isCorrect(ArrayList<Integer> arr){
		ArrayList<Boolean> b =new ArrayList<>();
		for (Integer integer : arr) {
			int i =0;
			for (Integer integer2 : arr)
				if (integer!=0 &&(integer == integer2)) i++;
			if (i!=0) {
				if (i==1) 
					b.add(true);
				else
					b.add(false);
			}
		}

		if (b.contains(false)) 
			return false;
		else 
			return true;
	}

	public boolean isCorrect(int arr[]){
		ArrayList<Boolean> b =new ArrayList<>();
		for (Integer integer : arr) {
			int i =0;
			for (Integer integer2 : arr) 
				if (integer!=0 &&(integer == integer2)) i++;
			if (i!=0) {
				if (i==1) 
					b.add(true);
				else 
					b.add(false);
			}
		}
		if (b.contains(false))
			return false;
		else 
			return true;
	}

	private ArrayList<Integer> possibleEntries(int x,int y){
		ArrayList<Integer> possible = new ArrayList<Integer>();

		if (getValue(x, y)==0) {

			ArrayList<Integer> app = new ArrayList<Integer>();	

			for (Integer integer : getRow(x, y)) 
				if (!app.contains(integer)) 
					app.add(integer);

			for (Integer integer : getCol(x, y)) 
				if (!app.contains(integer)) 
					app.add(integer);

			for (Integer integer : getBloc(x, y)) 
				if (!app.contains(integer)) 
					app.add(integer);

			for (int i = 1; i < sudoku.length+1; i++) 
				if (!app.contains(i)) 
					possible.add(i);

			return possible;
		}
		return possible;		
	}

	public boolean isPossibleSudoku(){
		ArrayList<Boolean> b =new ArrayList<>();
		for (int i = 0; i < solving_sudoku.length; i++)
			for (int j = 0; j < solving_sudoku.length; j++) {
				b.add(isCorrect(getRow(i, j)));
				b.add(isCorrect(getCol(i, j)));
				b.add(isCorrect(getBloc(i, j)));		
			}
		if (b.contains(false)) 
			return false;
		else 
			return true;
	}

	public static void main(String[] args) {

		Sudoku ms = new Sudoku();

		if (ms.isPossibleSudoku()) {
			ms.sudokuSolver();	
		}else{
			System.out.println("Error we can't solve this !! ");
			System.out.println("Put the right number in his the right case :)");
		}
	}
}
