package win.yulongsun.clubapp.entity;

import java.sql.Timestamp;

/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.bean
 * USER : yulongsun on 2016/4/21
 * NOTE :
 */
public class MemberVo {
    public int    id;
    public String avatar;
    public String name;
    public String mobile;
    public String addr;
    public int    score;
    public int    rank;
    public int    card_id;
    public int    gender;
    public int    c_id;
    public int    operator_id;
    public int    is_enable;
    public String create_time;

    public MemberVo() {
    }

    public MemberVo(int mId, String mName, String mMobile) {
        id = mId;
        name = mName;
        mobile = mMobile;
    }
}
