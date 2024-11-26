let corner = []
let side = []
let i = 0
let rightAngle = 90

// Get the corner and side values from the user
do {
    corner[i] = Number(prompt(`Enter corner${i+1}: `))
    side[i] = Number(prompt(`Enter side${i+1}: `))
    i++
} while (i<4)

// Check if the sum of the corners are equal to 360, which is a necessary condition for a quadrilateral
if (corner.reduce((total, currentValue) => total + currentValue, 0) == 360) {
    // Check if the corner values are equal to 90 and the side values are equal
    if (corner.every(value => value === rightAngle) && side.every(value => value === side[0])) {
        console.log("It's a square.")
    // Check if the corner values are equal to 90 and the side values are equal in opposite pairs
    } else if (corner.every(value => value === rightAngle) && side[0] === side[2] && side[1] === side[3]) {
        console.log("It's a rectangle.")
    // Check if the side values are equal and the corner values are equal in opposite pairs
    } else if (side.every(value => value === side[0]) && corner[0] === corner[2] && corner[1] === corner[3] && corner[0] !== rightAngle) {
        console.log("It's a rhombus.") 
    // Check if the corner values are equal in opposite pairs and the side values are equal in opposite pairs
    } else if (corner[0] === corner[2] && corner[1] === corner[3] && corner[0] !== rightAngle && side[0] === side[2] && side[1] === side[3]) {
        console.log("It's a parallelogram.")
    } else {
        console.log("Apologies, we have not labeled this quadrilateral.")
    }

} else {
    console.log("This is not a quadrilateral.")
}