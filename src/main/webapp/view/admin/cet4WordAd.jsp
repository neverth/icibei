<%--
  Created by IntelliJ IDEA.
  User: HUSHUHUA
  Date: 2019/9/17
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/css/bootstrap-grid.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/css/bootstrap.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.js"></script>
    <script src="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/js/bootstrap.js"></script>
    <script src="https://cdn.bootcss.com/vue/2.6.5/vue.js"></script>

    <title>dashboard</title>
    <style>
        body {
            margin: 0 auto;
        }

        .pa-header {
            height: 48px;
            background-color: #3C8EBE;
            margin-bottom: 50px;
            /* box-shadow: 0 2px 4px 0 #3C8EBE; */
        }

        .pa-header-left {
            height: 48px;
            background-color: #3680AB;
            width: 200px;
        }

        .pa-header-left p {
            font-size: 30px;
            color: #fff;
            margin-left: 27%;
        }

        .pa_body .body-menu {
            position: absolute;
            top: 48px;
            left: 0;
            bottom: 0;
            width: 200px;
            background-color: #3E4356;
        }

        .pa_body .body-content {
            position: absolute;
            top: 48px;
            left: 210px;
            right: 0;
            background-color: #fff;
            bottom: 0;
            /* “bottom:0 与 overflow” 结合使用的效果：当内容超出显示器时，自动添加本区域的滚动条，其他区域保持不变*/
            overflow: auto;
            /*除了上面的方式，还有一种是不定义bottom，也不用overflow，这样内容是多少，中间内容区的行高就是多少，自适应，但是菜单和标题栏就会随着滚轮而动*/
        }

        .body-menu-ul {
            margin: 0;
            padding: 0;
            list-style-type: none;
        }

        .breadcrumb {
            padding: 8px 16px;
            margin-bottom: 20px;
            list-style: none;
            background-color: #f5f5f5;
            -moz-border-radius: 4px;
            border-radius: 4px;
        }

        #tdOk {
            display: none;
        }

        #tdUpdata {
            display: inline;
        }
    </style>
</head>

<body>
<div class="pa-header">
    <div class="pa-header-left">
        <p>ICIBEI</p>
    </div>
</div>
<div class="pa_body">
    <div class="body-menu">

        <ul class="body-menu-ul">
            <li>单词库</li>
            <ul>
                <li>
                    <a href="#">初中</a>
                </li>
                <li>
                    <a href="#">高中</a>
                </li>
                <li>
                    <a href="${request.getContextPath()}/cet4WordAd">CET4</a>
                </li>
            </ul>
            <li><a href="${request.getContextPath()}/userAd">用户管理</a></li>
            <li><a href="#">管理管理</a></li>
            <li><a href="#">管理管理</a></li>
        </ul>
    </div>
    <div class="body-content">
        <nav aria-label="breadcrumb" style="margin-top: 12px;">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="#">ICIBEI</a></li>
                <li class="breadcrumb-item"><a href="#">单词管理</a></li>
                <li class="breadcrumb-item active" aria-current="page">CET4</li>

            </ol>
        </nav>
        <div>
            <button id="lastPage" type="button" class="btn btn-outline-secondary">上一页</button>
            <button  id="nextPage" type="button" class="btn btn-outline-secondary">下一页</button>
            <button id="insertWord" type="button" class="btn btn-outline-dark">添加单词</button>
            <div class="input-group mb-3" style="float: right;
                width: 30%;">
                <input id="selectWordInput" type="text" class="form-control" placeholder="请输入查询单词" aria-label="请输入查询单词" aria-describedby="button-addon2">
                <div class="input-group-append">
                    <button id="selectCet4WordByWord" class="btn btn-outline-secondary" type="button" id="button-addon2">查询</button>
                </div>
            </div>
        </div>
        <table class="table" id="tableDisPaly">

            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">ID</th>
                <th scope="col">word</th>
                <th scope="col">phonetic</th>
                <th scope="col">definition</th>
                <th scope="col">translation</th>
                <th scope="col">pos</th>
                <th scope="col">collins</th>
                <th scope="col">tag</th>
                <th scope="col">bnc</th>
                <th scope="col">frq</th>
                <th scope="col">操作</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="(item, index) in items[2].tableData">
                <th scope="row">{{ index + 1 }}</td>
                <td>{{ item.id }}</td>
                <td>{{ item.word }}</td>
                <td>{{ item.phonetic }}</td>
                <td>{{ item.definition }}</td>
                <td>{{ item.translation }}</td>
                <td>{{ item.pos }}</td>
                <td>{{ item.collins }}</td>
                <td>{{ item.tag }}</td>
                <td>{{ item.bnc }}</td>
                <td>{{ item.frq }}</td>
                <td>
                    <button id="tdOk" @click="updataWord(item.id, index + 1, 1)" type="button" class="btn btn-outline-success">完成</button>
                    <button id="tdUpdata" @click="updataWord(item.id, index + 1, 0)" type="button" class="btn btn-outline-warning">修改</button>
                    <button @click="deleteWord(item.id)" type="button" class="btn btn-outline-danger">删除</button>
                    <!-- <a id="tdOk" href='javascript:;' v-on:click='updataUser(item.user_id, index + 1, 1)'>完成</a>
    <a id="tdUpdata" href="javascript:;" v-on:click="updataUser(item.user_id, index + 1, 0)" >修改</a> |
    <a href="javascript:;" v-on:click="deleteUser(item.user_id)">删除</a> -->
                </td>
            </tr>

            </tbody>
        </table>
    </div>
</div>
<textarea rows='5' cols='20'>

            </textarea>
<script>
    var vm = new Vue({
        el: '#tableDisPaly',
        data: {
            items: [{
                pages: 0
            },
                {
                    pageNum: 0
                },
                {
                    tableData: [

                    ]
                }
            ]
        },
        methods: {
            deleteWord: function (id) {
                alert("为了数据库的安全，不允许删除单词！！");
            },
            updataWord: function (word_id, tr_id, flag) {
                var secletTr = $("#tableDisPaly tr").eq(tr_id);
                var secletTd = secletTr.children("td");
                var updataData = [];
                if (flag == 0) {
                    console.log("修改html");
                    for (let i = 1; i < 10; i++) {
                        let secletOneTd = secletTd.eq(i);
                        secletOneTd.html("<textarea rows='5' cols='20'>" + secletOneTd.text() +
                            "</textarea>");
                    }
                    secletTd.eq(10).children("button").eq(0).css("display", "inline");
                    secletTd.eq(10).children("button").eq(1).css("display", "none");
                } else {
                    console.log("准备修改");
                    for (let i = 1; i < 10; i++) {
                        let secletOneTdInput = secletTd.eq(i).children();
                        updataData.push(secletOneTdInput.val());
                        console.log(updataData);
                    }
                    if (word_id == 0) {
                        insertCet4Word(updataData, tr_id);
                        return;
                    }
                    console.log("updata 数据");
                    $.ajax({
                        type: "post",
                        url: "${request.getContextPath()}/updateWordById/" + word_id,
                        dataType: "json",
                        data: {
                            word: updataData[0],
                            phonetic: updataData[1],
                            definition: updataData[2],
                            translation: updataData[3],
                            pos: updataData[4],
                            collins: updataData[5],
                            tag: updataData[6],
                            bnc: updataData[7],
                            frq: updataData[8],
                        },
                        success: function (result) {
                            console.log(result);
                            getData(vm.items[1].pageNum);
                            for (let i = 1; i < 10; i++) {
                                let secletOneTd = secletTd.eq(i);
                                secletOneTd.html(secletOneTd.children().val());
                            }
                            secletTd.eq(10).children("button").eq(0).css("display", "none");
                            secletTd.eq(10).children("button").eq(1).css("display", "inline");
                        },
                        error: function (result) {
                            console.log("失败");
                        }
                    })
                }
            }
        }
    });
    $(document).ready(function () {
        getData(1);
    });
    $("#nextPage").click(function () {
        if (++vm.items[1].pageNum <= vm.items[0].pages) {
            getData(vm.items[1].pageNum);
        }
    });
    $("#lastPage").click(function () {
        if (--vm.items[1].pageNum >= 1) {
            getData(vm.items[1].pageNum);
        }
    });
    $("#insertWord").click(function () {
        console.log("插入数据")
        console.log(vm.items[2].tableData);
        vm.items[2].tableData.unshift({
            bnc: 0,
            collins: "",
            definition: "",
            exchange: "",
            frq: 0,
            id: 0,
            phonetic: "",
            pos: "",
            tag: "",
            translation: "",
            word: ""
        });
        setTimeout(function () {
            $("#tdUpdata").click();
        }, 200);
    })

    function getData(pageNum) {
        $.ajax({
            type: "get",
            url: "${request.getContextPath()}/getWordList/" + pageNum,
            dataType: "json", //跨域json请求一定是jsonp
            data: {},
            success: function (result) {
                console.log(result);
                vm.items[0].pages = result["totalPage"];
                vm.items[1].pageNum = result["pageNum"];
                vm.items[2].tableData = result["data"];
            },
            error: function (result) {
                console.log("失败");
            }
        })
    }
    function insertCet4Word(insertData, tr_id) {
        console.log("insert 数据");
        $.ajax({
            type: "post",
            url: "${request.getContextPath()}/insertWord/",
            dataType: "json",
            data: {
                word: insertData[0],
                phonetic: insertData[1],
                definition: insertData[2],
                translation: insertData[3],
                pos: insertData[4],
                collins: insertData[5],
                tag: insertData[6],
                bnc: insertData[7],
                frq: insertData[8],
            },
            success: function (result) {
                var secletTr = $("#tableDisPaly tr").eq(tr_id);
                var secletTd = secletTr.children("td");
                console.log(result);
                getData(vm.items[1].pageNum);
                for (let i = 1; i < 10; i++) {
                    let secletOneTd = secletTd.eq(i);
                    secletOneTd.html(secletOneTd.children().val());
                }
                secletTd.eq(10).children("button").eq(0).css("display", "none");
                secletTd.eq(10).children("button").eq(1).css("display",
                    "inline");
            },
            error: function (result) {
                console.log("失败");
            }
        })
    }
    $("#selectCet4WordByWord").click(function () {
        console.log("123");
        var wordInput = $("#selectWordInput").val()
        $.ajax({
            type: "post",
            url: "${request.getContextPath()}/selectWordByWord/",
            dataType: "json",
            data: {
                word: wordInput
            },
            success: function (result) {
                console.log(result);
                vm.items[0].pages = result["totalPage"];
                vm.items[1].pageNum = result["pageNum"];
                vm.items[2].tableData = result["data"];
            },
            error: function (result) {
                console.log("失败");
            }
        })
    })
</script>
</body>

</html>


