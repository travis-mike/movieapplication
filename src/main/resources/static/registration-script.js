(function() {

    let userExistsInDatabase = false;

    document.getElementById("submit").style.display = 'none';
    document.querySelector("#username").addEventListener('keyup', checkIfUserExistsInDatabase);

    function checkIfUserExistsInDatabase() {
        let enteredUsername = document.querySelector('#username').value;
        fetch("register/" + enteredUsername).then(response => {
            response.json().then(data => {
                console.log(data);
                allowUserToSubmitFormIfUsernameIsAvailable(data);
            })
        })

    }

    function allowUserToSubmitFormIfUsernameIsAvailable(data) {
        if (!data) {
            document.getElementById("submit").style.display = '';
        } else document.getElementById("submit").style.display = 'none';
    }


})();