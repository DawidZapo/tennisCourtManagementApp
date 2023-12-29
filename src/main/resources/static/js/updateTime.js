function updateDateTime() {
    const now = new Date();
    const formattedDate = now.toLocaleDateString('pl-PL');
    const formattedTime = now.toLocaleTimeString('pl-PL');
    document.getElementById('current-date-time').textContent = `${formattedDate} ${formattedTime}`;
}

// Initial update
updateDateTime();
setInterval(updateDateTime, 1000);





const datePicker = document.getElementById('date-picker');
document.addEventListener('DOMContentLoaded', function() {
    const datePicker = document.getElementById('date-picker');

    datePicker.addEventListener('change', function() {
        const selectedDate = datePicker.value;
        changeDateWithSelected(selectedDate);
    });
});

function changeDateWithSelected(selectedDate) {
    window.location.href = `/?date=${selectedDate}`;
}


