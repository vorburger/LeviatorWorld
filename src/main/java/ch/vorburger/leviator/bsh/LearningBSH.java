package ch.vorburger.leviator.bsh;

import bsh.Interpreter;

public class LearningBSH {

	public static void main(String[] args) throws Exception {
		Interpreter i = new Interpreter();
		i.set("i", 123);
		Class<?> clazz = (Class<?>) i.eval(" 2*123; class A { } class B { String toString() { return \"hello, world\"; } }");
		System.out.println(clazz.newInstance().toString());
	}
	
}
