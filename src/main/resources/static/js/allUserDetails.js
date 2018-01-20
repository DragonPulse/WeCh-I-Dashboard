$(document).ready( function () {
    var table = $('#userDetailsTable').DataTable({
        "sAjaxSource": "/allUserDetailsNew",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "aoColumns": [
            { "mData": "openid"},
            { "mData": "nickname"},
            { "mData": "language"},
            { "mData": "province"},
            { "mData": "country"}
        ]
    })
});