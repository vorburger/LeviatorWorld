LeviatorWorld
=============

MMORPG MUD, for learning Java; Text-based, for now:

	It's Spring
	Hello Dév, your energy bar is 20, and you're in Plain which has:
	10x ashs 20x roses 10x medicalHerbs 100x dirts 50x Apples 
	Dév: What would you like to do? 
	> help
	Dév: Available commands are:
	Dév:    help 
	Dév:    quit 
	Dév:    go <Place>
	Dév:    take <Thing>
	Dév:    give <Player> <Thing> [howMany/1]
	Dév: What would you like to do? 
	> take apple
	
	Hello Michael, your energy bar is 20, and you're in Plain which has:
	10x ashs 20x roses 10x medicalHerbs 100x dirts 49x Apples 
	Michael: What would you like to do? 
	> go forest
	Michael: OK, you're now in Forest
	
	Hello Dév, your energy bar is 19, and you're in Plain which has:
	10x ashs 20x roses 10x medicalHerbs 100x dirts 49x Apples
	You yourself on you have: 1x Apples 
	Dév: What would you like to do? 
	> give michael medicalHerb
	Dév: Command failed, because: No such thing: medicalHerb, only: Apple 
	Dév: What would you like to do? 
	> give michael apple
	
	Hello Michael, your energy bar is 19, and you're in Forest which has:
	20x birchWoods 25x sticks 30x oakWoods 50x mangos
	You yourself on you have: 1x Apples 
	Michael: What would you like to do? 
	> quit
