function changeTip() {
    fetch("data.json")
        .then(response => response.json())
        .then(data => returnTipData(data));
        
};

function returnTipData(data) {
    let tipArray = [];

    for (let i = 0; i < data.tips.length; i++) {
        let title = data.tips[i].title;
        let num = data.tips[i].num;
        let desc = data.tips[i].description;

        let tip = {
            title: title,
            num: num,
            desc: desc
        }

        tipArray.push(tip);
    }
    
    setTip(tipArray);
};

function setTip(tipArray) {
    var randNum = Math.floor((Math.random() * tipArray.length));

    var tipTitle = tipArray[randNum].title;
    var tipNum = tipArray[randNum].num;
    var tipDesc = tipArray[randNum].desc;

    document.getElementById("tipTitle").innerHTML = "Daily Tip #" + tipNum + " : " + tipTitle;
    document.getElementById("tipDesc").innerHTML = tipDesc;
};