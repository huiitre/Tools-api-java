#!/bin/bash

if [ -z "$1" ]; then
  echo "Usage: $0 <nom_du_module>"
  exit 1
fi

MODULE="$1"
MODULE_CLASS=$(echo "${MODULE:0:1}" | tr '[:lower:]' '[:upper:]')$(echo "${MODULE:1}")

BASE_DIR="src/main/java/fr/huiitre/tools/tools_core/${MODULE}"
echo "Création de l'arborescence pour le module '${MODULE}' dans ${BASE_DIR}"

mkdir -p "${BASE_DIR}/controller" "${BASE_DIR}/model" "${BASE_DIR}/service" "${BASE_DIR}/repository"

touch "${BASE_DIR}/controller/${MODULE_CLASS}Controller.java"
touch "${BASE_DIR}/service/${MODULE_CLASS}Service.java"
touch "${BASE_DIR}/repository/${MODULE_CLASS}Repository.java"
touch "${BASE_DIR}/model/${MODULE_CLASS}.java"

echo "Structure du module '${MODULE}' créée avec succès."