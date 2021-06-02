const mainNav = document.querySelector(".mainNav");
const menuOpen = document.querySelector(".menuOpen");
const menuClose = document.querySelector(".menuClose");

menuOpen.addEventListener("click", openMenu);
menuClose.addEventListener("click", closeMenu);

function openMenu() {
  mainNav.style.display = "flex";
  mainNav.style.top = "0";
}

function closeMenu() {
  mainNav.style.top = "-100%";
}
