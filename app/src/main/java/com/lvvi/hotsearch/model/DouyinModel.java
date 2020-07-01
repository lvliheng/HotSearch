package com.lvvi.hotsearch.model;

import java.util.List;

public class DouyinModel {

    private int status_code;
    private String active_time;
    private ExtraBean extra;
    private List<AwemeListBean> aweme_list;

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getActive_time() {
        return active_time;
    }

    public void setActive_time(String active_time) {
        this.active_time = active_time;
    }

    public ExtraBean getExtra() {
        return extra;
    }

    public void setExtra(ExtraBean extra) {
        this.extra = extra;
    }

    public List<AwemeListBean> getAweme_list() {
        return aweme_list;
    }

    public void setAweme_list(List<AwemeListBean> aweme_list) {
        this.aweme_list = aweme_list;
    }

    public static class ExtraBean {

        private long now;
        private String logid;

        public long getNow() {
            return now;
        }

        public void setNow(long now) {
            this.now = now;
        }

        public String getLogid() {
            return logid;
        }

        public void setLogid(String logid) {
            this.logid = logid;
        }
    }

    public static class AwemeListBean {

        private int hot_value;
        private int label;
        private AwemeInfoBean aweme_info;

        public int getHot_value() {
            return hot_value;
        }

        public void setHot_value(int hot_value) {
            this.hot_value = hot_value;
        }

        public int getLabel() {
            return label;
        }

        public void setLabel(int label) {
            this.label = label;
        }

        public AwemeInfoBean getAweme_info() {
            return aweme_info;
        }

        public void setAweme_info(AwemeInfoBean aweme_info) {
            this.aweme_info = aweme_info;
        }

        public static class AwemeInfoBean {

            private ShareInfoBean share_info;
            private Object geofencing;
            private Object position;
            private long author_user_id;
            private int is_preview;
            private String desc;
            private int create_time;
            private AuthorBean author;
            private MusicBean music;
            private String share_url;
            private boolean is_live_replay;
            private Object comment_list;
            private Object video_text;
            private Object promotions;
            private String aweme_id;
            private int aweme_type;
            private Object image_infos;
            private Object uniqid_position;
            private long group_id;
            private VideoBean video;
            private Object video_labels;
            private int duration;
            private Object label_top_text;
            private Object long_video;
            private List<TextExtraBean> text_extra;
            private List<ChaListBean> cha_list;

            public ShareInfoBean getShare_info() {
                return share_info;
            }

            public void setShare_info(ShareInfoBean share_info) {
                this.share_info = share_info;
            }

            public Object getGeofencing() {
                return geofencing;
            }

            public void setGeofencing(Object geofencing) {
                this.geofencing = geofencing;
            }

            public Object getPosition() {
                return position;
            }

            public void setPosition(Object position) {
                this.position = position;
            }

            public long getAuthor_user_id() {
                return author_user_id;
            }

            public void setAuthor_user_id(long author_user_id) {
                this.author_user_id = author_user_id;
            }

            public int getIs_preview() {
                return is_preview;
            }

            public void setIs_preview(int is_preview) {
                this.is_preview = is_preview;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            public AuthorBean getAuthor() {
                return author;
            }

            public void setAuthor(AuthorBean author) {
                this.author = author;
            }

            public MusicBean getMusic() {
                return music;
            }

            public void setMusic(MusicBean music) {
                this.music = music;
            }

            public String getShare_url() {
                return share_url;
            }

            public void setShare_url(String share_url) {
                this.share_url = share_url;
            }

            public boolean isIs_live_replay() {
                return is_live_replay;
            }

            public void setIs_live_replay(boolean is_live_replay) {
                this.is_live_replay = is_live_replay;
            }

            public Object getComment_list() {
                return comment_list;
            }

            public void setComment_list(Object comment_list) {
                this.comment_list = comment_list;
            }

            public Object getVideo_text() {
                return video_text;
            }

            public void setVideo_text(Object video_text) {
                this.video_text = video_text;
            }

            public Object getPromotions() {
                return promotions;
            }

            public void setPromotions(Object promotions) {
                this.promotions = promotions;
            }

            public String getAweme_id() {
                return aweme_id;
            }

            public void setAweme_id(String aweme_id) {
                this.aweme_id = aweme_id;
            }

            public int getAweme_type() {
                return aweme_type;
            }

            public void setAweme_type(int aweme_type) {
                this.aweme_type = aweme_type;
            }

            public Object getImage_infos() {
                return image_infos;
            }

            public void setImage_infos(Object image_infos) {
                this.image_infos = image_infos;
            }

            public Object getUniqid_position() {
                return uniqid_position;
            }

            public void setUniqid_position(Object uniqid_position) {
                this.uniqid_position = uniqid_position;
            }

            public long getGroup_id() {
                return group_id;
            }

            public void setGroup_id(long group_id) {
                this.group_id = group_id;
            }

            public VideoBean getVideo() {
                return video;
            }

            public void setVideo(VideoBean video) {
                this.video = video;
            }

            public Object getVideo_labels() {
                return video_labels;
            }

            public void setVideo_labels(Object video_labels) {
                this.video_labels = video_labels;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public Object getLabel_top_text() {
                return label_top_text;
            }

            public void setLabel_top_text(Object label_top_text) {
                this.label_top_text = label_top_text;
            }

            public Object getLong_video() {
                return long_video;
            }

            public void setLong_video(Object long_video) {
                this.long_video = long_video;
            }

            public List<TextExtraBean> getText_extra() {
                return text_extra;
            }

            public void setText_extra(List<TextExtraBean> text_extra) {
                this.text_extra = text_extra;
            }

            public List<ChaListBean> getCha_list() {
                return cha_list;
            }

            public void setCha_list(List<ChaListBean> cha_list) {
                this.cha_list = cha_list;
            }

            public static class ShareInfoBean {

                private String share_weibo_desc;
                private String share_desc;
                private String share_title;

                public String getShare_weibo_desc() {
                    return share_weibo_desc;
                }

                public void setShare_weibo_desc(String share_weibo_desc) {
                    this.share_weibo_desc = share_weibo_desc;
                }

                public String getShare_desc() {
                    return share_desc;
                }

                public void setShare_desc(String share_desc) {
                    this.share_desc = share_desc;
                }

                public String getShare_title() {
                    return share_title;
                }

                public void setShare_title(String share_title) {
                    this.share_title = share_title;
                }
            }

            public static class AuthorBean {

                private AvatarLargerBean avatar_larger;
                private AvatarMediumBean avatar_medium;
                private Object followers_detail;
                private Object platform_sync_info;
                private String uid;
                private String short_id;
                private String nickname;
                private String signature;
                private Object geofencing;
                private AvatarThumbBean avatar_thumb;
                private String unique_id;
                private Object policy_version;

                public AvatarLargerBean getAvatar_larger() {
                    return avatar_larger;
                }

                public void setAvatar_larger(AvatarLargerBean avatar_larger) {
                    this.avatar_larger = avatar_larger;
                }

                public AvatarMediumBean getAvatar_medium() {
                    return avatar_medium;
                }

                public void setAvatar_medium(AvatarMediumBean avatar_medium) {
                    this.avatar_medium = avatar_medium;
                }

                public Object getFollowers_detail() {
                    return followers_detail;
                }

                public void setFollowers_detail(Object followers_detail) {
                    this.followers_detail = followers_detail;
                }

                public Object getPlatform_sync_info() {
                    return platform_sync_info;
                }

                public void setPlatform_sync_info(Object platform_sync_info) {
                    this.platform_sync_info = platform_sync_info;
                }

                public String getUid() {
                    return uid;
                }

                public void setUid(String uid) {
                    this.uid = uid;
                }

                public String getShort_id() {
                    return short_id;
                }

                public void setShort_id(String short_id) {
                    this.short_id = short_id;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public String getSignature() {
                    return signature;
                }

                public void setSignature(String signature) {
                    this.signature = signature;
                }

                public Object getGeofencing() {
                    return geofencing;
                }

                public void setGeofencing(Object geofencing) {
                    this.geofencing = geofencing;
                }

                public AvatarThumbBean getAvatar_thumb() {
                    return avatar_thumb;
                }

                public void setAvatar_thumb(AvatarThumbBean avatar_thumb) {
                    this.avatar_thumb = avatar_thumb;
                }

                public String getUnique_id() {
                    return unique_id;
                }

                public void setUnique_id(String unique_id) {
                    this.unique_id = unique_id;
                }

                public Object getPolicy_version() {
                    return policy_version;
                }

                public void setPolicy_version(Object policy_version) {
                    this.policy_version = policy_version;
                }

                public static class AvatarLargerBean {

                    private String uri;
                    private List<String> url_list;

                    public String getUri() {
                        return uri;
                    }

                    public void setUri(String uri) {
                        this.uri = uri;
                    }

                    public List<String> getUrl_list() {
                        return url_list;
                    }

                    public void setUrl_list(List<String> url_list) {
                        this.url_list = url_list;
                    }
                }

                public static class AvatarMediumBean {

                    private String uri;
                    private List<String> url_list;

                    public String getUri() {
                        return uri;
                    }

                    public void setUri(String uri) {
                        this.uri = uri;
                    }

                    public List<String> getUrl_list() {
                        return url_list;
                    }

                    public void setUrl_list(List<String> url_list) {
                        this.url_list = url_list;
                    }
                }

                public static class AvatarThumbBean {

                    private String uri;
                    private List<String> url_list;

                    public String getUri() {
                        return uri;
                    }

                    public void setUri(String uri) {
                        this.uri = uri;
                    }

                    public List<String> getUrl_list() {
                        return url_list;
                    }

                    public void setUrl_list(List<String> url_list) {
                        this.url_list = url_list;
                    }
                }
            }

            public static class MusicBean {

                private String author;
                private CoverLargeBean cover_large;
                private CoverMediumBean cover_medium;
                private PlayUrlBean play_url;
                private String mid;
                private String title;
                private CoverThumbBean cover_thumb;
                private int duration;
                private Object position;
                private int status;
                private long id;
                private CoverHdBean cover_hd;

                public String getAuthor() {
                    return author;
                }

                public void setAuthor(String author) {
                    this.author = author;
                }

                public CoverLargeBean getCover_large() {
                    return cover_large;
                }

                public void setCover_large(CoverLargeBean cover_large) {
                    this.cover_large = cover_large;
                }

                public CoverMediumBean getCover_medium() {
                    return cover_medium;
                }

                public void setCover_medium(CoverMediumBean cover_medium) {
                    this.cover_medium = cover_medium;
                }

                public PlayUrlBean getPlay_url() {
                    return play_url;
                }

                public void setPlay_url(PlayUrlBean play_url) {
                    this.play_url = play_url;
                }

                public String getMid() {
                    return mid;
                }

                public void setMid(String mid) {
                    this.mid = mid;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public CoverThumbBean getCover_thumb() {
                    return cover_thumb;
                }

                public void setCover_thumb(CoverThumbBean cover_thumb) {
                    this.cover_thumb = cover_thumb;
                }

                public int getDuration() {
                    return duration;
                }

                public void setDuration(int duration) {
                    this.duration = duration;
                }

                public Object getPosition() {
                    return position;
                }

                public void setPosition(Object position) {
                    this.position = position;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public long getId() {
                    return id;
                }

                public void setId(long id) {
                    this.id = id;
                }

                public CoverHdBean getCover_hd() {
                    return cover_hd;
                }

                public void setCover_hd(CoverHdBean cover_hd) {
                    this.cover_hd = cover_hd;
                }

                public static class CoverLargeBean {

                    private String uri;
                    private List<String> url_list;

                    public String getUri() {
                        return uri;
                    }

                    public void setUri(String uri) {
                        this.uri = uri;
                    }

                    public List<String> getUrl_list() {
                        return url_list;
                    }

                    public void setUrl_list(List<String> url_list) {
                        this.url_list = url_list;
                    }
                }

                public static class CoverMediumBean {

                    private String uri;
                    private List<String> url_list;

                    public String getUri() {
                        return uri;
                    }

                    public void setUri(String uri) {
                        this.uri = uri;
                    }

                    public List<String> getUrl_list() {
                        return url_list;
                    }

                    public void setUrl_list(List<String> url_list) {
                        this.url_list = url_list;
                    }
                }

                public static class PlayUrlBean {

                    private String uri;
                    private List<String> url_list;

                    public String getUri() {
                        return uri;
                    }

                    public void setUri(String uri) {
                        this.uri = uri;
                    }

                    public List<String> getUrl_list() {
                        return url_list;
                    }

                    public void setUrl_list(List<String> url_list) {
                        this.url_list = url_list;
                    }
                }

                public static class CoverThumbBean {

                    private String uri;
                    private List<String> url_list;

                    public String getUri() {
                        return uri;
                    }

                    public void setUri(String uri) {
                        this.uri = uri;
                    }

                    public List<String> getUrl_list() {
                        return url_list;
                    }

                    public void setUrl_list(List<String> url_list) {
                        this.url_list = url_list;
                    }
                }

                public static class CoverHdBean {

                    private String uri;
                    private List<String> url_list;

                    public String getUri() {
                        return uri;
                    }

                    public void setUri(String uri) {
                        this.uri = uri;
                    }

                    public List<String> getUrl_list() {
                        return url_list;
                    }

                    public void setUrl_list(List<String> url_list) {
                        this.url_list = url_list;
                    }
                }
            }

            public static class VideoBean {

                private String vid;
                private PlayAddrBean play_addr;
                private CoverBean cover;
                private int width;
                private String ratio;
                private boolean has_watermark;
                private Object bit_rate;
                private int duration;
                private int height;
                private DynamicCoverBean dynamic_cover;
                private OriginCoverBean origin_cover;
                private String url;
                private boolean isSelected;
                private boolean isPlaying;

                public String getVid() {
                    return vid;
                }

                public void setVid(String vid) {
                    this.vid = vid;
                }

                public PlayAddrBean getPlay_addr() {
                    return play_addr;
                }

                public void setPlay_addr(PlayAddrBean play_addr) {
                    this.play_addr = play_addr;
                }

                public CoverBean getCover() {
                    return cover;
                }

                public void setCover(CoverBean cover) {
                    this.cover = cover;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public String getRatio() {
                    return ratio;
                }

                public void setRatio(String ratio) {
                    this.ratio = ratio;
                }

                public boolean isHas_watermark() {
                    return has_watermark;
                }

                public void setHas_watermark(boolean has_watermark) {
                    this.has_watermark = has_watermark;
                }

                public Object getBit_rate() {
                    return bit_rate;
                }

                public void setBit_rate(Object bit_rate) {
                    this.bit_rate = bit_rate;
                }

                public int getDuration() {
                    return duration;
                }

                public void setDuration(int duration) {
                    this.duration = duration;
                }

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }

                public DynamicCoverBean getDynamic_cover() {
                    return dynamic_cover;
                }

                public void setDynamic_cover(DynamicCoverBean dynamic_cover) {
                    this.dynamic_cover = dynamic_cover;
                }

                public OriginCoverBean getOrigin_cover() {
                    return origin_cover;
                }

                public void setOrigin_cover(OriginCoverBean origin_cover) {
                    this.origin_cover = origin_cover;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public boolean isSelected() {
                    return isSelected;
                }

                public void setSelected(boolean selected) {
                    isSelected = selected;
                }

                public boolean isPlaying() {
                    return isPlaying;
                }

                public void setPlaying(boolean playing) {
                    isPlaying = playing;
                }

                public static class PlayAddrBean {

                    private String uri;
                    private List<String> url_list;

                    public String getUri() {
                        return uri;
                    }

                    public void setUri(String uri) {
                        this.uri = uri;
                    }

                    public List<String> getUrl_list() {
                        return url_list;
                    }

                    public void setUrl_list(List<String> url_list) {
                        this.url_list = url_list;
                    }
                }

                public static class CoverBean {

                    private String uri;
                    private List<String> url_list;

                    public String getUri() {
                        return uri;
                    }

                    public void setUri(String uri) {
                        this.uri = uri;
                    }

                    public List<String> getUrl_list() {
                        return url_list;
                    }

                    public void setUrl_list(List<String> url_list) {
                        this.url_list = url_list;
                    }
                }

                public static class DynamicCoverBean {

                    private String uri;
                    private List<String> url_list;

                    public String getUri() {
                        return uri;
                    }

                    public void setUri(String uri) {
                        this.uri = uri;
                    }

                    public List<String> getUrl_list() {
                        return url_list;
                    }

                    public void setUrl_list(List<String> url_list) {
                        this.url_list = url_list;
                    }
                }

                public static class OriginCoverBean {

                    private String uri;
                    private List<String> url_list;

                    public String getUri() {
                        return uri;
                    }

                    public void setUri(String uri) {
                        this.uri = uri;
                    }

                    public List<String> getUrl_list() {
                        return url_list;
                    }

                    public void setUrl_list(List<String> url_list) {
                        this.url_list = url_list;
                    }
                }
            }

            public static class TextExtraBean {

                private int start;
                private int end;
                private int type;
                private String hashtag_name;
                private long hashtag_id;

                public int getStart() {
                    return start;
                }

                public void setStart(int start) {
                    this.start = start;
                }

                public int getEnd() {
                    return end;
                }

                public void setEnd(int end) {
                    this.end = end;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public String getHashtag_name() {
                    return hashtag_name;
                }

                public void setHashtag_name(String hashtag_name) {
                    this.hashtag_name = hashtag_name;
                }

                public long getHashtag_id() {
                    return hashtag_id;
                }

                public void setHashtag_id(long hashtag_id) {
                    this.hashtag_id = hashtag_id;
                }
            }

            public static class ChaListBean {

                private int view_count;
                private boolean is_commerce;
                private String cid;
                private Object connect_music;
                private int user_count;
                private int type;
                private String hash_tag_profile;
                private String cha_name;
                private String desc;

                public int getView_count() {
                    return view_count;
                }

                public void setView_count(int view_count) {
                    this.view_count = view_count;
                }

                public boolean isIs_commerce() {
                    return is_commerce;
                }

                public void setIs_commerce(boolean is_commerce) {
                    this.is_commerce = is_commerce;
                }

                public String getCid() {
                    return cid;
                }

                public void setCid(String cid) {
                    this.cid = cid;
                }

                public Object getConnect_music() {
                    return connect_music;
                }

                public void setConnect_music(Object connect_music) {
                    this.connect_music = connect_music;
                }

                public int getUser_count() {
                    return user_count;
                }

                public void setUser_count(int user_count) {
                    this.user_count = user_count;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public String getHash_tag_profile() {
                    return hash_tag_profile;
                }

                public void setHash_tag_profile(String hash_tag_profile) {
                    this.hash_tag_profile = hash_tag_profile;
                }

                public String getCha_name() {
                    return cha_name;
                }

                public void setCha_name(String cha_name) {
                    this.cha_name = cha_name;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }
            }
        }
    }
}
