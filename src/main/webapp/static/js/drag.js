$(document).ready(function() {
    const canvas = $('#main-canvas'); // Получаем элемент .plotData
    let isDragging = false;
    let offsetX, offsetY;

    // Начало перетаскивания
    canvas.on('mousedown', function(e) {
        isDragging = true;
        offsetX = e.clientX - canvas.offset().left;
        offsetY = e.clientY - canvas.offset().top;
        canvas.css('cursor', 'grabbing'); // Изменение курсора на перетаскивание
    });

    // Движение мыши
    $(document).on('mousemove', function(e) {
        if (isDragging) {
            const x = e.clientX - offsetX;
            const y = e.clientY - offsetY;

            canvas.css({
                position: 'absolute',
                left: `${x}px`,
                top: `${y}px`
            });
        }
    });

    // Остановка перетаскивания
    $(document).on('mouseup', function() {
        if (isDragging) {
            isDragging = false;
            canvas.css('cursor', 'default'); // Возвращаем курсор
        }
    });
});
