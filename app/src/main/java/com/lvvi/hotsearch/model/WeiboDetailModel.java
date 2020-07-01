package com.lvvi.hotsearch.model;

import java.util.List;

public class WeiboDetailModel {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<CardsBean> cards;


        public List<CardsBean> getCards() {
            return cards;
        }

        public void setCards(List<CardsBean> cards) {
            this.cards = cards;
        }


        public static class CardsBean {

            private int card_type;
            private String desc;
            //            private String scheme;
//            private String itemid;
//            private ActionlogBeanXX actionlog;
//            private String analysis_extra;
//            private int is_hotweibo;
//            private int display_socialtitle;
//            private int display_followbtn;
            private MblogBean mblog;
            private int display_arrow;
            private int show_type;
            private String card_type_name;
            //            private int is_asyn;
//            private String async_api;
            private String title;
            private List<CardGroupBean> card_group;

            public int getCard_type() {
                return card_type;
            }

            public void setCard_type(int card_type) {
                this.card_type = card_type;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }


            public MblogBean getMblog() {
                return mblog;
            }

            public void setMblog(MblogBean mblog) {
                this.mblog = mblog;
            }

            public int getDisplay_arrow() {
                return display_arrow;
            }

            public void setDisplay_arrow(int display_arrow) {
                this.display_arrow = display_arrow;
            }

            public int getShow_type() {
                return show_type;
            }

            public void setShow_type(int show_type) {
                this.show_type = show_type;
            }

            public String getCard_type_name() {
                return card_type_name;
            }

            public void setCard_type_name(String card_type_name) {
                this.card_type_name = card_type_name;
            }


            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<CardGroupBean> getCard_group() {
                return card_group;
            }

            public void setCard_group(List<CardGroupBean> card_group) {
                this.card_group = card_group;
            }


            public static class MblogBean {

                private String text;
                private String source;
                private String obj_ext;
                private PageInfoBean page_info;
                private List<PicsBean> pics;

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }


                public String getSource() {
                    return source;
                }

                public void setSource(String source) {
                    this.source = source;
                }


                public String getObj_ext() {
                    return obj_ext;
                }

                public void setObj_ext(String obj_ext) {
                    this.obj_ext = obj_ext;
                }

                public PageInfoBean getPage_info() {
                    return page_info;
                }

                public void setPage_info(PageInfoBean page_info) {
                    this.page_info = page_info;
                }

                public List<PicsBean> getPics() {
                    return pics;
                }

                public void setPics(List<PicsBean> pics) {
                    this.pics = pics;
                }

                public static class PicsBean {
                    private String url;
                    private LargeBean large;

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    public LargeBean getLarge() {
                        return large;
                    }

                    public void setLarge(LargeBean large) {
                        this.large = large;
                    }

                    @Override
                    public String toString() {
                        return "PicsBean{" +
                                "url='" + url + '\'' +
                                '}';
                    }

                    public static class LargeBean {
                        private String url;
                        private GeoBean geo;

                        public String getUrl() {
                            return url;
                        }

                        public void setUrl(String url) {
                            this.url = url;
                        }

                        public GeoBean getGeo() {
                            return geo;
                        }

                        public void setGeo(GeoBean geo) {
                            this.geo = geo;
                        }

                        @Override
                        public String toString() {
                            return "LargeBean{" +
                                    "url='" + url + '\'' +
                                    ", geo=" + geo +
                                    '}';
                        }

                        public static class GeoBean {
                            private String width;
                            private String height;
                            private boolean croped;

                            public String getWidth() {
                                return width;
                            }

                            public void setWidth(String width) {
                                this.width = width;
                            }

                            public String getHeight() {
                                return height;
                            }

                            public void setHeight(String height) {
                                this.height = height;
                            }

                            public boolean isCroped() {
                                return croped;
                            }

                            public void setCroped(boolean croped) {
                                this.croped = croped;
                            }

                            @Override
                            public String toString() {
                                return "GeoBean{" +
                                        "width='" + width + '\'' +
                                        ", height='" + height + '\'' +
                                        ", croped=" + croped +
                                        '}';
                            }
                        }
                    }
                }


                public static class PageInfoBean {

                    private PagePicBean page_pic;
                    private String page_url;
                    private String page_title;
//                    private String content1;
//                    private String content2;
                    private String type;//video/article
                    private MediaInfoBean media_info;
//                    private String play_count;
                    private String object_id;
                    private String icon;


                    public PagePicBean getPage_pic() {
                        return page_pic;
                    }

                    public void setPage_pic(PagePicBean page_pic) {
                        this.page_pic = page_pic;
                    }

                    public String getPage_url() {
                        return page_url;
                    }

                    public void setPage_url(String page_url) {
                        this.page_url = page_url;
                    }

                    public String getPage_title() {
                        return page_title;
                    }

                    public void setPage_title(String page_title) {
                        this.page_title = page_title;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }

                    public MediaInfoBean getMedia_info() {
                        return media_info;
                    }

                    public void setMedia_info(MediaInfoBean media_info) {
                        this.media_info = media_info;
                    }

                    public String getObject_id() {
                        return object_id;
                    }

                    public void setObject_id(String object_id) {
                        this.object_id = object_id;
                    }

                    public String getIcon() {
                        return icon;
                    }

                    public void setIcon(String icon) {
                        this.icon = icon;
                    }

                    public static class PagePicBean {
                        private String url;

                        public String getUrl() {
                            return url;
                        }

                        public void setUrl(String url) {
                            this.url = url;
                        }
                    }

                    public static class MediaInfoBean {

                        private String video_orientation;
                        private String name;
                        private String stream_url;
                        private String stream_url_hd;
                        private String h5_url;
                        private String mp4_sd_url;
                        private String mp4_hd_url;
                        private String h265_mp4_hd;
                        private String h265_mp4_ld;
                        private String inch_4_mp4_hd;
                        private String inch_5_mp4_hd;
                        private String inch_5_5_mp4_hd;
                        private String mp4_720p_mp4;
                        private String hevc_mp4_720p;

//                        private int prefetch_type;
//                        private int prefetch_size;
//                        private int act_status;
//                        private String protocol;
//                        private String media_id;
//                        private int origin_total_bitrate;
                        private int duration;
//                        private String next_title;
//                        private int video_publish_time;
//                        private int play_loop_type;
//                        private String author_mid;
//                        private String author_name;
//                        private int is_playlist;
//                        private long get_playlist_id;
//                        private ExtraInfoBean extra_info;
//                        private int has_recommend_video;
//                        private VideoDownloadStrategyBean video_download_strategy;
//                        private int jump_to;
//                        private String online_users;
//                        private int online_users_number;
//                        private int ttl;
//                        private String storage_type;
//                        private int is_keep_current_mblog;
//                        private List<PlayCompletionActionsBean> play_completion_actions;

                        public String getVideo_orientation() {
                            return video_orientation;
                        }

                        public void setVideo_orientation(String video_orientation) {
                            this.video_orientation = video_orientation;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public String getStream_url() {
                            return stream_url;
                        }

                        public void setStream_url(String stream_url) {
                            this.stream_url = stream_url;
                        }

                        public String getStream_url_hd() {
                            return stream_url_hd;
                        }

                        public void setStream_url_hd(String stream_url_hd) {
                            this.stream_url_hd = stream_url_hd;
                        }

                        public String getH5_url() {
                            return h5_url;
                        }

                        public void setH5_url(String h5_url) {
                            this.h5_url = h5_url;
                        }

                        public String getMp4_sd_url() {
                            return mp4_sd_url;
                        }

                        public void setMp4_sd_url(String mp4_sd_url) {
                            this.mp4_sd_url = mp4_sd_url;
                        }

                        public String getMp4_hd_url() {
                            return mp4_hd_url;
                        }

                        public void setMp4_hd_url(String mp4_hd_url) {
                            this.mp4_hd_url = mp4_hd_url;
                        }

                        public String getH265_mp4_hd() {
                            return h265_mp4_hd;
                        }

                        public void setH265_mp4_hd(String h265_mp4_hd) {
                            this.h265_mp4_hd = h265_mp4_hd;
                        }

                        public String getH265_mp4_ld() {
                            return h265_mp4_ld;
                        }

                        public void setH265_mp4_ld(String h265_mp4_ld) {
                            this.h265_mp4_ld = h265_mp4_ld;
                        }

                        public String getInch_4_mp4_hd() {
                            return inch_4_mp4_hd;
                        }

                        public void setInch_4_mp4_hd(String inch_4_mp4_hd) {
                            this.inch_4_mp4_hd = inch_4_mp4_hd;
                        }

                        public String getInch_5_mp4_hd() {
                            return inch_5_mp4_hd;
                        }

                        public void setInch_5_mp4_hd(String inch_5_mp4_hd) {
                            this.inch_5_mp4_hd = inch_5_mp4_hd;
                        }

                        public String getInch_5_5_mp4_hd() {
                            return inch_5_5_mp4_hd;
                        }

                        public void setInch_5_5_mp4_hd(String inch_5_5_mp4_hd) {
                            this.inch_5_5_mp4_hd = inch_5_5_mp4_hd;
                        }

                        public String getMp4_720p_mp4() {
                            return mp4_720p_mp4;
                        }

                        public void setMp4_720p_mp4(String mp4_720p_mp4) {
                            this.mp4_720p_mp4 = mp4_720p_mp4;
                        }

                        public String getHevc_mp4_720p() {
                            return hevc_mp4_720p;
                        }

                        public void setHevc_mp4_720p(String hevc_mp4_720p) {
                            this.hevc_mp4_720p = hevc_mp4_720p;
                        }

                        public int getDuration() {
                            return duration;
                        }

                        public void setDuration(int duration) {
                            this.duration = duration;
                        }
                    }
                }
            }

            public static class CardGroupBean {

                private int card_type;
                private int is_hotweibo;
                private MblogBean mblog;
                private int display_arrow;
                private int show_type;
                private String desc;

                public int getCard_type() {
                    return card_type;
                }

                public void setCard_type(int card_type) {
                    this.card_type = card_type;
                }

                public int getIs_hotweibo() {
                    return is_hotweibo;
                }

                public void setIs_hotweibo(int is_hotweibo) {
                    this.is_hotweibo = is_hotweibo;
                }


                public MblogBean getMblog() {
                    return mblog;
                }

                public void setMblog(MblogBean mblog) {
                    this.mblog = mblog;
                }

                public int getDisplay_arrow() {
                    return display_arrow;
                }

                public void setDisplay_arrow(int display_arrow) {
                    this.display_arrow = display_arrow;
                }

                public int getShow_type() {
                    return show_type;
                }

                public void setShow_type(int show_type) {
                    this.show_type = show_type;
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
