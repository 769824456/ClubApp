package win.yulongsun.clubapp.entity;


public class UserVo {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long   id;
    public String name;
    public String addr;
    public long   job_id;
    public int    gender;
    public int    is_enable;
    public String create_time;
}
