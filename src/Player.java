import java.util.ArrayList;
import java.util.List;

public class Player {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	public int leftHu = 0;
	public int tingNum = 0;
	public int willKeep = 0;
	public float partition = 0;
	public boolean isTing = false;
	public List<Tile> tingTile = new ArrayList<Tile>();
	public int[][] A = new int[3][11];
	public int[][] B = new int[3][11];
	public void mGet(final int color , final int num){
		
		A[color][num]++;
		B[color][num]++;
		if(B[color][num] == 3 ){
			B[color][num] -= 3;
		}
		if(num == 1 ){
			if(B[color][num]>0&&B[color][num+1]>0 && B[color][num+2]>0){
				B[color][num] -= 1;
				B[color][num+1] -= 1;
				B[color][num+2] -= 1;
			}
		}
		else if(num == 9){
			if(B[color][num]>0&&B[color][num-1]>0 && B[color][num-2]>0){
				B[color][num] -= 1;
				B[color][num-1] -= 1;
				B[color][num-2] -= 1;
			}
		}
		else if(num>1&&num<9){
			if(B[color][num]>0&&B[color][num-1]>0 && B[color][num-2]>0){
				B[color][num] -= 1;
				B[color][num-1] -= 1;
				B[color][num-2] -= 1;
			}
			if(B[color][num]>0&&B[color][num-1]>0 && B[color][num+1]>0){
				B[color][num] -= 1;
				B[color][num-1] -= 1;
				B[color][num+1] -= 1;
			}
			if(B[color][num]>0&&B[color][num+1]>0 && B[color][num+2]>0){
				B[color][num] -= 1;
				B[color][num+1] -= 1;
				B[color][num+2] -= 1;
			}
		}		
	}
	public Tile mPut(){
		Tile t = null;
		int col = 0;
		int num = 0;
		for(int i = 0 ; i < 3; i++){
			for(int j = 1 ; j< 10 ; j++){
				if(B[i][j]>0){
					t = new Tile(i,j);
					col = i;
					num = j;
					if(!isNeed(t)){
						A[i][j]--;
						B[i][j]--;
						return t;
					}
				}
			}
		}
		
		B[col][num]--;
		A[col][num]--;
		return t;
	}
	public boolean mPeng(Tile t){
		int color = t.color;
		int num = t.num;
		if(B[color][num] == 2){
			return true;
		}
		return false;
		
	}
	public boolean isNeed(Tile t){
		int color  = t.color;
		int num = t.num;
		if(B[color][num] >1 ){
			return true;
		}
		if(num == 1){
			if(B[color][num+1]>0 || B[color][num+2]>0){
				return true;
			}
		}
		if(num == 9){
			if(B[color][num-1]>0 || B[color][num-2]>0){
				return true;
			}
		}
		if(num>1&&num<9){
			if(B[color][num-1]>0 || B[color][num-2]>0){
				return true;
			}
			if(B[color][num-1]>0 || B[color][num+1]>0){
				return true;
			}
			if(B[color][num+1]>0 || B[color][num+2]>0){
				return true;
			}
		}
		return false;
		
	}
	public boolean mIsTing(){
		int[][] cA = new int[3][11];
		int[][] cB = new int[3][11];
		mCopy(cA,A);
		mCopy(cB,B);
		for(int i = 0 ; i < 3; i++){
			for(int j = 1 ; j < 10 ;j++){
				mGet(i,j);
				if(mIsWin()){
					isTing = true;
					tingTile.add(new Tile(i,j));
				}
				mCopy(A,cA);
				mCopy(B,cB);
			}
		}
		return isTing;	
	}
	public boolean mIsWin(){
		int doublec = 0;
		int sum  =0 ;
		for(int i = 0 ; i < 3 ; i++){
			for(int j = 1; j< 10; j++){
				if(B[i][j] == 2){
					doublec++;
				}
				sum+=B[i][j];
			}
		}
		if(doublec == 1 && sum == 2){
			return true;
		}
		return false;
	}
	public boolean mIsWin(int color,int num){
		int[][] cA = new int[3][11];
		int[][] cB = new int[3][11];
		mCopy(cA,A);
		mCopy(cB,B);
		boolean b = false;
		mGet(color,num);
		if(mIsWin()){
			b = true;
		}
		mCopy(A,cA);
		mCopy(B,cB);
		return b;
	}
	public void mPrint(){
		int sum = 0;
		for(int i = 0 ; i< 3 ; i++){
			for(int j = 0 ; j < 11 ; j++){
				if(A[i][j]!=0){
					System.out.print("A"+j+": "+A[i][j]+" ;");
					sum+=A[i][j];
				}
			}
			System.out.println();
		}
		System.out.println("sum:"+sum);
	}
	public void mPrintB(){
		int sum = 0;
		for(int i = 0 ; i< 3 ; i++){
			for(int j = 0 ; j < 11 ; j++){
				if(B[i][j]!=0){
					System.out.print("B"+j+": "+B[i][j]+" ;");
					sum+=B[i][j];
				}
			}
			System.out.println();
		}
		System.out.println("sum:"+sum);
	}
	private void mCopy(int[][] a ,int[][] b){
		for(int i = 0 ; i  < 3; i++){
			for(int j = 0 ; j < 11 ;j++){
				a[i][j] = b[i][j];
			}
		}
	}
	
}
