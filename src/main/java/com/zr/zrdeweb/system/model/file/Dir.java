package com.zr.zrdeweb.system.model.file;

public class Dir {
    private String dirname;
    private String dirpath;

    public Dir() {
    }

    public Dir(String dirname, String dirpath) {
        this.dirname = dirname;
        this.dirpath = dirpath;
    }

    @Override
    public String toString() {
        return "Dir{" +
                "dirname='" + dirname + '\'' +
                ", dirpath='" + dirpath + '\'' +
                '}';
    }

    public String getDirname() {
        return dirname;
    }

    public void setDirname(String dirname) {
        this.dirname = dirname;
    }

    public String getDirpath() {
        return dirpath;
    }

    public void setDirpath(String dirpath) {
        this.dirpath = dirpath;
    }
}
