package ch.vorburger.worlds;

/**
 * Text is just any String who's typical length is multi-line.
 * 
 * UIs should offer "opening" such objects for "editing" using something
 * "larger" than a single-line buffer command prompt.
 * 
 * This isn't the same as a WTextFile - Text could e.g. be used on something as
 * simple as... the "content" of an in-world Book object (which doesn't really
 * have a "File" connotation).
 */
public interface Text {
	// rich text formatting (e.g. MD, Wiki etc.) isn't really the intention here. Maybe sub-interfaces, later.

	String getTextContent();

	void getTextContent(String text);

}
