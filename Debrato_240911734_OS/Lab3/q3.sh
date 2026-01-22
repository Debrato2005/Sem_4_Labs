echo "Write a shell script to replace all files with .txt extension with .text in the current di-
rectory. This has to be done recursively i.e if the current folder contains a folder
“OS” with abc.txt then it has to be changed to abc.text ( Hint: use find, mv )"
echo "enter file or directory name"
read name
ls "$name"
if [ -d "$name" ]
then
find "$name" -name "*.txt" | while read file
do
mv "$file" "${file%.txt}.text"
done
ls "$name"
else
echo "directory does not exist "
fi
