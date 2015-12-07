//页面一加载，就向后端发起请求，获取第一页的书籍信息、一共有多少页、共有多少条书籍信息
$(document).ready(function(){
    $.ajax({
        url: "getResources",
        type: "post",
        datatype: "json",
        data: {
            pn: 0,
            rn: 10
        },
        success: function (data) {
            var jsonData = eval("(" + data + ")");
            var books = jsonData.res;
            var pages = jsonData.totalPages;
            var bookNumbers = jsonData.bookNumbers;
            console.log(books);
            $.each(books, function (index, element) {
                var content = "<div><span class='isHide'>"+element.id+"</span><p class=" + "name" + ">" + element.title + "</p>" + "<p class=" + "ctime" + ">" + "Created @ " + unixToDate(element.created) + "</p><button class='deleteThisBook' onclick='deleteBookMsg(event)'>删除</button></div>";
                $("#content").append(content);
            });
            var content = "<p class='result'>搜索到" + bookNumbers + "个结果</p>"
            $(".search").append(content);
            splitPages(pages);
        }
    });
    searchMark();
});
function unixToDate(time) {
    var unixTime = parseInt(time);
    var date = new Date(unixTime * 1000);
    var year = date.getUTCFullYear();
    var month = date.getUTCMonth()+1;
    var day = date.getUTCDate();
    return year+"-"+month+"-"+day;
}

//翻页
function sendPages(event){
    var keyword = $("#search-content").val();
    $(event.target).parent().children().removeClass('clicked');
    $(event.target).parent().children().addClass('notClicked');
    $(event.target).removeClass('notClicked');
    $(event.target).addClass('clicked');
    $(".content div").remove();
    $(".search p").remove();
    ajaxToTurnPage(event.target.id,keyword);
    console.log("page"+event.target.id);
}

//根据页数，画出页数链接
function splitPages(pages){
    var number = "<a href='#' class='clicked' onclick='sendPages(event)'id='1' >1</a>";
    $("#pages").append(number);
    for(var i=2;i <= pages;i++){
        var number = "<a href='#'class='notClicked' onclick='sendPages(event)' id='"+i+"'>"+i+"</a>";
        $("#pages").append(number);
    }
}

//翻页时使用的ajax请求
function ajaxToTurnPage(currentPage,keyword){
    $.ajax({
        url: "getResources",
        type: "post",
        datatype: "json",
        data: {
            pn: currentPage-1,
            rn: 10,
            keyword:keyword
        },
        success: function (data) {
            var jsonData = eval("(" + data + ")");
            var books = jsonData.res;
            var pages = jsonData.totalPages;
            var bookNumbers = jsonData.bookNumbers;
            console.log(books);
            $.each(books, function (index, element) {
                var content = "<div><span class='isHide'>"+element.id+"</span><p class=" + "name" + ">" + element.title + "</p>" + "<p class=" + "ctime" + ">" + "Created @ " + unixToDate(element.created) + "</p><button class='deleteThisBook' onclick='deleteBookMsg(event)'>删除</button></div>";
                $("#content").append(content);
            });
            var content = "<p class='result'>搜索到" + bookNumbers + "个结果</p>"
            $("#search-content").val(keyword);
            $(".search").append(content);
            var children = $("#content div").children(".name").each(function(){
                var p_content = $(this).text();
                var reg = new RegExp("("+keyword+")","gi");
                $(this).html( $(this).html().replace(reg,"<span class='highlight'>$1</span>"));
            });
        }
    });
}

//“添加”按钮按下时触发，显示弹层
function addMarksDialog(){
    $(".addDialog").show();
    $(".transparentLayer").show();
}

//“删除”按钮按下时触发，获取当前页号和该行的书籍id号，当弹框中"是"按钮被按下时，发起请求逻辑删除书籍信息
//删除成功发起查询请求，重新查询
function deleteBookMsg(event){
    $(".deleteDialog").show();
    $(".transparentLayer").show();
    var id = $(event.target).parent().children("span").text();
    var currentPage = $(".clicked").text();
    console.log(id);
    console.log(currentPage);
    $(".delete").click(function(){
        // 用户点击了“是”
        ajaxToDeleteBook(id,currentPage);

    });
}
//删除书籍用的请求
function ajaxToDeleteBook(id,currentPage){
    $.ajax({
        url:"deleteBook",
        type: "post",
        datatype: "json",
        data: {
            id: id
        },
        success:function(data){
            //删除成功，发送请求重新查询
            var jsonData = eval("(" + data + ")");
            $(".content div").remove();
            $(".search p").remove();
            console.log(jsonData);
            console.log(jsonData.isdeleted);
            if(data.isdeleted == false){
                alert("删除失败");
            }
            ajaxToTurnPage(currentPage);
            $(".deleteDialog").hide();
            $(".transparentLayer").hide();
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            alert("删除失败");
        }
    });
}

//取消“添加”的弹层
function cancleAddMark(){
    $(".addDialog").hide();
    $(".transparentLayer").hide();
    $("#bookname").val('');
    $("#bookurl").val('');
}

//取消“删除”的弹层
function cancleDeleteBook(){
    $(".deleteDialog").hide();
    $(".transparentLayer").hide();
}

//按关键字查询
function textChanged(){
    var keyword = $("#search-content").val();
    var pn = 1;
    $(".content div").remove();
    $(".search p").remove();
    $("#pages a").remove();
    ajaxToSearchBook(pn,keyword);
};
function ajaxToSearchBook(currentPage,keyword){
    $.ajax({
        url: "getResources",
        type: "post",
        datatype: "json",
        data: {
            pn: currentPage-1,
            rn: 10,
            keyword:keyword
        },
        success: function (data) {
            var jsonData = eval("(" + data + ")");
            var books = jsonData.res;
            var pages = jsonData.totalPages;
            var bookNumbers = jsonData.bookNumbers;
            console.log(books);
            console.log(pages);
            console.log(bookNumbers);

            $.each(books, function (index, element) {
                var content = "<div><span class='isHide'>"+element.id+"</span><p class=" + "name" + ">" + element.title + "</p>" + "<p class=" + "ctime" + ">" + "Created @ " + unixToDate(element.created) + "</p><button class='deleteThisBook' onclick='deleteBookMsg(event)'>删除</button></div>";
                $("#content").append(content);
            });
            var content = "<p class='result'>搜索到" + bookNumbers + "个结果</p>"
            $("#search-content").val(keyword);
            $(".search").append(content);
            splitPages(pages);
            var children = $("#content div").children(".name").each(function(){
                var p_content = $(this).text();
                var reg = new RegExp("("+keyword+")","gi");
                $(this).html( $(this).html().replace(reg,"<span class='highlight'>$1</span>"));
            });
        }
    });
}

//添加书签
function addMark(){
    var bookname = $("#bookname").val();
    var bookurl = $("#bookurl").val();
    console.log(bookname);
    console.log(bookurl);
    if(bookname.length < 1 || bookurl.length < 1){
        $(".hiddenMsg").show();
    }
    else{//发起请求，添加书签
        $.ajax({
            url: "addBookMark",
            type: "post",
            datatype: "json",
            data: {
                name: bookname,
                url: bookurl,
            },
            success: function (data) {
                var jsonData = eval("(" + data + ")");
                var isCreated = data.isCreated;
                if(false == isCreated ){
                    alert("添加书签失败");
                }
                cancleAddMark();
                $(".mark a").remove();
                searchMark();

            }

        });
    }
}
//查询书签
function searchMark(){
    $.ajax({
        url: "searchMarks",
        type: "post",
        datatype: "json",
        success: function (data) {
            var jsonData = eval("(" + data + ")");
            var marks = jsonData.res;
            $.each(marks, function (index, element) {
                var content = "<a href='"+element.url+"' class='bookMarkName' target='_blank'>"+element.title+"</a>";
                $(".mark").append(content);
            });
        }

    });
}
function marksDialog(){
    $(".bookmarks").toggle();
}