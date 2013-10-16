package ch.vorburger.worlds.naming;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

public class Names {
	private Names() { }
	
	private static Function<? extends Named, /*? extends*/ QualifiedName> namedToQualifiedNameFunction =
		new Function<Named, QualifiedName>() {
			@Override public QualifiedName apply(Named input) {
				// TODO when Named returns QualifiedName instead String: return input.name();
				return QualifiedName.create(/* parent?!, */ input.name());
			}
		};
		
	public static Iterable<QualifiedName> transform(Iterable<? extends Named> nameds) {
		// TODO how-to generics? return Iterables.transform(nameds, namedToQualifiedNameFunction);
		throw new IllegalStateException();
	}
}
