package ui.model;

public class ModelURL {

    public String index;
    public String url;

    public ModelURL(int index, String url) {
        this.index = String.valueOf(index);
        this.url = url;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    
    public void printModel(){
        System.out.println("Index: " + this.index + " , URL: " + this.url);
    }
}

/*
public class ModelURL {

    public SimpleStringProperty index;
    public SimpleStringProperty url;

    public ModelURL(int index, String url) {
        this.index = new SimpleStringProperty(String.valueOf(index));
        this.url = new SimpleStringProperty(url);
    }

    public String getURL() {
        return this.url.get();
    }

    public String getIndex() {
        return this.index.get();
    }

    public void setURL(String url) {
        this.url = new SimpleStringProperty(url);
    }

    public void setIndex(int idx) {
        this.index = new SimpleStringProperty(String.valueOf(index));
    }
}

*/