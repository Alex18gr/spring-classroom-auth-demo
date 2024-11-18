# Spring Classroom with Authentication

This demo showcases some Spring Framework features for API development. It also enables authentication of users
with roles

Technologies and features used an implemented in this example application are the following:

- Spring JPA
- JWT Authentication
- RFC-9457 Exception Handling

## JWT Implementation

The authentication in this application is a custom JWT token implementation. The users and their roles
are saved in the database. Tokens are generated after a successful username and password
authentication.