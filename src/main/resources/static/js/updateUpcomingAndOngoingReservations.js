document.addEventListener("DOMContentLoaded", function() {
    function fetchData(){
        const courtPreview = document.getElementById('courtPreview');

        fetch('/api/courtReservations/upcomingAndOngoing')
            .then(response => response.json())
            .then(reservations => {
                for(let i=1;i<=5;i++){
                    const card = document.getElementById(`futureCourtCard${i}`);
                    const body = card.querySelector('.card-body');
                    console.log(body);
                    while (body.firstChild) {
                        body.removeChild(body.firstChild);
                    }

                }

                reservations.forEach(reservation => {
                    console.log(reservation);
                    const courtId = reservation.courtNumber;
                    const card = document.getElementById(`futureCourtCard${courtId}`);

                    if (card) {
                        const body = card.querySelector('.card-body');
                        // body.innerHTML = '';

                        if (reservation) {
                            const paragraph1 = document.createElement('p');
                            paragraph1.classList.add('mb-2');
                            paragraph1.innerHTML = `<a href="/reservation?id=${reservation.id}" style="text-decoration: none;"><p class="mb-0" style="color: black;"><strong>${reservation.timeStart.toString().substring(0,5)} - ${reservation.timeEnd.toString().substring(0,5)}</strong> : ${reservation.customer.firstName} ${reservation.customer.lastName}<hr class="my-2"></p></a>`;
                            body.appendChild(paragraph1);
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