package ch.vorburger.worlds.naming;

/**
 * A (qualified) Name is something which identifies a Thing/Place/etc. to an end-user.
 * UIs would typically display these, and users would type/pick them.
 * Names may change over time (renaming).
 */
public class QualifiedName extends Path<String> {

	// Do not expose constructor, but use factory method, so that Path implementation could be changed later.

	static public QualifiedName create(String rootName) {
		return new QualifiedName(rootName);
	}

	static public QualifiedName create(QualifiedName parent, String localName) {
		return new QualifiedName(parent, localName);
	}
	
	protected QualifiedName(Path<String> parent, String lastSegment) {
		super(parent, lastSegment);
	}

	protected QualifiedName(String rootName) {
		super(rootName);
	}
}
