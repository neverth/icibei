<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HUSHUHUA
  Date: 2019/9/8
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fun.neverth.bean.po.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="fun.neverth.bean.po.User" %>
<% User user = (User) session.getAttribute("user");%>
<!doctype html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>IcIbeI</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/css/bootstrap.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.15.0/umd/popper.js"></script>
    <script src="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/js/bootstrap.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://cdn.bootcss.com/howler/2.1.1/howler.core.min.js"></script>

    <link rel="stylesheet" href="css/keyWrapper.css">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/shake.css">
    <link rel="stylesheet" href="css/siderbar.css">

    <style>
        .registerInput {
            opacity: 0;
            transition: all 500ms ease-out 0ms;
        }

        #box {
            position: relative;
            overflow: hidden;
        }

        .bgColor {
            position: absolute;
            left: 0;
            top: 0;
            width: 40px;
            height: 35px;
            background-color: #F44336;
            border: #F44336;
            border-radius: 2.75rem;
        }

        .txt {
            position: absolute;
            height: 40px;
            line-height: 40px;
            font-size: 1rem;
            font-weight: 400;
            font-size: 14px;
            color: #73808b;
            text-align: center;
        }

        .slider {
            position: absolute;
            left: 0;
            top: -1px;
            width: 50px;
            height: 38px;
            background: #fff;
            text-align: center;
            cursor: move;
            border: 1px solid #ced4da;
            border-radius: 0.25rem;
        }

        .slider > i {
            position: absolute;
            top: 50%;
            left: 50%;
            font-size: 24px;
            color: #afa6a6;
            transform: translate(-50%, -50%);
        }

        .slider.active > i {
            color: green;
        }

        .safety-factor-div {
            position: absolute;
            top: 32px;
            left: 211px;
            width: 70px;
        }

        .safety-factor-div div {
            float: left;
            width: 21px;
            height: 39px;
            border-radius: 30px;
            margin: 0px -3px;
            visibility: visible;
            border: 1px solid #ced4da;
            opacity: 0;
            transition: all 500ms ease-out 0ms;
        }
        .modal-content {
            background-color: #ce6d39;
        }
    </style>
</head>

<body id="body">
<div id="nav-siderbar" class="sidebar-wrapper">
    <a id="toggle-sidebar" style="cursor:pointer">
        <i class="fa fa-bars"></i>
    </a>
    <div class="sidebar-brand">
        <a href="#">IcIbeI</a>
    </div>
    <div class="sidebar-header">
        <c:if test="${sessionScope.LoginState != 1}">
            <p>抱歉您还没有登录哦！</p>
            <p class="registerInput" style="opacity: 1">没有账号？<a href="#" id="registerTips">注册</a></p>
            <form action="${request.getContextPath()}/userLogin">
                <div class="form-group">
                    <label>请输入账号：</label>
                    <input required="required" id="username" type="text" class="form-control"
                           placeholder="Enter account"
                           name="account">
                </div>
                <div class="form-group" style="position: relative">
                    <label id="passw-err">密码：</label>
                    <div class="safety-factor-div">

                        <div id="safe-d1" style="background-color:red;"></div>
                        <div id="safe-d2" style="background-color:#F0F028;"></div>
                        <div id="safe-d3" style="background-color:green;"></div>
                    </div>
                    <input required="required" id="passw" type="password" class="form-control"
                           placeholder="Password"
                           name="password">
                </div>
                <div class="form-group registerInput">
                    <label id="repassw-err">确认密码：</label>
                    <input id="repassw" type="password" class="form-control" placeholder="Password"
                           name="password1">
                </div>
                <div class="form-group registerInput">
                    <label>滑动验证</label>
                    <div id="box" onselectstart="return false;" class="form-control">
                        <div class="bgColor txt">滑动验证</div>
                        <!--给i标签添加上相应字体图标的类名即可-->
                        <div class="slider"><i class="fa fa-angle-double-right"></i></div>
                    </div>
                    <!-- <input type="text" class="form-control" placeholder="CAPTCHA" name="CAPTCHA"> -->
                </div>
                <button id="registerButton" type="submit" class="btn btn-primary registerInput" style="opacity: 1;
                                                            position: relative;
                                                            top: -180px;">登录
                </button>
                <br>
            </form>
        </c:if>
        <c:if test="${sessionScope.LoginState == 1}">
            <div class="user-pic">
                <img class="img-responsive img-rounded mCS_img_loaded"
                     src="img/defaultLogo.jpeg"
                     style="width: 54px; height: 54px;">
            </div>
            <div class="user-info">
                <span class="user-name"><%=user.getUser_id()%></span>
                <span class="user-role">Welcome</span>
            </div>
        </c:if>

    </div>
    <c:if test="${sessionScope.LoginState == 1}">
        <div class="sidebar-menu">
            <ul>
                <li class="header-menu"><span>action</span></li>
                <li class="sidebar-dropdown">
                    <a href="#"><i class="fa fa-align-center" aria-hidden="true"></i><span>单词本</span></a>
                    <div class="sidebar-submenu" style="display: none;">
                        <ul>
                            <li><a href="${request.getContextPath()}/userWordList">全部单词</a></li>
                            <li><a href="#">己练单词</a></li>
                            <li><a href="#">未学单词</a></li>
                            <li><a href="${request.getContextPath()}/wordListLike">收藏单词</a></li>
                            <li><a href="#">已斩单词</a></li>
                        </ul>
                    </div>
                </li>
                <li>
                    <a href="#" title="Warring" data-container="body" data-toggle="popover" data-placement="right"
                       data-content="功能还未完成哦！">
                        <i class="fa fa-circle-o-notch" aria-hidden="true">
                        </i>
                        <span>练习统计</span>
                    </a>
                </li>

                <li>
                    <a href="#" title="Warring" data-container="body" data-toggle="popover" data-placement="right"
                       data-content="功能还未完成哦！">
                        <i class="fa fa-circle-o-notch" aria-hidden="true"></i>
                        <span>个性化</span></a></li>

                <li>
                    <a href="#" title="Warring" data-container="body" data-toggle="popover" data-placement="right"
                       data-content="功能还未完成哦！">
                        <i class="fa fa-circle-o-notch" aria-hidden="true"></i>
                        <span>我的</span></a></li>

                <li><a href="${request.getContextPath()}/userLoginOut"><i class="fa fa-circle-o-notch" aria-hidden="true"></i><span>注销</span></a></li>

            </ul>
        </div>
    </c:if>
    <div class="siderbar-footer">
        <p>© 2019 <a href="${request.getContextPath()}/admin">NEVERTHELESS</a> . 赣ICP备19000765号.</p>
        <p>TRY YOU DO.</p>
    </div>
</div>
<div id="content" class="page-content">

    <div id="display" class="display_i">
        <p class="engli">{{wordlist.word}}</p>
        <p class="chin " style="
    display: block;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
    width: 70%;
    margin: 0px auto;
    ">{{wordlist.chinese}}</p>
        <p><i class="fa fa-star fa-2x" style="color: red"></i></p>
    </div>

    <div class="key-wrapper">
        <ul>
            <li class="drum" data-key="1" data-code="49">1</li>
            <li class="drum" data-key="2" data-code="50">2</li>
            <li class="drum" data-key="3" data-code="51">3</li>
            <li class="drum" data-key="4" data-code="52">4</li>
            <li class="drum" data-key="5" data-code="53">5</li>
            <li class="drum" data-key="6" data-code="54">6</li>
            <li class="drum" data-key="7" data-code="55">7</li>
            <li class="drum" data-key="8" data-code="56">8</li>
            <li class="drum" data-key="9" data-code="57">9</li>
            <li class="drum" data-key="0" data-code="48">0</li>
            <li class="drum" data-key="-" data-code="173">-</li>
        </ul>

        <ul>
            <li class="drum" data-key="q" data-code="81">q</li>
            <li class="drum" data-key="w" data-code="87">w</li>
            <li class="drum" data-key="e" data-code="69">e</li>
            <li class="drum" data-key="r" data-code="82">r</li>
            <li class="drum" data-key="t" data-code="84">t</li>
            <li class="drum" data-key="y" data-code="89">y</li>
            <li class="drum" data-key="u" data-code="85">u</li>
            <li class="drum" data-key="i" data-code="73">i</li>
            <li class="drum" data-key="o" data-code="79">o</li>
            <li class="drum" data-key="p" data-code="80">p</li>
        </ul>

        <ul>
            <li class="drum" data-key="a" data-code="65">a</li>
            <li class="drum" data-key="s" data-code="83">s</li>
            <li class="drum" data-key="d" data-code="68">d</li>
            <li class="drum" data-key="f" data-code="70">f</li>
            <li class="drum" data-key="g" data-code="71">g</li>
            <li class="drum" data-key="h" data-code="72">h</li>
            <li class="drum" data-key="j" data-code="74">j</li>
            <li class="drum" data-key="k" data-code="75">k</li>
            <li class="drum" data-key="l" data-code="76">l</li>
        </ul>

        <ul>
            <li class="drum" data-key="z" data-code="90">z</li>
            <li class="drum" data-key="x" data-code="88">x</li>
            <li class="drum" data-key="c" data-code="67">c</li>
            <li class="drum" data-key="v" data-code="86">v</li>
            <li class="drum" data-key="b" data-code="66">b</li>
            <li class="drum" data-key="n" data-code="78">n</li>
            <li class="drum" data-key="m" data-code="77">m</li>
            <li class="drum" data-key="," data-code="188">,</li>
        </ul>

        <ul>
            <li class="effect" data-key="SPACE" data-code="32">SPACE</li>
        </ul>

    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="indexModel" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">提示</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                您可以先按照屏幕显示的字母敲击键盘哦！
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                <button id="indexModalBodyButton2" type="button" class="btn btn-primary">不在提示</button>
            </div>
        </div>
    </div>
</div>

<script src="js/index.js"></script>
<script src="js/siderbar.js"></script>
<script>
    <%--    <%--%>
    <%--      for (WordCet4 word : (ArrayList<WordCet4>)request.getAttribute("wordCet4ArrayList")) {--%>
    <%--          out.print("wordList.push(" +--%>
    <%--                  "{" +--%>
    <%--                  "word:'" + word.getWord_cet4_spell() + "', " +--%>
    <%--                  "chinese:'" + word.getWord_cet4_Interpretation() + "', " +--%>
    <%--                  "});");--%>
    <%--      }--%>
    <%--  %>--%>
    var getWordList;
    $.ajax({
        type: "post",
        url: "${request.getContextPath()}/getIndexWordList/",
        dataType: "json",
        data: {},
        success: function (result) {
            getWordList = result;
            console.log(wordList);
            getWordList.data.forEach(function (value) {
                wordList.push({
                    "word": value.word,
                    "chinese": value.translation
                });
            })
            // wordList.push("word": )
        },
        error: function (result) {
            console.log("失败");
        }
    })
    $("#registerTips").click(function () {
        let registerInput = $('.registerInput');
        registerInput.eq(0).html("已有账号？<a href='./'>登录</a>");
        registerInput.eq(1).css("opacity", "1");
        registerInput.eq(2).css("opacity", "1");
        registerInput.eq(3).text("注册");
        registerInput.eq(3).css("top", "0px");
        var username = document.getElementById('username');
        var myInput = document.getElementById('passw');
        var myReinput = document.getElementById('repassw');
        var registerButton = document.getElementById('registerButton');
        var code, password, repassword;

        myInput.addEventListener('input', function (e) {

            let safe_d1 = document.getElementById('safe-d1');
            let safe_d2 = document.getElementById('safe-d2');
            let safe_d3 = document.getElementById('safe-d3');
            let input_err = document.getElementById('passw-err');

            console.log("输入密码： " + myInput.value);

            password = myInput.value;
            switch (checkPwd(password)) {
                case 0: {
                    input_err.innerHTML = "密码不能小于6位";
                    safe_d1.style.opacity = "1";
                    safe_d2.style.opacity = "0";
                    safe_d3.style.opacity = "0";
                    break;
                }
                case 1: {
                    input_err.innerHTML = "密码：";
                    safe_d1.style.opacity = "1";
                    safe_d2.style.opacity = "1";
                    safe_d3.style.opacity = "0";
                    break;
                }
                case 2: {
                    input_err.innerHTML = "密码：";
                    safe_d1.style.opacity = "1";
                    safe_d2.style.opacity = "1";
                    safe_d3.style.opacity = "1";
                    break;
                }
                default: {
                    input_err.innerHTML = "密码：";
                }
            }
        });
        registerButton.onclick = function () {
            if (password.length >= 6) {
                if (myReinput.value) {
                    if (myReinput.value == password) {
                        if (isSuccess == true) {
                            return true;
                        } else alert("请滑动验证");
                    } else alert("重复密码不相同哦")
                } else alert("请输入重复密码")
            } else alert("请输入符合规范的密码")
            return false;
        }

        myReinput.addEventListener('input', function (e) {
            let reinput_err = document.getElementById('repassw-err');

            console.log("重复密码输入：" + myReinput.value);

            repassword = myReinput.value;
            if (repassword != password) {
                reinput_err.innerHTML = "确认密码不相同哦";
            } else {
                reinput_err.innerHTML = "确认密码：";
            }
        });

    })

    function checkPwd(str) {
        var pattern1 = /([0-9]+)/i;
        var pattern2 = /([a-z]+)/i;
        if (str.length < 6 || str.length > 20) {
            return 0;
        }
        if (pattern1.exec(str)) {
            if (pattern2.exec(str)) {
                return 2
            }
            return 1;
        }
    }
</script>
<script>
    //一、定义了一个获取元素的方法
    function getEle(selector) {
        return document.querySelector(selector);
    }

    //二、获取到需要用到的DOM元素
    var box = getEle("#box"), //容器
        bgColor = getEle(".bgColor"), //背景色
        txt = getEle(".txt"), //文本
        slider = getEle(".slider"), //滑块
        icon = getEle(".slider>i"),
        successMoveDistance = box.offsetWidth - slider.offsetWidth, //解锁需要滑动的距离
        downX, //用于存放鼠标按下时的位置
        isSuccess = false; //是否解锁成功的标志，默认不成功

    //三、给滑块添加鼠标按下事件
    slider.onmousedown = mousedownHandler;

    //3.1鼠标按下事件的方法实现
    function mousedownHandler(e) {
        bgColor.style.transition = "";
        slider.style.transition = "";
        var e = e || window.event || e.which;
        downX = e.clientX;
        //在鼠标按下时，分别给鼠标添加移动和松开事件
        document.onmousemove = mousemoveHandler;
        document.onmouseup = mouseupHandler;
    };

    //四、定义一个获取鼠标当前需要移动多少距离的方法
    function getOffsetX(offset, min, max) {
        if (offset < min) {
            offset = min;
        } else if (offset > max) {
            offset = max;
        }
        return offset;
    }

    //3.1.1鼠标移动事件的方法实现
    function mousemoveHandler(e) {
        var e = e || window.event || e.which;
        var moveX = e.clientX;
        var offsetX = getOffsetX(moveX - downX, 0, successMoveDistance);
        bgColor.style.width = offsetX + "px";
        slider.style.left = offsetX + "px";

        if (offsetX == successMoveDistance) {
            success();
        }
        //如果不设置滑块滑动时会出现问题（目前还不知道为什么）
        e.preventDefault();
    };

    //3.1.2鼠标松开事件的方法实现
    function mouseupHandler(e) {
        if (!isSuccess) {
            bgColor.style.width = 0 + "px";
            slider.style.left = 0 + "px";
            bgColor.style.transition = "width 0.8s linear";
            slider.style.transition = "left 0.8s linear";
        }
        document.onmousemove = null;
        document.onmouseup = null;
    };

    //五、定义一个滑块解锁成功的方法
    function success() {
        isSuccess = true;
        txt.innerHTML = "验证成功";
        bgColor.style.backgroundColor = "#28a745";
        slider.className = "slider active";
        icon.className = "fa fa-thumbs-o-up";
        //滑动成功时，移除鼠标按下事件和鼠标移动事件
        slider.onmousedown = null;
        document.onmousemove = null;
    };

    $(document).ready(function () {
        let storage = window.localStorage;
        let indexModelTipsState = storage.getItem("indexModelTipsState");
        if (indexModelTipsState != 1) {
            $('#indexModel').modal();
        }
        $("#indexModalBodyButton2").click(function () {
            $('#indexModel').modal('hide');
            storage.setItem("indexModelTipsState", 1);
        });
    });
</script>
</body>

</html>
