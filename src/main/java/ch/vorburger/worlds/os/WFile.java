package ch.vorburger.worlds.os;

import ch.vorburger.worlds.naming.QualifiedName;

import com.google.common.net.MediaType;

// TODO implement.. idea here is that this is NOT a java.io.File, but an "in-world"
public class WFile implements WFolderOrFile {
	// TODO extends WObject which extends DynamicAdaptable?

	protected QualifiedName name = QualifiedName.create("HasNoName");

	@Override
	public QualifiedName name() {
		return name;
	}

	/**
	 * Content Type, AKA MIME Type, AKA Internet media type.
	 * @return MediaType (from Guava, better than javax.activation.MimeType
	 */
	public MediaType getContentType() {
		// TODO implement this based on something like:
		//   * javax.activation.MimetypesFileTypeMap
		//   * Files.probeContentType
		//   * java.net.FileNameMap = java.net.URLConnection#getFileNameMap()
		// but keep it pluggable/injectable/configurable..
		// @see http://www.rgagnon.com/javadetails/java-0487.html ?
		throw new IllegalStateException();
	}
	
	// UNLESS REALLY NEEDED, there probably won't actually be an InputStream
	// getContent() (and never byte[]) here.. and certainly no String
	// getContent() !  Maybe it's just a protected but never public method?
	
	// TODO hierarchy stuff (directory, parent, URI etc.) although that may belong to a higher up class, actually; e.g. Named with QualifiedName
	// TODO rename(), delete() etc. usual methods - although those may belong to a higher up generic class, actually
	// TODO permissions stuff - although those may belong to a higher up generic class, actually
	
	// BEWARE: There should never ever be a save() method, nor a load() here - Worlds is REACTIVE!
}
