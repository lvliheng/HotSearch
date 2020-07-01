package com.lvvi.hotsearch.model;

public class V2exTopicModel {

    private NodeBean node;
    private MemberBean member;
    private String last_reply_by;
    private int last_touched;
    private String title;
    private String url;
    private int created;
    private String content;
    private String content_rendered;
    private int last_modified;
    private int replies;
    private int id;

    private boolean isHeaderShow;

    public NodeBean getNode() {
        return node;
    }

    public void setNode(NodeBean node) {
        this.node = node;
    }

    public MemberBean getMember() {
        return member;
    }

    public void setMember(MemberBean member) {
        this.member = member;
    }

    public String getLast_reply_by() {
        return last_reply_by;
    }

    public void setLast_reply_by(String last_reply_by) {
        this.last_reply_by = last_reply_by;
    }

    public int getLast_touched() {
        return last_touched;
    }

    public void setLast_touched(int last_touched) {
        this.last_touched = last_touched;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
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

    public int getReplies() {
        return replies;
    }

    public void setReplies(int replies) {
        this.replies = replies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isHeaderShow() {
        return isHeaderShow;
    }

    public void setHeaderShow(boolean headerShow) {
        isHeaderShow = headerShow;
    }

    public static class NodeBean {

        private String avatar_large;
        private String name;
        private String avatar_normal;
        private String title;
        private String url;
        private int topics;
        private String footer;
        private String header;
        private String title_alternative;
        private String avatar_mini;
        private int stars;
//        private AliasesBean aliases;
        private boolean root;
        private int id;
        private String parent_node_name;

        public String getAvatar_large() {
            return avatar_large;
        }

        public void setAvatar_large(String avatar_large) {
            this.avatar_large = avatar_large;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAvatar_normal() {
            return avatar_normal;
        }

        public void setAvatar_normal(String avatar_normal) {
            this.avatar_normal = avatar_normal;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getTopics() {
            return topics;
        }

        public void setTopics(int topics) {
            this.topics = topics;
        }

        public String getFooter() {
            return footer;
        }

        public void setFooter(String footer) {
            this.footer = footer;
        }

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        public String getTitle_alternative() {
            return title_alternative;
        }

        public void setTitle_alternative(String title_alternative) {
            this.title_alternative = title_alternative;
        }

        public String getAvatar_mini() {
            return avatar_mini;
        }

        public void setAvatar_mini(String avatar_mini) {
            this.avatar_mini = avatar_mini;
        }

        public int getStars() {
            return stars;
        }

        public void setStars(int stars) {
            this.stars = stars;
        }

//        public AliasesBean getAliases() {
//            return aliases;
//        }
//
//        public void setAliases(AliasesBean aliases) {
//            this.aliases = aliases;
//        }

        public boolean isRoot() {
            return root;
        }

        public void setRoot(boolean root) {
            this.root = root;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getParent_node_name() {
            return parent_node_name;
        }

        public void setParent_node_name(String parent_node_name) {
            this.parent_node_name = parent_node_name;
        }

        public static class AliasesBean {
        }
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
