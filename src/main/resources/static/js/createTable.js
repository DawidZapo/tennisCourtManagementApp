// Pobranie referencji do elementu tbody w tabeli
var tbody = document.querySelector('tbody');

// Godzina początkowa
var startHour = 8;
var startMinute = 0;

// Godzina końcowa
var endHour = 22;
var endMinute = 0;

// Pętla dodająca wiersze z godzinami co 30 minut
for (var hour = startHour; hour < endHour; hour++) {
    for (var minute = 0; minute < 60; minute += 30) {
        // Formatowanie godziny i minuty
        var time = ('0' + hour).slice(-2) + ':' + ('0' + minute).slice(-2);

        // Tworzenie nowego wiersza
        var newRow = document.createElement('tr');

        // Dodawanie komórek do wiersza
        var timeCell = document.createElement('td');
        timeCell.textContent = time;
        newRow.appendChild(timeCell);

        // Dodawanie pustych komórek dla każdego kortu
        for (var court = 0; court < 5; court++) {
            var emptyCell = document.createElement('td');
            newRow.appendChild(emptyCell);
        }

        //dodanie przykladowych komorek
        // if (time === '09:30') {
        //     var kort1_9_30 = newRow.querySelectorAll('td')[1]; // Kolumna "Kort 1", wiersz 9:30
        //     kort1_9_30.textContent = 'Zajęty';
        // }
        //
        // if (time === '15:00') {
        //     var kort3_15_00 = newRow.querySelectorAll('td')[3]; // Kolumna "Kort 3", wiersz 15:00
        //     kort3_15_00.textContent = 'Zajęty';
        // }


        // Dodanie identyfikatorów dla odpowiednich wierszy
        if (time === '08:00') {
            newRow.setAttribute('id', 'morning');
        } else if (time === '12:00') {
            newRow.setAttribute('id', 'afternoon');
        } else if (time === '17:00') {
            newRow.setAttribute('id', 'evening');
        }

        // Dodanie nowego wiersza do tabeli
        tbody.appendChild(newRow);
    }
}
