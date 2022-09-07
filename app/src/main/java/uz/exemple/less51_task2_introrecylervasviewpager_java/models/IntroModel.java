package uz.exemple.less51_task2_introrecylervasviewpager_java.models;

public class IntroModel {
    private String title;
    private String desc;
    private String lottyName;

    public IntroModel(String title, String desc, String lottyName) {
        this.title = title;
        this.desc = desc;
        this.lottyName = lottyName;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getLottyName() {
        return lottyName;
    }
}
