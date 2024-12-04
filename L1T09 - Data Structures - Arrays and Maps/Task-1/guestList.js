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
    if (guestList.length >= 10) {
        alert("You have already added 10 people to your guest list.");
        break;
    }
    guestList.push(guest.trim());
}

alert(`Guest list: ${guestList.join(", ")}`);