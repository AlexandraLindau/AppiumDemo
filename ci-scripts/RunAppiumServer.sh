#!/bin/bash
set -ex
npm install -g appium@next
appium -v
appium &>/dev/null &