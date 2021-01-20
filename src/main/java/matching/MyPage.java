package matching;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="MyPage_table")
public class MyPage {

    @Id
    private Long id;
    private Integer price;
    private String teacher;
    private String visitDate;
    private String status;
    private String student; //학생정보 관리를 위한 student 정보 추가


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    /** student 정보 추가 **/
    public String getStudent() {
        return student;
    }
    public void setStudent(String student) {
        this.student = student;
    }



}
