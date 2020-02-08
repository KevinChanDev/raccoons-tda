# raccoons-tda project
The raccoons-tda project is a collection of the following libraries and services
- raccoons-tda-api (The core Java implementation for the TDA API as outlined by the developer page (see below))
- raccoons-tda-auth (A custom implementation of an access token management service)
- raccoons-tda-auth-client (A custom implementation of an access token client. This is used inconjunction with raccoons-tda-auth)
- raccoons-tda-auth-lib (A library leveraged by raccoons-tda-auth & raccoons-tda-auth-client)

More information for the TDA developer API can be found [here](https://developer.tdameritrade.com/)


The project is written with concurrency in mind to allow non-blocking REST API calls


## Dependencies
- N/A
