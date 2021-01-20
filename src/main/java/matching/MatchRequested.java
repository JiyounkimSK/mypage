
package matching;

public class MatchRequested extends AbstractEvent {

    private Long id;
    private Integer price;
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

    /** student 정보 추가 **/
    public String getStudent() {
        return student;
    }
    public void setStudent(String student) {
        this.student = student;
    }

}
