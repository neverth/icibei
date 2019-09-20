<%--
  Created by IntelliJ IDEA.
  User: HUSHUHUA
  Date: 2019/9/9
  Time: 15:16
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
  <title>icibei-admin</title>
  <style>
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
    .footer {
      position: absolute;
      bottom: 0;
      width: 100%;
      text-align: center;
      color: #999;
      border-top: 1px solid #eee;
      background-color: #fff;

    }
  </style>
</head>
<body>

<div class="pa-header">
  <div class="pa-header-left">
    <p>ICIBEI</p>
  </div>
</div>

<div class="container">
  <div class="row">
    <div class="col-sm">
    </div>
    <div class="col-sm">
      <form action="${request.getContextPath()}/dashboardLogin">
        <div class="form-group">
          <label for="exampleInputEmail1">请输入邮箱：</label>
          <input type="email" class="form-control" aria-describedby="emailHelp" placeholder="Enter email" name="email">
        </div>
        <div class="form-group">
          <label for="exampleInputPassword1">密码：</label>
          <input type="password" class="form-control"  placeholder="Password" name="password">
        </div>
        <button type="submit" class="btn btn-primary">登录</button>
      </form>
    </div>
    <div class="col-sm">
    </div>
  </div>
</div>
<footer class="footer" >
  <p>copyright © 2019
    <a href="#">NEVERTHELESS</a>
  </p>
</footer>
</body>
</html>
