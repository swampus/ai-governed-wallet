This module implements a minimal wallet service using AI-governed clean architecture.

Rules for AI-assisted modifications:
- Do not introduce dependencies from domain to application or api.
- Do not bypass WalletService for balance changes.
- A wallet balance must never become negative.
- Deposit amount must be positive.
- Transfer logic must remain value-conserving.
- API DTOs must not leak into the domain layer.

Typical safe changes:
- add transaction history
- add idempotency support
- add validation or monitoring
- add new read-only endpoints

Unsafe changes:
- importing Spring classes into the domain layer
- mutating wallet balance directly from controllers
- removing invariant checks
