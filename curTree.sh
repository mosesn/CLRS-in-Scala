#!/bin/bash
tree . -d -L 4 | egrep -v 'project|src|target'