// 提交回复
function doComment() {
    var tieId = $("#tie_id").val();
    var content = $("#comment_content").val();
    var comment_id = $("#comment_id").val();
    var comment_username = $("#comment_username").val();

    if (content == "" || content == null) {
        alert("内容不能为空");
        return;
    }

    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId": tieId,
            "content": content,
            "comment_id": comment_id,
            "comment_username": comment_username,
            "type": 1
        }),
        success: function a() {
            function flush() {
                history.go(0);
            }
        },
        dataType: "json"
    });
}



