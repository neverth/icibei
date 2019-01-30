
var osc = {};
var wordList = new Array("whether", "abbreviate", "destination", "operating"
, 'coprocessor', 'automatically', "determine" );
function TextCut($) {
  var target = $;
  var targetTxt = target.contents();
  targetTxt.each(function () {
    var texts = targetTxt;
    if(this.nodeType == 3){
      texts.replaceWith(texts.text().replace(/(\S)/g,'<span>$1</span>'));
      console.log("单词切割完成")
    }
  })
}
function keyprassJudge(word, key){
  for (var i = 0; i < word.length; i++){
    if(word.eq(i).prop("sta") != 1){
      var inputKey = word.eq(i).prop("textContent");
      if(key == inputKey){
        $(".ex1 span").eq(i).css("color", "#ffffff");
        $(".ex1 span").eq(i).prop("sta", 1);
        if(i == word.length - 1) return 2;
        else return 1;
      }
      else{
        console.log("cuowu");
        break;
      }
    }
    else{
      console.log("步过");
    }
  }
  return 0;
}

$(window).keydown(function(event) {
  //键盘区域
  var code = (event.keyCode ? event.keyCode : event.which);
  if(osc[code])
    return;
  $("li[data-code='"+code+"']").addClass("active")
  var key = $("li[data-code='"+code+"']").data("key");
  var code = $("li[data-code='"+code+"']").data("code");
  console.log("按住:" + key);
  //单词区域
  var word = $(".ex1 span");
  if(keyprassJudge(word, key) == 2){
    $(".ex1").children().remove();
    $(".ex1").text(wordList[Math.floor(Math.random()*6)]);
    TextCut($(".ex1"));
  }
});

$(window).keyup(function(event) {
  var code = (event.keyCode ? event.keyCode : event.which);
  $("li[data-code='"+code+"']").removeClass("active")
  var key = $("li[data-code='"+code+"']").data("key");
  var code = $("li[data-code='"+code+"']").data("code");
  if(!osc[code])
    return;
});

$("li").mousedown(function(event) {
  $(this).addClass("active");
  var key = $(this).data("key");
  var code = $(this).data("code");
  console.log("点住:" + key );
});

$("li").mouseup(function(event) {
  $(this).removeClass("active");
  var key = $(this).data("key");
  var code = $(this).data("code");
});

$(window).on("load", function () {
  TextCut($(".ex1"));
})
