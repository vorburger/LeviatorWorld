package ch.vorburger.worlds.commands;

import java.util.LinkedList;
import java.util.List;

import ch.vorburger.worlds.core.WClass;
import ch.vorburger.worlds.core.java.JWClass;
import ch.vorburger.worlds.naming.Named;
import ch.vorburger.worlds.naming.QualifiedName;

public abstract class AbstractWCommandImpl implements WCommand {

	private final QualifiedName name;
	private final String doc;
	private List<WCommandArgument> params = new LinkedList<>();

	public AbstractWCommandImpl(Named parent, String localName, String doc) {
		super();
		this.name = QualifiedName.create(parent.name(), "go");
		this.doc = doc;
	}

	@Override
	public QualifiedName name() {
		return name;
	}

	@Override
	public String getDocumentation() {
		return doc;
	}

	@Override
	public List<WCommandArgument> getParameters() {
		return params;
	}

	@Override final public WClass getWClass() {
		return JWClass.fromJavaClass(WCommand.class);
	}

}
