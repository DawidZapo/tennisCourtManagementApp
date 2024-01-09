document.addEventListener("DOMContentLoaded", function() {
    var searchInputs = document.querySelectorAll('.row input#searchCustomer');
    var selectCustomers = document.querySelectorAll('.row select#selectCustomer');
    var submitBtn = document.getElementById('submitBtn');
    var defaultValue = document.getElementById('defaultValue');

    searchInputs.forEach(function(searchInput, index) {
        searchInput.addEventListener('input', function() {
            var alertDiv = document.getElementById('alertDiv-' + index);
            var addCustomerBtn = document.getElementById('addCustomerBtn-' + index);
            var inputPhone = document.getElementById('inputPhone-' + index);
            var searchValue = searchInput.value.trim().toLowerCase();
            var select = selectCustomers[index];
            var options = select.getElementsByTagName('option');
            var defaultOption = options[0];
            var foundCustomers = 0;

            for (var i = 0; i < options.length; i++) {
                var customerText = options[i].text.toLowerCase();
                var option = options[i];
                if (customerText.includes(searchValue)) {
                    option.style.display = '';
                    foundCustomers++;
                } else {
                    option.style.display = 'none';
                }
            }

            if (searchValue === '') {
                defaultOption.selected = true;
                defaultOption.style.display = '';
            } else {
                defaultOption.style.display = 'none';
            }


            //select.selectedIndex = -1;
            // mozna dodac opcje by zmienic to w ustawieniach
            for (let i = 0; i < options.length; i++) {
                var optionToCheck = options[i];
                if (optionToCheck.style.display !== 'none') {
                    optionToCheck.selected = true;
                    break;
                }
            }

            if (foundCustomers === 0) {
                alertDiv.style.display = '';
                addCustomerBtn.style.display = '';
            } else {
                alertDiv.style.display = 'none';
                addCustomerBtn.style.display = 'none';
            }


            checkSelectionForBtn(); // Sprawdzamy po zmianie wartości
        });
    });

    function checkSelectionForBtn() {
        var allSelected = true;

        selectCustomers.forEach(function(select) {
            if (select.value === '' || select.value === 'placeholder') {
                allSelected = false;
            }
        });

        submitBtn.disabled = !allSelected;

    }

    selectCustomers.forEach(function(select) {
        select.addEventListener('change', checkSelectionForBtn);
    });

    checkSelectionForBtn(); // Sprawdzamy na początku
});

