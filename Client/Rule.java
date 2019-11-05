package game;


import java.util.Random;

public class Rule {
	/*
	 * 判断出牌和自己上一次储存的牌信息是否相同， 若相同则说明自己可以任意出牌(符合规则)， 否则，必须出比上家大的牌
	 */
	public static boolean Issame(String[] x, String[] y) {
		boolean result = true;
		if (x.length == y.length)
			for (int i = 0; i < x.length; i++) {
				if (!x[i].equals(y[i]))
					result = false;
			}
		else
			result = false;
		return result;
	}

	/*
	 * 判断出牌是否合法
	 */
	public static boolean Isregular(String[] x) {
		boolean result = false;
		int length = x.length;
		int[] y = new int[length];
		for (int i = 0; i < length; i++) {
			y[i] = Integer.parseInt(x[i].substring(1, x[i].length()));
		}
		switch (length) {
		case 1:
			if (duizi(y) != 0) {
				result = true;
				Playsound.play("audio//Man//" + duizi(y) + ".wav");
			}
			break;
		case 2:
			if (duizi(y) != 0) {
				result = true;
				Playsound.play("audio//Man//dui" + duizi(y) + ".wav");
			}
			break;
		case 3:
			if (duizi(y) != 0) {
				result = true;
				Playsound.play("audio//Man//sange.wav");
			}
			break;
		case 4:
			if (duizi(y) != 0) {
				result = true;
				Playsound.play("audio//Man//zhadan.wav");
			}
			
			
			break;
		case 5:
			if (shunzi(y) != 0) {
				result = true;
				Playsound.play("audio//Man//shunzi.wav");
			}
			if (sandaier(y) != 0) {
				result = true;
				Playsound.play("audio//Man//sandaier.wav");
			}
			break;
		case 6:
			if (shunzi(y) != 0) {
				result = true;
				Playsound.play("audio//Man//shunzi.wav");
			}
			if (sansan(y) != 0) {
				result = true;
				Playsound.play("audio//Man//sange.wav");
			}
			if (liandui(y) != 0) {
				result = true;
				Playsound.play("audio//Man//liandui.wav");
			}
			break;
		case 7:
			if (shunzi(y) != 0) {
				result = true;
				Playsound.play("audio//Man//shunzi.wav");
			}
			break;
		case 8:
			if (shunzi(y) != 0) {
				result = true;
				Playsound.play("audio//Man//shunzi.wav");
			}
			if (liandui(y) != 0) {
				result = true;
				Playsound.play("audio//Man//liandui.wav");
			}
			break;
		case 9:
			if (shunzi(y) != 0) {
				result = true;
				Playsound.play("audio//Man//shunzi.wav");
			}
			if (sansan(y) != 0)
				result = true;
			break;
		case 10:
			if (shunzi(y) != 0) {
				result = true;
				Playsound.play("audio//Man//shunzi.wav");
			}
			if (feiji10(y) != 0) {
				result = true;

			}
			if (liandui(y) != 0) {
				result = true;
				Playsound.play("audio//Man//liandui.wav");
			}
			break;
		case 11:
			if (shunzi(y) != 0) {
				result = true;
				Playsound.play("audio//Man//shunzi.wav");
			}
			break;
		case 12:
			if (shunzi(y) != 0) {
				result = true;
				Playsound.play("audio//Man//shunzi.wav");
			}
			if (sansan(y) != 0) {
				result = true;
				Playsound.play("audio//Man//sange.wav");
			}
			if (liandui(y) != 0) {
				result = true;
				Playsound.play("audio//Man//liandui.wav");
			}
			break;
		case 13:
			if (shunzi(y) != 0) {
				result = true;
				Playsound.play("audio//Man//shunzi.wav");
			}
		case 14:
			if (liandui(y) != 0) {
				result = true;
				Playsound.play("audio//Man//liandui.wav");
			}
		case 15:
			if (sansan(y) != 0) {
				result = true;
				Playsound.play("audio//Man//sange.wav");
			}
			if (feiji10(y) != 0) {
				result = true;

			}
		case 16:
			if (liandui(y) != 0) {
				result = true;
				Playsound.play("audio//Man//liandui.wav");
			}
		case 17:
//			if (shunzi(y) != 0) {
//				result = true;
//				Playsound.play("audio//Man//shunzi.wav");
//			}
		case 18:
			if (liandui(y) != 0) {
				result = true;
				Playsound.play("audio//Man//liandui.wav");
			}
			if (sansan(y) != 0) {
				result = true;
				Playsound.play("audio//Man//sange.wav");
			}
		case 19:
//			if (shunzi(y) != 0) {
//				result = true;
//				Playsound.play("audio//Man//shunzi.wav");
//			}
		case 20:
			if (liandui(y) != 0) {
				result = true;
				Playsound.play("audio//Man//liandui.wav");
			}
			if (feiji10(y) != 0) {
				result = true;

			}
		case 21:
			if (sansan(y) != 0) {
				result = true;
				Playsound.play("audio//Man//sange.wav");
			}
		case 22:
			if (liandui(y) != 0) {
				result = true;
				Playsound.play("audio//Man//liandui.wav");
			}
		case 23:
//			if (shunzi(y) != 0) {
//				result = true;
//				Playsound.play("audio//Man//shunzi.wav");
//			}
		case 24:
			if (sansan(y) != 0) {
				result = true;
				Playsound.play("audio//Man//sange.wav");
			}
			if (liandui(y) != 0) {
				result = true;
				Playsound.play("audio//Man//liandui.wav");
			}
		case 25:
		
			if (feiji10(y) != 0) {
				result = true;

			}
		case 26:
			if (liandui(y) != 0) {
				result = true;
				Playsound.play("audio//Man//liandui.wav");
			}
		case 27:
			if (sansan(y) != 0) {
				result = true;
				Playsound.play("audio//Man//sange.wav");
			}
		case 28:
//			if (shunzi(y) != 0) {
//				result = true;
//				Playsound.play("audio//Man//shunzi.wav");
//			}
		case 29:
//			if (shunzi(y) != 0) {
//				result = true;
//				Playsound.play("audio//Man//shunzi.wav");
//			}
		case 30:
			if (sansan(y) != 0) {
				result = true;
				Playsound.play("audio//Man//sange.wav");
			}
		case 31:
//			if (shunzi(y) != 0) {
//				result = true;
//				Playsound.play("audio//Man//shunzi.wav");
//			}
		case 32:
//			if (shunzi(y) != 0) {
//				result = true;
//				Playsound.play("audio//Man//shunzi.wav");
//			}
		case 33:
			if (sansan(y) != 0) {
				result = true;
				Playsound.play("audio//Man//sange.wav");
			}
			
			break;
		}
		return result;
	}
	

	/*
	 * 判断自己的盘是否大于上家的牌
	 */
	public static boolean Isregular(String[] x, String[] y) {
		boolean result = false;
		int lengthX = x.length;
		int lengthY = y.length;

		
		int[] dataX = new int[lengthX];
		int[] dataY = new int[lengthY];
		for (int i = 0; i < lengthX; i++) {
			dataX[i] = Integer.parseInt(x[i].substring(1, x[i].length()));
			
		}
		for (int i = 0; i < lengthY; i++) {
			dataY[i] = Integer.parseInt(y[i].substring(1, y[i].length()));
			
		}
		
		if(wangzha(dataX)==1)//首先判断王炸
			return true;
		if(wangzha(dataY)==1)//首先判断王炸
			return false;
		
		if (lengthX != lengthY && lengthX < 4  )//牌数不相同直接是小
			return false;
			
		if (lengthX >= 4 && lengthX != lengthY && duizi(dataX)==0&&wangzha(dataX)==0) {//牌数不相同直接是小
			return false;
		}
		if (lengthX >= 4 && lengthY < lengthX  && duizi(dataY)!=0) {//数目不同的炸弹的比较
			Playsound.play("audio/Man/zhadan.wav");
			return true;
		}
		if (lengthX >= 4 && lengthY > lengthX  && duizi(dataY)==0) {//炸弹的判断
			Playsound.play("audio/Man/zhadan.wav");
			return true;
		}
		
	
			
		
		
		
		
		switch (lengthX) {//判断牌相同的情况
		case 1:
			if (duizi(dataX) > duizi(dataY)) {
				result = true;
			}
			break;
		case 2:
			if (duizi(dataX) > duizi(dataY))
				result = true;
			break;
		case 3:
			if (duizi(dataX) > duizi(dataY))
				result = true;
			break;
		case 4:
			if (duizi(dataX) > duizi(dataY))
				result = true;
			break;
		case 5:
			if (shunzi(dataX) > shunzi(dataY))
				result = true;
			if (sandaier(dataX) > sandaier(dataY))
				result = true;
			if (duizi(dataX) > duizi(dataY))//炸弹
				result = true;
			break;
		case 6:
			if (shunzi(dataX) > shunzi(dataY))
				result = true;
			if (sansan(dataX) > sansan(dataY))
				result = true;
			if (liandui(dataX) > liandui(dataY))
				result = true;
			break;
		case 7:
			if (shunzi(dataX) > shunzi(dataY))
				result = true;
			break;
		case 8:
			if (shunzi(dataX) > shunzi(dataY))
				result = true;
			if (liandui(dataX) > liandui(dataY))
				result = true;
			break;
		case 9:
			if (shunzi(dataX) > shunzi(dataY))
				result = true;
			if (sansan(dataX) > sansan(dataY))
				result = true;
			
			break;
		case 10:
			if (shunzi(dataX) > shunzi(dataY))
				result = true;
			if (liandui(dataX) > liandui(dataY))
				result = true;
			
			if (feiji10(dataX) > feiji10(dataY))
				result = true;
			
			
			break;
		case 11:
			if (shunzi(dataX) > shunzi(dataY))
				result = true;
			break;
		case 12:
			if (shunzi(dataX) > shunzi(dataY))
				result = true;
			if (sansan(dataX) > sansan(dataY))
				result = true;
			if (liandui(dataX) > liandui(dataY))
				result = true;
			break;
		case 13:
			if (shunzi(dataX) > shunzi(dataY))
				result = true;
		case 14:
			if (liandui(dataX) > liandui(dataY))
				result = true;
		case 15:
			if (sansan(dataX) > sansan(dataY))
				result = true;
		case 16:
			if (liandui(dataX) > liandui(dataY))
				result = true;
		case 17:
//			if (shunzi(dataX) > shunzi(dataY))
//				result = true;
		case 18:
			if (liandui(dataX) > liandui(dataY))
				result = true;
		case 19:
//			if (shunzi(dataX) > shunzi(dataY))
//				result = true;
		case 20:
			if (liandui(dataX) > liandui(dataY))
				result = true;
			if (feiji20(dataX) > feiji20(dataY))
				result = true;
			
		case 21:
			if (sansan(dataX) > sansan(dataY))
				result = true;
		case 22:
			if (liandui(dataX) > liandui(dataY))
				result = true;
		case 23:
//			if (shunzi(dataX) > shunzi(dataY))
//				result = true;
		case 24:
			if (sansan(dataX) > sansan(dataY))
				result = true;
			if (liandui(dataX) > liandui(dataY))
				result = true;
		case 25:
			if (feiji25(dataX) > feiji25(dataY))
				result = true;
		case 26:
			if (liandui(dataX) > liandui(dataY))
				result = true;
		case 27:
			if (sansan(dataX) > sansan(dataY))
				result = true;
		case 28:
//			if (shunzi(dataX) > shunzi(dataY))
//				result = true;
		case 29:
//			if (shunzi(dataX) > shunzi(dataY))
//				result = true;
		case 30:
			if (sansan(dataX) > sansan(dataY))
				result = true;
		case 31:
//			if (shunzi(dataX) > shunzi(dataY))
//				result = true;
		case 32:
//			if (shunzi(dataX) > shunzi(dataY))
//				result = true;
		case 33:
			if (sansan(dataX) > sansan(dataY))
				result = true;
			
			break;
		}
		if (result)
			Playsound.play("audio//Man//dani" + new Random().nextInt(100) % 3
					+ ".wav");
		return result;}
	

	/*
	 * 
	 */
//	private static int sansanerer(int[] y) {//三带二
//		if (y[0] == y[1] && y[2] == y[3] && y[4] == y[5] && y[5] == y[6]
//				&& y[6] == y[7] - 1 && y[7] == y[8] && y[8] == y[9])
//			return y[4];
//		else if (y[0] == y[1] && y[2] == y[3] && y[3] == y[4]
//				&& y[4] == y[5] - 1 && y[5] == y[6] && y[6] == y[7]
//				&& y[8] == y[9])
//			return y[2];
//		else if (y[0] == y[1] && y[1] == y[2] && y[2] == y[3] - 1
//				&& y[3] == y[4] && y[4] == y[5] && y[6] == y[7] && y[8] == y[9])
//			return y[0];
//		return 0;
//	}
//	
	
//	private static int sandaiyi(int[] y) {  //三带1
//		if (y[0] == y[1] && y[2] == y[3] )
//			return y[0];
//		else if(y[1] == y[2] && y[2] == y[3])
//			return y[1];
//	
//	
//		return 0;
//	}

	/*
	 * n个三相连
	 */
	private static int sansan(int[] x) {
		for (int i = 0; i < x.length - 2; i = i + 3) {
			if (x[i] != x[i + 1] || x[i + 1] != x[i + 2])
				return 0;
		}
		return x[0];
	}

	/*
	 * 顺子
	 */
	public static int shunzi(int[] x) {
		for (int i = 1; i < x.length; i++) {
			if (x[i - 1] != x[i] + 1)
				return 0;
		}
		return x[0];
	}

	/*
	 * 相同的牌
	 */
	public static int duizi(int[] x) {//对子和炸弹
		for (int i = 1; i < x.length; i++) {
			if (x[i - 1] != x[i])
				return 0;
		}
		return x[0];
	}

	/*
	 * 三带二
	 */
	public static int sandaier(int[] x) {
		if (x[0] == x[1] && x[2] == x[3] && x[3] == x[4])
			return x[2];
		else if (x[0] == x[1] && x[1] == x[2] && x[3] == x[4])
			return x[0];
		return 0;
	}
	
	//飞机
	public static int feiji10(int[] x) {
		if (x[0] == x[1] && x[2] == x[3] &&x[4] == x[5] && x[5] == x[6] 
				&& x[7] == x[8]&& x[8] == x[9])
			return x[4];
		else if  (x[0] == x[1] && x[2] == x[3] &&x[3] == x[4] && x[5] == x[6] 
				&& x[6] == x[7]&& x[8] == x[9])
			return x[2];
		else if  (x[0] == x[1] && x[1] == x[2] &&x[3] == x[4] && x[4] == x[5] 
				&& x[6] == x[7]&& x[8] == x[9])
			return x[0];
		return 0;
	}
	
	public static int feiji15(int[] x) {
		if (x[0] == x[1] && x[1] == x[2] &&x[3] == x[4] && x[4] == x[5] 
				&& x[6] == x[7]&& x[7] == x[8]&& x[9] == x[10]&& x[11] == x[12]&& x[13] == x[14])
			return x[0];
		if (x[0] == x[1] && x[2] == x[3] &&x[3] == x[4] && x[5] == x[6] 
				&& x[6] == x[7]&& x[8] == x[9]&& x[9] == x[10]&& x[11] == x[12]&& x[13] == x[14])
			return x[2];
		if (x[0] == x[1] && x[2] == x[3] &&x[4] == x[5] && x[5] == x[6] 
				&& x[7] == x[7]&& x[8] == x[9]&& x[10] == x[11]&& x[11] == x[12]&& x[13] == x[14])
			return x[4];
		if (x[0] == x[1] && x[2] == x[3] &&x[4] == x[5] && x[6] == x[7] 
				&& x[7] == x[8]&& x[9] == x[10]&& x[10] == x[11]&& x[12] == x[13]&& x[13] == x[14])
			return x[5];
		return 0;
	}
	
	public static int feiji20(int[] x) {
		if (x[0] == x[1] && x[1] == x[2] &&x[3] == x[4] && x[4] == x[5] 
				&& x[6] == x[7]&& x[7] == x[8]&& x[9] == x[10]&& x[10] == x[11]&& x[12] == x[13]
						&& x[14] == x[15]&& x[16] == x[17]&& x[18] == x[19])
			return x[0];
		if (x[0] == x[1] && x[2] == x[3] &&x[3] == x[4] && x[5] == x[6] 
				&& x[6] == x[7]&& x[8] == x[9]&& x[9] == x[10]&& x[11] == x[12]&& x[12] == x[13]
						&& x[14] == x[15]&& x[16] == x[17]&& x[18] == x[19])
			return x[2];
		if (x[0] == x[1] && x[2] == x[3] &&x[4] == x[5] && x[5] == x[6] 
				&& x[7] == x[8]&& x[8] == x[9]&& x[10] == x[11]&& x[11] == x[12]&& x[13] == x[14]
						&& x[14] == x[15]&& x[16] == x[17]&& x[18] == x[19])
			return x[4];
		if (x[0] == x[1] && x[2] == x[3] &&x[4] == x[5] && x[6] == x[7] 
				&& x[7] == x[8]&& x[9] == x[10]&& x[10] == x[11]&& x[12] == x[13]&& x[13] == x[14]
						&& x[15] == x[16]&& x[16] == x[17]&& x[18] == x[19])
			return x[6];
		if (x[0] == x[1] && x[2] == x[3] &&x[4] == x[5] && x[6] == x[7] 
				&& x[8] == x[9]&& x[9] == x[10]  && x[11] == x[12]&& x[12] == x[13] && x[14] == x[15]
						&& x[15] == x[16] && x[17] == x[18]&& x[18] == x[19])
			return x[8];
		return 0;
	}
	
	public static int feiji25(int[] x) {
		if (x[0] == x[1] && x[2] == x[3] && x[3] == x[4])
			return x[2];
		else if (x[0] == x[1] && x[1] == x[2] && x[3] == x[4])
			return x[0];
		return 0;
	}
	
	public static int feiji30(int[] x) {
		if (x[0] == x[1] && x[2] == x[3] && x[3] == x[4])
			return x[2];
		else if (x[0] == x[1] && x[1] == x[2] && x[3] == x[4])
			return x[0];
		return 0;
	}
	/*
	 * n个对子相连
	 */
		
  public static int liandui(int[] x) {
		for (int i = 0; i < x.length - 1; i = i + 2) {
			if (x[i] != x[i + 1])
				return 0;
		}
		return x[0];
	}

		
	public static int wangzha(int[] x) {
		int flag=1;
		int rj=0;
		int bj=0;
		for (int i = 0; i < x.length; i++) {
			if(x[i]<16) {
				return 0;	
			}
			else if(x[i]==16) {
				bj++;
			}
			else if(x[i]==17) {
				rj++;
			}
		}
		if (x.length==4  && bj==2&&rj==2)
			return 1;
		return 0;
		
			
	}

}
