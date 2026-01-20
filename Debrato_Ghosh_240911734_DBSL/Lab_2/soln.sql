create table Employee (
EmpNo int primary key, 
EmpName varchar(5) not null, 
Gender char(1) check(Gender in ('M' , 'F')) not null , 
Salary int not null, 
Address varchar(5) not null, 
DNo int
);

create table Department (
DeptNo int primary key, 
DeptName varchar(5) unique not null, 
Location varchar(5));

alter table Employee
add constraint fk_empt_dept
foreign key(Dno)
references Department(DeptNo);

insert into Department(DeptNo, DeptName, Location) values('1','cse','ab5');
insert into Department(DeptNo, DeptName, Location) values('2','chem','ab2');
insert into Department(DeptNo, DeptName, Location) values('3','ece','ab5');

insert into Employee (EmpNo, EmpName, Gender, Salary, Address, DNo) values (1,'deb','M',100,'mum',1);
insert into Employee (EmpNo, EmpName, Gender, Salary, Address, DNo) values (2,'rai','G',101,'del',2);
insert into Employee (EmpNo, EmpName, Gender, Salary, Address, DNo) values (1,'ary','M',103,'mum',1); 

delete from Department where DeptNo='1';

alter table Employee drop constraint fk_emp_dept;

alter table Employee
add constraint fk_emp_dept
foreign key (DNo)
references Department(DeptNo)
on delete set null;

alter table Employee add constraint sal_cons check(salary>=10000); -- cannot add default with add constrain therefore using with check
UPDATE Employee
SET Salary = 10000;

drop table Employee;

\i 'C:/Users/Debrato/Desktop/Coding/Sem_4_Labs/Debrato_Ghosh_240911734_DBSL/Lab_2/university.sql';
\i 'C:/Users/Debrato/Desktop/Coding/Sem_4_Labs/Debrato_Ghosh_240911734_DBSL/Lab_2/smallRelations.sql';

select name, dept_name from student;

select name from instructor where dept_name='Comp. Sci.';

select title from Course where dept_name='Comp. Sci.' and credits=3; 

select s.name, c.course_id, c.title
from takes t , course c , student s
where t.course_id = c.course_id and t.ID = s.ID and s.ID='12345';

select i.name, d.dept_name
from instructor i, department d
where i.dept_name=d.dept_name
and i.salary between 40000 and 90000;

select i.name
from instructor i
where i.id not in (select t.ID from teaches t);

SELECT s.name, c.title, sec.year
FROM student s, takes t, section sec, course c
WHERE s.ID = t.ID
  AND t.course_id = sec.course_id
  AND t.sec_id = sec.sec_id
  AND t.semester = sec.semester
  AND t.year = sec.year
  AND sec.course_id = c.course_id
  AND sec.room_number = '303';

  SELECT s.name,
       c.course_id,
       c.title AS c_name
FROM student s, takes t, course c
WHERE s.ID = t.ID
  AND t.course_id = c.course_id
  AND t.year = 2015;

SELECT name,
       salary AS inst_salary
FROM instructor
WHERE salary >
      (SELECT MIN(salary)
       FROM instructor
       WHERE dept_name = 'Comp. Sci.');

SELECT name
FROM instructor
WHERE dept_name LIKE '%ch%';

SELECT name,
       LENGTH(name) AS name_length
FROM student;

SELECT dept_name,
       SUBSTRING(dept_name FROM 3 FOR 3) AS part
FROM department;

SELECT UPPER(name)
FROM instructor;

SELECT name,
       COALESCE(salary, 0) AS salary
FROM instructor;

SELECT salary,
       ROUND(salary / 3, -2) AS rounded_salary
FROM instructor;



