#!/bin/bash

BUILD_DATE=`date`
TEXT="Pipeline Build on $BUILD_DATE https://euda-builds.sfo2.cdn.digitaloceanspaces.com/lad/android-client/$BITBUCKET_BRANCH/"${@#"app/build/outputs/apk/"}
curl -v -F channel=builds-notifications -F text="$TEXT" https://slack.com/api/chat.postMessage -H "Authorization: Bearer xoxb-559351942663-2681298472918-pS4EtOViXbPRYXBZRdJH8kvc"
