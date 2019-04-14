(function() {

    let usernameIsValid = false;
    let emailIsValid = false;
    let passwordIsValid = false;
    let usernameField = document.querySelector("#username");
    let userWarning = document.querySelector("#user-warning");
    let emailField = document.querySelector("#email");
    let emailWarning = document.querySelector("#email-warning");

    document.getElementById("submit").style.display = 'none';

    usernameField.addEventListener('keyup', checkIfUserExistsInDatabase);
    emailField.addEventListener('keyup', checkIfEmailExistsInDatabase);

    function checkIfUserExistsInDatabase() {
        fetch("register/" + usernameField.value).then(response => {
            response.json().then(data => {
                allowUserToSubmitFormIfUsernameIsAvailable(data);
            })
        })
    }

    function checkIfEmailExistsInDatabase() {
        fetch("register/email/" + emailField.value).then(response => {
            response.json().then(data => {
                declareEmailAsValidIfDoesNotExistInDB(data);
            })
        })
    }

    function allowUserToSubmitFormIfUsernameIsAvailable(data) {
        if (usernameField.value.length < 6 || usernameField.value.length > 20) {
            document.querySelector("#submit").style.display = 'none';
            userWarning.innerHTML = "<p>Username must be between 6 and 20 characters.</p>";
        } else if (data) {
            document.querySelector("#submit").style.display = 'none';
            userWarning.innerHTML = "<p>Username already exists!</p>"
        } else if (!data) {
            document.querySelector("#submit").style.display = '';
            userWarning.innerHTML = "<p>Username is valid!</p>";
            usernameIsValid = true;
        }
    }

    function declareEmailAsValidIfDoesNotExistInDB(data) {
        if (!validateEmail(emailField.value)) {
            emailWarning.innerHTML = "<p>Email needs to be in correct format.</p>";
        } else if (data) {
            console.log(data);
            emailWarning.innerHTML = "<p>Email already exists</p>";
        } else if (validateEmail(emailField.value) && !data) {
            emailWarning.innerHTML = "<p>Email looks valid</p>";
        }

    }

    function validateEmail(email) {
        let re = /\S+@\S+\.\S+/;
        return re.test(email);
    }


})();