package com.pleshcheev.loader;

import org.jsoup.nodes.Element;

import java.nio.file.Path;

public class Data {
    String uri;
    String attr;
    Element elem;
    Path path;

    public Element getElem() {
        return elem;
    }
    public String getAttr() {
        return attr;
    }
    public Path getPath() {
        return path;
    }
    public String getUri() {
        return uri;
    }
    public void setAttr(String attr) {
        this.attr = attr;
    }
    public void setElem(Element elem) {
        this.elem = elem;
    }
    public void setPath(Path path) {
        this.path = path;
    }
    public void setUri(String uri) {
        this.uri = uri;
    }

    Data(String uri, Path path, Element elem, String attr) {
        this.uri = uri;
        this.path = path;
        this.attr = attr;
        this.elem = elem;
    }
}
