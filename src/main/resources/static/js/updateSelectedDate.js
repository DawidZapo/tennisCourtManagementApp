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
