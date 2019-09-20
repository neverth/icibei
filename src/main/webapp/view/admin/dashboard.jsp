<%--
  Created by IntelliJ IDEA.
  User: HUSHUHUA
  Date: 2019/9/9
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link href="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/css/bootstrap-grid.css" rel="stylesheet">
  <link href="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/css/bootstrap.css" rel="stylesheet">
  <script src="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/js/bootstrap.js"></script>
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
      <p>ICIBEI/</p>
    </div>
    <table class="table">
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
      <tr>
        <th scope="row">1</th>
        <td>Mark</td>
        <td>Otto</td>
        <td>@mdo</td>
        <td>@mdo</td>
      </tr>
      <tr>
        <th scope="row">2</th>
        <td>Jacob</td>
        <td>Thornton</td>
        <td>@fat</td>
        <td>@mdo</td>
      </tr>
      <tr>
        <th scope="row">3</th>
        <td>Larry</td>
        <td>the Bird</td>
        <td>@twitter</td>
        <td>@mdo</td>
      </tr>
      </tbody>
    </table>
  </div>
</div>

</body>
</html>
