// Prompts the user to enter income and expenses, calculates disposable income, and prompts for savings amount

// Constructor function for income
class Income {
  constructor(name, amount, recurring) {
    this.name = name;
    this.amount = amount;
    this.recurring = recurring;
  }
}

// Array to store income
let myIncome = [];

// Create 5 incomes
myIncome.push(new Income("Salary", 30000, true));
myIncome.push(new Income("Freelance", 10000, false));
myIncome.push(new Income("Investments", 5000, true));
myIncome.push(new Income("Rental income", 7000, true));
myIncome.push(new Income("Consulting", 20000, false));

// Constructor function for expenses
class Expense {
  constructor(name, amount, recurring) {
    this.name = name;
    this.amount = amount;
    this.recurring = recurring;
  }
}

// Array to store expenses
let myExpenses = [];

// Create 5 expenses
myExpenses.push(new Expense("Rental expense", 7000, true));
myExpenses.push(new Expense("Groceries", 2000, true));
myExpenses.push(new Expense("Utilities", 1000, true));
myExpenses.push(new Expense("Internet", 800, true));
myExpenses.push(new Expense("Flat tyre", 10000, false));

// Function to save to sessionStorage
function saveToStorage() {
  sessionStorage.setItem("myIncome", JSON.stringify(myIncome));
  sessionStorage.setItem("myExpenses", JSON.stringify(myExpenses));
}

// Function to load from sessionStorage
function loadFromStorage() {
  try {
    const savedIncome = sessionStorage.getItem("myIncome");
    const savedExpenses = sessionStorage.getItem("myExpenses");

    if (savedIncome) myIncome = JSON.parse(savedIncome);
    if (savedExpenses) myExpenses = JSON.parse(savedExpenses);
  } catch (error) {
    console.error("Error loading from sessionStorage:", error);
  }
}

// Display current income items
let incomeList = "Current Income Items:\n";
myIncome.forEach((item, index) => {
  incomeList += `${index + 1}. ${item.name}: R${item.amount} (${
    item.recurring ? "Recurring" : "One-time"
  })\n`;
});

// Save initial income & expenses
saveToStorage();

// Prompt user for new income details
let newIncomeName = prompt(incomeList + "\nEnter name for new income:");
let newIncomeAmount = parseFloat(prompt("Enter amount:"));
let newIncomeRecurring = confirm(
  "Is this a recurring income? (OK for Yes, Cancel for No)"
);

// Add new income if name and amount were provided
if (newIncomeName && newIncomeAmount) {
  myIncome.push(new Income(newIncomeName, newIncomeAmount, newIncomeRecurring));
  saveToStorage(); // Save updated income
}

// Display current expense items
let expenseList = "Current Expense Items:\n";
myExpenses.forEach((item, index) => {
  expenseList += `${index + 1}. ${item.name}: R${item.amount} (${
    item.recurring ? "Recurring" : "One-time"
  })\n`;
});

// Prompt user for new expense details
let newExpenseName = prompt(expenseList + "\nEnter name for new expense:");
let newExpenseAmount = parseFloat(prompt("Enter amount:"));
let newExpenseRecurring = confirm(
  "Is this a recurring expense? (OK for Yes, Cancel for No)"
);

// Add new expense if name and amount were provided
if (newExpenseName && newExpenseAmount) {
  myExpenses.push(
    new Expense(newExpenseName, newExpenseAmount, newExpenseRecurring)
  );
  saveToStorage(); // Save updated expenses
}

// Load data when page loads
document.addEventListener("DOMContentLoaded", loadFromStorage);

// Calculate total income and expenses
function calculateTotalIncome() {
  return myIncome.reduce((total, item) => total + item.amount, 0);
}

function calculateTotalExpenses() {
  return myExpenses.reduce((total, item) => total + item.amount, 0);
}

// Calculate and display disposable income
function displayDisposableIncome() {
  try {
    const totalIncome = calculateTotalIncome();
    const totalExpenses = calculateTotalExpenses();
    const disposableIncome = totalIncome - totalExpenses;

    // Format for currency
    const formatter = new Intl.NumberFormat("en-ZA", {
      style: "currency",
      currency: "ZAR",
    });

    // Display disposable income
    const disposableIncomeElement = document.getElementById("disposableIncome");
    if (!disposableIncomeElement) {
      throw new Error("Disposable income element not found");
    }
    disposableIncomeElement.textContent = formatter.format(disposableIncome);

    // Prompt for savings
    const savingsPrompt = prompt(
      `Your disposable income is ${formatter.format(
        disposableIncome
      )}.\nHow much would you like to save?`
    );

    if (savingsPrompt === null || savingsPrompt.trim() === "") {
      return; // User cancelled or entered empty string
    }

    const savingsAmount = parseFloat(savingsPrompt);

    // Validate savings amount
    if (isNaN(savingsAmount) || savingsAmount < 0) {
      alert("Please enter a valid amount");
      return;
    }

    if (savingsAmount > disposableIncome) {
      alert("You cannot save more than your disposable income");
      return;
    }

    // Calculate and display remaining amount
    const remainingAmount = disposableIncome - savingsAmount;
    alert(
      `After saving ${formatter.format(savingsAmount)},\n` +
        `you have ${formatter.format(remainingAmount)} remaining`
    );
  } catch (error) {
    console.error("Error in displayDisposableIncome:", error);
    alert("An error occurred while processing your budget");
  }
}

// Ensure DOM is loaded before running
document.addEventListener("DOMContentLoaded", () => {
  try {
    loadFromStorage();
    displayDisposableIncome();
  } catch (error) {
    console.error("Error initializing budget:", error);
  }
});
