function updateDateTime() {
    const now = new Date();
    const formattedDate = now.toLocaleDateString('pl-PL');
    const formattedTime = now.toLocaleTimeString('pl-PL');
    document.getElementById('current-date-time').textContent = `${formattedDate} ${formattedTime}`;
}

// Initial update
updateDateTime();
setInterval(updateDateTime, 1000);


function updateCurrentDay(date) {
    const formattedDate = date.toLocaleDateString('pl-PL', { day: 'numeric', month: 'numeric' });
    document.getElementById('current-day').textContent = `Bieżący dzień: ${formattedDate}`;
}

const datePicker = document.getElementById('date-picker');
datePicker.addEventListener('change', function () {
    const selectedDate = new Date(this.value);
    updateCurrentDay(selectedDate);
});

document.getElementById('prev-day').addEventListener('click', function () {
    const datePicker = document.getElementById('date-picker');
    const currentDate = new Date(datePicker.value);
    currentDate.setDate(currentDate.getDate() - 1);
    datePicker.value = currentDate.toISOString().split('T')[0];
    updateCurrentDay(currentDate);
});

document.getElementById('next-day').addEventListener('click', function () {
    const datePicker = document.getElementById('date-picker');
    const currentDate = new Date(datePicker.value);
    currentDate.setDate(currentDate.getDate() + 1);
    datePicker.value = currentDate.toISOString().split('T')[0];
    updateCurrentDay(currentDate);
});

// Initial update
const today = new Date();
datePicker.value = today.toISOString().split('T')[0];
updateCurrentDay(today);