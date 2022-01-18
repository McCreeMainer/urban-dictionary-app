# Urban Dictionary App

App that provides [Urban Dictionary](https://www.urbandictionary.com/) resources.

## Setup

0. Install Docker
1. Clone this repository
2. Build image with `docker build -t UDApp .`
3. Run App with `docker run -it UDApp`

## Commands

- `key [key]` - specify key for [RapidAPI](https://rapidapi.com/) (App uses this key for [Urban Dictionary API](https://mashape-community-urban-dictionary.p.rapidapi.com/define) access)
- `dict [word]` - get `word` definition
- `random` - get random definition
- `help` - get available command list
- `exit` - exit Application
