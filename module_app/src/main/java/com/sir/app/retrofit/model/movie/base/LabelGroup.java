package com.sir.app.retrofit.model.movie.base;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zhuyinan on 2017/04/11
 */
public class LabelGroup implements Serializable {

    @SerializedName("1")
    private Label label1;

    @SerializedName("2")
    private Label label2;

    @SerializedName("3")
    private Label label3;

    @SerializedName("4")
    private Label label4;

    public Label getLabel1() {
        return label1;
    }

    public void setLabel1(Label label1) {
        this.label1 = label1;
    }

    public Label getLabel2() {
        return label2;
    }

    public void setLabel2(Label label2) {
        this.label2 = label2;
    }

    public Label getLabel3() {
        return label3;
    }

    public void setLabel3(Label label3) {
        this.label3 = label3;
    }

    public Label getLabel4() {
        return label4;
    }

    public void setLabel4(Label label4) {
        this.label4 = label4;
    }

}
