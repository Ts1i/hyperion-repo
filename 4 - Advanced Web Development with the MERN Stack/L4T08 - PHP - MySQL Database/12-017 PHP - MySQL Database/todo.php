<?php

require 'secrets.php';

/* Attempt MySQL server connection.  */
$mysqli = new mysqli("localhost", DB_UID, DB_PWD, "example_db");
 
// Check connection
if ($mysqli === false){
    die("ERROR: Could not connect. " . $mysqli->connect_error);
}
 
// Print host information
echo "Connect Successfully. Host info: " . $mysqli->host_info;

// queries executed based on whether completed or submit was clicked.
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    if (isset($_POST['id'])){

        $id = $_POST['id'];
        $sql = "DELETE FROM todos WHERE id = (?)";

        if($stmt = $mysqli->prepare($sql)){
            $stmt->bind_param("s", $id);
            $stmt->execute();
            echo "Records deleted successfully.";
        } else {
            echo "ERROR: Could not prepare query: $sql. " . $mysqli->error;
        }

    } else {
        $new_title = $_POST['title'];

        $sql = "INSERT INTO todos(title) VALUES (?)";
    
        if ($stmt = $mysqli->prepare($sql)) {
            $stmt->bind_param("s", $new_title);
            $stmt->execute();
            echo "Records inserted successfully.";
        } else {
            echo "ERROR: Could not prepare query: $sql. " . $mysqli->error;
        }
    }
}


// First setup the heading and the table headers
?>
<h2>To-do list items</h2>
<table><tbody>
<tr><th>Item</th><th>Added on</th><th>Complete</th></tr>

<?php
// create a sql query string for querying the database to populate the table
$sql = "SELECT id, title, created FROM todos";

// execute the query and check that it is successful
if ($result = $mysqli->query($sql)) {
    // check that there were rows returned
    if ($result->num_rows > 0) {
        /* a loop that goes through each row of the 
        query result and fetches it as an associative array
        i.e. an array with named keys for each column */
        while ($row = $result->fetch_array()) {
            /* uses the data from each row of the query result to 
            create new table rows and populate with data */
            echo "<tr>";
                echo "<td>" . $row['title'] . "</td>";
                echo "<td>" . $row['created'] . "</td>";
                echo '<td><form method="post" action="todo.php">
                <input type="hidden" name="id" value="'.$row['id'].'">
                <button type="submit">Done</button>
                </form></td>';
            echo "</tr>";
        }
        // close off the table
        echo "</table>";
        // Free result set to free up memory associated with the result.
        $result->free();
    } else {
        echo "No records matching your query were found.";
    }

} else {
    echo "ERROR: Could not execute $sql. " . $mysqli->error;
}

?>

</tbody></table>

<form method="post" action="todo.php">
   <input type="text" name="title" placeholder="To-do item">
   <button type="submit">Submit</button>
</form>




