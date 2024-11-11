let motherName = String(prompt("What is your mother's name?"));
let streetName = String(prompt("What is the name of the street you grew up on?"));
let faveColor = String(prompt("What was your favorite color as a child?"));
let currentAge = Number(prompt("How old are you?"));
let randomNumber = Number(prompt("Enter a random number between 1 and 10"));

console.log(`In ${randomNumber} years you are going to meet your best friend named ${motherName} ${streetName}.
You will get married in ${Math.round(currentAge/randomNumber)} years and have ${currentAge % randomNumber} children.
In ${currentAge - randomNumber} years you will dye your hair ${faveColor}.`)