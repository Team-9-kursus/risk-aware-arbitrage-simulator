# VS Code Remote SSH - VPS Access

> This guide is **optional** and intended for team members who want
> to open the project directly on the VPS using VS Code.
> Production deployment is handled via CI/CD.

---

## Prerequisites

- VS Code installed
- SSH key pair on local machine
- Access granted by project maintainer

---

## 1. Install VS Code Extension

Install **Remote - SSH** (Microsoft).

---

## 2. Add SSH Key

If you already have an SSH key, skip this step.

```bash
ssh-keygen -t ed25519 -C "team9-vscode"
````

Send the **public key** (`.pub`) to the project maintainer.

---

## 3. Configure SSH (Local Machine)

Edit SSH config:

```text
~/.ssh/config
```

Add:

```ssh
Host team9-vps
  HostName <SERVER_IP_OR_DOMAIN>
  User ubuntu
  Port 22
  IdentityFile ~/.ssh/id_ed25519
```

---

## 4. Connect with VS Code

* `Ctrl + Shift + P`
* `Remote-SSH: Connect to Host`
* Select `team9-vps`

---

## 5. Open Project Folder

Once connected:

```
/var/www/risk-aware-arbitrage-simulator
```

The project will appear in VS Code Explorer.

---

## Notes

* Do **not** push directly to `main`
* Production deploys are handled by CI/CD
* Use feature branches for all changes

---

## Need Help?

Contact the project maintainer.