const datePicker = document.getElementById('date-picker');
document.addEventListener('DOMContentLoaded', function() {
    const datePicker = document.getElementById('date-picker');
    const urlParams = new URLSearchParams(window.location.search);

    if (urlParams.has('date')) {
        const urlDate = urlParams.get('date');
        datePicker.value = urlDate;
    } else {
        const today = new Date().toISOString().slice(0, 10);
        datePicker.value = today;
    }

    datePicker.addEventListener('change', function() {
        const selectedDate = datePicker.value;
        changeDateWithSelected(selectedDate);
    });
});

function changeDateWithSelected(selectedDate) {
    window.location.href = `/?date=${selectedDate}`;
}
