package com.example.frases;
//convierte del json a aqui
public class Quote {
    int id;
    String title;
    String content;
    String link;
    //genera el constructor vacio por DEFECTO
    public Quote(int id) {this.id = id; }

    public Quote() {    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


}
