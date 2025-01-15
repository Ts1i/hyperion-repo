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
