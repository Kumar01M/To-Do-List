var map = document.getElementById("map");
var clicked = true;
var selected;

function load() {
    for (let i = 1; i <= 11; i++) {
        let div = document.createElement('div');
        div.setAttribute("class", "row");
        for (let j = 1; j <= 10; j++) {
            let button = document.createElement('button');
            if ((i == 6 && j != 5) || (i != 6 && j == 5)) {
                button.innerText = '🏕️';
                button.setAttribute("class", "rome");
            } else {
                button.innerText = '🎪';
                button.setAttribute("class", "tro");
            }
            div.appendChild(button);
            button.id = "but"+i+'_'+j;
            button.onclick = buttonClicked;
        }
        map.appendChild(div);
    }
}

Window.onload = load();

function buttonClicked() {
    let id = this.id;
    let cname = this.className;
    if (clicked) {
        selected = document.getElementById(id);
        if (cname == 'rome') {
            selected.setAttribute("class", "romecli");
        } else {
            selected.setAttribute("class", "trocli");
        }
        clicked = false;
    } else {
        cname = selected.className;
        if (cname == 'romecli') {
            selected.setAttribute("class", "rome");
        } else {
            selected.setAttribute("class", "tro");
        }
        clicked = true;
        swap(id);
    }
}

function swap(id) {
    let change = document.getElementById(id);
    let temp = change.innerText;
    let tempclass = change.className;
    change.innerText = selected.innerText;
    change.className = selected.className;
    selected.innerText = temp;
    selected.className = tempclass;
}