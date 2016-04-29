package win.yulongsun.clubapp.entity;

import java.sql.Timestamp;

/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.bean
 * USER : yulongsun on 2016/4/21
 * NOTE :
 */
public class MemberVo {
    public int       id;
    public String    name;
    public String    mobile;
    public int       rank;
    public int       card_id;
    public int       gender;
    public String    varchar;
    public int       is_enable;
    public Timestamp create_time;

    public MemberVo() {
    }

    public MemberVo(int mId, String mName, String mMobile) {
        id = mId;
        name = mName;
        mobile = mMobile;
    }
}
