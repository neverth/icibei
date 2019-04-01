<!doctype html>
<html lang="zh">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>IcIbeI</title>
  <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
  <script src="https://cdn.bootcss.com/howler/2.1.1/howler.core.min.js"></script>
  <link href="http://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
  <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
  <link rel="stylesheet" href="./css/keyWrapper.css">
  <link rel="stylesheet" href="./css/main.css">
  <link rel="stylesheet" href="./css/shake.css">
  <link rel="stylesheet" href="./css/siderbar.css">

</head>

<body id="body">
  <div id="nav-siderbar" class="sidebar-wrapper">
    <a id="toggle-sidebar" style="cursor:pointer">
      <i class="fa fa-bars"></i>
    </a>
    <div class="siderbar-header">
      <h2>IcIbeI</h2>
      <hr>
      <div class="hearder-list">
        <ul>
          <li><a href="#">单词本</a></li>
          <li><a href="#">单词本</a></li>
          <li><a href="#">单词本</a></li>
          <li><a href="#">单词本</a></li>
          <li><a href="#">单词本</a></li>
        </ul>
      </div>
    </div>
    <div class="siderbar-footer">
      <p>© 2019 NEVERTHELESS. 赣ICP备19000765号.</p>
      <p>TRY YOU DO.</p>
    </div>
  </div>
  <div id="content" class="page-content" >

    <div id="display" class="display_i">
      <p class="engli">{{wordlist.word}}</p>
      <p class="chin ">{{wordlist.chinese}}</p>
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

  <script src="./js/index.js"></script>
  <script src="./js/siderbar.js"></script>
  <script>
  </script>
  <?php
  require('./php/getList.php');
  ?>

</body>

</html>