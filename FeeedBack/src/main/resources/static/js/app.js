// Инициализация рейтинга
function initRatingStars() {
    document.querySelectorAll('.rating-star').forEach(star => {
        star.addEventListener('click', function() {
            const ratingContainer = this.closest('.rating');
            const input = ratingContainer.querySelector('input[type="hidden"]');
            const value = this.getAttribute('data-value');

            input.value = value;

            // Подсветка звезд
            ratingContainer.querySelectorAll('.rating-star').forEach((s, i) => {
                s.classList.toggle('text-warning', i < value);
            });
        });
    });
}

// Инициализация при загрузке страницы
document.addEventListener('DOMContentLoaded', function() {
    initRatingStars();
});