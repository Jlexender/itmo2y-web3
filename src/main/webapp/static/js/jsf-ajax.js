function handleFormSubmit(event) {
    // Проверяем состояние выполнения AJAX-запроса
    if (event.status === 'success') {
        playShootSound();

        // Получаем значения полей
        const xValue = document.getElementById('main-form:x').value;
        const yValue = document.getElementById('main-form:y').value;
        const rValue = document.getElementById('main-form:r').value;

        console.log(xValue, yValue, rValue);

        // Получаем значения result и time, обновленные сервером
        const resultValue = document.getElementById('main-form:result').value === 'true';
        const timeValue = document.getElementById('main-form:time').value;

        // resultValue -> boolean

        // clone table template
        const newRow = $('#results-table template').contents().clone();
        // fill row with data
        newRow.find('td').eq(0).text(xValue);
        newRow.find('td').eq(1).text(yValue);
        newRow.find('td').eq(2).text(rValue);
        newRow.find('td').eq(3).text(new Date().toLocaleString());
        newRow.find('td').eq(4).text(resultValue ? "Пробитие" : "Не пробил");
        newRow.find('td').eq(5).text(`${timeValue} ns`);

        $('#results-table tbody').prepend(newRow);

        if (resultValue) {
            announceGood();
        } else {
            announceBad();
        }

        insertPoint(xValue, yValue, rValue, resultValue);
    }
}
