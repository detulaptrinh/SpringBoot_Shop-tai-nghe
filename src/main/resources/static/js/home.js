/*==================== tooltip ====================*/
$(function () {
    $('[data-toggle="tooltip"]').tooltip()
})

/*==================== SHOW SCROLL TOP ====================*/
function scrollTop(){
    const scrollTop = document.getElementById('scroll-top');
    // When the scroll is higher than 560 viewport height, add the show-scroll class to the a tag with the scroll-top class
    if(this.scrollY >= 560) scrollTop.classList.add('show-scroll'); else scrollTop.classList.remove('show-scroll')
}
window.addEventListener('scroll', scrollTop);

$(document).ready(function() {
	// $('#part-1').on('click', function(event) {
	// 	event.preventDefault();

	// 	$('html, body').animate({scrollTop: 1500},400);
	// });

	$('.scrolltop a').click(function(event) {

		// $('#part-1').offset().top;
		// hiệu ứng
		$('html, body').animate({scrollTop: position},1400,'easeInOutSine');
	});
});


/*==================== input number ====================*/
$(document).ready(function(){
	$('.count').prop('disabled', true);
	   $(document).on('click','.plus',function(){
		$('.count').val(parseInt($('.count').val()) + 1 );
	});
	$(document).on('click','.minus',function(){
		$('.count').val(parseInt($('.count').val()) - 1 );
			if ($('.count').val() == 0) {
				$('.count').val(1);
			}
		});
 });




