#!/bin/bash
aws --version
aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name sample-queue --profile default --region us-east-1 --output table | cat

aws --endpoint-url=http://localhost:4566 sqs list-queues --profile default --region us-east-1 --output table
