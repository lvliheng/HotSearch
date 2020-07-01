package com.lvvi.hotsearch.model;

import java.util.List;

public class WeiboModel {

    private int ok;
    private DataBean data;

    public int getOk() {
        return ok;
    }

    public void setOk(int ok) {
        this.ok = ok;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private PageInfoBean pageInfo;
        private Object banners;
        private String scheme;
        private List<CardsBean> cards;

        public PageInfoBean getPageInfo() {
            return pageInfo;
        }

        public void setPageInfo(PageInfoBean pageInfo) {
            this.pageInfo = pageInfo;
        }

        public Object getBanners() {
            return banners;
        }

        public void setBanners(Object banners) {
            this.banners = banners;
        }

        public String getScheme() {
            return scheme;
        }

        public void setScheme(String scheme) {
            this.scheme = scheme;
        }

        public List<CardsBean> getCards() {
            return cards;
        }

        public void setCards(List<CardsBean> cards) {
            this.cards = cards;
        }

        public static class PageInfoBean {

            private int page_size;
            private int can_shared;
            private BannerInfoBean banner_info;
            private String containerid;
            private int show_style;
            private int total;
            private String page_title;
            private int starttime;
            private String title_top;
            private String v_p;
            private String page_type;
            private String nick;
            private Object background;
            private int attitudes_count;
            private int attitudes_status;
            private Object page;
            private List<ToolbarMenusBean> toolbar_menus;
            private List<?> cardlist_menus;
            private List<ButtonsBean> buttons;

            public int getPage_size() {
                return page_size;
            }

            public void setPage_size(int page_size) {
                this.page_size = page_size;
            }

            public int getCan_shared() {
                return can_shared;
            }

            public void setCan_shared(int can_shared) {
                this.can_shared = can_shared;
            }

            public BannerInfoBean getBanner_info() {
                return banner_info;
            }

            public void setBanner_info(BannerInfoBean banner_info) {
                this.banner_info = banner_info;
            }

            public String getContainerid() {
                return containerid;
            }

            public void setContainerid(String containerid) {
                this.containerid = containerid;
            }

            public int getShow_style() {
                return show_style;
            }

            public void setShow_style(int show_style) {
                this.show_style = show_style;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public String getPage_title() {
                return page_title;
            }

            public void setPage_title(String page_title) {
                this.page_title = page_title;
            }

            public int getStarttime() {
                return starttime;
            }

            public void setStarttime(int starttime) {
                this.starttime = starttime;
            }

            public String getTitle_top() {
                return title_top;
            }

            public void setTitle_top(String title_top) {
                this.title_top = title_top;
            }

            public String getV_p() {
                return v_p;
            }

            public void setV_p(String v_p) {
                this.v_p = v_p;
            }

            public String getPage_type() {
                return page_type;
            }

            public void setPage_type(String page_type) {
                this.page_type = page_type;
            }

            public String getNick() {
                return nick;
            }

            public void setNick(String nick) {
                this.nick = nick;
            }

            public Object getBackground() {
                return background;
            }

            public void setBackground(Object background) {
                this.background = background;
            }

            public int getAttitudes_count() {
                return attitudes_count;
            }

            public void setAttitudes_count(int attitudes_count) {
                this.attitudes_count = attitudes_count;
            }

            public int getAttitudes_status() {
                return attitudes_status;
            }

            public void setAttitudes_status(int attitudes_status) {
                this.attitudes_status = attitudes_status;
            }

            public Object getPage() {
                return page;
            }

            public void setPage(Object page) {
                this.page = page;
            }

            public List<ToolbarMenusBean> getToolbar_menus() {
                return toolbar_menus;
            }

            public void setToolbar_menus(List<ToolbarMenusBean> toolbar_menus) {
                this.toolbar_menus = toolbar_menus;
            }

            public List<?> getCardlist_menus() {
                return cardlist_menus;
            }

            public void setCardlist_menus(List<?> cardlist_menus) {
                this.cardlist_menus = cardlist_menus;
            }

            public List<ButtonsBean> getButtons() {
                return buttons;
            }

            public void setButtons(List<ButtonsBean> buttons) {
                this.buttons = buttons;
            }

            public static class BannerInfoBean {

                private int auto_flow;
                private int height;
                private int flow_gap;
                private int card_type;
                private int width;
                private List<PicItemsBean> pic_items;

                public int getAuto_flow() {
                    return auto_flow;
                }

                public void setAuto_flow(int auto_flow) {
                    this.auto_flow = auto_flow;
                }

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }

                public int getFlow_gap() {
                    return flow_gap;
                }

                public void setFlow_gap(int flow_gap) {
                    this.flow_gap = flow_gap;
                }

                public int getCard_type() {
                    return card_type;
                }

                public void setCard_type(int card_type) {
                    this.card_type = card_type;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public List<PicItemsBean> getPic_items() {
                    return pic_items;
                }

                public void setPic_items(List<PicItemsBean> pic_items) {
                    this.pic_items = pic_items;
                }

                public static class PicItemsBean {

                    private String scheme;
                    private String pic;

                    public String getScheme() {
                        return scheme;
                    }

                    public void setScheme(String scheme) {
                        this.scheme = scheme;
                    }

                    public String getPic() {
                        return pic;
                    }

                    public void setPic(String pic) {
                        this.pic = pic;
                    }
                }
            }

            public static class ToolbarMenusBean {

                private String title_color_dark;
                private String pic;
                private int skip_format;
                private ParamsBean params;
                private ActionlogBean actionlog;
                private String name;
                private String title_color;
                private String type;
                private String scheme;

                public String getTitle_color_dark() {
                    return title_color_dark;
                }

                public void setTitle_color_dark(String title_color_dark) {
                    this.title_color_dark = title_color_dark;
                }

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public int getSkip_format() {
                    return skip_format;
                }

                public void setSkip_format(int skip_format) {
                    this.skip_format = skip_format;
                }

                public ParamsBean getParams() {
                    return params;
                }

                public void setParams(ParamsBean params) {
                    this.params = params;
                }

                public ActionlogBean getActionlog() {
                    return actionlog;
                }

                public void setActionlog(ActionlogBean actionlog) {
                    this.actionlog = actionlog;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getTitle_color() {
                    return title_color;
                }

                public void setTitle_color(String title_color) {
                    this.title_color = title_color;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getScheme() {
                    return scheme;
                }

                public void setScheme(String scheme) {
                    this.scheme = scheme;
                }

                public static class ParamsBean {

                    private String scheme;

                    public String getScheme() {
                        return scheme;
                    }

                    public void setScheme(String scheme) {
                        this.scheme = scheme;
                    }
                }

                public static class ActionlogBean {

                    private int act_code;
                    private String ext;
                    private String luicode;
                    private String fid;
                    private String lfid;

                    public int getAct_code() {
                        return act_code;
                    }

                    public void setAct_code(int act_code) {
                        this.act_code = act_code;
                    }

                    public String getExt() {
                        return ext;
                    }

                    public void setExt(String ext) {
                        this.ext = ext;
                    }

                    public String getLuicode() {
                        return luicode;
                    }

                    public void setLuicode(String luicode) {
                        this.luicode = luicode;
                    }

                    public String getFid() {
                        return fid;
                    }

                    public void setFid(String fid) {
                        this.fid = fid;
                    }

                    public String getLfid() {
                        return lfid;
                    }

                    public void setLfid(String lfid) {
                        this.lfid = lfid;
                    }
                }
            }

            public static class ButtonsBean {

                private String type;
                private String sub_type;
                private String name;
                private ParamsBeanX params;
                private String scheme;

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getSub_type() {
                    return sub_type;
                }

                public void setSub_type(String sub_type) {
                    this.sub_type = sub_type;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public ParamsBeanX getParams() {
                    return params;
                }

                public void setParams(ParamsBeanX params) {
                    this.params = params;
                }

                public String getScheme() {
                    return scheme;
                }

                public void setScheme(String scheme) {
                    this.scheme = scheme;
                }

                public static class ParamsBeanX {

                    private String id;
                    private String type;

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }
                }
            }
        }

        public static class CardsBean {

            private int show_type;
            private String title;
            private int card_type;
            private String itemid;
            private String desc_extr;
            private String pic;
            private int display_arrow;
            private ActionlogBeanX actionlog;
            private String scheme;
            private String desc;
            private List<CardGroupBean> card_group;

            public int getShow_type() {
                return show_type;
            }

            public void setShow_type(int show_type) {
                this.show_type = show_type;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getCard_type() {
                return card_type;
            }

            public void setCard_type(int card_type) {
                this.card_type = card_type;
            }

            public String getItemid() {
                return itemid;
            }

            public void setItemid(String itemid) {
                this.itemid = itemid;
            }

            public String getDesc_extr() {
                return desc_extr;
            }

            public void setDesc_extr(String desc_extr) {
                this.desc_extr = desc_extr;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public int getDisplay_arrow() {
                return display_arrow;
            }

            public void setDisplay_arrow(int display_arrow) {
                this.display_arrow = display_arrow;
            }

            public ActionlogBeanX getActionlog() {
                return actionlog;
            }

            public void setActionlog(ActionlogBeanX actionlog) {
                this.actionlog = actionlog;
            }

            public String getScheme() {
                return scheme;
            }

            public void setScheme(String scheme) {
                this.scheme = scheme;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public List<CardGroupBean> getCard_group() {
                return card_group;
            }

            public void setCard_group(List<CardGroupBean> card_group) {
                this.card_group = card_group;
            }

            public static class ActionlogBeanX {

                private int act_code;
                private String ext;
                private String luicode;
                private String fid;
                private String lfid;
                private String uicode;

                public int getAct_code() {
                    return act_code;
                }

                public void setAct_code(int act_code) {
                    this.act_code = act_code;
                }

                public String getExt() {
                    return ext;
                }

                public void setExt(String ext) {
                    this.ext = ext;
                }

                public String getLuicode() {
                    return luicode;
                }

                public void setLuicode(String luicode) {
                    this.luicode = luicode;
                }

                public String getFid() {
                    return fid;
                }

                public void setFid(String fid) {
                    this.fid = fid;
                }

                public String getLfid() {
                    return lfid;
                }

                public void setLfid(String lfid) {
                    this.lfid = lfid;
                }

                public String getUicode() {
                    return uicode;
                }

                public void setUicode(String uicode) {
                    this.uicode = uicode;
                }
            }

            public static class CardGroupBean {

                private int card_type;
                private String type;
                private String pic;
                private String itemid;
                private String scheme;
                private String desc;
                private String icon;
                private String desc_extr;

                private String isDetailShow;
                private String detailText;
                private String pagePic;
                private String pageTitle;
                private String pageUrl;
                private String pageTypeIcon;
                private String videoUrl;
                private int duration;
                private List<WeiboDetailModel.DataBean.CardsBean.MblogBean.PicsBean> pics;

                private String objectId;

                public int getCard_type() {
                    return card_type;
                }

                public void setCard_type(int card_type) {
                    this.card_type = card_type;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public String getItemid() {
                    return itemid;
                }

                public void setItemid(String itemid) {
                    this.itemid = itemid;
                }


                public String getScheme() {
                    return scheme;
                }

                public void setScheme(String scheme) {
                    this.scheme = scheme;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getIcon() {
                    return icon == null ? "" : icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }

                public String getDesc_extr() {
                    return desc_extr == null ? "" : desc_extr;
                }

                public void setDesc_extr(String desc_extr) {
                    this.desc_extr = desc_extr;
                }

                public String getIsDetailShow() {
                    return isDetailShow;
                }

                public void setIsDetailShow(String isDetailShow) {
                    this.isDetailShow = isDetailShow;
                }

                public String getDetailText() {
                    return detailText;
                }

                public void setDetailText(String detailText) {
                    this.detailText = detailText;
                }

                public String getPagePic() {
                    return pagePic;
                }

                public void setPagePic(String pagePic) {
                    this.pagePic = pagePic;
                }

                public String getPageTitle() {
                    return pageTitle;
                }

                public void setPageTitle(String pageTitle) {
                    this.pageTitle = pageTitle;
                }

                public String getPageUrl() {
                    return pageUrl;
                }

                public void setPageUrl(String pageUrl) {
                    this.pageUrl = pageUrl;
                }

                public String getPageTypeIcon() {
                    return pageTypeIcon;
                }

                public void setPageTypeIcon(String pageTypeIcon) {
                    this.pageTypeIcon = pageTypeIcon;
                }

                public int getDuration() {
                    return duration;
                }

                public void setDuration(int duration) {
                    this.duration = duration;
                }

                public String getVideoUrl() {
                    return videoUrl;
                }

                public void setVideoUrl(String videoUrl) {
                    this.videoUrl = videoUrl;
                }

                public List<WeiboDetailModel.DataBean.CardsBean.MblogBean.PicsBean> getPics() {
                    return pics;
                }

                public void setPics(List<WeiboDetailModel.DataBean.CardsBean.MblogBean.PicsBean> pics) {
                    this.pics = pics;
                }

                public String getObjectId() {
                    return objectId;
                }

                public void setObjectId(String objectId) {
                    this.objectId = objectId;
                }
            }
        }
    }
}
