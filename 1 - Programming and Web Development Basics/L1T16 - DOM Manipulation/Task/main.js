// Takes a grocery list and a list of purchased items and displays the grocery list with a checkmark next to purchased items

// Grocery list arrays
let groceryList = ["apples", "grapes", "plums", "bananas"];
let purchasedItems = ["apples", "plums"];

// Function to create a checkMark
function createCheckMark() {
  let checkMark = document.createElement("span");
  checkMark.innerHTML = "x";
  checkMark.style.color = "green";
  checkMark.style.marginLeft = "5px";
  return checkMark;
}

// Function to display the grocery list and mark purchased items
function displayGroceryList(groceryList, purchasedItems) {
  // Get the ordered list element
  let groceryDisplayArea = document.querySelector("ol");

  // Error handling for missing ol element
  if (!groceryDisplayArea) {
    alert("Could not find ordered list element");
    return;
  }

  // Clears the existing list items
  groceryDisplayArea.innerHTML = "";

  // Create list items
  for (let i = 0; i < groceryList.length; i++) {
    let listItem = document.createElement("li");
    listItem.innerHTML = groceryList[i];

    // Check if item is in purchasedItems array
    if (purchasedItems.includes(groceryList[i])) {
      listItem.appendChild(createCheckMark());
    }

    groceryDisplayArea.appendChild(listItem);
  }
}

// Call the function to display the list
displayGroceryList(groceryList, purchasedItems);
