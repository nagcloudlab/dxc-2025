

console.log("Web UI is running");


// using DOM api
// -----------------------------

const cardTextDiv = document.querySelector(".card-text");
const btnEle = document.querySelector(".btn-primary");

btnEle.addEventListener("click", () => {
    // change the text of the card
    cardTextDiv.innerHTML = "You clicked the button!";
    // change the color of the card
    cardTextDiv.style.color = "red";
    // change the background color of the card
    cardTextDiv.style.backgroundColor = "yellow";
    // change the font size of the card
    cardTextDiv.style.fontSize = "2rem";
});


// using DOM & XHR/Fetch API
// -----------------------------

const top5TodosBtn = document.querySelector("#top5-todos");
const todosList = document.querySelector("#todos-table-body");
top5TodosBtn.addEventListener("click", () => {
    // fetch top 5 todos from the API
    fetch("https://jsonplaceholder.typicode.com/todos?_limit=5") // async http request
        .then(response => response.json())
        .then(data => {
            const todoRows = data.map(todo => { 
                return `<tr>
                            <td>${todo.id}</td>
                            <td>${todo.title}</td>
                            <td>${todo.completed ? "Yes" : "No"}</td>
                        </tr>`;
            });
            // clear the existing rows
            todosList.innerHTML = "";
            // add the new rows
            todosList.innerHTML = todoRows.join("");
        })
        .catch(error => console.error("Error fetching todos:", error));
});


//--