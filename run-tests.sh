#!/bin/bash
set -e

SECONDS=0
SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"

bash "$SCRIPT_DIR/scripts/propio-run-main.sh"
bash "$SCRIPT_DIR/scripts/propio-run-failed.sh"
bash "$SCRIPT_DIR/scripts/propio-allure-merge.sh"

if [[ "$CI" != "true" ]]; then
    echo "📂 Opening report..."
    allure open target/allure-report &
fi

duration=$SECONDS

echo "⏱️ Total execution time: ${duration}s ($(($duration / 60)) min $(($duration % 60)) sec)"
echo "🎉 PROPIO RUN FINISHED"