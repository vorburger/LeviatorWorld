package ch.vorburger.worlds.code;

public interface WClassCompiler {

	Class<?> compile(String text) throws WClassCompilerException;
	
}
