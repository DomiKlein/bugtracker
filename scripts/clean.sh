#!/bin/bash
cd ..

# Stop and delete all containers
echo ">> Stopping and removing all docker containers"
docker-compose down

# Remove old database files
echo
echo ">> Deleting database files"
rm -rf database/data
