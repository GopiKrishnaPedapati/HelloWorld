package com.me;

public class ProduceConsumeTest {
	public static void main(String[] args) {
		Factory f = new Factory();
		Thread1 t1 = new Thread1(f);
		Thread2 t2 = new Thread2(f);
		t1.setName("Producer Thread");
		t2.setName("Consumer Thread");
		t1.start();
		t2.start();
	}

}

class Factory {

	public int item = 0;
	public boolean isProduced = false;

	synchronized public void produce() throws InterruptedException {
		System.out.println("Produce Mehtod");
		while (true) {
			Thread.sleep(1000);
			if (!isProduced) {
				item = item + 1;
				System.out.println("Produced :" + item);
				isProduced = true;
				notify();
				if(item==10)
					break;
			}
			wait();

		}

	}

	synchronized public void consume() throws InterruptedException {
		System.out.println("Consume Mehtod");
		while (true) {
			Thread.sleep(1000);
			if (isProduced) {
				System.out.println("consumed :" + item);
				isProduced = false;
				notify();
				if(item==10)
					break;

			}
			wait();
		}

	}
}

class Thread1 extends Thread {
	Factory f;

	Thread1(Factory f) {
		this.f = f;
	}

	@Override
	public void run() {
		try {
			f.produce();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class Thread2 extends Thread {
	Factory f;

	Thread2(Factory f) {

		this.f = f;
	}

	@Override
	public void run() {
		try {
			f.consume();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
