

$("#toggle-sidebar").click(function(){
    $("#nav-siderbar").toggleClass("toggled");
    $("#content").toggleClass("toggled");
    // 该方法检查每个元素中指定的类。如果不存在则添加类，如果已设置则删除之
});
jQuery(function ($) {
    $(".sidebar-dropdown > a").click(function () {
        $(".sidebar-submenu").slideUp(250);
        if ($(this).parent().hasClass("active")) {
            $(".sidebar-dropdown").removeClass("active");
            $(this).parent().removeClass("active");
        } else {
            $(".sidebar-dropdown").removeClass("active");
            $(this).next(".sidebar-submenu").slideDown(250);
            $(this).parent().addClass("active");
        }

    });
});
$(function () {
    $("[data-toggle='popover']").popover();
});