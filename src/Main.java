
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Tile t = new Tile(1,3);
		//System.out.println("ttt:"+t.color+t.num);//return;
		int count = 0;
		System.out.println("剩余听张\t"+"已听玩家\t"+"威胁玩家\t"+"同花色比例\t"+"玩家结果\t");
		int jia = 0;
		while(count<10000){
			MaJiang mj = new MaJiang();
			mj.mStart();
			//System.out.println("count:"+count);
			mj.mRun();
			Player p = new Player();
			
			if(mj.result != 0){
				if(mj.result == 1)jia++;
				count++;
				for(int i = 0 ; i < 4 ;i++){
					p = mj.p[i];
					if(p.isTing){
						int res = 0;
						if(i+1 == mj.result){
							res = 1;
						}
						System.out.println(p.leftHu+"\t"+p.tingNum+"\t"+p.willKeep+
								"\t"+(float)Math.round(p.partition*100)/100+"\t\t"+res);
					}
				}
			}
		}
		System.out.println("甲胜率："+(float)jia/10000);
	}

}
