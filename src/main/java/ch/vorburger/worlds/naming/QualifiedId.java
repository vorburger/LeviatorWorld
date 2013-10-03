package ch.vorburger.worlds.naming;

/**
 * A (qualified) Id is something which uniquely identifies a Thing/Place/etc. to the system.
 * UIs would typically do not display these.
 * IDs will never change (TODO below!!!!).
 */
public abstract class QualifiedId // TODO extends Path<String> .. add respective constructors and static create(), like in QualifiedName 
{
	// TODO re-consider later if this could be Path<Long> ?
	
	// TODO Is this smart? If a Thing moves from one Place to another, it's QualifiedId would change.. stupid idea all together? 
	// Or just not make it based on "current World location", but simply a QN of worldsUUID/uniqueLong? 
	
}
