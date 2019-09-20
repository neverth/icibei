<%--
  Created by IntelliJ IDEA.
  User: HUSHUHUA
  Date: 2019/9/15
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fun.nevertheless.bean.po.User" %>
<% User user = (User) session.getAttribute("user");%>
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

    <link rel="stylesheet" href="./css/main.css">
    <link rel="stylesheet" href="./css/siderbar.css">
    <script src="js/siderbar.js"></script>

    <style>
        .table {
            color: white;
        }
    </style>

</head>

<body id="body">
<div id="nav-siderbar" class="sidebar-wrapper toggled">
    <a id="toggle-sidebar" style="cursor:pointer">
        <i class="fa fa-bars"></i>
    </a>
    <div class="sidebar-brand">
        <a href="#">IcIbeI</a>
    </div>
    <div class="sidebar-header">
        <div class="user-pic">
            <img class="img-responsive img-rounded mCS_img_loaded"
                 src="img/defaultLogo.jpeg"
                 style="width: 54px; height: 54px;">
        </div>
        <div class="user-info">
            <span class="user-name"><%=user.getUser_id()%></span>
            <span class="user-role">Welcome</span>
        </div>
    </div>
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

            <li><a href="${request.getContextPath()}/userLoginOut"><i class="fa fa-circle-o-notch"
                                                                      aria-hidden="true"></i><span>注销</span></a></li>

        </ul>
    </div>
    <div class="siderbar-footer">
        <p>© 2019 <a href="${request.getContextPath()}/admin">NEVERTHELESS</a> . 赣ICP备19000765号.</p>
        <p>TRY YOU DO.</p>
    </div>
</div>
<div id="content" class="page-content toggled">
    <table class="table" id="tableDisPlay">
        <thead>
        <tr>
            <th><a href="${request.getContextPath()}/userWordList">全部单词</a></th>
            <th><a href="#">己练单词</a></th>
            <th><a href="#">未学单词</a></th>
            <th><a href="${request.getContextPath()}/wordListLike">收藏单词</a></th>
            <th><a href="#">已斩单词</a></th>
        </tr>
        <tr>
            <th scope="col">#</th>
            <th scope="col">like</th>
            <th scope="col">word</th>
            <th scope="col">phonetic</th>
            <th scope="col" style="width: 25rem">translation</th>
            <th scope="col">collins</th>
            <th scope="col">tag</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(item, index) in wordListData">
            <th scope="row">{{ index + 1 }}
            </td>
            <td>
            <span v-if="!item.userWordRelation" style="color:white; cursor:pointer" id="likeIcon"
                  @click="addUserWordRelation(<%=user.getUser_id()%>, item.wordCet4.id, 1)">
              ❤
            </span>
                <span v-else>
              <span v-if="item.userWordRelation.user_word_relation_is_like == 1" style="color:red; cursor:pointer"
                    id="likeIcon" @click="userLikeRelation(item.userWordRelation.user_word_relation_id,
                item.userWordRelation.user_word_relation_is_like)">
                ❤
              </span>
              <span v-else style="color:white; cursor:pointer" id="likeIcon" @click="userLikeRelation(item.userWordRelation.user_word_relation_id,
                item.userWordRelation.user_word_relation_is_like)">
                ❤
              </span>
            </span>
            </td>
            <td @click="voicePlay(item.wordCet4.word)">{{ item.wordCet4.word }}</td>
            <td @click="voicePlay(item.wordCet4.word)">{{ '[' + item.wordCet4.phonetic + ']'}}</td>
            <td style="width: 25rem">{{ item.wordCet4.translation }}</td>
            <td>{{ item.wordCet4.collins }}</td>
            <td>{{ item.wordCet4.tag }}</td>
        </tr>
        </tbody>
    </table>
</div>

<script>

    var vm = new Vue({
        el: '#tableDisPlay',
        data: {
            pages: 0,
            pageNum: 0,
            wordListData: []
        },
        methods: {
            userLikeRelation: function (wordId) {
                console.log(wordId);
            },
            addUserWordRelation: function (userId, wordId, isLike) {
                console.log(userId + " " + wordId + " " + isLike);
                $.ajax({
                    type: "post",
                    url: "${request.getContextPath()}/addUserWordRelation/",
                    dataType: "json",
                    data: {
                        "userId": userId,
                        "wordId": wordId,
                        "like": isLike
                    },
                    success: function (result) {
                        for (let i = 1; i <= vm.pageNum; i++) {
                            getUserWordList(i);
                        }
                    },
                    error: function (result) {
                        console.log("失败");
                    }
                })
            },
            userLikeRelation: function (relationId, isLike) {
                console.log(relationId + " " + isLike);
                if (isLike == 1) {
                    isLike = 0;
                } else {
                    isLike = 1;
                }
                $.ajax({
                    type: "post",
                    url: "${request.getContextPath()}/updateRelationById/" + relationId,
                    dataType: "json",
                    data: {
                        "like": isLike,
                        "type": 1
                    },
                    success: function (result) {
                        for (let i = 1; i <= vm.pageNum; i++) {
                            getUserWordList(i);
                        }
                    },
                    error: function (result) {
                        console.log("失败");
                    }
                })
            },
            voicePlay: function (word) {
                let src_t = "${request.getContextPath()}/mp3/" + word + ".mp3";
                console.log(src_t);
                sound = new Howl({
                    src: src_t
                });
                sound.play();
            }
        }
    });

    function getUserWordList(pageNum) {
        $.ajax({
            type: "post",
            // url: "${request.getContextPath()}/getWordList/" + pageNum,
            url: "${request.getContextPath()}/getWordUserAndRelationList/" + pageNum,
            dataType: "json",
            data: {
                "userId": <%=user.getUser_id()%>
            },
            success: function (result) {
                console.log(result);
                // vm.pageNum = result.pageNum;
                // vm.pages = result.totalPage;
                // if (pageNum > 1) {
                //   vm.wordListData.push.apply(vm.wordListData, result.data);
                // } else {
                //   vm.wordListData = result.data;
                // }
                vm.pageNum = result.pageNum;
                vm.pages = result.totalPage;
                if (pageNum > 1) {
                    vm.wordListData.push.apply(vm.wordListData, result.userWordData);
                } else {
                    vm.wordListData = result.userWordData;
                }
                console.log(vm.wordListData);
            },
            error: function (result) {
                console.log("失败");
            }
        })
    }

    getUserWordList(1);

    $(window).scroll(function () {
        // 当滚动到最底部以上n像素时， 加载新内容
        if ($(document).height() - $(this).scrollTop() - document.body.clientHeight < 1) {
            getUserWordList(vm.pageNum + 1);
            console.log("-------------------------------------------------------------------")
        }
    });
    $("#toggle-sidebar").click(function () {
        $("#nav-siderbar").toggleClass("toggled");
        $("#content").toggleClass("toggled");
        setTimeout(function () {
            window.location.href = './';
        }, "150");
        //
        // 该方法检查每个元素中指定的类。如果不存在则添加类，如果已设置则删除之
    });
</script>
</body>
</html>
