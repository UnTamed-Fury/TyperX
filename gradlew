#!/bin/sh

#
# Copyright © 2015-present The GradleFx Project.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

##############################################################################
#
# Wrapper script for Gradle
#
# This script is used to execute Gradle on Unix systems.
# It takes care of setting up the environment and then
# delegates to the actual Gradle startup script.
#
##############################################################################

# Attempt to set APP_HOME

# Resolve links: $0 may be a link
app_path=$0

# Need this for daisy-chained symlinks.
while
    APP_HOME=${app_path%"${app_path##*/}"}    # leaves a trailing /; empty if no leading path
    [ -h "$app_path" ]
do
    ls=$( ls -ld "$app_path" )
    link=${ls#*' -> '}
    case $link in             #(
      /*)   app_path=$link ;; #(
      *)    app_path=$APP_HOME$link ;;
    esac
done

# Change from the script directory to the app root directory
APP_HOME=$( cd "${APP_HOME:-./}" && pwd -P ) || exit

# Set the script directory as the location of the wrapper jar
WRAPPER_PARENT_DIR=$( dirname "$APP_HOME" )
WRAPPER_JAR="$WRAPPER_PARENT_DIR/gradle/wrapper/gradle-wrapper.jar"
if [ ! -f "$WRAPPER_JAR" ]; then
    WRAPPER_JAR="$APP_HOME/gradle/wrapper/gradle-wrapper.jar"
fi

# Define the main class
MAIN_CLASS=org.gradle.wrapper.GradleWrapperMain

# Use the maximum available, or set MAX_FD != -1 to use that value.
MAX_FD="maximum"

warn () {
    echo "$*"
} >&2

die () {
    echo
    echo "$*"
    echo
    exit 1
} >&2

# OS specific support (must be 'true' or 'false').
cygwin=false
msys=false
darwin=false
case "$(uname)" in                #(
  CYGWIN* )     cygwin=true  ;; #(
  Darwin* )     darwin=true  ;; #(
  MSYS* )       msys=true    ;; #(
esac

# For Cygwin, ensure paths are in UNIX format before anything is touched.
if $cygwin ; then
    [ -n "$JAVA_HOME" ] &&
        JAVA_HOME=$( cygpath --unix "$JAVA_HOME" )
    [ -n "$CLASSPATH" ] &&
        CLASSPATH=$( cygpath --path --unix "$CLASSPATH" )
    [ -n "$APP_BASE_NAME" ] &&
        APP_BASE_NAME=$( cygpath --unix "$APP_BASE_NAME" )
    [ -n "$APP_HOME" ] &&
        APP_HOME=$( cygpath --unix "$APP_HOME" )
fi

# For Msys, ensure paths are in UNIX format before anything is touched.
if $msys ; then
    [ -n "$JAVA_HOME" ] &&
        JAVA_HOME=$( winpty cygpath --unix "$JAVA_HOME" )
    [ -n "$CLASSPATH" ] &&
        CLASSPATH=$( winpty cygpath --path --unix "$CLASSPATH" )
    [ -n "$APP_BASE_NAME" ] &&
        APP_BASE_NAME=$( winpty cygpath --unix "$APP_BASE_NAME" )
    [ -n "$APP_HOME" ] &&
        APP_HOME=$( winpty cygpath --unix "$APP_HOME" )
fi

# Attempt to find the Java command
if [ -n "$JAVA_HOME" ] ; then
    if [ -x "$JAVA_HOME/jre/sh/java" ] ; then
        # IBM's JDK on AIX uses strange locations for the executables
        JAVACMD="$JAVA_HOME/jre/sh/java"
    else
        JAVACMD="$JAVA_HOME/bin/java"
    fi
    if [ ! -x "$JAVACMD" ] ; then
        die "ERROR: JAVA_HOME is set to an invalid directory: $JAVA_HOME

Please set the JAVA_HOME variable in your environment to match the
location of your Java installation."
    fi
else
    JAVACMD="java"
    which java > /dev/null 2>&1 || die "ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.

Please set the JAVA_HOME variable in your environment to match the
location of your Java installation."
fi

# Increase the maximum file descriptors if we can.
if $cygwin || $darwin || $msys ; then
    MAX_FD_LIMIT=$( ulimit -H -n )
    if [ $? -eq 0 ] ; then
        if [ "$MAX_FD_LIMIT" = "unlimited" ] || [ "$MAX_FD_LIMIT" -gt "$MAX_FD" ] ; then
            [ "$MAX_FD" = "maximum" ] && MAX_FD="$MAX_FD_LIMIT"
            ulimit -n "$MAX_FD"
            if [ $? -ne 0 ] ; then
                warn "Could not set maximum file descriptor limit: $MAX_FD"
            fi
        else
            warn "Could not set maximum file descriptor limit: $MAX_FD"
        fi
    else
        warn "Could not query maximum file descriptor limit: $MAX_FD_LIMIT"
    fi
fi

# Collect all arguments for the java command:
#   * Add the main class
#   * Add all the jar files from the lib dir
#   * Add the -classpath argument
#   * Add all the remaining arguments
eval set -- "\"$JAVACMD\"" -classpath "\"$WRAPPER_JAR\"" -Dorg.gradle.appname=\"$APP_BASE_NAME\" "\"$MAIN_CLASS\"" "$@"

# Execute the command
exec "$@"