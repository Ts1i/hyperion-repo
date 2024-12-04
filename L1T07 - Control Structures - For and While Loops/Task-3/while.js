// This program will prompt the user to enter numbers until they enter -1. The program will then calculate the average of the numbers entered and display it to the user.
let userNumbers = []
let number = ''

// Loop to get user input and add to array
while (number !== -1) {
  number = Number(prompt('Enter a number:'))
  if (number === -1) {
    break // Exit the loop if the user enters -1
  }
  if (isNaN(number)) {
    alert('That is not a number, please try again')
  } else {
    userNumbers.push(number)
  }
}

// Calculate the average of the input numbers
let denominator = userNumbers.length
let sum = userNumbers.reduce((total, currentValue) => total + Number(currentValue), 0)
let average = sum / denominator

alert(`The average of the numbers is ${average}`)