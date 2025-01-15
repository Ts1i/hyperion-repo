// Allows the user to enter the names of guests for a party, and then displays the list of guests. Maximum 10 guests can be added.

let guestList = [];

while (true) {
    let guest = prompt("Enter who you'd like to invite to the party, or 'done' when you've added all your intended attendees: ");
    
    // Check if the user entered a valid name
    if (guest === null || guest.trim() === "" || !/^[a-zA-Z]+$/.test(guest.trim())) {
        alert("Please enter a valid name.");
        continue;
    }
    
    // Check if the user is done adding guests
    if (guest.toLowerCase().trim() === "done") {
        break;
    }
    
    // If the user tries to add more than 10 guests, ask if they would like to replace someone on the list (y/n)
    if (guestList.length >= 10) {
        let answer = prompt("You have already added 10 people to your guest list. Would you like to replace someone on the list with this person? y/n:");

        // If the user responds with a "y", prompt them to enter the name of the person they would like to replace
        if (answer.toLowerCase().trim() === "y") {
            let replace;
            do {
                replace = prompt(`Who would you like to replace?
                ${guestList.join(", ")}`);
                
                // Check that the guest exists on the list
                if (!guestList.includes(replace.trim())) {
                    alert("That person is not on the list. Please enter a valid name.");
                }
            } while (!guestList.includes(replace.trim()));

            // Check that the new guest is not already on the list
            if (guestList.includes(guest.trim())) {
                alert("The new guest is already on the list. Please enter a different name.");
            } else {
                guestList[guestList.indexOf(replace.trim())] = guest.trim();
                break;
            }
        }
    } else {
        guestList.push(guest.trim());
    }
}

alert(`Guest list: ${guestList.join(", ")}`);