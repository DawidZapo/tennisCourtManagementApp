document.addEventListener("DOMContentLoaded", function() {
    var selectCustomers = document.querySelectorAll('select[id^=selectCustomer]');
    var submitBtn = document.getElementById('submitBtn');

    function checkSelectionForBtn() {
        var allSelected = true;
        selectCustomers.forEach(function(select) {
            if (select.value === '') {
                allSelected = false;
            }
        });

        submitBtn.disabled = !allSelected;
    }

    selectCustomers.forEach(function(select) {
        select.addEventListener('change', checkSelectionForBtn);
    });

    checkSelectionForBtn();
});