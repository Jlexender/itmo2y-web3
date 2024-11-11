function handleFormSubmit(event) {
    if (event.status === 'success') {
        playShootSound();

        const xValue = document.getElementById('main-form:x').value;
        const yValue = document.getElementById('main-form:y').value;
        const rValue = document.getElementById('main-form:r').value;

        const resultValue = document.getElementById('main-form:result').value === 'Пробитие';
        const timeValue = document.getElementById('main-form:time').value;

        const newRow = document.createElement('tr');
        newRow.innerHTML = `
            <td>${xValue}</td>
            <td>${yValue}</td>
            <td>${rValue}</td>
            <td>${resultValue ? "Пробитие" : "Не пробил"}</td>
            <td>${timeValue} ns</td>
        `;

        const tableBody = document.querySelector('#results-table tbody');
        tableBody.prepend(newRow);

        if (resultValue) {
            announceGood();
        } else {
            announceBad();
        }

        insertPoint(xValue, yValue, rValue, resultValue);
    }
}
