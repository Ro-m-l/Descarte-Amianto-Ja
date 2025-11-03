#!/usr/bin/env bash
# wait-for-it.sh

# Uso:
# ./wait-for-it.sh host:port -- comando [args]

set -e

hostport="$1"
shift

cmd="$@"

host=$(echo $hostport | cut -d: -f1)
port=$(echo $hostport | cut -d: -f2)

echo "Esperando $host:$port..."

while ! nc -z $host $port; do
  sleep 1
done

echo "$host:$port disponível!"
exec $cmd