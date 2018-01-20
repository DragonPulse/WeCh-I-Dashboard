

function drawBarChart(infoType){

    $.getJSON("/getMessageSummary?infoType="+infoType, function (json) {
        var labels = new Array();
        var values = new Array();
        var backgrounds = new Array();
        var backgroundColor = new Array();
        json.forEach(function (item) {
            labels.push(item.createdDate);
            values.push(item.count);
            var color = getRandomRgb(false);
            console.log(color);
            backgrounds.push(color);
            backgroundColor.push(getRandomRgb(true));
        });

        console.log(backgrounds);
        //<!-- Draw Graph-->
        new Chart(document.getElementById("barChart"),
            {
                responsive: true,
                "type": "bar",
                "data": {
                    "labels": labels,
                    "datasets": [{
                        "label": "Total Message Sent",
                        "data": values,
                        "fill": false,
                        "backgroundColor": backgrounds,
                        //"borderColor": backgrounds,
                        "borderWidth": 1
                    }]
                }, "options": {"scales": {"yAxes": [{"ticks": {"beginAtZero": true}}]}}
            });

        //End of Graph
    });


}



function drawPolarChart(infoType){
    $.getJSON("/getSubscriberSummary?infoType="+infoType, function (json) {
        var labels = new Array();
        var values = new Array();
        var backgrounds=new Array();
        json.forEach(function(item){
            labels.push(item.country);
            values.push(item.count);
            backgrounds.push(getRandomRgb(false));
        });

        new Chart(document.getElementById("polarChart"), {
            "type": "doughnut",
            "data": {
                "labels": labels,
                "datasets": [{
                    "label": "Subscribers count",
                    "data": values,
                    "backgroundColor": backgrounds
                    // "backgroundColor": ["rgb(255, 99, 132)", "rgb(75, 192, 192)", "rgb(255, 205, 86)", "rgb(201, 203, 207)", "rgb(54, 162, 235)"]
                }]
            }
        });
    });
}


//Function to generate the Random RGB
function getRandomRgb(gradient) {
    var num = Math.round(0xffffff * Math.random());
    var r = num >> 16;
    var g = num >> 8 & 255;
    var b = num & 255;
    if(gradient){
        return 'rgb(' + r + ', ' + g + ', ' + b + ',0.2)';
    }
    return 'rgb(' + r + ', ' + g + ', ' + b + ')';

}


drawPolarChart("country");
drawBarChart("createdDate");