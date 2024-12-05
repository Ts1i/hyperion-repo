// Lets the user enter a message and encrypts it using a Caesar cypher.

let userMessage = prompt("Enter a message to encrypt:");
let cypherMessage = "";
let shift = 15;

function encryptMessage(message, shift) {
    let result = "";
    for (let i = 0; i < message.length; i++) {
        let char = message[i];

        // Check if the character is a letter
        if (char.match(/[a-z]/i)) {
            let code = message.charCodeAt(i);
            // Encrypt upper case letters
            if (code >= 65 && code <= 90) {
                char = String.fromCharCode(((code - 65 + shift) % 26) + 65);
            } 
            // Encrypt lower case letters
            else if (code >= 97 && code <= 122) {
                char = String.fromCharCode(((code - 97 + shift) % 26) + 97);
            }
        }
        // Append the character to the result. Skip the caesar transformation for non-letters.
        result += char;
    }
    return result;
}

cypherMessage = encryptMessage(userMessage, shift);
alert(`Original message: ${userMessage}
Encrypted message: ${cypherMessage}`);