package ch.vorburger.worlds.naming;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.Nullable;

import ch.vorburger.base.Preconditions;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

/**
 * Sequence of T with a notion of hierarchy.

 * Intentionally abstract, because specific subclass should give it semantic (Path of/for what?).
 *
 * @param <T> type of segment, typically String, but could be something else (as long as it has a valid toString())
 */
public abstract class Path<T> { // TODO implements CharSequence??  Comparable<QualifiedName>, why? 
	
	protected static Joiner joiner = Joiner.on(".").useForNull("??whyNull??");
	
	// TODO it would be nicer if there was a subclasses for PathRoot (without parent), but I'm too dumb to figure out how to combine that with QualifiedName & QualifiedId right now :( 
	// alternative implementation choice: protected final T[] segments;
	protected final @Nullable Path<T> parent;
	protected final T lastSegment;

	private final int hashCode;
	
	protected Path(T rootSegment) {
		this.parent = null;
		this.lastSegment = Preconditions.checkNotNull(rootSegment);
		this.hashCode = rootSegment.hashCode();
	}
	
	protected Path(Path<T> parent, T lastSegment) {
		this.parent = Preconditions.checkNotNull(parent);
		this.lastSegment = Preconditions.checkNotNull(lastSegment);
		// NOT ... Arrays.hashCode(parent.getSegments().toArray());
		this.hashCode = 31 * parent.hashCode + lastSegment.hashCode();
	}

//	public Path<T> append(String segment) {
//		Preconditions.checkNotNull(segment);
//  TODO need to figure out generics black magic to make this work..	
//		return new Path<T>(this, segment);
//	}
	
	@Override public final int hashCode() {
		return hashCode;
	}

	@Override public final boolean equals(@Nullable Object obj) {
		if (obj==this)
			return true;
		if (obj instanceof Path) {
			@SuppressWarnings("unchecked")
			Path<T> other = (Path<T>) obj;
			if (hashCode != other.hashCode)
				return false;
			if (!getClass().equals(other.getClass()))
				// @see ch.vorburger.worlds.naming.PathTest.testEqualsBetweenDifferentPathSubclasses()
				return false;
			if (parent != null) {
				return parent.equals(other.parent)
						&& lastSegment.equals(other.lastSegment);
			}
			else {
				return lastSegment.equals(other.lastSegment);
			}
		}
		return false;
	}

	@Override public final String toString() {
		if (parent != null)
			return joiner.join(joiner.join(parent.getSegments()), getLastSegment());
		else
			return lastSegment.toString();
	}
		
	public final List<T> getSegments() {
		if (parent != null) {
			List<T> parentSegments = parent.getSegments();
			List<T> segments = new ArrayList<T>(parentSegments.size() + 1);
			segments.addAll(parentSegments);
			segments.add(lastSegment);
			return segments;
		} else {
			return ImmutableList.of(lastSegment);
		}
	}
	
	public final T getLastSegment() {
		return lastSegment;
	}

	// TODO org.eclipse.xtext.naming.QualifiedName.equalsIgnoreCase(Object) could be useful?
	// TODO org.eclipse.xtext.naming.QualifiedName.startsWith(QualifiedName, boolean) could be useful?

	// TODO methods for "relative" Path processing could be useful?
	//	 public boolean startsWith(Path prefix)
	//   public Path relativeTo(Path rootPath) ?
	
}
