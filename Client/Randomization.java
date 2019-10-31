	public void Randomization() {
		int[] a = new int[4];
		for (int i = 0; i < 4; i++)
			a[i] = 0;//标记每个人牌的数量
		for (int i = 0; i < 13; i++)//牌的点数
			for (int j = 0; j < 4; j++) {//牌的花色
				int row = (new Random().nextInt(100)) % 4;//随机的人
				if (a[row] < 25)
					player[row][a[row]++] = "" + color[j] + num[i];
				else {
					while (a[row] >= 25)//牌发满了就顺延续到下个人
						row = (row + 1) % 4;
					player[row][a[row]++] = "" + color[j] + num[i];
				}

			}
	}
