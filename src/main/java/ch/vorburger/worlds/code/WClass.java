package ch.vorburger.worlds.code;

import org.eclipse.jdt.annotation.Nullable;

import ch.vorburger.worlds.Validating;
import ch.vorburger.worlds.os.WTextFile;

/**
 * Executable Class.
 * 
 * Note that how this extends WTextFile is not a coincidence, but an explicit
 * (very early days) design choice: Code in Worlds should always be available in
 * source form...
 */
public class WClass extends WTextFile implements Validating {

	protected @Nullable Class<?> jClass;

	// TODO compile, and re-compile if its content changes! how will we have generic change tracking??
	// TODO verify invariant jClass.getSimpleName() == this.getName() - fileExtension, always; else ch.vorburger.worlds.Validating errors (just like if it doesn't compile)
	
	public Class<?> getJClass() {
		if (jClass != null)
			return jClass;
		else
			throw new IllegalStateException();
	}

	public CodeLang getCodeLang() {
		for (CodeLang codeLang : CodeLang.values()) {
			if (this.getContentType().equals(codeLang.contentType))
				return codeLang;
		}
		throw new IllegalStateException("Could not determine CodeLang for " + this.toString());
	}
	
	@Override
	public boolean isValid() {
		return jClass != null;
	}
	
	// BEWARE: There should never ever be a compile() method here - Worlds is REACTIVE!
}
