// This code illustrates simple runtime and logical errors

let myName = "Tshwanelo"
let age = 6

Console.log(`My name is ${age} and I am ${myName} years old`)
// The above code will throw a runtime error because the Console object is not defined. The correct object is console.
// Even once the correct object is used, we will notice that the variables are in the wrong places and should be swapped in the logged string. This is a logical error.