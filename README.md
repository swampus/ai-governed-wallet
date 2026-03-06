# AI-Governed Wallet

more on Medium: https://medium.com/@dmitrijs.gavrilovs.swampus/ai-is-writing-your-code-now-it-must-govern-your-architecture-83a534a9f911

This repository is a small engineering experiment that combines **Clean Architecture**
with an **AI-governed architecture layer**.

The idea is simple:

- **Clean Architecture** keeps the dependency flow explicit:
  `api -> application -> domain`
- **AI-governed architecture** makes the system easier for AI tools to modify safely through:
  - machine-readable architecture manifests
  - explicit module contracts
  - invariant definitions
  - an architecture validator

## Why this exists

Most software architectures were designed for humans.

But modern codebases are increasingly modified with AI tools. That changes the problem.
A system should not only be readable by engineers — it should also be understandable,
navigable, and constrainable for AI-assisted modification.

## Project structure

- `architecture/` — machine-readable architecture manifest
- `constraints/` — global system invariants
- `modules/` — AI-readable module contracts and guidance
- `wallet-domain/` — domain entities and domain rules
- `wallet-application/` — use cases and ports
- `wallet-api/` — Spring Boot API layer
- `architecture-validator/` — validator for dependency graph and architecture files

## AI-governed principles

1. **Machine-readable architecture**
   - `architecture/system.yaml` defines allowed module dependencies.

2. **Explicit module contracts**
   - `modules/wallet/contract.yaml` defines the module responsibility.

3. **Invariant-aware evolution**
   - `constraints/invariants.yaml` describes system rules.
   - code validates critical invariants at runtime.

4. **AI-readable intent**
   - `modules/wallet/AI_README.md` explains constraints in plain text for AI tools.

## Dependency rule

Allowed dependency flow:

```text
wallet-api -> wallet-application -> wallet-domain
```

The reverse direction is forbidden.

## Run the API

```bash
mvn clean test
mvn -pl wallet-api spring-boot:run
```

## Example requests

Deposit:

```bash
curl -X POST "http://localhost:8080/wallet/deposit?userId=alice&amount=100"
```

Transfer:

```bash
curl -X POST "http://localhost:8080/wallet/transfer?from=alice&to=bob&amount=30"
```

Balance:

```bash
curl "http://localhost:8080/wallet/alice"
```

## Positioning

This project is intentionally framed as an **experiment**, not as a claim of inventing
an entirely new universal architecture. The value is in making the idea concrete:
**architecture that governs safe AI-assisted code evolution**.
