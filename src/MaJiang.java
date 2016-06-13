
public class MaJiang {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello");
		MaJiang mj = new MaJiang();
		mj.mStart();
		System.out.println("总牌：");
		mj.mPrint();
		System.out.println("A:");
		mj.p[0].mPrint();
		for(int i = 0 ; i < 3 ; i++){
			for(int j = 1 ; j < 10;j++){
				System.out.print(mj.p[0].A[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		for(int i = 0 ; i < 3 ; i++){
			for(int j = 1 ; j < 10;j++){
				System.out.print(mj.p[0].B[i][j]);
			}
			System.out.println();
		}
		System.out.println("B:");
		mj.p[1].mPrint();
		System.out.println("C:");
		mj.p[2].mPrint();
		System.out.println("D:");
		mj.p[3].mPrint();
		
	}
	public int result;
	private static final int MAX = 108;
	private int left = MAX;
	private int[][] A = new int[3][11];
	public  Player[] p = new Player[4];
	private int[][] AA =  new int[3][11];
	public void mStart(){
		for(int j = 0 ; j< 4;j++){
			p[j] = new Player();
			for(int i  = 0 ; i < 13 ;i++){
				Tile t = mTakeNum();
				p[j].mGet(t.color,t.num);
				
			}
		}
		
	}
	public void mRun(){	
		int start = (int) (Math.random()*4);//随机选择先手摸牌的玩家
		while(true){
			Tile t = mTakeNum();
			if(t!=null)
				p[start].mGet(t.color, t.num);
			if(p[start].mIsWin()){
//				System.out.println("Get hu:"+t.color+":::"+t.num);
//				System.out.println("zm赢家："+start+": "+p[start].leftHu+"\t"+p[start].tingNum+"\t"+p[start].willKeep+"\t"+p[start].partition+"\t");
//				System.out.print("p[i]A:\t");p[start].mPrint();
//				System.out.print("p[i]B:\t");p[start].mPrintB();
//				System.out.print("p[i]tingList:\t");for(Tile ting: p[start].tingTile)System.out.print(ting.color+"+"+ting.num);System.out.println();
//				for(Tile tt :p[start].tingTile)System.out.print(tt.color+":::"+tt.num+"\t");System.out.println();
				result = start+1;
				return;
			}
			else{
				if(mIsEnd()){//流局
					result = 0;
					return;
				}
				Tile  tt = p[start].mPut();
				if(tt!=null)
					AA[tt.color][tt.num]++;				
				mSetData(start);//判听
				for(int i = (start+1)%4 ; i != start ; i = (i+1)%4){//System.out.println("70");					
					if(p[i].mIsWin(tt.color,tt.num)){
//						System.out.println("Get hu:"+tt.color+":::"+tt.num);
//						System.out.println("hu赢家："+start+": "+p[start].leftHu+"\t"+p[start].tingNum+"\t"+p[start].willKeep+"\t"+p[start].partition+"\t");
//						System.out.print("p[i]A:\t");p[i].mPrint();
//						System.out.print("p[i]B:\t");p[i].mPrintB();
//						System.out.print("p[i]tingList:\t");for(Tile ting: p[i].tingTile)System.out.print(ting.color+"+"+ting.num);System.out.println();
						//for(Tile ttt :p[i].tingTile)System.out.println(ttt.color+":"+ttt.num);
						result = i+1;
						return;
					}
					else if(p[i].mPeng(tt)){
						start = i;
						p[start].mGet(tt.color, tt.num);
						tt = p[start].mPut();
						AA[tt.color][tt.num]++;
						mSetData(start);
					}
				}	
				start=(start+1)%4;//下一玩家
			}
				
		}	
	}
	public void mSetData(int start){
		if(!p[start].isTing&&p[start].mIsTing()){//若听牌，则获取所需数据
			p[start].isTing = true;
			for(int i = (start+1)%4 ; i != start ;i=(i+1)%4){//已听的玩家数
				if(p[i].isTing)
					p[start].tingNum++;
			}
			p[start].leftHu = p[start].tingTile.size() * 4;//听的牌还剩余的数目
			int col = -1;
			for(int i = 0 ; i < p[start].tingTile.size();i++){
				col = p[start].tingTile.get(i).color;
				int n = p[start].tingTile.get(i).num;
				p[start].leftHu -= AA[col][n];
				for(int j = (start+1)%4; j != start; j = (j+1)%4){
					if(p[j].isNeed(p[start].tingTile.get(i))){
						p[start].willKeep++;	//听的牌对几个玩家属于有用牌
					}
				}
			}
			int temp = 0;
			for(int i = 0 ; i< 11;i++){
				temp+=AA[col][i];
			}
			p[start].partition = (float)temp/(MAX-left-13*4);//所需花色已打出去的比例
		}
	}
	public boolean mIsEnd(){
		if(left <= 0){
			return true;
		}
		return false;
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
	private Tile mTakeNum(){//随机取牌
		Tile t = new Tile();
		while(true){
			int color = (int)(Math.random()*3);
			int num = (int) (1+Math.random()*9);
			if(A[color][num]<4){
				A[color][num]++;
				t.color = color;
				t.num = num;
				left--;
				break;
				
			}
		}
		return t;
	}
}
