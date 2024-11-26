let userInput; // Declare a variable to store the user input

// Prompt the user to input a single character
do {
    userInput = prompt("Input an uppercase letter, a lowercase letter, or a number: ");
    if (userInput.length > 1) {
        console.log("Please input only one character.");
    }
} while (userInput.length > 1); // Continue prompting the user until they input only one character

// Check if the user input is a special character, an uppercase letter, a lowercase letter, or a number
if (!Number.isInteger(Number(userInput)) && userInput.toUpperCase() === userInput.toLowerCase()) {
    console.log(`${userInput} is not a letter or number.`)
} else if (!Number.isInteger(Number(userInput)) && userInput === userInput.toUpperCase()) {
    console.log(`${userInput} is an uppercase letter.`)
} else if (!Number.isInteger(Number(userInput)) && userInput === userInput.toLowerCase()) {
    console.log(`${userInput} is a lowercase letter.`)
} else if (Number.isInteger(Number(userInput))) {
    console.log(`${userInput} is a number.`)
} else {
    console.log("Invalid input.")
}