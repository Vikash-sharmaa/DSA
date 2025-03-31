package JAVA;

import java.util.List;
import java.util.Objects;

class Student {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String departmentName;
    private int joinedYear;
    private String city;
    private int rank;
    private List<String> skills;
    

    public Student(int id, String firstName, String lastName, int age, String gender, String departmentName,
                   int joinedYear, String city, int rank) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.departmentName = departmentName;
        this.joinedYear = joinedYear;
        this.city = city;
        this.rank = rank;
    }

    public Student(int id, String firstName, String lastName, int age, String gender, String departmentName,
                   int joinedYear, String city, int rank,List<String> skills) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.departmentName = departmentName;
        this.joinedYear = joinedYear;
        this.city = city;
        this.rank = rank;
        this.skills = skills;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, gender, departmentName, joinedYear, city, rank);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return id == student.id &&
                age == student.age &&
                joinedYear == student.joinedYear &&
                rank == student.rank &&
                Objects.equals(firstName, student.firstName) &&
                Objects.equals(lastName, student.lastName) &&
                Objects.equals(gender, student.gender) &&
                Objects.equals(departmentName, student.departmentName) &&
                Objects.equals(city, student.city);
    }

    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getDepartmentName() { return departmentName; }
    public int getJoinedYear() { return joinedYear; }
    public String getCity() { return city; }
    public int getRank() { return rank; }
    public List<String> getSkills() {
        return skills;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", joinedYear=" + joinedYear +
                ", city='" + city + '\'' +
                ", rank=" + rank +
                '}';
    }
}
