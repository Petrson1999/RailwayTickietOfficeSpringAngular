<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <title>Sending Email with Freemarker HTML Template Example</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<div class="navbar navbar-dark bg-dark">
    <div class="container-fluid px-0">
        <div class="row w-100">
            <div class="container">
                <a class="navbar-brand" href="#">Railway ticket office<img src="train-32.png" width="30" height="30" class="d-inline-block align-top" alt=""></a>
            </div>
        </div>
    </div>
</div>

<div style="
  background-color: beige;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  background-size: cover;
  -o-background-size: cover;">
    <div class="py-5">
        <h5 class="card-title text-center">Dear ${name} Thanks for using "Railway ticket office"</h5>
        <h5 class="card-title text-center">In the attachment of this letter is a copy of your documents.</h5>
    </div>
</div>

</body>
</html>
