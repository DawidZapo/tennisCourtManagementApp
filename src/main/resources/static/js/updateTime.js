function updateDateTime() {
    const now = new Date();
    const formattedDate = now.toLocaleDateString('pl-PL');
    const formattedTime = now.toLocaleTimeString('pl-PL');
    document.getElementById('current-date-time').textContent = `${formattedDate} ${formattedTime}`;
}

// Initial update
updateDateTime();
setInterval(updateDateTime, 1000);