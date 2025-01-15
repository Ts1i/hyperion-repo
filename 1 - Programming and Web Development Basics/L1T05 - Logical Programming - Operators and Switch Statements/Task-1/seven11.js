let userInput;

// Get the number from the user
do {
    userInput = Number(prompt("Enter a number: "))
    if (isNaN(userInput)) {
        console.log("Please input a number.")
    }
} while (isNaN(userInput))

// Check if the number is divisible by 7 and 11
if (userInput % 7 === 0 && userInput % 11 === 0) {
    console.log(`${userInput} is divisible by 7 and 11.`)    
// Check if the number is divisible by 7
} else if (userInput % 7 === 0) {
    console.log(`${userInput} is divisible by 7.`)
// Check if the number is divisible by 11
} else if (userInput % 11 === 0) {
    console.log(`${userInput} is divisible by 11.`)
} else {
    console.log(`${userInput} is not divisible by 7 or 11.`)
}