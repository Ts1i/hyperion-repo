// Translates English words into Latin using a dictionary

let dictionary = new Map([
    ["hello", "salve"],
    ["goodbye", "vale"],
    ["please", "quaeso"],
    ["thank you", "gratias ago tibi"],
    ["yes", "ita"],
    ["no", "non"],
    ["friend", "amicus"],
    ["love", "amor"],
    ["peace", "pax"],
    ["war", "bellum"]
])

// Prompt the user for a word to translate. Display an alert if the word is not in the dictionary.
do {
    let word = prompt(`Which of the following words would you like to translate:
${Array.from(dictionary.keys()).join(", ")}`).trim();
    if (!dictionary.has(word)) {
        alert(`The word ${word} is not in the dictionary. Please try again.`);
        continue;
    }
    alert(`The Latin translation of ${word} is ${dictionary.get(word)}`);
    break;
} while (true);