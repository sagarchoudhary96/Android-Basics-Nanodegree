package choudhary96.sagar.p5_reportcard;

/**
 * Created by admin on 29-06-2016.
 */
public class ReportCard {

    private char grade;
    private String courseName;
    private int courseCredit;

    ReportCard(String courseName, int courseCredit, char grade){
        this.courseName = courseName;
        this.courseCredit = courseCredit;
        this.grade = grade;
    }

    //getter functions
    public int getCourseCredit() {
        return courseCredit;
    }

    public String getCourseName(){
        return courseName;
    }

    public char getGrade(){
        return grade;
    }

    //Setter function
    public void setCourseName(String courseName){
        this.courseName = courseName;
    }

    public void setGrade(char grade){
        this.grade = grade;
    }

    public void setCourseCredit (int courseCredit){
        this.courseCredit = courseCredit;
    }

    //returns credit as a string
    public String creditToString() {
        String courseCredit = String.valueOf(this.courseCredit);
        return courseCredit;
    }

    //returns grade as a string
    public String gradeToString() {
        String courseGrade = String.valueOf(this.grade);
        return courseGrade;
    }

    //returns the content of the class
    @Override
    public String toString() {
        return "ReportCard{" +
                "grade=" + grade +
                ", courseName='" + courseName + '\'' +
                ", courseCredit=" + courseCredit +
                '}';
    }
}
