console.log("Games js loaded");

//const urlParams = new URLSearchParams(window.location.search);
//const gameId = urlParams.get('gameId')

const gamesPromise = axios.get("http://localhost:8080/api/games");
// const gamesPromise = fetch("http://localhost:8080/api/games/" + gameId);

gamesPromise
    //.then(x => x.json()) // converts the response to JSON
    .then( function(game) {
        // This function will be called when the data comes
        console.log(game);



            const p = document.createElement("p");
            p.textContent = "Game " + game.id + " State " + game.state + " player names: " + game.playerNames;
            const container = document.getElementById("games-container");
            container.appendChild(p);


    });