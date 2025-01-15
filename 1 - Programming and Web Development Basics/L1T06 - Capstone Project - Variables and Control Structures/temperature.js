// This is a termperature converter that converts between Celsius, Fahrenheit, and Kelvin.
// The user is prompted to input the starting unit, the value to be converted, and the destination unit.
// The program then converts the value to the destination unit and displays the result to the user.

let inputValue
let convertedValue
let startingUnit
let destinationUnit

// Get the startingUnit
do {
    startingUnit = prompt(
        `In which metric is the temperature you are converting (i.e. the starting unit)?
        C - Celsius
        F - Fahrenheit
        K - Kelvin`
    ).toUpperCase().trim()
    if (startingUnit !== "C" && startingUnit !== "F" && startingUnit !== "K") {
        alert("Invalid input. Please try again.")
    }
} while (startingUnit !== "C" && startingUnit !== "F" && startingUnit !== "K")

// Get the inputValue
do {
    inputValue = prompt(
        `Please input the value that you are converting (please do not include the units): `
    ).trim()
    if (isNaN(inputValue) || inputValue === "") {
        alert("Invalid input. Please try again.")
    }
} while (isNaN(inputValue) || inputValue === "")

inputValue = parseFloat(inputValue) // Convert inputValue to a number

// Get the destinationUnit
do {
    destinationUnit = prompt(
        `Which metric are you converting to (i.e. the destination unit)?
        C - Celsius
        F - Fahrenheit
        K - Kelvin`
    ).toUpperCase().trim()
    if (destinationUnit !== "C" && destinationUnit !== "F" && destinationUnit !== "K") {
        alert("Invalid input. Please try again.")
    }
} while (destinationUnit !== "C" && destinationUnit !== "F" && destinationUnit !== "K")

switch (destinationUnit) {
    case "C":
        if (startingUnit === "F") {
            convertedValue = (inputValue - 32) * 5 / 9
        }
        else if (startingUnit === "K") {
            convertedValue = inputValue - 273.15
        }
        else {
            convertedValue = inputValue
        }
        break
    case "F":
        if (startingUnit === "C") {
            convertedValue = inputValue * 9 / 5 + 32
        }
        else if (startingUnit === "K") {
            convertedValue = (inputValue - 273.15) * 9 / 5 + 32
        }
        else {
            convertedValue = inputValue
        }
        break
    case "K":
        if (startingUnit === "C") {
            convertedValue = inputValue + 273.15
        }
        else if (startingUnit === "F") {
            convertedValue = (inputValue - 32) * 5 / 9 + 273.15
        }
        else {
            convertedValue = inputValue
        }
        break
}

alert(`${inputValue} ${startingUnit} is ${convertedValue.toFixed(1)} ${destinationUnit}.`)