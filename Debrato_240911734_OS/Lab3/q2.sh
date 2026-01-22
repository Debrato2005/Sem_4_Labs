echo "2. Write a shell script to list all files (only file names) containing the input pattern
(string) in the folder entered by the user."
echo "enter file or directory name"
read name
echo "enter pattern(string)"
read str
if [ -d "$name" ]
then
ls "$name" | grep "$str"
else
echo "directory doesnt exist"
fi
