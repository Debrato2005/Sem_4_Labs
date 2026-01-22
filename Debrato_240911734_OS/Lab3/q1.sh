echo "1. Write a shell script to find whether a given file is the directory or regular file."
echo "enter file or directory name"
read name
if [ -d "$name" ]
then
echo "$name is a directory"
elif [ -f "$name" ]
then
echo "$name is a file"
else
echo "$name  does not exist"
fi

