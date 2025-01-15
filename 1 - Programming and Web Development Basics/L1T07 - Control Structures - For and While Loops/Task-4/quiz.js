// The user is asked a multiple choice question and must answer correctly to continue. If the user answers incorrectly, they are asked if they would like to try again. If the user answers correctly, they are congratulated and the game ends. If the user answers incorrectly and chooses not to try again, the correct answer is displayed and the game ends.

let userAnswer
let correctAnswer

do {
    userAnswer = prompt(    // Prompt the user with the question
        `What colour is the sky?
        a) Red
        b) Blue
        c) Green
        d) Yellow
        Enter a, b, c or d`
    ).toLowerCase() // Convert the user's answer to lowercase to make it case-insensitive
    correctAnswer = 'b' // The correct answer is 'b' (blue)
    if (userAnswer === correctAnswer) {
        alert('Correct!')
    } else {
        let continueGame = prompt("That's incorrect. Would you like to try again? yes/no ").toLowerCase() 
        if (continueGame === 'no') {
            alert('The correct answer is blue')
            break
        }
    }
} while (userAnswer !== correctAnswer)