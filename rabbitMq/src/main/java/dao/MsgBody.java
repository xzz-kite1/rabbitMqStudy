package dao;

public class MsgBody {

    private String id;

    private String description;

    private String body;

    private Integer delayTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(Integer delayTime) {
        this.delayTime = delayTime;
    }

    @Override
    public String toString() {
        return "MsgBody{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", body='" + body + '\'' +
                ", delayTime=" + delayTime +
                '}';
    }
}
