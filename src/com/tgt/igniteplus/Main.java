/*
1) User Should be able to Create, Delete and Update the Department.
2) Each and every department should have at least 1 Ignite Member
3) Department can have more than 1 Ignite Member
Features:
1) Swapping of Ignite Members from one dept to another dept
2) Add a new Skillset to all the Ignite members for the specific dept
3) List all the users who have the Skill of “Java” with department name
4) Make Sure unique Ignite Members are added into the department (Assuming the
unique attribute is Name)
5) Validations at all operation is mandatory
 */
package com.tgt.igniteplus;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    static List<IgniteMembers> memberObj = new CopyOnWriteArrayList<>();
    static List<String> Department = new ArrayList<>();
    static Set<String> SkillSet = new HashSet<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int choice;

        Set<String> gauthamSkillSet = new HashSet<>();
        gauthamSkillSet.add("Java");
        gauthamSkillSet.add("SQL");
        gauthamSkillSet.add("DS");

        Set<String> divyaSkillSet = new HashSet<>();
        divyaSkillSet.add("Java");
        divyaSkillSet.add("NOSQL");
        divyaSkillSet.add("ML");

        Set<String> amitSkillSet = new HashSet<>();
        amitSkillSet.add("Linux");
        amitSkillSet.add("PSQL");
        amitSkillSet.add("Scripting");

        Set<String> naveenSkillSet = new HashSet<>();
        naveenSkillSet.add("Chef");
        naveenSkillSet.add("React");
        naveenSkillSet.add("AI");

        Department.add("Data Science");
        Department.add("Infrastructure");

        memberObj.add(new IgniteMembers("Gautham", "VTU", Department.get(0), gauthamSkillSet, 28));
        memberObj.add(new IgniteMembers("Divya", "TGT", Department.get(0), divyaSkillSet, 26));
        memberObj.add(new IgniteMembers("Amit", "TMT", Department.get(1), amitSkillSet, 25));
        memberObj.add(new IgniteMembers("Naveen", "DOJO", Department.get(1), naveenSkillSet, 22));

        do {
            System.out.println("\n-------------------------------------------------\n" +
                    "Option Menu:\n" +
                    "1. Display list of Departments\n" +
                    "2. Create a new Department\n" +
                    "3. Delete a Department\n" +
                    "4. Display all Members based on departments\n" +
                    "5. Create a Member and add to a department\n" +
                    "6. Display members based on given skill\n" +
                    "7. Swap department of a member\n" +
                    "8. Add new skill set to all members of a department\n" +
                    "-------------------------------------------------");
            System.out.print("Enter your option:\t");
            int option = in.nextInt();

            switch (option) {
                case 1:
                    displayDept();
                    break;

                case 2:
                    String department = createDept();
                    System.out.println("Now enter a member in this department:");
                    createMember(department);
                    break;

                case 3:
                    removeDept();
                    break;

                case 4:
                    displayMemberDeptWise();
                    break;

                case 5:
                    createMember(null);
                    break;

                case 6:
                    displayMemberSkillWise();
                    break;

                case 7:
                    swapDept();
                    break;

                case 8:
                    addNewSkill();
                    break;

                default:
                    System.out.print("Invalid option!\n Enter a valid option:\t");
            }

            System.out.print("-------------------------------------------------\n" +
                    "Do you want to continue? (0/1):\t");
            choice = in.nextInt();
        } while (choice == 1);

        System.exit(0);
    }

    private static void addNewSkill() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the new skill:\t");
        String newSkill = in.next();
        System.out.print("Enter the department choice:\n");
        int j = 1;
        for (String deptObj : Department) {
            System.out.println(j + ". " + deptObj);
            j++;
        }
        String dept = null;
        int deptChoice = in.nextInt();
        int k = 1;
        for (String deptObj : Department) {
            if (k == deptChoice) {
                dept = deptObj;
                break;
            }
            k++;
        }
        for (IgniteMembers im : memberObj) {
            if (im.getDepartment().contains(dept)) {
                Set<String> skill = im.getSkillSet();
                skill.add(newSkill);
                im.setSkillSet(skill);
            }
        }
        System.out.println("Added!");
        for (IgniteMembers im : memberObj)
            if (im.getDepartment().contains(dept))
                System.out.println("Name:\t" + im.getName() + "\t\t\tSkills:\t" + im.getSkillSet());
    }

    private static void swapDept() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the name of the member who's department you want to change:\t");
        String memName = in.next();
        System.out.println("Enter the choice of department:");
        int j = 1;
        for (String deptObj : Department) {
            System.out.println(j + ". " + deptObj);
            j++;
        }
        String dept = null;
        int deptChoice = in.nextInt();
        int k = 1;
        for (String deptObj : Department) {
            if (k == deptChoice) {
                dept = deptObj;
                break;
            }
            k++;
        }
        for (IgniteMembers im : memberObj) {
            if (im.getName().contains(memName))
                im.setDepartment(dept);
        }
    }

    private static void createMember(String deptParam) {
        Scanner in = new Scanner(System.in);
        Set<String> SkillSet = new HashSet<>();
        String newName, newCollege;
        String dept = deptParam;
        int newAge, count, deptChoice;
        System.out.print("\nEnter the Name of the member:\t");
        newName = in.next();
        do {
            for (IgniteMembers im : memberObj) {
                if (im.getName().contains(newName)) {
                    System.out.print("\nName already exists!\n" +
                            "Enter a UNIQUE name:\t");
                    newName = in.next();
                }
            }
        } while (memberObj.contains(newName));

        System.out.print("Enter the Age of the member:\t");
        newAge = in.nextInt();
        System.out.print("Enter the College of the member:\t");
        newCollege = in.next();
        System.out.print("Enter the NUMBER of skills:\t");
        count = in.nextInt();
        System.out.print("Enter the Skill Set:\t");
        for (int j = 0; j < count; j++) {
            String newSkill = in.next();
            SkillSet.add(newSkill);
        }
        if (dept == null) {
            System.out.println("\nAdd the member to one of the departments:");
            int j = 1;
            for (String deptObj : Department) {
                System.out.println(j + ". " + deptObj);
                j++;
            }
            System.out.print("Enter your option:\t");
            deptChoice = in.nextInt();
            int k = 1;
            for (String deptObj : Department) {
                if (k == deptChoice) {
                    dept = deptObj;
                    break;
                }
                k++;
            }
        }
        memberObj.add(new IgniteMembers(newName, newCollege, dept, SkillSet, newAge));
        System.out.println("Created!");
    }

    private static void displayMemberSkillWise() {
        Scanner in = new Scanner(System.in);
        System.out.println("List of members according to skill:");
        System.out.print("Enter the skill:\t");
        String skill = in.nextLine();
        System.out.println("\nMembers having " + skill + " skills:");
        for (IgniteMembers im : memberObj) {
            if (im.getSkillSet().contains(skill))
                System.out.println(im);
        }
    }

    private static void displayMemberDeptWise() {
        System.out.println("\nDEPARTMENT\t\t\t|\t\tMEMBER NAME\n" +
                "-------------------------------------------------");
        for (IgniteMembers mem : memberObj) {
            System.out.println(mem.getDepartment() + "\t\t|\t\t" + mem.getName());
        }
    }

    private static void removeDept() {
        Scanner in = new Scanner(System.in);
        int deptChoice;
        String deleteDept = null;
        System.out.println("\nEnter the department to be deleted:\t");
        int j = 1;
        for (String deptObj : Department) {
            System.out.println(j + ". " + deptObj);
            j++;
        }
        deptChoice = in.nextInt();
        int k = 1;
        for (String deptObj : Department) {
            if (k == deptChoice) {
                deleteDept = deptObj;
                break;
            }
            k++;
        }
       /* Iterator<IgniteMembers> iter = memberObj.iterator();
        List<IgniteMembers> str = new ArrayList<>();
        while(iter.hasNext()){
            str = Collections.singletonList(iter.next());
            if(str.contains(deleteDept)){
                iter.remove();
            }
        }*/
        for (IgniteMembers mem : memberObj) {
            if(mem.getDepartment().contains(deleteDept)) {
                memberObj.remove(mem);
            }
        }
        Department.remove(deleteDept);
        System.out.println("Removed!");
    }

    private static void displayDept() {
        int i = 1;
        System.out.println("\nDepartments:");
        for (String deptObj : Department) {
            System.out.println(i + ". " + deptObj);
            i++;
        }
    }

    private static String createDept() {
        Scanner in = new Scanner(System.in);
        System.out.println("\nEnter the name of the department to be added:\t");
        String newDept = in.next();
        Department.add(newDept);
        return newDept;
    }
}
 /*
 Output:
-------------------------------------------------
Option Menu:
1. Display list of Departments
2. Create a new Department
3. Delete a Department
4. Display all Members based on departments
5. Create a Member and add to a department
6. Display members based on given skill
7. Swap department of a member
8. Add new skill set to all members of a department
-------------------------------------------------
Enter your option:	2

Enter the name of the department to be added:
Security
Now enter a member in this department:

Enter the Name of the member:	Divya

Name already exists!
Enter a UNIQUE name:	Rahul
Enter the Age of the member:	21
Enter the College of the member:	SMS
Enter the NUMBER of skills:	3
Enter the Skill Set:	Algorithm DataStructure OOPS
Created!
-------------------------------------------------
Do you want to continue? (0/1):	1

-------------------------------------------------
Option Menu:
1. Display list of Departments
2. Create a new Department
3. Delete a Department
4. Display all Members based on departments
5. Create a Member and add to a department
6. Display members based on given skill
7. Swap department of a member
8. Add new skill set to all members of a department
-------------------------------------------------
Enter your option:	5

Enter the Name of the member:	Yashaswini
Enter the Age of the member:	20
Enter the College of the member:	VTU
Enter the NUMBER of skills:	2
Enter the Skill Set:	Java DS

Add the member to one of the departments:
1. Data Science
2. Infrastructure
3. Security
Enter your option:	3
Created!
-------------------------------------------------
Do you want to continue? (0/1):	1

-------------------------------------------------
Option Menu:
1. Display list of Departments
2. Create a new Department
3. Delete a Department
4. Display all Members based on departments
5. Create a Member and add to a department
6. Display members based on given skill
7. Swap department of a member
8. Add new skill set to all members of a department
-------------------------------------------------
Enter your option:	4

DEPARTMENT			|		MEMBER NAME
-------------------------------------------------
Data Science		|		Gautham
Data Science		|		Divya
Infrastructure		|		Amit
Infrastructure		|		Naveen
Security		|		Rahul
Security		|		Yashaswini
-------------------------------------------------
Do you want to continue? (0/1):	1

-------------------------------------------------
Option Menu:
1. Display list of Departments
2. Create a new Department
3. Delete a Department
4. Display all Members based on departments
5. Create a Member and add to a department
6. Display members based on given skill
7. Swap department of a member
8. Add new skill set to all members of a department
-------------------------------------------------
Enter your option:	6
List of members according to skill:
Enter the skill:	Java

Members having Java skills:
Ignite Members:
Name: 'Gautham'	College: 'VTU'	Department: 'Data Science'	SkillSet: [Java, SQL, DS]	Age: 28
Ignite Members:
Name: 'Divya'	College: 'TGT'	Department: 'Data Science'	SkillSet: [Java, NOSQL, ML]	Age: 26
Ignite Members:
Name: 'Yashaswini'	College: 'VTU'	Department: 'Security'	SkillSet: [Java, DS]	Age: 20
-------------------------------------------------
Do you want to continue? (0/1):	1

-------------------------------------------------
Option Menu:
1. Display list of Departments
2. Create a new Department
3. Delete a Department
4. Display all Members based on departments
5. Create a Member and add to a department
6. Display members based on given skill
7. Swap department of a member
8. Add new skill set to all members of a department
-------------------------------------------------
Enter your option:	7
Enter the name of the member who's department you want to change:	Rahul
Enter the choice of department:
1. Data Science
2. Infrastructure
3. Security
1
-------------------------------------------------
Do you want to continue? (0/1):	1

-------------------------------------------------
Option Menu:
1. Display list of Departments
2. Create a new Department
3. Delete a Department
4. Display all Members based on departments
5. Create a Member and add to a department
6. Display members based on given skill
7. Swap department of a member
8. Add new skill set to all members of a department
-------------------------------------------------
Enter your option:	4

DEPARTMENT			|		MEMBER NAME
-------------------------------------------------
Data Science		|		Gautham
Data Science		|		Divya
Infrastructure		|		Amit
Infrastructure		|		Naveen
Data Science		|		Rahul
Security		|		Yashaswini
-------------------------------------------------
Do you want to continue? (0/1):	1

-------------------------------------------------
Option Menu:
1. Display list of Departments
2. Create a new Department
3. Delete a Department
4. Display all Members based on departments
5. Create a Member and add to a department
6. Display members based on given skill
7. Swap department of a member
8. Add new skill set to all members of a department
-------------------------------------------------
Enter your option:	8
Enter the new skill:	AI
Enter the department choice:
1. Data Science
2. Infrastructure
3. Security
3
Added!
Name:	Yashaswini			Skills:	[Java, AI, DS]
-------------------------------------------------
Do you want to continue? (0/1):	1

-------------------------------------------------
Option Menu:
1. Display list of Departments
2. Create a new Department
3. Delete a Department
4. Display all Members based on departments
5. Create a Member and add to a department
6. Display members based on given skill
7. Swap department of a member
8. Add new skill set to all members of a department
-------------------------------------------------
Enter your option:	3

Enter the department to be deleted:
1. Data Science
2. Infrastructure
3. Security
3
Removed!
-------------------------------------------------
Do you want to continue? (0/1):	1

-------------------------------------------------
Option Menu:
1. Display list of Departments
2. Create a new Department
3. Delete a Department
4. Display all Members based on departments
5. Create a Member and add to a department
6. Display members based on given skill
7. Swap department of a member
8. Add new skill set to all members of a department
-------------------------------------------------
Enter your option:	4

DEPARTMENT			|		MEMBER NAME
-------------------------------------------------
Data Science		|		Gautham
Data Science		|		Divya
Infrastructure		|		Amit
Infrastructure		|		Naveen
Data Science		|		Rahul
-------------------------------------------------
Do you want to continue? (0/1):	0
*/
