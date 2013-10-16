package ch.vorburger.leviator.scope;

import ch.vorburger.leviator.Player;
import ch.vorburger.worlds.commands.Scope;
import ch.vorburger.worlds.commands.ScopeProvider;

public class PlayerScopeProvider implements ScopeProvider<Player> {

	@Override
	public Scope getScope(Player p) {
		return new PlayerScope(p);
	}

}
