document.getElementById('input').onclick = input;
var streak = 0;

function click() {

    temp = (Math.floor(2*Math.random()) == 1);
    if (temp) {
        streak = 0;
        show(streak);
    } else {
        streak = streak + 1;
        show(streak);
    }

};



function show(result) {

    document.getElementById('result').innerHTML = result;

};



