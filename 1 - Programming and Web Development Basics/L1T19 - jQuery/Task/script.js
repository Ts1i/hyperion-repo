// Alert once the page has loaded
$(document).ready(function () {
  alert("The page has loaded!");
});

// Changes the background color of the body to #fafafa once the page has loaded
$(function () {
  $("body").animate({ backgroundColor: "lightgrey" }, 2000);
});

// Italicize hero section paragraph
$(function () {
  $("#our-story .hero-content-wrapper p").css("font-style", "italic");
});

// Add fade toggle to clicked elements
$(function () {
  // Targets all elements
  $(
    ".hero-content-wrapper p, .team-member, .food-image, .content-wrapper p, h1, h2, .center-content, .caption, .role, .dropdown-menu li"
  ).click(function (e) {
    // Please note: This will only works on these selections
    e.stopPropagation(); // Prevents event bubbling to parent
    $(this).fadeToggle(1000);
  });
});

// Creates a dropdown menu when the mouse hovers over an item
$(function () {
  // Create dropdown menu HTML
  const menuHTML = `
        <ul class="dropdown-menu">
            <li>Breakfast</li>
            <li>Lunch</li>
            <li>Dinner</li>
            <li>Desserts</li>
            <li>Drinks</li>
        </ul>
    `;

  // Add dropdown menu to each section
  $("section").each(function () {
    $(this).append(menuHTML);
  });

  // Add hover event and accordion animation
  $("section").hover(
    function () {
      $(this).find(".dropdown-menu").slideDown(300);
    },
    function () {
      $(this).find(".dropdown-menu").slideUp(300);
    }
  );
});

// Add chained animation effect to move sections around until stopped
$(function () {
  function animateElements() {
    $(".section")
      .animate({ marginLeft: "100px", backgroundColor: "#ffebee" }, 1000)
      .animate({ marginTop: "50px", backgroundColor: "#e3f2fd" }, 1000)
      .animate({ marginLeft: "-100px", backgroundColor: "#f3e5f5" }, 1000)
      .animate({ marginTop: "-50px", backgroundColor: "#e8f5e9" }, 1000)
      .animate(
        { marginLeft: "0px", marginTop: "0px", backgroundColor: "#fff" },
        1000,
        function () {
          // Callback to create infinite loop
          animateElements();
        }
      );
  }

  // Add button to start/stop animations
  $("body").append(
    '<button id="toggleAnimation" style="position: fixed; top: 10px; left: 10px; z-index: 1000; font-size: 2rem;">Toggle Animation</button>'
  );

  let isAnimating = false;

  $("#toggleAnimation").click(function () {
    if (!isAnimating) {
      animateElements();
      isAnimating = true;
      $(this).text("Stop Animation");
    } else {
      $(".section").stop(true, false);
      isAnimating = false;
      $(this).text("Start Animation");
      // Reset positions
      $(".section").animate({ marginLeft: "0px", marginTop: "0px" }, 500);
    }
  });
});

// When any image is clicked, it will fade in and out over 3 seconds, until it is stopped by clicking the image again
// !!!Note: Test this by clicking on the image of a plate of food.
$(function () {
  // Track animation state for each image
  const animatingImages = new Set();

  // Add click handler to all images
  $("img").click(function () {
    const $img = $(this);

    if (animatingImages.has(this)) {
      // Stop animation if already animating
      $img.stop().css("opacity", 1);
      animatingImages.delete(this);
    } else {
      // Start fade animation
      animatingImages.add(this);
      function fadeLoop() {
        if (animatingImages.has($img[0])) {
          $img.fadeOut(3000).fadeIn(3000, fadeLoop);
        }
      }
      fadeLoop();
    }
  });
});
