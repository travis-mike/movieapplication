(function() {

    "use script";

    let usernameId = document.querySelector("#username-field").innerHTML;
    let movieId = document.querySelector("#movie-id").innerHTML;
    let userPreferredGenre = document.querySelector("#preferred-genre").innerHTML;
    let movieGenre = document.querySelector("#movie-genre").innerHTML;
    let movieGenreMatch = false;


    console.log(userPreferredGenre);
    console.log(movieGenre);

    if (userPreferredGenre === movieGenre) {
        movieGenreMatch = true;
    }

    document.querySelector("#add-to-favorites").addEventListener("click", updateMovieListWithPatchRequest);
    document.querySelector("#one-star").addEventListener("click", updateOneStarMovieRatingWithPatchRequest);
    document.querySelector("#two-star").addEventListener("click", updateTwoStarMovieRatingWithPatchRequest);
    document.querySelector("#three-star").addEventListener("click", updateThreeStarMovieRatingWithPatchRequest);
    document.querySelector("#four-star").addEventListener("click", updateFourStarMovieRatingWithPatchRequest);
    document.querySelector("#five-star").addEventListener("click", updateFiveStarMovieRatingWithPatchRequest);

    function updateMovieListWithPatchRequest(event) {
        event.preventDefault();
        fetch("/api/v1/" + usernameId + "/add/" + movieId, {
            method: 'PATCH',
            headers: { 'Content-Type': 'application/json' }
        });
    }

    function updateOneStarMovieRatingWithPatchRequest(event) {
        event.preventDefault();
        fetch("/api/v1/" + movieId + "/rating/" + movieGenreMatch, {
            method: 'PATCH',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                "totalPossiblePoints": 100,
                "totalActualPoints": 20,
                "totalPossibleGenrePoints": 100,
                "totalActualGenrePoints": 20
            })
        });
    }

    function updateTwoStarMovieRatingWithPatchRequest(event) {
        event.preventDefault();
        fetch("/api/v1/" + movieId + "/rating/" + movieGenreMatch, {
            method: 'PATCH',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                "totalPossiblePoints": 100,
                "totalActualPoints": 40,
                "totalPossibleGenrePoints": 100,
                "totalActualGenrePoints": 40
            })
        })
    }

    function updateThreeStarMovieRatingWithPatchRequest(event) {
        event.preventDefault();
        fetch("/api/v1/" + movieId + "/rating/" + movieGenreMatch, {
            method: 'PATCH',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                "totalPossiblePoints": 100,
                "totalActualPoints": 60,
                "totalPossibleGenrePoints": 100,
                "totalActualGenrePoints": 60
            })
        })
    }

    function updateFourStarMovieRatingWithPatchRequest(event) {
        event.preventDefault();
        fetch("/api/v1/" + movieId + "/rating", {
            method: 'PATCH',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                "totalPossiblePoints": 100,
                "totalActualPoints": 80,
                "totalPossibleGenrePoints": 100,
                "totalActualGenrePoints": 80
            })
        })
    }

    function updateFiveStarMovieRatingWithPatchRequest(event) {
        event.preventDefault();
        fetch("/api/v1/" + movieId + "/rating", {
            method: 'PATCH',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                "totalPossiblePoints": 100,
                "totalActualPoints": 100,
                "totalPossibleGenrePoints": 100,
                "totalActualGenrePoints": 100
            })
        })
    }



})();
