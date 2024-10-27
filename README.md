# MinecraftRoutes

- [MinecraftRoutes](#minecraftroutes)
    - [Introduction](#introduction)
    - [Installation](#installation)
    - [Usage](#usage)
    - [Contributing](#contributing)

## Introduction
This simple project is a Minecraft plugin that enables an HTTP server to be run on a Minecraft server. This allows for players to interact with the server and issue 
commands to the server via HTTP requests. The plugin is written in Java and uses the Spigot API to interact with the Minecraft server. The plugin is designed to be
extensible and allows for custom routes to be added to the server. The reason for creating this plugin was to allow for a way to interact with the server without
having to be in the game. This allows for automation of the server and for the server to be controlled by external programs.

## Installation
1. Download the latest release from the [releases page](insert github link)
2. Download a plugin capable server such as [Spigot](https://getbukkit.org/download/spigot)
3. Place the jar file in the plugins folder of your server
4. Enable port forwarding on your router and port forward port 25565 to your server, for a guide on how to do this visit [portforward.com](https://portforward.com/)
5. Start the server(agree to EULA) and connect to it using your public IP address

## Usage
The plugin is designed to be extensible and allows for custom routes to be added to the server.

## Contributing
If you would like to contribute to this project please fork the repository and submit a pull request. For this repository:
- I used the [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html) for code style.
- Maven is used for dependency management and building the project.
- Spigot is used for the Minecraft server API. JavaDocs can be found [here](https://hub.spigotmc.org/javadocs/spigot/overview-summary.html)
- Java version 21 is used for this project.
- Minecraft version 1.21

## LICENSE
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.