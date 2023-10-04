package ra.functionalinterface;

public class Subject {
    Student student;
    double point;

    public Subject(Student student, double point) {
        this.student = student;
        this.point = point;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }
}
