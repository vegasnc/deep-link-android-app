package com.test.a360camera.cameraview;

public class Pose {
    private int mPreWidth;
    private int mPreHeight;
    private int mPicWidth;
    private int mPicHeight;
    private int mCamOrient;

    public Pose(int preWidth, int preHeight, int picWidth, int picHeight, int camOrient) {
        this.mPreWidth = preWidth;
        this.mPreHeight = preHeight;
        this.mPicWidth = picWidth;
        this.mPicHeight = picHeight;
        this.mCamOrient = camOrient;
    }

    public void setPreWidth(int preWidth) {
        this.mPreWidth = preWidth;
    }

    public void setPreHeight(int preHeight) {
        this.mPreHeight = preHeight;
    }

    public void setPicWidth(int picWidth) {
        this.mPicWidth = picWidth;
    }

    public void setPicHeight(int picHeight) {
        this.mPicHeight = picHeight;
    }

    public void setCamOrient(int camOrient) {
        this.mCamOrient = camOrient;
    }

    public int getPreWidth() {
        return this.mPreWidth;
    }

    public int getPreHeight() {
        return this.mPreHeight;
    }

    public int getPicWidth() {
        return this.mPicWidth;
    }

    public int getPicHeight() {
        return this.mPicHeight;
    }

    public int getCamOrient() {
        return this.mCamOrient;
    }
}
