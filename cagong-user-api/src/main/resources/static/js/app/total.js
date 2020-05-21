let url = window.location.href;
let hostname = window.location.hostname;
console.log(hostname);
let element = document.getElementById('background');
let navbar = document.getElementById('top-navbar');
let searchbar = document.getElementById('search-bar');
let footbar = document.getElementById('footer-bar');


if (url.includes("home")) {
    element.removeAttribute('id');
    element.setAttribute('id', 'activeBack');
    searchbar.style.display = "none";
} else {
    navbar.setAttribute('style', 'background-color: #6F4E37;');
    footbar.setAttribute('class', 'bg-dark');
}



