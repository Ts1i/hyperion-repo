// Lets the user input 10 numbers separated by commas, then calculates and displays the sum, average, index of the maximum and minimum numbers, and the median of the numbers.

let userNumbers = prompt("Enter 10 numbers separated by commas (please do not include spaces): ");

let numbers = userNumbers.split(",").map(Number); // Convert the string of numbers to an array of numbers

function sumArray(array) {
    let sumOfArray = array.reduce((total, currentValue) => total + currentValue, 0); // Calculate the sum of the numbers
    return sumOfArray;
}

function averageArray(array) {
    let averageOfArray = sumArray(array) / array.length; // Calculate the average of the numbers
    return Number(averageOfArray.toFixed(2)); // Round the average to two decimal places
}

function arrayIndex(array) {
    let maxIndex = array.indexOf(Math.max(...array)); // Find the index of the maximum number
    let minIndex = array.indexOf(Math.min(...array)); // Find the index of the minimum number
    return { maxIndex, minIndex };
}

function medianArray(array) {
    array.sort((a, b) => a - b); // Sort the array in ascending order
    let mid = Math.floor(array.length / 2); // Find the middle index of the array

    if (array.length % 2 === 0) {
        // If the array has an even number of elements, return the average of the two middle elements
        return (array[mid - 1] + array[mid]) / 2;
    } else {
        // If the array has an odd number of elements, return the middle element
        return array[mid];
    }
}

let sumOfArray = sumArray(numbers);
let averageOfArray = averageArray(numbers);
let { maxIndex, minIndex } = arrayIndex(numbers);
let medianOfArray = medianArray(numbers);

console.log(`The sum of the numbers is: ${sumOfArray}`);
console.log(`The average of the numbers is: ${averageOfArray}`);
console.log(`The index of the maximum number is: ${maxIndex}`);
console.log(`The index of the minimum number is: ${minIndex}`);
console.log(`The median of the numbers is: ${medianOfArray}`);