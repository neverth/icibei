<%--
  Created by IntelliJ IDEA.
  User: HUSHUHUA
  Date: 2019/9/9
  Time: 16:38
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
    body{
      margin:0 auto;
    }
    .pa-header{
      height:48px;
      background-color:#3C8EBE;
      margin-bottom: 50px;
      /* box-shadow: 0 2px 4px 0 #3C8EBE; */
    }
    .pa-header-left{
      height:48px;
      background-color:#3680AB;
      width: 200px;
    }
    .pa-header-left p{
      font-size: 30px;
      color: #fff;
      margin-left: 27%;
    }
    .pa_body .body-menu{
      position:absolute;
      top:48px;
      left:0;
      bottom:0;
      width:200px;
      background-color:#3E4356;
    }
    .pa_body .body-content{
      position:absolute;
      top:48px;
      left:210px;
      right:0;
      background-color:#fff;
      bottom:0;
      /* “bottom:0 与 overflow” 结合使用的效果：当内容超出显示器时，自动添加本区域的滚动条，其他区域保持不变*/
      overflow:auto;
      /*除了上面的方式，还有一种是不定义bottom，也不用overflow，这样内容是多少，中间内容区的行高就是多少，自适应，但是菜单和标题栏就会随着滚轮而动*/
    }
    .body-menu-ul{
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
    #tdUpdata{
      display:inline;
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
    <div class="col breadcrumb" >
      <p>ICIBEI/用户管理</p>
    </div>
    <table class="table" id="tableDisPaly">

      <thead>
      <tr>
        <th scope="col">#</th>
        <th scope="col">用户Id</th>
        <th scope="col">用户账号</th>
        <th scope="col">用户密码</th>
        <th scope="col">最后一次登录时间</th>
        <th scope="col">操作</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(item, index) in items[2].tableData" v-if="item.user_id != -999" >
        <th scope="row">{{ index + 1 }}</td>
        <td>{{ item.user_id }}</td>
        <td>{{ item.user_account }}</td>
        <td>{{ item.user_passw }}</td>
        <td>{{ item.user_last_login }}</td>
        <td>
          <a id="tdOk" href='javascript:' v-on:click='updataUser(item.user_id, index + 1, 1)'>完成</a>
          <a id="tdUpdata" href="javascript:" v-on:click="updataUser(item.user_id, index + 1, 0)" >修改</a> |
          <a href="javascript:" v-on:click="deleteUser(item.user_id)">删除</a>
        </td>
      </tr>
      <tr>
        <td> <span id="lastPage">上一页</span>  <span id="nextPage">下一页</span> </td>
      </tr>
      </tbody>
    </table>
  </div>
</div>
<script>
    var vm = new Vue({
        el: '#tableDisPaly',
        data: {
            items: [
                {pages : 0},
                {pageNum : 0},
                {tableData : []}
            ]
        },
        methods:{
            deleteUser :function(id){
                console.log("正在删除id" + id + "的用户");
                $.ajax({
                    type: "get",
                    url: "${request.getContextPath()}/userAdDeleteApi/" + id,
                    dataType: "json",
                    data: {
                    },
                    success: function(result){
                        console.log(result);
                        getData(vm.items[1].pageNum);
                    },
                    error: function(result) {
                        console.log("失败");
                    }
                })
            },
            updataUser: function(user_id, tr_id, flag){
                var secletTr = $("#tableDisPaly tr").eq(tr_id);
                var secletTd = secletTr.children("td");
                var updataData = [];
                if (flag == 0){
                    console.log("修改html");
                    for(let  i = 1; i < 3; i++){
                        let secletOneTd = secletTd.eq(i);
                        secletOneTd.html('<input size="8" type="text" value="' + secletOneTd.text() + '">');
                    }
                    secletTd.eq(4).children("a").eq(0).css("display", "inline");
                    secletTd.eq(4).children("a").eq(1).css("display", "none");
                }
                else{
                    console.log("准备修改");
                    for(let  i = 1; i < 3; i++){
                        let secletOneTdInput = secletTd.eq(i).children();
                        updataData.push(secletOneTdInput.val());
                        console.log(updataData) ;
                    }
                    $.ajax({
                        type: "post",
                        url: "${request.getContextPath()}/userAdUpdateApi/",
                        dataType: "json",
                        data: {
                            user_id: user_id,
                            user_account : updataData[0],
                            user_passw: updataData[1],
                        },
                        success: function(result){
                            console.log(result);
                            getData(vm.items[1].pageNum);
                            for(let  i = 1; i < 3; i++){
                                let secletOneTd = secletTd.eq(i);
                                secletOneTd.html(secletOneTd.children().val());
                            }
                            secletTd.eq(4).children("a").eq(0).css("display", "none");
                            secletTd.eq(4).children("a").eq(1).css("display", "inline");
                        },
                        error: function(result) {
                            console.log("失败");
                        }
                    })
                }
            }
        }
    })
    $(document).ready(function(){
        getData(1);
    });
    $("#nextPage").click(function(){
        if(++vm.items[1].pageNum <= vm.items[0].pages){
            getData(vm.items[1].pageNum);
        }
    })
    $("#lastPage").click(function(){
        if (--vm.items[1].pageNum >= 1){
            getData(vm.items[1].pageNum);
        }

    })
    function getData(pageNum) {
        $.ajax({
            type: "get",
            url: "${request.getContextPath()}/userAdApi/" + pageNum,
            dataType: "json", //跨域json请求一定是jsonp
            data: {
            },
            success: function(result){
                console.log(result);
                vm.items[0].pages = result[result.length - 1]['user_passw'];
                vm.items[1].pageNum = result[result.length - 1]['user_account'];
                vm.items[2].tableData = result;
            },
            error: function(result) {
                console.log("失败");
            }
        })
    }
</script>
</body>
</html>

