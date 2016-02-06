# NoPickup
Bukkit plugin to block certain items being picked up.

Use commands to add items that cannot be picked up to the list in the config.
Players will not be able to pickup the items listed in the defined list.
Multiple permissions for the plugin.
Block gets removed off the ground via the entity UUID.
Add / Remove items using simple commands.
Reload the config using simple command.

Operators have instant access to every command on the plugin.

Commands:
	/nopickup - Tells you version number and developer: GetSomePanda, and help page.
	/nopickup help - Tells you all the commands and what they do.
	/nopickup add <entityID> - Add an entitys item id and it will not be able to be picked up by players.
	/nopickup remove <entityID> - Remove entity from the list using item id.
	/nopickup reload - Reload the config.yml.
	
Permissions:
	nopickup.bypass - Player with this can pickup any item off the ground even if its listed.
	nopickup.help - Access to /nopickup help
	nopickup.add - Access to /nopickup add
	nopickup.remove - Access to /nopickup remove