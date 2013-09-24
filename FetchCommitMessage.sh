#!/bin/bash

#Description: Fetches the commit message of every commit

while read oldrev newrev refname
do
# logm=`git log -2 --pretty=oneline $oldrev..$newrev`
commits=`git rev-list $oldrev..$newrev`

for commit in $commits
do
message=`git log --format=%s -1 $commit`
echo "$message"
done

# fi
done
exit 2
