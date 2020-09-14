package springboot.topjava.ru;

public class Response {
    private String reply;

    Response(String reply){
        this.reply = reply;
    }

    public String getReply(){
        return reply;
    }

    public void setReply(String reply){
        this.reply = reply;
    }
}
