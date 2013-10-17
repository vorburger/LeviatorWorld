package ch.vorburger.worlds.commands;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import ch.vorburger.leviator.Place;
import ch.vorburger.leviator.Player;
import ch.vorburger.leviator.World;
import ch.vorburger.leviator.scope.PlayerScopeProvider;
import ch.vorburger.leviator.tests.NoOpUI;
import ch.vorburger.worlds.core.WClass;
import ch.vorburger.worlds.core.java.JWClass;
import ch.vorburger.worlds.naming.QualifiedName;

import com.google.common.collect.Iterables;

public class MicroWorldCommandsTest {

	@Test
	public void testSomeCommands() {
		World w = new World();
		w.setUI(new NoOpUI());
		Place spawningPlace = new Place("SomePlace");
		w.getPlaces().add(spawningPlace);
		w.getPlaces().add(new Place("AnotherPlace"));
		Player p1 = new Player("Dév", spawningPlace, w);
		// TODO LATER Player p2 = new Player("Michael", spawningPlace, w);
		
		// AbstractAdaptable scopePlace = p1.inPlace;
		// TODO I'm not sure at all yet if this is really smart...
		// TODO may be instead of this it would be clearer if the World WAS-ADAPTABLE-TO ScopeProvider<Player> ?
		ScopeProvider<Player> scopeProvider = new PlayerScopeProvider(); // TODO -OR- Guice inject, later? NAH: scopePlace.adaptTo(ScopeProvider.class);
		// What's the point of this indirection? I could just adaptTo(Scope.class) ?
		Scope scope = scopeProvider.getScope(p1);
		
		JWClass<WCommand> wMethodClass = JWClass.fromJavaClass(WCommand.class);
		Iterable<QualifiedName> availableCommands = scope.getAll(wMethodClass);
		QualifiedName goCommandQN = null; // NOTE: CLI or UI will never "create" a QualifiedName itself.. just obtain it from Scope. 
		for (QualifiedName qualifiedName : availableCommands) {
			// TODO qualifiedName.getParent().toString() -or- (better perhaps) getRelativeTo(scopePlace)
			System.out.println(qualifiedName.getLastSegment() + "-(TODO)");
			if (qualifiedName.getLastSegment().equals("go"))
				goCommandQN = qualifiedName;
		}
		assertThat(goCommandQN, not(nullValue()));
		
		WCommand goCommand = scope.get(wMethodClass, goCommandQN);
		List<WCommandArgument> parameters = goCommand.getParameters();
		assertThat(parameters.size(), is(1));
		WCommandArgument placeArgument = parameters.get(0);
		WClass firstParameterType = placeArgument.getType();
		assertThat(firstParameterType.name().getLastSegment(), is("Place"));
		//ScopeProvider<WCommandArgument> commandArgScopeProvider = null; // TODO how?
		//Scope commandArgScope = commandArgScopeProvider.getScope(placeArgument);
		Scope commandArgScope = placeArgument.getScope();
		Iterable<QualifiedName> availablePlaces = commandArgScope.getAll(null); // NOT scope.getAll/*Applicable*/(placeArgument);
		QualifiedName[] availablePlacesArray = Iterables.toArray(availablePlaces, QualifiedName.class);
		assertThat(availablePlacesArray.length, is(1));
		
		Place newPlace = (Place) scope.get(firstParameterType, availablePlacesArray[0]);
		// LIKE p1.go(newPlace);
		goCommand.invoke(newPlace);
		
		assertThat(p1.inPlace, is(newPlace));
	}

}
