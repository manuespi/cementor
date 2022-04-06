$(document).ready(function(){

    toggle();
});

function toggle(){
    $('.box').click(function(e){
        e.preventDefault();
        $(this).closest('.comment_structure').find('.sectionToggle').slideToggle();
    });
}