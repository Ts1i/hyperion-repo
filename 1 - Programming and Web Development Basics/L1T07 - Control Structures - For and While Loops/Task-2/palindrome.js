// This code gets a user's input and checks with the input  is a palindrome.

let userInput

// I leveraged chatGPT to learn how to apply a regular expression to check if a string contains only alphabetic characters

// Get the user input and ensure that the input is a word containing only alphabetic characters
do {
    userInput = prompt('Enter a word to check if it is a palindrome: ').trim();
    if (!/^[a-zA-Z]+$/.test(userInput)) {
        alert('Invalid input. Please enter a word containing only alphabetic characters.');
    }
} while (!/^[a-zA-Z]+$/.test(userInput));

let reverseIndex = userInput.length - 1;
let i = 0;
let isPalindrome = true;

// Check that the charaacters are equivalent towards the center of the string, until the indeces are equivalent
// If the characters are not equivalent, the string is not a palindrome

while (i <= reverseIndex / 2) {
    if (userInput[i] !== userInput[reverseIndex - i]) {
        alert(`${userInput} is not a palindrome`);
        isPalindrome = false;
        break
    }
    i++;
}

if (isPalindrome) {
    alert(`${userInput} is a palindrome`)    
};
