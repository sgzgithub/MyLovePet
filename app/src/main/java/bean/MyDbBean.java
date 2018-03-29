package bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by I'M宋国召 on 2018/3/29.
 */
@Entity
public class MyDbBean {
    @Id(autoincrement = true)
    private Long id;
    private String image;
    private String name;
    private String type;
    private String sterilization;
    private String shengri;
    private String kg;
    private String immune;
    private String intro;
    @Generated(hash = 10288343)
    public MyDbBean(Long id, String image, String name, String type,
            String sterilization, String shengri, String kg, String immune,
            String intro) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.type = type;
        this.sterilization = sterilization;
        this.shengri = shengri;
        this.kg = kg;
        this.immune = immune;
        this.intro = intro;
    }
    @Generated(hash = 720896287)
    public MyDbBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getImage() {
        return this.image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getSterilization() {
        return this.sterilization;
    }
    public void setSterilization(String sterilization) {
        this.sterilization = sterilization;
    }
    public String getShengri() {
        return this.shengri;
    }
    public void setShengri(String shengri) {
        this.shengri = shengri;
    }
    public String getKg() {
        return this.kg;
    }
    public void setKg(String kg) {
        this.kg = kg;
    }
    public String getImmune() {
        return this.immune;
    }
    public void setImmune(String immune) {
        this.immune = immune;
    }
    public String getIntro() {
        return this.intro;
    }
    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Override
    public String toString() {
        return "MyDbBean{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", sterilization='" + sterilization + '\'' +
                ", shengri='" + shengri + '\'' +
                ", kg='" + kg + '\'' +
                ", immune='" + immune + '\'' +
                ", intro='" + intro + '\'' +
                '}';
    }
}
