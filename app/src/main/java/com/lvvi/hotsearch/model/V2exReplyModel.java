package com.lvvi.hotsearch.model;

public class V2exReplyModel {

    private MemberBean member;
    private int created;
    private int topic_id;
    private String content;
    private String content_rendered;
    private int last_modified;
    private int member_id;
    private int id;

    public MemberBean getMember() {
        return member;
    }

    public void setMember(MemberBean member) {
        this.member = member;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent_rendered() {
        return content_rendered;
    }

    public void setContent_rendered(String content_rendered) {
        this.content_rendered = content_rendered;
    }

    public int getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(int last_modified) {
        this.last_modified = last_modified;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static class MemberBean {

        private String username;
        private Object website;
        private Object github;
        private Object psn;
        private String avatar_normal;
        private Object bio;
        private String url;
        private Object tagline;
        private Object twitter;
        private int created;
        private String avatar_large;
        private String avatar_mini;
        private Object location;
        private Object btc;
        private int id;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Object getWebsite() {
            return website;
        }

        public void setWebsite(Object website) {
            this.website = website;
        }

        public Object getGithub() {
            return github;
        }

        public void setGithub(Object github) {
            this.github = github;
        }

        public Object getPsn() {
            return psn;
        }

        public void setPsn(Object psn) {
            this.psn = psn;
        }

        public String getAvatar_normal() {
            return avatar_normal;
        }

        public void setAvatar_normal(String avatar_normal) {
            this.avatar_normal = avatar_normal;
        }

        public Object getBio() {
            return bio;
        }

        public void setBio(Object bio) {
            this.bio = bio;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Object getTagline() {
            return tagline;
        }

        public void setTagline(Object tagline) {
            this.tagline = tagline;
        }

        public Object getTwitter() {
            return twitter;
        }

        public void setTwitter(Object twitter) {
            this.twitter = twitter;
        }

        public int getCreated() {
            return created;
        }

        public void setCreated(int created) {
            this.created = created;
        }

        public String getAvatar_large() {
            return avatar_large;
        }

        public void setAvatar_large(String avatar_large) {
            this.avatar_large = avatar_large;
        }

        public String getAvatar_mini() {
            return avatar_mini;
        }

        public void setAvatar_mini(String avatar_mini) {
            this.avatar_mini = avatar_mini;
        }

        public Object getLocation() {
            return location;
        }

        public void setLocation(Object location) {
            this.location = location;
        }

        public Object getBtc() {
            return btc;
        }

        public void setBtc(Object btc) {
            this.btc = btc;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
