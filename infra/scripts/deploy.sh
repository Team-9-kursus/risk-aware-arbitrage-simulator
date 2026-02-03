#!/bin/bash
set -e

echo "Deploying risk aware arbitrage simulator..."
docker compose build
docker compose up -d
