# quikbite
This is a food ordering app

## Database via Docker

Use the provided `docker-compose.yml` to spin up MySQL and PostgreSQL locally.

### Start databases

```bash
docker compose up -d
```

### Spring profiles

- MySQL: run the backend with the `mysql` profile
- PostgreSQL: run the backend with the `postgres` profile

Examples:

```bash
# From backend/
./mvnw spring-boot:run -Dspring-boot.run.profiles=mysql

./mvnw spring-boot:run -Dspring-boot.run.profiles=postgres
```

### Connection settings

- MySQL: host `localhost:3306`, db `quikbitedb`, user `quikbite`, password `quikbite`
- Postgres: host `localhost:5432`, db `quikbitedb`, user `quikbite`, password `quikbite`

## MCP Servers (Model Context Protocol)

Basic MCP server configuration is provided in `.mcp/servers.json` for MySQL and PostgreSQL.
You may need to install the MCP servers (e.g., via `uvx mcp-server-postgres` and `uvx mcp-server-mysql`) or adjust the commands per your MCP client.

Environment is pre-set to match the Docker Compose databases:

- Postgres: `PGHOST=127.0.0.1`, `PGPORT=5432`, `PGUSER=quikbite`, `PGPASSWORD=quikbite`, `PGDATABASE=quikbitedb`
- MySQL: `MYSQL_HOST=127.0.0.1`, `MYSQL_PORT=3306`, `MYSQL_USER=quikbite`, `MYSQL_PASSWORD=quikbite`, `MYSQL_DATABASE=quikbitedb`

