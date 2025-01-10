// Main script for the Capstone Project - JavaScript Fundamentals

// Initialize the page with fade-in effect, likes, comments and stored data
$(document).ready(function () {
  $("body").addClass("fade-in");

  $("a").on("click", function (e) {
    if ($(this).attr("target") !== "_blank") {
      e.preventDefault();
      $("body").css("opacity", "0");

      setTimeout(() => {
        window.location.href = $(this).attr("href");
      }, 500);
    }
  });

  initializeLikes();
  initializeComments();
  loadStoredData();
});

// Initialize likes in local storage
function initializeLikes() {
  if (!localStorage.getItem("blogLikes")) {
    localStorage.setItem("blogLikes", JSON.stringify({}));
  }
  if (!localStorage.getItem("userLikes")) {
    localStorage.setItem("userLikes", JSON.stringify({}));
  }
}

// Initialize comments in local storage
function initializeComments() {
  if (!localStorage.getItem("blogComments")) {
    localStorage.setItem("blogComments", JSON.stringify({}));
  }
}

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

//   Toggle like button and update like count
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

function updateLikeUI(blogId, likeCount, isLiked) {
  $(`#like-${blogId}`).toggleClass("liked", isLiked);
  $(`#like-count-${blogId}`).text(likeCount);
}

// Comment modal overlays the full viewport and displays comments for a blog post. Can be dismissed by clicking the close button.
function showCommentModal(blogId) {
  const modal = $("<div>", {
    class: "comment-modal",
    html: `
            <div class="modal-content">
                <span class="close-btn">&times;</span>
                <h3>Comments</h3>
                <div class="comments-container" id="comments-${blogId}"></div>
                <textarea id="comment-input" placeholder="Add your comment..."></textarea>
                <button class="submit-comment">Submit</button>
            </div>
        `,
  });

  $("body").append(modal).addClass("modal-open");
  loadExistingComments(blogId);

  modal.find(".close-btn").on("click", function () {
    modal.remove();
    $("body").removeClass("modal-open");
  });

  modal.find(".submit-comment").on("click", function () {
    handleCommentSubmit(blogId);
  });
}

// Pulls existing comments from local storage and displays them on the modal
function loadExistingComments(blogId) {
  const comments =
    JSON.parse(localStorage.getItem("blogComments"))[blogId] || [];
  $(`#comments-${blogId}`).html(
    comments
      .map(
        (comment) => `
            <div class="comment">
                <p>${comment.text}</p>
                <small>${new Date(comment.date).toLocaleString()}</small>
            </div>
        `
      )
      .join("")
  );
}

function updateCommentCount(blogId) {
  const blogComments = JSON.parse(localStorage.getItem("blogComments"));
  const commentCount = blogComments[blogId]?.length || 0;
  $(`#comment-count-${blogId}`).text(commentCount);
}

// Handle comment submission
function handleCommentSubmit(blogId) {
  const text = $("#comment-input").val().trim();
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

  $("#comment-input").val("");
}

// Mailto for Contact Me form
function sendEmail(e) {
  e.preventDefault();

  const firstName = $("#firstName").val();
  const lastName = $("#lastName").val();
  const email = $("#email").val();
  const message = $("#message").val();

  if (!$("#privacyPolicy").is(":checked")) {
    alert("Please accept the privacy policy");
    return;
  }

  const mailtoLink = `mailto:your-email@example.com?subject=Message from ${firstName} ${lastName}&body=${encodeURIComponent(
    message
  )}%0D%0A%0D%0AFrom: ${email}`;
  window.location.href = mailtoLink;
}

// Load stored data from local storage
function loadStoredData() {
  const blogLikes = JSON.parse(localStorage.getItem("blogLikes")) || {};
  const userLikes = JSON.parse(localStorage.getItem("userLikes")) || {};
  const userId = generateUserId();
  const blogComments = JSON.parse(localStorage.getItem("blogComments")) || {};

  $.each(blogComments, (blogId) => updateCommentCount(blogId));

  $.each(blogLikes, (blogId) => {
    const hasLike = userLikes[userId]?.[blogId];
    const likeCount = blogLikes[blogId] || 0;

    if (likeCount > 0 || hasLike) {
      updateLikeUI(blogId, likeCount, hasLike);
    } else {
      $(`#like-${blogId}`).removeClass("liked");
    }
  });
}

// Save for later functionality for blog cards
$(document).ready(function () {
  // Add menu to each blog card
  $(".blog-card").each(function () {
    $(this).append(`
          <div class="save-menu">
              <div class="save-option">
                  <i class="fas fa-bookmark"></i> Save for later
              </div>
          </div>
      `);
  });

  // Handle save click
  $(".save-option").on("click", function (e) {
    e.preventDefault();
    e.stopPropagation();

    const blogCard = $(this).closest(".blog-card");

    const blogLink = blogCard.parent("a.blog-link").attr("href");
    console.log("Blog link:", blogLink);

    const blogData = {
      image: blogCard.find("img").attr("src"),
      meta: blogCard.find(".blog-meta").text(),
      title: blogCard.find(".blog-title").text(),
      link: blogLink,
      id: Date.now(),
    };

    saveBlog(blogData);
  });

  // Load saved blogs on save-for-later page
  if (window.location.href.includes("page-4-saveforlater")) {
    loadSavedBlogs();
  }
});

// Check if a blog is already saved
function isBlogAlreadySaved(blogLink) {
  const savedBlogs = JSON.parse(localStorage.getItem("savedBlogs")) || [];
  return savedBlogs.some((blog) => blog.link === blogLink);
}

function saveBlog(blogData) {
  if (isBlogAlreadySaved(blogData.link)) {
    // Alert with fail feedback
    alert("Error: You've already saved this blog");
    return;
  }

  let savedBlogs = JSON.parse(localStorage.getItem("savedBlogs")) || [];
  savedBlogs.push(blogData);
  localStorage.setItem("savedBlogs", JSON.stringify(savedBlogs));

  // Alert with success feedback
  alert("Blog saved!");
}

function loadSavedBlogs() {
  const savedBlogs = JSON.parse(localStorage.getItem("savedBlogs")) || [];

  // Create container for saved blogs if it doesn't exist
  if (!$("#saved-blogs-container").length) {
    $(".container").eq(1).append('<div id="saved-blogs-container"></div>');
  }

  // Display message if there are no saved blogs
  if (savedBlogs.length === 0) {
    $("#saved-blogs-container").html(
      '<p class="text-center my-5">No saved blogs yet!</p>'
    );
    return;
  }

  // Creates HTML for each saved blog that will be used to populate the Save for Later page with cards for each saved blog
  const blogsHTML = savedBlogs
    .map(
      (blog) => `
      <div class="col-md-4 mb-4">
        <div class="blog-card" data-id="${blog.id}">
          <a href="${blog.link}" target="_blank" rel="noopener noreferrer" class="blog-link">
            <img src="../page-2-blog/${blog.image}" alt="Blog image" class="blog-image mb-3">
            <div class="blog-meta">${blog.meta}</div>
            <h3 class="blog-title my-3">${blog.title}</h3>
          </a>
          <button class="btn btn-link text-danger remove-saved" 
                onclick="removeSavedBlog(${blog.id})">
            <i class="fas fa-trash"></i> Remove
          </button>
        </div>
      </div>
  `
    )
    .join("");

  $("#saved-blogs-container").html(`
      <div class="row">
          ${blogsHTML}
      </div>
  `);
}

function removeSavedBlog(id) {
  let savedBlogs = JSON.parse(localStorage.getItem("savedBlogs")) || [];
  savedBlogs = savedBlogs.filter((blog) => blog.id !== id);
  localStorage.setItem("savedBlogs", JSON.stringify(savedBlogs));
  loadSavedBlogs();
}
