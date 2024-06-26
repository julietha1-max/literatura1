package com.desafioliteratura.literatura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.IdGeneratorType;

import java.util.List;
import java.util.Map;
@JsonIgnoreProperties
public class GutendexResponse {

        private int count;
        private String next;
        private String previous;
        private List<GutendexBook> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<GutendexBook> getResults() {
        return results;
    }

    public void setResults(List<GutendexBook> results) {
        this.results = results;
    }


    public static class GutendexBook {
        private int id;
        private String title;
        private List<GutendexAuthor> authors;
        private List<String> languages;
        private boolean copyright;
        private String media_type;
        private Map<String, String> formats;
        private int download_count;



public GutendexBook(){}
        //voy adicionar
        public GutendexBook(int id, String title, List<GutendexAuthor> authors, List<String> languages, int downloadCount) {
            this.id = id;
            this.title = title;
            this.authors = authors;
            this.languages = languages;
            this.download_count = downloadCount;
        }


        public List<String> getLanguages() {
            return languages;
        }

        public void setLanguages(List<String> languages) {
            this.languages = languages;
        }

        public boolean isCopyright() {
            return copyright;
        }

        public void setCopyright(boolean copyright) {
            this.copyright = copyright;
        }

        public String getMedia_type() {
            return media_type;
        }

        public void setMedia_type(String media_type) {
            this.media_type = media_type;
        }

        public Map<String, String> getFormats() {
            return formats;
        }

        public void setFormats(Map<String, String> formats) {
            this.formats = formats;
        }

        public int getDownload_count() {
            return download_count;
        }

        public void setDownload_count(int download_count) {
            this.download_count = download_count;
        }

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

        public List<GutendexAuthor> getAuthors() {
            return authors;
        }

        public void setAuthors(List<GutendexAuthor> authors) {
            this.authors = authors;
        }


// Getters y setters
    }

    public static class GutendexAuthor {
        private String name;
        private int birth_year;
        private int death_year;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getBirth_year() {
            return birth_year;
        }

        public void setBirth_year(int birth_year) {
            this.birth_year = birth_year;
        }

        public int getDeath_year() {
            return death_year;
        }

        public void setDeath_year(int death_year) {
            this.death_year = death_year;
        }

    }
}
