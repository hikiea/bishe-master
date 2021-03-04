// 展开二级评论
function secondComments(e) {

    var id = e.getAttribute("data-id");
    var comments = $("#comment-" + id);

    //获取二级评论展开状态
    var collapse = e.getAttribute("data-collapse");
    if (collapse) {
        //折贴二级评论
        comments.removeClass("in");
        e.removeAttribute("data-collapse")
    } else {
        //展开二级评论
        comments.addClass("in");
        //标记二级评论展开状态
        e.setAttribute("data-collapse", "in");
    }
}
