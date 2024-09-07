# Rewards Management API

## Project Overview

The Rewards Management API is a backend service designed to handle customer rewards and cashback transactions for Balanceè. Built using Java Spring Boot, this API provides endpoints for retrieving current cashback balances, award customers cashbacks and viewing transaction histories. Additionally, it includes authentication functionality for secure access.

## Table of Contents

- [Authentication](#authentication)
- [Features](#features)
- [Endpoints](#endpoints)
    - Register
    - Login
    - Get Rewards Balance (protected)
    - Get Cashback History (protected)
    - Award Cashback
- [Models](#models)
- [Setup Instructions](#setup-instructions)
- [Running the Application](#running-the-application)
- [Contributing](#contributing)

## Authentication

Authentication is implemented using JWT (JSON Web Token). The following security setup is in place:

- **Login Endpoint:** `/api/auth/login` - Authenticates the user and returns a JWT token.
- **Registration Endpoint:** `/api/auth/register` - Registers a new user, also returning a JWT token

Include the JWT token in the `Authorization` header of requests to secure endpoints. Example:
```http
Authorization: Bearer <token>
```

## Features

- **Retrieve Current Cashback Balance:** Get the total and available cashback balance for a specific customer.
- **View Cashback Transaction History:** Retrieve a list of all cashback transactions associated with a customer.
- **Award Cashback:** Add new cashback transactions to a customer’s account.

## Endpoints

### 1. Register

- **Endpoint:** `/api/auth/register`
- **Request Type:** POST
- **Description:** Register a new user.
- **Request Body:**
  ```json
  {
    "name": "Victor Adepoju",
    "email": "victoradepoju@gmail.com",
    "password": "password"
  }
  ```
- **Response:**
  ```json
  {
    "token": "string",
    "customerId": "string",
    "name": "Victor Adepoju",
    "email": "victoradepoju@gmail.com",
    "message": "Registration successful"
  }
  ```
- **Example Response:**
  ```json
  {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkFkbWluIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c",
    "customerId": "b95fcfb6-8aa2-400a-a585-027d944fe5a7",
    "name": "Victor Adepoju",
    "email": "victoradepoju@gmail.com",
    "message": "Registration successful"
  }
  ```

### 2. Login

- **Endpoint:** `/api/auth/login`
- **Request Type:** POST
- **Description:** Authenticate a user and return a JWT token.
- **Request Body:**
  ```json
  {
    "email": "victoradepoju@gmail.com",
    "password": "password"
  }
  ```
- **Response:**
  ```json
  {
    "token": "string",
    "customerId": "string",
    "name": "Victor Adepoju",
    "email": "victoradepoju@gmail.com",
    "message": "Login successful"
  }
  ```
- **Example Response:**
  ```json
  {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkFkbWluIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c",
    "customerId": "b95fcfb6-8aa2-400a-a585-027d944fe5a7",
    "name": "Victor Adepoju",
    "email": "victoradepoju@gmail.com",
    "message": "Login successful"
  }
  ```

### 3. Get Rewards Balance

- **Endpoint:** `/api/rewards/balance/{customerId}`
- **Request Type:** GET
- **Description:** Retrieve the total cashback and current balance for a specific customer.
- **Request Parameters:**
    - `customerId` (Path Variable): Unique identifier for the customer.
- **Response:**
  ```json
  {
    "customerId": "string",
    "totalCashback": "number",
    "currentBalance": "number"
  }
  ```
- **Example Response:**
  ```json
  {
    "customerId": "12345",
    "totalCashback": 150.00,
    "currentBalance": 75.00
  }
  ```

### 4. Get Cashback History

- **Endpoint:** `/api/rewards/history/{customerId}`
- **Request Type:** GET
- **Description:** Retrieve a list of cashback transactions for a specific customer.
- **Request Parameters:**
    - `customerId` (Path Variable): Unique identifier for the customer.
- **Response:**
  ```json
  [
    {
      "transactionId": "string",
      "transactionDate": "YYYY-MM-DD",
      "amountEarned": "number",
      "description": "string"
    }
  ]
  ```
- **Example Response:**
  ```json
  [
    {
      "transactionId": "txn123",
      "transactionDate": "2024-09-01",
      "amountEarned": 25.00,
      "description": "Purchase at Store X"
    },
    {
      "transactionId": "txn124",
      "transactionDate": "2024-09-05",
      "amountEarned": 50.00,
      "description": "Online Booking"
    }
  ]
  ```

### 5. Award Cashback (note that this route is unprotected)

- **Endpoint:** `/api/rewards/award-cashback/{customerId}`
- **Request Type:** POST
- **Description:** Award cashback to a customer. This endpoint is publicly accessible and does not require authentication.
- **Request Parameters:**
    - `customerId` (Path Variable): Unique identifier for the customer.
- **Request Body:**
  ```json
  {
    "amount": "number",
    "description": "string"
  }
  ```
- **Response:**
  ```json
  "Cashback awarded successfully"
  ```
- **Example Request Body:**
  ```json
  {
    "amount": 20.00,
    "description": "Referral Bonus"
  }
  ```
- **Example Response:**
  ```json
  "Victor Adepoju has been awarded 20.00 cashbacks!"
  ```

## Models

### `Customer`

- **ID:** Unique identifier (UUID)
- **Email:** Customer's email address
- **Name:** Customer's name
- **Password:** Encrypted password
- **CustomerRewards:** Associated rewards data

### `CustomerRewards`

- **RewardsId:** Unique identifier (UUID)
- **TotalCashback:** Total cashback earned
- **CurrentBalance:** Available balance for cashout
- **Customer:** Associated customer
- **CashbackTransactions:** List of associated cashback transactions

### `CashbackTransaction`

- **TransactionId:** Unique identifier (UUID)
- **TransactionDate:** Date of transaction
- **AmountEarned:** Amount of cashback earned
- **Description:** Description of the transaction
- **CustomerRewards:** Associated rewards data

## Setup Instructions

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/victoradepoju/reward-management.git
   cd reward-management
   ```

2. **Install Dependencies:**
   Ensure you have Java 17+ and Maven installed. Run:
   ```bash
   mvn clean install
   ```

3. **Configure Application:**
   The `application.yml` file under `src/main/resources` has the database and JWT configurations. Feel free to edit to preference.

## Running the Application

 **Using Docker:**

   Ensure you have Docker and Docker Compose installed. Start the container with:
   ```bash
   docker-compose up -d
   ```
   This will build and start the application postgres db in a Docker container as defined in the `docker-compose.yml` file.


   Start the application with:
   ```bash
   mvn spring-boot:run
   ```

   Access the API at `http://localhost:8080`.

## Contributing

We welcome contributions to the project. Please follow these steps:

1. Fork the repository.
2. Create a feature branch (`git checkout -b feature/YourFeature`).
3. Commit your changes (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature/YourFeature`).
5. Create a new Pull Request.
