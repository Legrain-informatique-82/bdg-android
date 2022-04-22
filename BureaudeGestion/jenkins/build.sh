#!/bin/bash

#!/bin/bash

source $WORKSPACE/svn_legrain-secrets/fr.legrain.secrets/bdg/applications-mobiles/app-android-dossier/deploiement/param.sh



PROJECT_NAME="BureaudeGestion"
ANDROID_TOOLS=/var/opt/android/tools

ANDROID_HOME=/var/opt/android/
ANDROID_SDK=/var/opt/android/

export ANDROID_HOME
export ANDROID_SDK
export JAVA_HOME=/var/opt/java/jdk1.8.0_111_x64

cd "$WORKSPACE/svn/$PROJECT_NAME"

###
#Copie du keystore dans le répertoire de compilation
cp -rap $WORKSPACE/svn_legrain-secrets/fr.legrain.secrets/bdg/applications-mobiles/app-android-dossier/* .
cp google-services.json app/
###

cp -rap keystore app/

echo "" > local.properties

./gradlew assembleDebug
./gradlew assembleRelease

KEYFILE=keystore/upload_key.jks
#STORE_PASSWORD=lgrxxxx
#KEY_ALIAS=upload
#KEY_PASSWORD=lgrxxxx

./gradlew assembleRelease -Pandroid.injected.signing.store.file=$KEYFILE -Pandroid.injected.signing.store.password=$STORE_PASSWORD -Pandroid.injected.signing.key.alias=$KEY_ALIAS -Pandroid.injected.signing.key.password=$KEY_PASSWORD

ls -lh app/build/outputs/apk/*/*.apk

#./gradlew sonarqube \
#  -Dsonar.projectKey=BDG_Android \
#  -Dsonar.host.url=https://sonar.legrain.dev \
#  -Dsonar.login=55b5a25e812f5581b3a5eadff371cf951325a5d5 \
#  -Dsonar.projectName="BDG Android"