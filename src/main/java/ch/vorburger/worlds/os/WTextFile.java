package ch.vorburger.worlds.os;

import ch.vorburger.worlds.Text;

public class WTextFile extends WFile implements Text {

	/**
	 * Note that this returns String - the encoding of a WTextFile is assumed to
	 * be fixed (e.g. all UTF-8), known already, or could e.g. be guessed by
	 * peeking into it if it's an OS file.
	 */
	@Override
	public String getTextContent() {
		// TODO implement me... somehow - needs thought.
		throw new IllegalStateException();
	}

	@Override
	public void getTextContent(String text) {
		throw new IllegalStateException();
	}

	// BEWARE: There should normally never ever be a need to have a getCharset() or getEncoding() method here.

}
