echo "6. Write a shell script which deletes all the even numbered lines in a text.
"
echo "enter file name:"
read file

if [ -f "$file" ]; then
    sed -i '2~2d' "$file"
    echo "Even numbered lines deleted"
else
    echo "File does not exist"
fi

