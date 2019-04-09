(function() {

    "use script";

    let usernameId = document.querySelector("#username-field").innerHTML;
    let movieId = document.querySelector("#movie-id").innerHTML;
    let userPreferredGenre = document.querySelector("#preferred-genre").innerHTML;
    let movieGenre = document.querySelector("#movie-genre").innerHTML;
    let userHorrorPointValue = document.querySelector("#horror-points").innerHTML;
    let userDramaPointValue = document.querySelector("#drama-points").innerHTML;
    let userComedyPointValue = document.querySelector("#comedy-points").innerHTML;
    let userSciFiPointValue = document.querySelector("#scifi-points").innerHTML;
    let userRomancePointValue = document.querySelector("#romance-points").innerHTML;
    let userMiscPointValue = document.querySelector("#misc-points").innerHTML;
    let actualUserPointValue = 0;
    let movieGenreMatch = false;

    retrievePointValueOffUserObject(movieGenre);
    console.log(actualUserPointValue);

    if (userPreferredGenre === movieGenre) {
        movieGenreMatch = true;
    }

    document.querySelector("#add-to-favorites").addEventListener("click", updateMovieListWithPatchRequest);
    document.querySelector("#one-star").addEventListener("click", updateOneStarMovieRatingWithPatchRequest);
    document.querySelector("#two-star").addEventListener("click", updateTwoStarMovieRatingWithPatchRequest);
    document.querySelector("#three-star").addEventListener("click", updateThreeStarMovieRatingWithPatchRequest);
    document.querySelector("#four-star").addEventListener("click", updateFourStarMovieRatingWithPatchRequest);
    document.querySelector("#five-star").addEventListener("click", updateFiveStarMovieRatingWithPatchRequest);

    function retrievePointValueOffUserObject (movieGenre) {
        if (movieGenre === "horror") {
            actualUserPointValue = parseInt(userHorrorPointValue);
        } else if (movieGenre === "drama" || movieGenre === "crime") {
            actualUserPointValue = parseInt(userDramaPointValue);
        } else if (movieGenre === "comedy") {
            actualUserPointValue = parseInt(userComedyPointValue);
        } else if (movieGenre === "science fiction" || movieGenre === "fantasy") {
            actualUserPointValue = parseInt(userSciFiPointValue);
        } else if (movieGenre === "romance") {
            actualUserPointValue = parseInt(userRomancePointValue);
        } else {
            actualUserPointValue = parseInt(userMiscPointValue);
        }
    }

    function updateMovieListWithPatchRequest(event) {
        event.preventDefault();
        fetch("/api/v1/" + usernameId + "/add/" + movieId, {
            method: 'PATCH',
            headers: { 'Content-Type': 'application/json' }
        });
    }

    function updateOneStarMovieRatingWithPatchRequest(event) {
        event.preventDefault();
        console.log(actualUserPointValue);
        console.log(actualUserPointValue * 0.2);
        fetch("/api/v1/" + movieId + "/rating/" + movieGenreMatch, {
            method: 'PATCH',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                "totalPossiblePoints": 100,
                "totalActualPoints": 20,
                "totalPossibleGenrePoints": 100,
                "totalActualGenrePoints": 20,
                "totalPossibleWeightedPoints": actualUserPointValue,
                "totalActualWeightedPoints": actualUserPointValue * 0.2
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
                "totalActualGenrePoints": 40,
                "totalPossibleWeightedPoints": actualUserPointValue,
                "totalActualWeightedPoints": actualUserPointValue * 0.4
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
                "totalActualGenrePoints": 60,
                "totalPossibleWeightedPoints": actualUserPointValue,
                "totalActualWeightedPoints": actualUserPointValue * 0.6
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
                "totalActualGenrePoints": 80,
                "totalPossibleWeightedPoints": actualUserPointValue,
                "totalActualWeightedPoints": actualUserPointValue * 0.8
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
                "totalActualGenrePoints": 100,
                "totalPossibleWeightedPoints": actualUserPointValue,
                "totalActualWeightedPoints": actualUserPointValue
            })
        })
    }



})();
