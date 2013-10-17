package ch.vorburger.worlds.naming;

import com.google.common.base.Function;

public class Names {
	private Names() { }
	
	static Function<? extends Named, /*? extends*/ QualifiedName> namedToQualifiedNameFunction =
		new Function<Named, QualifiedName>() {
			@Override public QualifiedName apply(Named input) {
				return input.name();
			}
		};
		
	public static Iterable<QualifiedName> transform(Iterable<? extends Named> nameds) {
		// TODO how-to generics? return Iterables.transform(nameds, namedToQualifiedNameFunction);
		throw new IllegalStateException();
	}
}
