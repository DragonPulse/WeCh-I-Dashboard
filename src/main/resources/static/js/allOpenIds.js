$(document).ready( function () {
    var table = $('#openIdsTable').DataTable({
        "sAjaxSource": "/allOpenIdsNew",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "aoColumns": [
            { "mData": "openid"}
        ]
    })
});