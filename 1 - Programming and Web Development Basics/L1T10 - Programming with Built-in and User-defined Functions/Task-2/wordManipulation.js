// Lets the user input a word, then performs the following operations on the word:
// Replaces every second character with a special character of your choice.
// Reverses the word.
// Converts every 6th character to uppercase.
// Outputs the ASCII values of the characters in the word.

let userWord = prompt("Please enter a word: ");
let specialChar = "!";

// Function to replace every second character in a word with a special character
function replaceEverySecondCharWithSpecialChar(word, specChar) {
    let newWord = "";
    for (let i = 0; i < word.length; i++) {
        if (i % 2 === 0) {
            newWord += word[i]; // If the character is at an even index, add it to the new word
        } else {
            newWord += specChar; // If the character is at an odd index, add the special character to the new word
        }
    }
    return newWord;
}

// Function to reverse a word
function reverseWord(word) {
    let newWord = "";
    for (let i = word.length - 1; i >= 0; i--) {
        newWord += word[i]; // Add the characters of the word in reverse order to the new word
    }
    return newWord;
}

// Function to convert every 6th character in a word to uppercase
function everySixthCharToUpper(word) {
    let newWord = "";
    for (let i = 0; i < word.length; i++) {
        if (i % 6 === 0) {
            newWord += word[i].toUpperCase(); // If the character is at the 6th index, add it in uppercase to the new word
        } else {
            newWord += word[i]; // Otherwise, add the character as is to the new word
        }
    }
    return newWord;
}

// Function to get the ASCII values of the characters in a word
function getAsciiChar(word) {
    let asciiChars = [];
    for (let i = 0; i < word.length; i++) {
        asciiChars.push(word.charCodeAt(i)); // Get the ASCII value of each character in the word and add it to the array
    }
    return asciiChars;
}

let specialCharReplace = replaceEverySecondCharWithSpecialChar(userWord, specialChar);
let reversedWord = reverseWord(userWord);
let sixthCharToUpper = everySixthCharToUpper(userWord);
let asciiChars = getAsciiChar(userWord);

console.log(`The word with every second character replaced with ${specialChar}: ${specialCharReplace}`);
console.log(`The reversed word: ${reversedWord}`);
console.log(`The word where every 6th letter is uppercase: ${sixthCharToUpper}`);
console.log(`The ASCII values of the characters in the word: ${asciiChars.join(", ")}`);