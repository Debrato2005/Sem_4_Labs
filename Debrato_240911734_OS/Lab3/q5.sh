echo "5. Write a program to copy all the files (having file extension input by the user) in the 
current folder to the new folder input by the user. ex: user enter .text TEXT then all
files with .text should be moved to TEXT folder. This should be done only at single
level. i.e if the current folder contains a folder name ABC which has .txt files then
these files should not be copied to TEXT."
echo "enter extension"
read ext
echo "enter new dir"
read new
if [ ! -d "$new" ] 
then
mkdir "$new"
fi

for file in *"$ext"
do
if [ -f "$file" ]; then
cp "$file" "$new"
fi
done
echo "done"
