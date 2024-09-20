<?php

include("connection.php");

$latitute = "10.0068309";
$longitude = "76.3029888";

// Haversine formula for distance calculation
//$distanceFormula = "6371 * 2 * ASIN(SQRT(POWER(SIN(RADIANS((latitude - $latitute) / 2)), 2) + COS(RADIANS($latitute)) * COS(RADIANS(longitude)) * POWER(SIN(RADIANS((longitude - $longitude) / 2)), 2)))";

$sql = "SELECT *,  SQRT( POW(69.1 * (latitude - '$latitute'), 2) + POW(69.1 * ('$longitude' - longitude) * COS(latitude / 57.3), 2)) AS distance FROM restaurant_tbl HAVING distance < 2 ORDER BY distance DESC";

$result = mysqli_query($con, $sql);

if(mysqli_num_rows($result) > 0) {
    while($row = mysqli_fetch_assoc($result)) {
        $data["data"][] = $row;
    }
    echo json_encode($data);
} else {
    echo "failed";
}


?>