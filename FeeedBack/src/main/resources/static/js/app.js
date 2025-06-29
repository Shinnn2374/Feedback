document.addEventListener('DOMContentLoaded', function() {
    // Обработка формы отзыва
    const feedbackForm = document.getElementById('feedback-form');
    if (feedbackForm) {
        feedbackForm.addEventListener('submit', function(e) {
            const ratings = document.querySelectorAll('input[name^="rating"]:checked');
            if (ratings.length < 3) {
                e.preventDefault();
                alert('Please rate all categories');
            }
        });
    }

    // Инициализация рейтинга
    const ratingStars = document.querySelectorAll('.rating-star');
    ratingStars.forEach(star => {
        star.addEventListener('click', function() {
            const ratingValue = this.getAttribute('data-value');
            const ratingInput = this.closest('.rating').querySelector('input[type="hidden"]');
            ratingInput.value = ratingValue;

            // Подсветка звезд
            const stars = this.parentElement.querySelectorAll('.rating-star');
            stars.forEach((s, index) => {
                if (index < ratingValue) {
                    s.classList.add('text-warning');
                } else {
                    s.classList.remove('text-warning');
                }
            });
        });
    });
});