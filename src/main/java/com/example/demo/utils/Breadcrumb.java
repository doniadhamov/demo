package com.example.demo.utils;

import java.util.LinkedList;
import java.util.List;

public class Breadcrumb {

    public class Link {

        private String url;
        private String label;

        public Link(String Label, String Url) {
            url = Url;
            label = Label;
        }

        public String getUrl() {
            return url;
        }

        public String getLabel() {
            return label;
        }
    }

    List<Link> links;

    public Breadcrumb() {
        links = new LinkedList<Link>();
    }

    public List<Link> getLinks() {
        return links;
    }

    public void addLink(String Label, String Url) {
        links.add(new Link(Label, Url));
    }
}
