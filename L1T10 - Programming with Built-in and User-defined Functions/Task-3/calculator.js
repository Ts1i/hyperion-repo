// Lets the user enter two numbers and an operation and then returns the result of the operation on the two numbers.

let num1 = Number(prompt("Enter the first number: "));
let num2 = Number(prompt("Enter the second number: "));
let operator = prompt("Enter the operation ('add', 'subtract', 'multiply', or 'divide'): ").toLowerCase();

// function to calculate the result of the operation on the two numbers
function calculator(num1, num2, operator) {
    if (operator === 'add') {
        let calc = num1 + num2;
        return { calc: calc, operation: '+' }; // return the sum of num1 and num2
    } else if (operator === 'subtract') {
        let calc = num1 - num2;
        return { calc: calc, operation: '-' }; // return the difference of num1 and num2
    } else if (operator === 'multiply') {
        let calc = num1 * num2;
        return { calc: calc, operation: 'x' }; // return the product of num1 and num2
    } else if (operator === 'divide') {
        let calc = num1 / num2;
        return { calc: calc, operation: '/' }; // return the division of num1 and num2
    } else {
        return { calc: "Invalid operation", operation: '' }; // return "Invalid operation" if the operation is not 'add', 'subtract', 'multiply', or 'divide'
    }
}

let { calc, operation } = calculator(num1, num2, operator); // call the calculator function and deconstruct the returned object into the answer "calc" and the operation "operation"

if (operation) {
    console.log(`${num1} ${operation} ${num2} is ${calc}`); // log the result of the operation on the two numbers
} else {
    console.log(calc); // log the invalid operation message
}