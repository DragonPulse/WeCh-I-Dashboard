$(document).ready( function () {
    var table = $('#messagesTable').DataTable({
        "sAjaxSource": "/getAllMessages",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "aoColumns": [
            // { "mData": "id.machineIdentifier"},
            { "mData": "touser"},
            { "mData": "nickName"},
            { "mData": "msgtype","mRender":function(data,type,full){
                                            if(data=="text" || data == "textmessage"){
                                                return "<span class='glyphicon glyphicon-envelope'> Text</span>";
                                            }else if(data=="news"){
                                                return "<span class='glyphicon glyphicon-film'> Rich</span>";
                                            }
            }
            },
            { "mData": "createdDate","mRender": function ( data, type, full ) {
                var d = new Date();
                d.setTime(data);
                return d.getDate() + "/"+  (d.getMonth() + 1) + "/" + d.getFullYear();
            }}
        ]
    })

});