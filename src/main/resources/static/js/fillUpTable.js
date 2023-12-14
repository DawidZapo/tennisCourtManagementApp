class Customer {
    constructor(id, firstName, lastName, phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }
}

class Reservation {
    constructor(id, courtNumber, reservationDate, timeStart, timeEnd, duration, priceSchedule, totalPrice, comments, reservationMadeTimestamp, paid, doublesMatch, customer) {
        this.id = id;
        this.courtNumber = courtNumber;
        this.reservationDate = reservationDate;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.duration = duration;
        this.priceSchedule = priceSchedule;
        this.totalPrice = totalPrice;
        this.comments = comments;
        this.reservationMadeTimestamp = reservationMadeTimestamp;
        this.paid = paid;
        this.doublesMatch = doublesMatch;
        this.customer = new Customer(
            customer.id,
            customer.firstName,
            customer.lastName,
            customer.phone
        );
    }
}

async function fetchReservations(date) {
    try {
        const response = await fetch(`http://localhost:8080/api/reservations?date=${date}`);
        const reservationsData = await response.json();

        const reservationsList = reservationsData.map(data => {
            return new Reservation(
                data.id,
                data.courtNumber,
                data.reservation_date,
                data.timeStart,
                data.timeEnd,
                data.duration,
                data.priceSchedule,
                data.totalPrice,
                data.comments,
                data.reservationMadeTimestamp,
                data.paid,
                data.doublesMatch,
                data.customer // Przekazanie danych klienta do konstruktora Reservation
            );
        });

        // Tutaj możesz wykonać operacje na danych, np. wyświetlić je w konsoli
        console.log(reservationsList);

        return reservationsList;
    } catch (error) {
        console.error('Wystąpił błąd podczas pobierania danych z API:', error);
    }
}


function iterateByCourts(reservation) {
    const table = document.getElementById('reservationTable'); // Znajdź tabelę po ID

    if (table) {
        const courtsCount = table.rows[0].cells.length - 1; // Zlicz liczbę kolumn kortów
        const startHour = 7; // Godzina początkowa
        const interval = 30; // Interwał czasowy w minutach

        for (let courtIndex = 1; courtIndex <= courtsCount; courtIndex++) {
            console.log(`Iteracja dla Kortu ${courtIndex}`);
            let currentHour = startHour;
            let currentMinute = 30;

            for (let i = 0; i < table.rows.length; i++) {
                const row = table.rows[i];
                const cell = row.cells[courtIndex]; // Użyj indeksu kolumny odpowiadającej danemu kortowi
                // Tutaj możesz wykonywać operacje na każdej komórce tabeli
                const timeString = `${('0' + currentHour).slice(-2)}:${('0' + currentMinute).slice(-2)}`;
                console.log(`Wiersz ${timeString}, Kolumna ${courtIndex}: ${cell.textContent}`);

                //console.log(reservation.startTime);

                currentMinute += interval;
                if (currentMinute >= 60) {
                    currentHour++;
                    currentMinute = 0;
                }
            }
        }
    } else {
        console.log('Nie znaleziono tabeli o podanym ID.');
    }
}
fetchReservations('2023-12-15')
    .then(reservationsList => {
        reservationsList.forEach(reservation => {
            console.log(reservation); // Tutaj będziesz mieć dostęp do pojedynczych obiektów Reservation
            iterateByCourts(reservation);
        });
    })
    .catch(error => {
        console.error('Wystąpił błąd:', error);
    });

//iterateByCourts(fetchReservations('2023-12-15'));
