// This code swaps the second and last digits of a 3-digit number entered by the user.

let userDigits = ''

// Get the user input and ensure that the input is a 3 digit number
while (isNaN(userDigits) || userDigits.length < 3 || Number(userDigits) < 0) {
    userDigits = prompt('Enter a a positive number that is at least 3 digits long.')
    if (isNaN(userDigits) || userDigits.length < 3 || Number(userDigits) < 0) {
        alert('Invalid input. Please enter a positive number that is at least 3 digits long.')
    }
}
  
// Swap the second and last digits
let swappedDigits = []
let index = userDigits.length-1

for (let i=0; i<=index; i++) {
  if (i==1) {
    swappedDigits[1] = userDigits[index]
  } else if (i==index) {
    swappedDigits[index] = userDigits[1]
  } else {
    swappedDigits[i] = userDigits[i]
  }
}

swappedDigits = swappedDigits.join('') // Convert the new array to a string

// Display the original and swapped numbers to the user
alert(
    `The original number is: ${userDigits}
The swapped number is: ${swappedDigits}`
)