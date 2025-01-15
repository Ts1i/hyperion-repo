// Handles transitions for link clicks. Smoothens.
document.addEventListener("DOMContentLoaded", function () {
  document.body.classList.add("fade-in");

  // Link clicks
  document.querySelectorAll("a").forEach((link) => {
    link.addEventListener("click", function (e) {
      if (this.getAttribute("target") !== "_blank") {
        e.preventDefault();
        document.body.style.opacity = "0";

        setTimeout(() => {
          window.location.href = this.href;
        }, 500);
      }
    });
  });
});

// Generate unique user ID using browser data
function generateUserId() {
  const browserData =
    navigator.userAgent +
    navigator.language +
    screen.height +
    screen.width +
    new Date().getTimezoneOffset();
  return btoa(browserData).slice(0, 32);
}

// Initialize likes in session storage
function initializeLikes() {
  if (!localStorage.getItem("blogLikes")) {
    localStorage.setItem("blogLikes", JSON.stringify({}));
  }
  if (!localStorage.getItem("userLikes")) {
    localStorage.setItem("userLikes", JSON.stringify({}));
  }
}

// Handle like click
function handleLike(blogId) {
  const userId = generateUserId();
  const blogLikes = JSON.parse(localStorage.getItem("blogLikes"));
  const userLikes = JSON.parse(localStorage.getItem("userLikes"));

  if (!userLikes[userId]) {
    userLikes[userId] = {};
  }

  if (!blogLikes[blogId]) {
    blogLikes[blogId] = 0;
  }

  if (userLikes[userId][blogId]) {
    // Unlike
    blogLikes[blogId]--;
    delete userLikes[userId][blogId];
  } else {
    // Like
    blogLikes[blogId]++;
    userLikes[userId][blogId] = true;
  }

  localStorage.setItem("blogLikes", JSON.stringify(blogLikes));
  localStorage.setItem("userLikes", JSON.stringify(userLikes));

  updateLikeUI(blogId, blogLikes[blogId], userLikes[userId][blogId]);
}

// Update the UI elements related to the "like" feature of a blog post
function updateLikeUI(blogId, likeCount, isLiked) {
  const likeButton = document.querySelector(`#like-${blogId}`);
  const likeCounter = document.querySelector(`#like-count-${blogId}`);

  likeButton.classList.toggle("liked", isLiked);
  likeCounter.textContent = likeCount;
}

// Handle comments
function initializeComments() {
  if (!localStorage.getItem("blogComments")) {
    localStorage.setItem("blogComments", JSON.stringify({}));
  }
}

function showCommentModal(blogId) {
  const modal = document.createElement("div");
  modal.className = "comment-modal";
  modal.innerHTML = `
      <div class="modal-content">
          <span class="close-btn">&times;</span>
          <h3>Comments</h3>
          <div class="comments-container" id="comments-${blogId}"></div>
          <textarea id="comment-input" placeholder="Add your comment..."></textarea>
          <button class="submit-comment">Submit</button>
      </div>
  `;

  document.body.appendChild(modal);
  document.body.classList.add("modal-open");

  loadExistingComments(blogId);

  // Close button handler
  modal.querySelector(".close-btn").onclick = () => {
    document.body.removeChild(modal);
    document.body.classList.remove("modal-open");
  };

  // Submit handler
  modal.querySelector(".submit-comment").onclick = () => {
    handleCommentSubmit(blogId);
  };
}

function loadExistingComments(blogId) {
  const comments =
    JSON.parse(localStorage.getItem("blogComments"))[blogId] || [];
  const container = document.getElementById(`comments-${blogId}`);

  container.innerHTML = comments
    .map(
      (comment) => `
      <div class="comment">
          <p>${comment.text}</p>
          <small>${new Date(comment.date).toLocaleString()}</small>
      </div>
  `
    )
    .join("");
}

function updateCommentCount(blogId) {
  const blogComments = JSON.parse(localStorage.getItem("blogComments"));
  const commentCount = blogComments[blogId]?.length || 0;
  const counterElement = document.querySelector(`#comment-count-${blogId}`);
  if (counterElement) {
    counterElement.textContent = commentCount;
  }
}

function handleCommentSubmit(blogId) {
  const input = document.getElementById("comment-input");
  const text = input.value.trim();

  if (!text) return;

  const blogComments = JSON.parse(localStorage.getItem("blogComments"));
  if (!blogComments[blogId]) {
    blogComments[blogId] = [];
  }

  blogComments[blogId].push({
    text,
    date: new Date().toISOString(),
  });

  localStorage.setItem("blogComments", JSON.stringify(blogComments));
  loadExistingComments(blogId);
  updateCommentCount(blogId);
  input.value = "";
}

// Initialize likes and comments
document.addEventListener("DOMContentLoaded", () => {
  initializeLikes();
  initializeComments();

  // Get stored data with defaults if empty
  const blogLikes = JSON.parse(localStorage.getItem("blogLikes")) || {};
  const userLikes = JSON.parse(localStorage.getItem("userLikes")) || {};
  const userId = generateUserId();

  // Update comment counts for all blogs
  const blogComments = JSON.parse(localStorage.getItem("blogComments")) || {};
  Object.keys(blogComments).forEach((blogId) => {
    updateCommentCount(blogId);
  });

  // Update UI only if blog has likes
  Object.keys(blogLikes).forEach((blogId) => {
    const hasLike = userLikes[userId] && userLikes[userId][blogId];
    const likeCount = blogLikes[blogId] || 0;

    if (likeCount > 0 || hasLike) {
      updateLikeUI(blogId, likeCount, hasLike);
    } else {
      // Reset UI state if no likes
      const likeButton = document.querySelector(`#like-${blogId}`);
      if (likeButton) {
        likeButton.classList.remove("liked");
      }
    }
  });
});

// Handles contact form
function sendEmail(e) {
  e.preventDefault();

  const firstName = document.getElementById("firstName").value;
  const lastName = document.getElementById("lastName").value;
  const email = document.getElementById("email").value;
  const message = document.getElementById("message").value;

  if (!document.getElementById("privacyPolicy").checked) {
    alert("Please accept the privacy policy");
    return;
  }

  // Dummy mailto link for now
  const mailtoLink = `mailto:your-email@example.com?subject=Message from ${firstName} ${lastName}&body=${encodeURIComponent(
    message
  )}%0D%0A%0D%0AFrom: ${email}`;

  window.location.href = mailtoLink;
}