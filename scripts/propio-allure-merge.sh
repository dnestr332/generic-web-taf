#!/bin/bash
set -e

echo "📊 Generating Allure report..."

MAIN=target/allure-results
RERUN=target/allure-results/rerun
MERGED=target/allure-results-merged
REPORT=target/allure-report

rm -rf "$MERGED"
rm -rf "$REPORT"

mkdir -p "$MERGED"

if [[ -d "$RERUN" && -n "$(ls -A "$RERUN" 2>/dev/null)" ]]; then
    echo "🔁 Merging rerun results"

    cp -R "$MAIN/." "$MERGED/"
    cp -R "$RERUN/." "$MERGED/"
else
    cp -R "$MAIN/." "$MERGED/"
fi

allure generate "$MERGED" -o "$REPORT"

echo "📦 Report ready: $REPORT"