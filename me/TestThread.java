package com.me;

public class TestThread {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		A a = new A();
		Thread11 t1=new Thread11(a);
		Thread11 t2=new Thread11(a);
		t1.setName("Gopi");
		t2.setName("Divya");
		t1.start();
		t2.start();
		for (int j = 100; j < 150; j++) {
			System.out.println("Current Thread :" + Thread.currentThread().getName() + ":" + j);
	
	}
		
}
}

class A {

	public int i = 1;

	synchronized public void accessData(){
		for (int j = 0; j < 10; j++) {
			i = i + 1;
			System.out.println("Current Thread :" + Thread.currentThread().getName() + ":" + i);
		
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

class Thread11 extends Thread
{
	A a;
	Thread11(A a)
	{
		this.a=a;
	}
	@Override
	public void run() {
	a.accessData();
	}
}

