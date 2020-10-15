# Bug found in TSec Http4s?

## Steps to reproduce

Start the server with:

```shell script
source .env
sbt run
``` 

Then:

```shell script
curl -X GET -v -H "Authorization: Bearer eyJhbGciOiJIUzM4NCIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE2MDI3NTI0NjIsImV4cCI6MjQ2Njc1MjQ2MiwibmJmIjoxNjAyNzUyNDYyLCJpc3MiOiJodHRwczovL2F1dGguYmx1ZXZhbGV0LmZyIiwic3ViIjoic2VydmljZTpibHVldmFsZXQ6c2VydmVyIiwiYXVkIjoiaHR0cHM6Ly9iaWxsaW5nLmJsdWV2YWxldC5mciIsImp0aSI6ImtleV9mb3JfYmx1ZXZhbGV0X3NlcnZlciIsInNjb3BlIjpbImJpbGxpbmc6aW52b2ljZTpyZWFkIiwiYmlsbGluZzppbnZvaWNlOmNyZWF0ZSIsImJpbGxpbmc6aW52b2ljZTpkZWxldGUiLCJiaWxsaW5nOmludm9pY2U6bGlzdCJdfQ.q6SRKebktys1UD54blkmholJSvdnikox5TddpSWh56mNKzsyk21kTFOE1zRL0UKo"  http://localhost:9000/v1/specimen-secured
```

The result is `401 Unauthorized`, and should be `204 No Content`.

# Problem analysis

The problem is the jwt subject parsing: the JSON parser requires a double quote to be present to parse the subject string as a string (and fails, as the subject does not start with a double quote, obviously).

I had to debug in IntelliJ to figure out the problem, the stack trace is captured as a PNG in `doc/tsec_debug.png`.
