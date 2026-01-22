echo "Write a shell script to modify all occurrences of \"ex:\" with \"Example:\"
in all the files present in the current folder only if \"ex:\" occurs
at the start of the line or after a period (.)."

for file in *
do
    if [ -f "$file" ]; then
        sed -i 's/^ex:/Example:/g; s/\.Example:/\.Example:/g' "$file"
    fi
done
echo "modified"
