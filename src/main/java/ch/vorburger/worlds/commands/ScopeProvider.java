package ch.vorburger.worlds.commands;


public interface ScopeProvider<Context> {

	Scope getScope(Context p);

}
