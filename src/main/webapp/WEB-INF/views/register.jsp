<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Details Form</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        /* Apply a box-sizing border-box to all elements */
        * {
            box-sizing: border-box;
        }

        /* Style the form container */
        form {
            background-color: #f2f2f2;
            padding: 10px;
            border-radius: 10px;
            width: 600px;
            margin: 0 auto;
        }

        /* Style the form headings */
        h1 {
            text-align: center;
            font-size: 24px;
            margin-top: 0;
        }

        h2 {
            text-align: center;
            font-size: 50px;
            margin-top: 10;
        }

        /* Style the form labels */
        label {
            display: block;
            margin-bottom: 8px;
        }

        /* Style the form input fields */
        input[type="text"],
        input[type="email"],
        textarea {
            width: 100%;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            resize: vertical;
        }

        /* Style the form submit button */
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
            margin-top: 16px;
        }

        /* Style the form submit button on hover */
        input[type="submit"]:hover {
            background-color: #45a049;
        }

        .error-input {
            border: 2px solid red;
        }

        .error-message {
            color: red;
            font-size: 14px;
            margin-top: 5px;
        }
    </style>
        <script>
            function validateForm() {
                // Get the input field values
                var name = document.getElementById('name').value.trim();
                var phoneNumber = document.getElementById('phoneNumber').value.trim();
                var email = document.getElementById('email').value.trim();

                // Initialize an empty message variable
                var validationMessages = "";

                // Perform validation for each field
                if (name === "") {
                    validationMessages += "Please enter your name.<br>";
                }

                if (phoneNumber === "" || phoneNumber.length !== 10 || isNaN(phoneNumber)) {
                    validationMessages += "Please enter a valid 10-digit phone number.<br>";
                    document.getElementById('phoneNumber').classList.add('error-input');
                } else {
                    document.getElementById('phoneNumber').classList.remove('error-input');
                }

                if (email === "" || !validateEmail(email)) {
                    validationMessages += "Please enter a valid email address.<br>";
                    document.getElementById('email').classList.add('error-input');
                } else {
                    document.getElementById('email').classList.remove('error-input');
                }

                // Get the div element to display validation messages
                var validationDiv = document.getElementById('validation-messages');

                // If there are validation messages, display them and prevent form submission
                if (validationMessages !== "") {
                    validationDiv.innerHTML = validationMessages;
                    return false;
                }

                // If all validations pass, clear any existing messages and allow form submission
                validationDiv.innerHTML = "";
                return true;
            }

            function validateEmail(email) {
                // Simple email validation using a regular expression
                var re = /\S+@\S+\.\S+/;
                return re.test(email);
            }
        </script>
</head>
<body>
    <div>
        <h2>Register For Movie Booking</h2>
    </div>
    <section class="h-100 gradient-form" style="background-color: #eee;">
        <div class="container py-5 h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-xl-10">
                    <div class="card rounded-3 text-black">
                        <div class="row g-0">
                            <div class="col-lg-6">
                                <div class="card-body p-md-5 mx-md-4">
                                    <form method="post" action="loginInsert" modelAttribute="customer" onsubmit="return validateForm()">
                                        <div class="input-group mb-3">
                                            <span class="input-group-text" id="inputGroup-sizing-default">Name</span>
                                            <input type="text" name="name" id="name" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" maxlength="20" />
                                        </div>
                                        <p>Gender</p>
                                        <input type="radio" name="gender" value="Male">Male
                                        <input type="radio" name="gender" value="Female">Female
                                        <br>
                                        <div class="input-group mb-3">
                                            <span class="input-group-text" id="inputGroup-sizing-default">Present Address</span>
                                            <input type="text" name="presentAddress" id="presentAddress" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" required="required" />
                                        </div>
                                        <div class="input-group mb-3">
                                            <span class="input-group-text" id="inputGroup-sizing-default">Mobile</span>
                                            <input type="number" name="phoneNumber" id="phoneNumber" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" maxlength="10" />
                                            <div class="error-message" id="error-phone"></div>
                                        </div>
                                        <div class="input-group mb-3">
                                            <span class="input-group-text" id="inputGroup-sizing-default">Email</span>
                                            <input type="email" name="email" id="email" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" />
                                            <div class="error-message" id="error-email"></div>
                                        </div>
                                        <div class="form-outline mb-4">
                                            <label class="form-label" for="password">Password</label>
                                            <input type="password" name="password" id="password" class="form-control" placeholder="Password" required="required" />
                                        </div>
                                        <div id="validation-messages"></div>
                                        <input type="submit" value="Submit">
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>


</body>
</html>
