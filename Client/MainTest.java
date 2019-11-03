package game;

public class MainTest implements Runnable {
	private final Thread thread1;
	private final Thread thread2;
	private final Thread thread3;
	private final Thread thread4;

	public MainTest() {
		thread1 = new Thread(this);
		thread2 = new Thread(this);
		thread3 = new Thread(this);
		thread4 = new Thread(this);
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
	}

	public static void main(String[] args) {
		new MainTest();
	}

	@Override
	public void run() {
		{
			{
				if (Thread.currentThread() == thread1) {
					Welcome p1 = new Welcome();
					
				} else if (Thread.currentThread() == thread2) {
					Welcome p2 = new Welcome();
				}

				else if (Thread.currentThread() == thread3) {
					Welcome p3 = new Welcome();

				}
				else if (Thread.currentThread() == thread4) {
					Welcome p4 = new Welcome();

				}
			}
		}
	}
}
