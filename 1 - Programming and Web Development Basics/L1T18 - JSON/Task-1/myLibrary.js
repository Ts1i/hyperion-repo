// Allows the user to add, edit, and delete books from a library

// Constructor function for book
class Book {
    constructor(title, author, year, genre, review) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.genre = genre;
        this.review = review;
    }
}

// Array to store books
let myLibrary = [];

// Handle form submission
document.getElementById('bookForm').addEventListener('submit', function(e) {
    e.preventDefault();
    
    // Get form values
    const title = document.getElementById('bookTitle').value.trim();
    const author = document.getElementById('bookAuthor').value.trim();
    const year = document.getElementById('bookYear').value.trim();
    const genre = document.getElementById('bookGenre').value.trim();
    const review = document.getElementById('bookReview').value.trim();
    
    // Create new book
    const newBook = new Book(title, author, year, genre, review);
    addBookToLibrary(newBook);
    
    // Clear form
    this.reset();
});

// Add book to library (pushes to the end of the list)
function addBookToLibrary(book) {
    myLibrary.push(book);
    saveBooks();
    displayBooks();
}

// Save books to active session storage
function saveBooks() {
    try {
        sessionStorage.setItem('books', JSON.stringify(myLibrary));
    } catch (error) {
        console.error('Error saving to sessionStorage:', error);
    }
}

// Display books using template
function displayBooks() {
    const bookCards = document.getElementById('bookCards');
    const template = document.getElementById('bookCardTemplate');
    
    // Clear existing cards
    bookCards.innerHTML = '';
    
    // Create a card for each book
    myLibrary.forEach((book, index) => {
        const card = template.content.cloneNode(true);
        
        // Fill in card content
        card.querySelector('.book-title').textContent = book.title;
        card.querySelector('.book-author').textContent = book.author;
        card.querySelector('.book-year').textContent = book.year;
        card.querySelector('.book-genre').textContent = book.genre;
        card.querySelector('.book-review').textContent = book.review;
        
        // Delete card
        card.querySelector('.delete-btn').addEventListener('click', () => {
            if(confirm('Are you sure you want to delete this book?')) {
                myLibrary.splice(index, 1);
                saveBooks();
                displayBooks();
            }
        });
        
        // Edit card (behaviour is to fill form with existing data)
        card.querySelector('.edit-btn').addEventListener('click', () => {
            document.getElementById('bookTitle').value = book.title;
            document.getElementById('bookAuthor').value = book.author;
            document.getElementById('bookYear').value = book.year;
            document.getElementById('bookGenre').value = book.genre;
            document.getElementById('bookReview').value = book.review;
            
            myLibrary.splice(index, 1);
            saveBooks();
            displayBooks();
        });
        
        // Append card to the list
        bookCards.appendChild(card);
    });
}

// Load books on page load with error handling
document.addEventListener('DOMContentLoaded', () => {
    try {
        const savedBooks = sessionStorage.getItem('books');
        if (savedBooks) {
            myLibrary = JSON.parse(savedBooks);
            displayBooks();
        }
    } catch (error) {
        console.error('Error loading books:', error);
        sessionStorage.removeItem('books'); // Clear corrupted data if any
    }
});