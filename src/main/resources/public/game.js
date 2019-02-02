console.log("Games js loaded");

const urlParams = new URLSearchParams(window.location.search);
const gameId = urlParams.get('gameId');

const gamePromise = axios.get("http://localhost:8080/api/games/" + gameId);

gamePromise
    //.then(x => x.json()) // converts the response to JSON
    .then(function(response) {
        // This function will be called when the data comes

        const game = response.data;

        let gameContainer = document.getElementById("game-container");

        const p = document.createElement("p");
        p.textContent = "Game " + game.id + " State " + game.state + " player names: " + game.playerNames;
        gameContainer.appendChild(p);

        const a = document.createElement("a");
        a.textContent = "Go back to game list";
        a.href = "http://localhost:8080/games.html";
        gameContainer.appendChild(a);


    });