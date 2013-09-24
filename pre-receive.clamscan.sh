#!/bin/bash

#Description: Hook script which clam scans each file of every commit
# git diff-tree -z --no-commit-id --name-only -r $commit | xargs -0 git archive -o /temp/list.zip HEAD:

tempdir=$(mktemp -d /temp/XXXX)
#echo "$tempdir"
scandir="$tempdir/scan"

while read oldrev newrev refname
do

commits=`git rev-list $oldrev..$newrev`
for commit in $commits
do
        git diff-tree -z --no-commit-id --name-only -r $commit | xargs -0 git archive -o $tempdir/list.zip HEAD: &>/dev/null
        unzip -o $tempdir/list.zip -d $scandir &>/dev/null
        rm -rf $tempdir/list.zip &>/dev/null
done

clamdscan --fdpass $scandir -i -l $tempdir/log.out
         rc=$?
         if [[ $rc != 0 ]] ; then
             echo "Virus scan detected threats - Push denied..."
             rm -rf $tempdir
             exit $rc
         fi

rm -rf $tempdir

done
exit 0
