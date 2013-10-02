package ch.vorburger.worlds.code;

public enum CodeLang {
	// TODO This should, LATER, be an ch.vorburger.meta.wenum-based "open enum", which new pluggable modules can extend...
	
	BeanShell(".bsh", "TODO"),
	Java(".java", "text/java"),
	JavaScript(".js", "TODO"),
	Smalltalk(".st", "TODO");
	
	// NOTE: All fields in an Enum should always be final!
	public final String fileExtension;
	public final String contentType;  // does this have to be a list?
	CodeLang(String fileExtension, String contentType) {
		this.fileExtension = fileExtension;
		this.contentType = contentType;
	}
}
