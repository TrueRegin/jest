# Jest 0.1.1

---
## Prerequesites
Before using this project you need to have Maven installed. You can learn how to set it up [here](https://maven.apache.org/install.html).

[> Maven Download <](https://maven.apache.org/download.cgi)

---
## Setup
Run `mvn install` to download all necessary dependencies for the project.

Download the 1.13.2 server jar from [the PaperMC legacy builds](https://papermc.io/legacy)

Double click `run.bat` in the `server/` directory to start your server. Make sure to set the EULA to true.

Run `mvn package` to build the code into a jar and place it into the `server/plugins/` directory. The plugin will automatically overwrite the old version of the plugin, so you can run `/reload confirm` on the Development MC Server to reload the plugin.


> You are now set up and ready to start tinkering with the Jest plugin.

---
## Overview
The plugin jest is designed to increase the difficulty of survival Minecraft.
Specifically, hardcore mode.

---
## Installation
Download *"Jest0-1-1.jar"* and put it in the plugins folder of a bukkit server.

---
## Commands
Currently, there are 3 commands:
| Command | Usage |
|---------|-------|
|*-*|Upon first time spawning in a world, it will spawn the player to a random x and z value withing *10000 blocks* of spawn.|
|/setMobDifficulty [easy/medium/hard]|Using this command without parameters will toggle it off and on. **Easy** forces all Zombies to have *Wooden Swords*, forces Creepers to have *Resistance 1*, and Spiders have *Speed 1* and *Resistance 1*. **Medium** forces all Zombies to have *Iron Swords*, forces Skeletons to have *Speed 2*, forces Creepers to have *Resistance 2* and *Speed 1*, and Spiders have *Speed 2* and *Resistance 2*. **Hard** forces all Zombies to have *Diamond Swords*, forces Skeletons to have *Invisibility*, forces Creepers to have *Fire Resistance* *Resistance 4* and *Speed 3*, and Spiders have *Fire Resistance* *Resistance 4* and *Speed 4*.|
|/togglePlayerEffects|Toggles the difficulty player effects on or off. **Running** for *250 ticks* will give the player *Confusion 6* and *Hunger 1* for *100 ticks*. **Swimming** for *100 ticks* will give the player *Blindness 3* for *75 ticks*.|
|/getJestStatus|Shows status of whats enabled, and the difficulty settings if applicable.|

---
## Code Notes
None.

---
