package ch.vorburger.worlds.code.bsh;

import bsh.EvalError;
import bsh.Interpreter;
import ch.vorburger.worlds.code.WClassCompiler;
import ch.vorburger.worlds.code.WClassCompilerException;

public class BSHCompiler implements WClassCompiler {

	protected Interpreter i = new Interpreter();

	@Override
	public Class<?> compile(String text) throws WClassCompilerException {
		// NOT i.setStrictJava(true);
		try {
			Object result = i.eval(text);
			if (result instanceof Class<?>)
				return (Class<?>) result;
			else
				throw new IllegalArgumentException("Text didn't compile to a Class, but to: " + result.toString());
		} catch (EvalError e) {
			throw new WClassCompilerException("Compilation Failure", e);
		}
	}

}
