#!/bin/bash
set -e

echo "🧹 Cleaning old results..."

rm -rf target/allure-results
rm -rf target/allure-results/rerun
rm -f target/rerun.txt

echo "🛠 Building project..."
mvn clean install -DskipTests

echo "🧪 Running main suite..."

mvn test \
  -Dtestng.suite=src/test/resources/testng/main-testng.xml \
  -Dallure.results.directory=target/allure-results \
  -DforkCount=1 \
  -DreuseForks=true || true

echo "✅ Main execution finished"