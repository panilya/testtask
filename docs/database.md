# PostgreSQL Setup

`docker run -p 5432:5432 -d --name uni-bot -e POSTGRES_USER=uni-bot -e POSTGRES_PASSWORD=uni-bot -e POSTGRES_DB=uni-bot postgres`

# Possible troubles running the application

1) FATAL: invalid value for parameter "TimeZone": "Europe/Kyiv"
If you're getting this error while running the application, try to add the following VM options:
`-Duser.timezone="UTC"`
