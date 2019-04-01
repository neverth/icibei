

$("#toggle-sidebar").click(function(){
    $("#nav-siderbar").toggleClass("toggled");
    $("#content").toggleClass("toggled");
    // 该方法检查每个元素中指定的类。如果不存在则添加类，如果已设置则删除之
});