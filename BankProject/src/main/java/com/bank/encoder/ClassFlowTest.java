package com.bank.encoder;

class Parent{
	public void walk() {
		System.out.println("parent walk");
	}
	
	public void run() {
		System.out.println("parent run");
		walk();
	}
}

class Child extends Parent{
	@Override
	public void walk() {
		System.out.println("child walk");
		super.walk();
	}
	
	@Override
	public void run() {
		System.out.println("child run");
		super.run();
	}
}

public class ClassFlowTest {

	public static void main(String[] args) {
		Parent p = new Child();
		p.run();
	}

}
