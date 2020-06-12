package com.lvvi.hotsearch.model;

import java.util.List;

public class V2exModel {

    /**
     * node : {"avatar_large":"https://cdn.v2ex.com/navatar/94f6/d7e0/300_large.png?m=1587133957","name":"programmer","avatar_normal":"https://cdn.v2ex.com/navatar/94f6/d7e0/300_normal.png?m=1587133957","title":"程序员","url":"https://www.v2ex.com/go/programmer","topics":37584,"footer":"","header":"While code monkeys are not eating bananas, they're coding.","title_alternative":"Programmer","avatar_mini":"https://cdn.v2ex.com/navatar/94f6/d7e0/300_mini.png?m=1587133957","stars":6692,"aliases":[],"root":false,"id":300,"parent_node_name":"computer"}
     * member : {"username":"clockOS","website":"https://cooldevelop.com","github":"","psn":"","avatar_normal":"https://cdn.v2ex.com/gravatar/978395c4d87c6c6bb95350775783f466?s=24&d=retro","bio":"","url":"https://www.v2ex.com/u/clockOS","tagline":"","twitter":"","created":1451405779,"avatar_large":"https://cdn.v2ex.com/gravatar/978395c4d87c6c6bb95350775783f466?s=24&d=retro","avatar_mini":"https://cdn.v2ex.com/gravatar/978395c4d87c6c6bb95350775783f466?s=24&d=retro","location":"","btc":"","id":153475}
     * last_reply_by : murmur
     * last_touched : 1588183811
     * title : 有什么讽刺 IT 行业的小短剧吗？
     * url : https://www.v2ex.com/t/667150
     * created : 1588120659
     * content :
     * content_rendered : 有讽刺足球的《勇夺世界杯冠军》，讽刺电影无极的《一个馒头引发的血案》<br /><br />我小时候看这样的片儿长大，深受影响，这些片儿也不为了盈利，是在那个年代网络，网民人均素质比较高的时候，感觉还有点内涵的东西，虽然制作粗糙。<br /><br />我曾经仰望这些大神，也想做出来个 meme，因为一直以为自己也挺有创意的。<br /><br />我打算在半年没事儿做的时候，也能做出一个现象级的<br /><br />可是我前几天做出来的 12 分钟影片，算是一黑黑俩， 黑了部分 IT 行业的人和华语乐坛的人<br /><br />两天前发了个帖子没说明白<br /><br />感谢很多人的鼓励，肯定了内容质量<br /><br />可是没什么传播力，估计是影片时间太长了，现在都看抖音那样的短视频，感觉跟不上时代，不懂年轻人了<br /><br />昨天不服输，24 小时内又是有了想法拍了新的，还没上传，因为最近灵感太多了，想攒起来。<br /><br />怕一下都发出去了以后就黔驴技穷了，也是 2 天做一个视频太累了。<br /><br />不要把我放到推广了，我都还不知道我推广啥呢...<br /><br />求波关注吧<br /><br />觉得慢的 1.5 倍速观看效果也不错<br /><br /><div class="embedded_video_wrapper"><iframe src="https://www.youtube.com/embed/G5FiAXqlHLM" class="embedded_video" allowfullscreen="" type="text/html" id="ytplayer" frameborder="0"></iframe></div>
     * last_modified : 1588121807
     * replies : 187
     * id : 667150
     */

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

    public static class NodeBean {
        /**
         * avatar_large : https://cdn.v2ex.com/navatar/94f6/d7e0/300_large.png?m=1587133957
         * name : programmer
         * avatar_normal : https://cdn.v2ex.com/navatar/94f6/d7e0/300_normal.png?m=1587133957
         * title : 程序员
         * url : https://www.v2ex.com/go/programmer
         * topics : 37584
         * footer :
         * header : While code monkeys are not eating bananas, they're coding.
         * title_alternative : Programmer
         * avatar_mini : https://cdn.v2ex.com/navatar/94f6/d7e0/300_mini.png?m=1587133957
         * stars : 6692
         * aliases : []
         * root : false
         * id : 300
         * parent_node_name : computer
         */

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
        private boolean root;
        private int id;
        private String parent_node_name;
        private List<?> aliases;

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

        public List<?> getAliases() {
            return aliases;
        }

        public void setAliases(List<?> aliases) {
            this.aliases = aliases;
        }
    }

    public static class MemberBean {
        /**
         * username : clockOS
         * website : https://cooldevelop.com
         * github :
         * psn :
         * avatar_normal : https://cdn.v2ex.com/gravatar/978395c4d87c6c6bb95350775783f466?s=24&d=retro
         * bio :
         * url : https://www.v2ex.com/u/clockOS
         * tagline :
         * twitter :
         * created : 1451405779
         * avatar_large : https://cdn.v2ex.com/gravatar/978395c4d87c6c6bb95350775783f466?s=24&d=retro
         * avatar_mini : https://cdn.v2ex.com/gravatar/978395c4d87c6c6bb95350775783f466?s=24&d=retro
         * location :
         * btc :
         * id : 153475
         */

        private String username;
        private String website;
        private String github;
        private String psn;
        private String avatar_normal;
        private String bio;
        private String url;
        private String tagline;
        private String twitter;
        private int created;
        private String avatar_large;
        private String avatar_mini;
        private String location;
        private String btc;
        private int id;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public String getGithub() {
            return github;
        }

        public void setGithub(String github) {
            this.github = github;
        }

        public String getPsn() {
            return psn;
        }

        public void setPsn(String psn) {
            this.psn = psn;
        }

        public String getAvatar_normal() {
            return avatar_normal;
        }

        public void setAvatar_normal(String avatar_normal) {
            this.avatar_normal = avatar_normal;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTagline() {
            return tagline;
        }

        public void setTagline(String tagline) {
            this.tagline = tagline;
        }

        public String getTwitter() {
            return twitter;
        }

        public void setTwitter(String twitter) {
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

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getBtc() {
            return btc;
        }

        public void setBtc(String btc) {
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
