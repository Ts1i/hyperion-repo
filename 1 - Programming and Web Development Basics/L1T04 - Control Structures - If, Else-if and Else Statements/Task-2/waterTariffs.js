let waterUsage
let waterBill

// Water Tariffs stored as a dictionary
let waterTariff = {
    "0-6": 15.73,
    "6-10.5": 22.38,
    "10.5-35": 31.77,
    "35+": 69.76
}

// Prompt the user to input the amount of water used in kilolitres and convert the input to a positive float
waterUsage = Math.abs(parseFloat(prompt("Enter the amount of water used in kilolitres: ")))

// Calculate the water bill based on the water usage
if (waterUsage <= 6) {
    waterBill = waterUsage * waterTariff["0-6"]
} else if (waterUsage > 6 && waterUsage <= 10.5) {
    waterBill = (6 * waterTariff["0-6"]) + ((waterUsage - 6) * waterTariff["6-10.5"])
} else if (waterUsage > 10.5 && waterUsage <= 35) {
    waterBill = (6 * waterTariff["0-6"]) + ((10.5 - 6) * waterTariff["6-10.5"]) + ((waterUsage - 10.5) * waterTariff["10.5-35"])
} else {
    waterBill = (6 * waterTariff["0-6"]) + ((10.5 - 6) * waterTariff["6-10.5"]) + ((35 - 10.5) * waterTariff["10.5-35"]) + ((waterUsage - 35) * waterTariff["35+"])
}

// Display the water bill to the user, to the nearest two decimal places
console.log(`Your water bill is R ${waterBill.toFixed(2)}.`)