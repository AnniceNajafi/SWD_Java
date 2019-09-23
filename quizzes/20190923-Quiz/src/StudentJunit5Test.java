import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentJunit5Test {
    @Test
    private Student mystudent;
    void createStudent (){
        mystudent = new Student("Test", 90);
    }
    @Test
    void setName(String name){

    }
    void setAverage(){

    }
}
