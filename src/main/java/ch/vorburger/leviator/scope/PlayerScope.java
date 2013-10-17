package ch.vorburger.leviator.scope;

import java.util.ArrayList;

import ch.vorburger.leviator.Place;
import ch.vorburger.leviator.Player;
import ch.vorburger.worlds.commands.AbstractWCommandImpl;
import ch.vorburger.worlds.commands.AbstractWObjectsScope;
import ch.vorburger.worlds.commands.EmptyScope;
import ch.vorburger.worlds.commands.Scope;
import ch.vorburger.worlds.commands.WCommand;
import ch.vorburger.worlds.commands.WCommandArgument;
import ch.vorburger.worlds.core.WClass;
import ch.vorburger.worlds.core.WObject;
import ch.vorburger.worlds.core.java.JWClass;
import ch.vorburger.worlds.naming.QualifiedName;

public class PlayerScope extends AbstractWObjectsScope {

	private final Player currentPlayer;

	public PlayerScope(Player p) {
		super(new WorldScope(p.world));
		this.currentPlayer = p;
	}

	@Override protected Iterable<WObject> getWObjects() {
		// TODO later use some sort of reflection util discovering annotated methods.. but, for now, just hardcode this:
		final WCommand goCommand = new AbstractWCommandImpl(currentPlayer, "go", "Player goes into Place") {
			@Override
			public void invoke(Object... parameters) {
				// TODO more checks.. 
				Place newPlace = (Place) parameters[0];
				currentPlayer.go(newPlace);
			}
		};
		
		WCommandArgument placeArg = new WCommandArgument() {
			// TODO create a command AbstractWCommandArgument ABC..
			
			@Override
			public QualifiedName name() {
				return QualifiedName.create(goCommand.name(), "place");
			}

			@Override
			public String getDocumentation() {
				return "";
			}

			@Override
			public WClass getType() {
				return JWClass.fromJavaClass(Place.class);
			}

			@Override
			public boolean isEllipsisVarArg() {
				return false;
			}

			@Override
			public Scope getScope() {
				return new EmptyScope() {
					@Override public Iterable<QualifiedName> getAll(WClass wClass) {
						return currentPlayer.go_newPlace_scope_names();
					}
				};
			}
		};
		goCommand.getParameters().add(placeArg);
		
		// cannot return Arrays.asList(goCommand);
		ArrayList<WObject> list = new ArrayList<WObject>(1);
		list.add(goCommand);
		return list;
	}
}
