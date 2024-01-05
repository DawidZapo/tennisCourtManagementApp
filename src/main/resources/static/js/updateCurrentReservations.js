document.addEventListener("DOMContentLoaded", function() {
    function fetchData(){
        const courtPreview = document.getElementById('courtPreview');

        fetch('/api/courtReservations/current')
            .then(response => response.json())
            .then(reservations => {
                for(let i=1;i<=5;i++){
                    const body = document.getElementById(`displayCourtCardBody${i}`);
                    body.innerHTML = `
                            <p class="mb-2"><strong>status: </strong>wolny</p>
                        `;
                }

                reservations.forEach(reservation => {
                    const courtId = reservation.courtNumber;
                    const card = document.getElementById(`displayCourtCard${courtId}`);

                    if (card) {
                        const body = card.querySelector('.card-body');
                        // body.innerHTML = '';

                        if (reservation) {
                            body.setAttribute('href',`/reservation?id=${reservation.id}`);
                            body.innerHTML = `
                            <p class="mb-2"><strong>Klient: </strong>${reservation.customer.firstName} ${reservation.customer.lastName}</p>
                            <p class="mb-2"><strong>Godzina rozpoczęcia: </strong>${reservation.timeStart.toString().substring(0,5)}</p>
                            <p class="mb-2"><strong>Godzina zakończenia: </strong>${reservation.timeEnd.toString().substring(0,5)}</p>

                        `;
                        }
                    }
                });
            })
            .catch(error => {
                console.error('Błąd podczas pobierania danych:', error);
            });
    }
    fetchData();
    setInterval(fetchData, 60000)

});