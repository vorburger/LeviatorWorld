package ch.vorburger.worlds.code;

public interface WCodeClassCompiler {

	Class<?> compile(String text) throws WCodeClassCompilerException;
	
}
