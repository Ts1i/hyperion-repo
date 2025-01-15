// Takes a grocery list and a list of purchased items and displays the grocery list with a checkmark next to purchased items

/*
NOTE FOR THE CODE REVIEWER: I couldn't understand the last 4 bulletpoints of the task, just above the "CSS" section in the notes. 
What I've assumed is that the user can click on the grocery items to mark them as purchased, and click on the X symbol to remove them from the list. 
I've implemented this functionality in the code below. Please let me know if I've misunderstood the requirements.
*/

// Grocery list array
let groceryList = ["apples", "grapes", "plums", "bananas"];

// Checked items
let checkedItems = [];

// Function to create a checkMark
function createCheckMark() {
  let checkMark = document.createElement("span");
  checkMark.innerHTML = "\u00D7";
  checkMark.className = "close";
  checkMark.style.color = "red";
  checkMark.style.marginLeft = "5px";

  // Add click event listener to checkMark
  checkMark.addEventListener("click", function () {
    const listItem = this.parentElement;
    const itemText = listItem.textContent.slice(0, -1); // Remove X symbol

    // Remove from groceryList array
    const index = groceryList.indexOf(itemText);
    if (index > -1) {
      groceryList.splice(index, 1);
    }

    // Remove from DOM
    listItem.remove();
  });

  return checkMark;
}

// Function to display the grocery list and mark purchased items
function displayGroceryList(groceryList) {
  // Get the ordered list element
  let groceryDisplayArea = document.getElementById("currentGroceryList");

  // Clears the existing list items
  groceryDisplayArea.innerHTML = "";

  // Create list items
  for (let i = 0; i < groceryList.length; i++) {
    let listItem = document.createElement("li");
    listItem.innerHTML = groceryList[i];
    // Add click handler to toggle checked state
    listItem.addEventListener("click", function () {
      this.classList.toggle("checked");
      const itemText = this.textContent.slice(0, -1);

      // Update checkedItems array
      const index = checkedItems.indexOf(itemText);
      if (index === -1) {
        checkedItems.push(itemText);
      } else {
        checkedItems.splice(index, 1);
      }
    });

    // Add checked class if item is in checkedItems
    if (checkedItems.includes(groceryList[i])) {
      listItem.classList.add("checked");
    }

    listItem.appendChild(createCheckMark());
    groceryDisplayArea.appendChild(listItem);
  }
}

// Display the grocery list and add event listener for form submission
document.addEventListener("DOMContentLoaded", function () {
  // Displays groceries after the DOM has loaded
  displayGroceryList(groceryList);

  // Binds html form object to "form" variable
  const form = document.getElementById("groceryForm");

  form.addEventListener("submit", function (event) {
    event.preventDefault();

    const input = document.getElementById("groceryInput");
    if (!input) {
      alert("Could not find grocery input");
      return;
    }

    const newItem = input.value.trim().toLowerCase();

    // Validate input
    if (!newItem) {
      alert("Please enter a grocery item");
      return;
    }

    // Check for duplicates
    if (groceryList.includes(newItem)) {
      alert("This item is already in the list");
      input.value = "";
      return;
    }

    // Add new item to grocery list and display
    groceryList.push(newItem);
    input.value = "";
    displayGroceryList(groceryList);
  });
});
