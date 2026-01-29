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

## GitHub Actions Workflows

### Auto-Approve Pull Requests

The repository includes a GitHub Actions workflow (`.github/workflows/auto-approve-prs.yml`) that automatically checks and approves pending pull requests based on specific criteria.

**Auto-approval criteria:**
- Pull requests from `dependabot[bot]`
- Pull requests from `github-actions[bot]`
- Pull requests from copilot agents
- Pull requests with the `auto-approve` label

**Workflow triggers:**
- When a pull request is opened, reopened, or updated
- Every 6 hours via scheduled cron job
- Manual trigger via workflow dispatch

The workflow will automatically approve eligible PRs that haven't been approved yet, helping to streamline the review process for automated and trusted contributions.

