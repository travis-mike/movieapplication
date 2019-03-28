(function() {

    "use script";

    let usernameId = document.querySelector("#username-field").innerHTML;
    let movieId = document.querySelector("#movie-id").innerHTML;

    console.log(usernameId);
    console.log(movieId);

    document.querySelector("#add-to-favorites").addEventListener("click", updateMovieListWithPatchRequest);
    // document.querySelector("#one-star").addEventListener("click", )

    function updateMovieListWithPatchRequest (event) {
        event.preventDefault();
        console.log(usernameId);
        console.log(movieId);
        fetch("/api/v1/" + usernameId + "/add/" + movieId, {
            method: 'PATCH',
            headers: { 'Content-Type': 'application/json' }
        });
    }

})();
