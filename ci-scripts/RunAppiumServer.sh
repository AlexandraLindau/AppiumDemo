#!/bin/bash
set -ex
npm install -g appium@next
appium -v
appium --address 0.0.0.0 --port 4723 --relaxed-security --base-path /wd/hub &