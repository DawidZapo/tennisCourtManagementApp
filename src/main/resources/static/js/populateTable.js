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
        const rows = table.rows.length-1;
        const courtIndex = reservation.courtNumber;

        for(let i=1; i <= rows;i++){
            const row = table.rows[i];
            const cell = row.cells[courtIndex];
            const string = reservation.timeStart.slice(0,5) +"court"+reservation.courtNumber;
            if(cell.id === string){
                const reservationTile = document.createElement('a');
                reservationTile.textContent = 'Rezerwacja'; // Możesz dostosować tekst oznaczenia

                const span = reservation.duration / 30;
                cell.rowSpan = span;

                // Dodanie klasy lub stylu dla oznaczenia (jeśli potrzebne)
                reservationTile.classList.add('btn', 'btn-warning', 'btn-custom'); // Dodaj klasę dla dodatkowego stylowania CSS

                cell.appendChild(reservationTile);
            }
        }

    } else {
        console.log('Nie znaleziono tabeli o podanym ID.');
    }
}

fetchReservations('2023-12-15')
    .then(reservationsList => {
        reservationsList.forEach(reservation => {
            iterateByCourts(reservation);
        });
    })
    .catch(error => {
        console.error('Wystąpił błąd:', error);
    });

//iterateByCourts(fetchReservations('2023-12-15'));
