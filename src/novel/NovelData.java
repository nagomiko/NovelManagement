package novel;

public class NovelData {
    private int ID;
    private int target_ID;
    private int no;
    private String url;
    private String title;
    private String pageData;
    private int isRead;


    public NovelData(){}

    public NovelData(int target_ID, int no, String url, String title, String pageData, int isRead) {
        this.target_ID = target_ID;
        this.no = no;
        this.url = url;
        this.title = title;
        this.pageData = pageData;
        this.isRead = isRead;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getTarget_ID() {
        return target_ID;
    }

    public void setTarget_ID(int target_ID) {
        this.target_ID = target_ID;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPageData() {
        return pageData;
    }

    public void setPageData(String pageData) {
        this.pageData = pageData;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }


}
