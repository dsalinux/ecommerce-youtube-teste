document.querySelector('.toogle-aside').addEventListener('click', () => {
    if(isMobile()){
        document.querySelector('aside.menu-left').classList.toggle('open-mobile');
    } else {
        document.querySelector('aside.menu-left').classList.toggle('close');
    }
});
document.querySelector('.overlay').addEventListener('click', () => {
    document.querySelector('aside.menu-left').classList.remove('open-mobile');
});
document.querySelectorAll('aside.menu-left nav ul li ul').forEach(ul => {
    ul.parentElement.querySelector('a').addEventListener('click', event => {
        event.preventDefault();
        ul.parentElement.classList.toggle('open');
    });
});

function isMobile(){
    return window.innerWidth <= 768;
}
