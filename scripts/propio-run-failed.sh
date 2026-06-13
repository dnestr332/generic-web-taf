#!/bin/bash
set -e

if [[ -s target/rerun.txt ]]; then

  echo "♻️ Rerunning failed scenarios..."

  mvn test \
    -Dtestng.suite=src/test/resources/testng/failed-testng.xml \
    -Dallure.results.directory=target/allure-results/rerun \
    -DforkCount=1 \
    -DreuseForks=true || true

else
  echo "✅ No failed scenarios"
fi