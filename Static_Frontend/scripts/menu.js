const mainNav = document.querySelector(".mainNav");
const mainNavW = document.querySelector(".mainNavW");
const menuOpen = document.querySelector(".menuOpen");
const menuClose = document.querySelector(".menuClose");

menuOpen.addEventListener("click", openMenu);
menuClose.addEventListener("click", closeMenu);

function openMenu() {
  if (mainNav !== null) {
    mainNav.style.display = "flex";
    mainNav.style.top = "0";
  }
  if (mainNavW !== null) {
    mainNavW.style.display = "flex";
    mainNavW.style.top = "0";
  }
}

function closeMenu() {
  if (mainNav !== null) {
    mainNav.style.top = "-100%";
  }
  if (mainNavW !== null) {
    mainNavW.style.top = "-100%";
  }
}
