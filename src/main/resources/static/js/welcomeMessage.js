var articleList ;



$(document).ready(function() {


    function displayMessage(messageType,messageContent){
        $.toast({
            text: messageContent, // Text that is to be shown in the toast
            //heading: 'Note', // Optional heading to be shown on the toast
            icon: messageType, // Type of toast icon
            showHideTransition: 'fade', // fade, slide or plain
            allowToastClose: true, // Boolean value true or false
            hideAfter: 3000, // false to make it sticky or number representing the miliseconds as time after which toast needs to be hidden
            stack: 5, // false if there should be only one toast at a time or a number representing the maximum number of toasts to be shown at a time
            position: 'top-right', // bottom-left or bottom-right or bottom-center or top-left or top-right or top-center or mid-center or an object representing the left, right, top, bottom values
            textAlign: 'left',  // Text alignment i.e. left, right or center
            loader: true,  // Whether to show loader or not. True by default
            loaderBg: '#9EC600',  // Background color of the toast loader
            beforeShow: function () {}, // will be triggered before the toast is shown
            afterShown: function () {}, // will be triggered after the toat has been shown
            beforeHide: function () {}, // will be triggered before the toast gets hidden
            afterHidden: function () {}  // will be triggered after the toast has been hidden
        });
    }


    $("#articleModalForm").validate({
        rules: {
            articleTitle: {
                required: true,
                minlength: 8
            },
            description: "required"
        },
        messages: {
            articleTitle: {
                required: "Please enter some data for title",
                minlength: "Your data must be at least 8 characters"
            },
            description: "Please provide some data for description"
        }
    });

    var options = {
        valueNames: [ 'title', 'description','url','picurl','action' ],
        item: '<li><h3 class="title"></h3><p class="description"></p><p class="url"></p></p><p class="picurl"></p><button class="action"></button></li>'
    };


    var items = [];
    var articlesList = new List('articles', options,items);



    console.log(articlesList.size());

    var items = [];

    var articleCount = 0;
    $("#btnSaveIt").click(function(){
        var articleTite = $("#articleTitle").val();
        var description = $("#description").val();
        var url = $("#url").val();
        var picurl = $("#picurl").val();

        articlesList.add({
            title: articleTite,
            description: description,
            url: url,
            picurl :picurl,
            action:"Delete"
        });
        articleCount++;
        console.log(articlesList.size());

    });

    $("#btnSave").click(function(event){
        event.preventDefault();
        //capture data from form
        var items = articlesList.items;
        var articleArray=new Array();
        for(i=0;i<items.length;i++){
            articleArray.push(items[i].values());
        }

        var data = JSON.stringify(articleArray);


        url = "/saveWelcomeMessage";
        $.ajax({
            url: url,
            data: { 'articles': data },
            type: "POST",
            dataType: "json",
            success: function (data) {
                displayMessage(data.responseType,data.responseMessage);
            },
            error: function (xhr, status) {
                displayMessage("error","Sorry, Try Later. SOmething went wrong.");
            },
            complete: function (xhr, status) {
                //$('#showresults').slideDown('slow')
            }
        });

    });

});