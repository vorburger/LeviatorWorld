package ch.vorburger.worlds.code.bsh;

import bsh.EvalError;
import bsh.Interpreter;
import ch.vorburger.worlds.code.WCodeClassCompiler;
import ch.vorburger.worlds.code.WCodeClassCompilerException;

public class BSHCompiler implements WCodeClassCompiler {

	protected Interpreter i = new Interpreter();

	@Override
	public Class<?> compile(String text) throws WCodeClassCompilerException {
		// NOT i.setStrictJava(true);
		try {
			Object result = i.eval(text);
			if (result instanceof Class<?>)
				return (Class<?>) result;
			else
				throw new IllegalArgumentException("Text didn't compile to a Class, but to: " + result.toString());
		} catch (EvalError e) {
			throw new WCodeClassCompilerException("Compilation Failure", e);
		}
	}

}
