console.log("Games js loaded");

const gamesPromise = fetch("http://localhost:8080/api/games");

gamesPromise
    .then(x => x.json()) // converts the response to JSON
    .then( function(games) {
    // This function will be called when the data comes
    console.log(games);


    for (let game of games) {
        const p = document.createElement("p");
        const a = document.createElement("a");
        a.textContent = "Get a game";
        a.href = "http://localhost:8080/game.html?gameId=" + game.id;
        p.textContent = "Game " + game.id + " State " + game.state;
        const container = document.getElementById("container");
        container.appendChild(p);
        container.appendChild(a);
    }

    });

