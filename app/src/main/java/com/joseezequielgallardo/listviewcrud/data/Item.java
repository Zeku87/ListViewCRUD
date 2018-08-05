package com.joseezequielgallardo.listviewcrud.data;

public class Item {
    private String text;
    private boolean isChecked;

    public Item(String text){
        this.text = text;
        this.isChecked = false;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        if(!text.isEmpty())
            this.text = text;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
