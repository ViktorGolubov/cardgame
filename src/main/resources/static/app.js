
console.log("From Russia with LOVE");

let text = document.getElementById("text");
console.log(text.textContent);


let paragraph = document.createElement("p");
paragraph.textContent = "Have a good day";
let container = document.getElementById("container");
container.appendChild(paragraph);


let message = document.createElement("p");
let counter = 1;
text.onmouseover =  function () {
    if (counter > 1 ) {
        message.textContent = "mouse detected "  + counter + " times";
    } else {
        message.textContent = "mouse detected " + counter;
    }
    counter++;

};
container.appendChild(message);



