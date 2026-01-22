echo "Write a shell script to calculate the gross salary. GS=Basics + TA + 10% of Basics.
Floating point calculations has to be performed."
echo " enter Basics and TA"
read bas ta
gs=$(echo "scale=2; $bas+($ta*10/100)"| bc)
echo "Gross Salary = $gs"
