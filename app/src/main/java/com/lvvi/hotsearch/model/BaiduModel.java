package com.lvvi.hotsearch.model;

import java.util.List;

public class BaiduModel {


    private int success;
    private ResultBean result;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
//        private BoardBean board;
//        private List<BoardsBean> boards;
        private List<TopwordsBean> topwords;
//        private List<DescsBean> descs;

//        public BoardBean getBoard() {
//            return board;
//        }
//
//        public void setBoard(BoardBean board) {
//            this.board = board;
//        }
//
//        public List<BoardsBean> getBoards() {
//            return boards;
//        }
//
//        public void setBoards(List<BoardsBean> boards) {
//            this.boards = boards;
//        }

        public List<TopwordsBean> getTopwords() {
            return topwords;
        }

//        public void setTopwords(List<TopwordsBean> topwords) {
//            this.topwords = topwords;
//        }
//
//        public List<DescsBean> getDescs() {
//            return descs;
//        }
//
//        public void setDescs(List<DescsBean> descs) {
//            this.descs = descs;
//        }

        public static class BoardBean {
            private String boardid;
            private String boardname;
            private String boarddesc;
            private String boardicon;
            private String url;
            private String hasword;
            private String logid;
            private String parentid;
            private String isdivsrch;
            private String flag;
            private String baselinkid;
            private String specialcomputer;
            private String tpl_var;
            private String templateid;
            private String mobile_templateid;
            private String appearance;
            private String display_num;
            private String hasdetail;
            private String haspage;
            private String logtype;
            private List<List<Integer>> linkarr;

            public String getBoardid() {
                return boardid;
            }

            public void setBoardid(String boardid) {
                this.boardid = boardid;
            }

            public String getBoardname() {
                return boardname;
            }

            public void setBoardname(String boardname) {
                this.boardname = boardname;
            }

            public String getBoarddesc() {
                return boarddesc;
            }

            public void setBoarddesc(String boarddesc) {
                this.boarddesc = boarddesc;
            }

            public String getBoardicon() {
                return boardicon;
            }

            public void setBoardicon(String boardicon) {
                this.boardicon = boardicon;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getHasword() {
                return hasword;
            }

            public void setHasword(String hasword) {
                this.hasword = hasword;
            }

            public String getLogid() {
                return logid;
            }

            public void setLogid(String logid) {
                this.logid = logid;
            }

            public String getParentid() {
                return parentid;
            }

            public void setParentid(String parentid) {
                this.parentid = parentid;
            }

            public String getIsdivsrch() {
                return isdivsrch;
            }

            public void setIsdivsrch(String isdivsrch) {
                this.isdivsrch = isdivsrch;
            }

            public String getFlag() {
                return flag;
            }

            public void setFlag(String flag) {
                this.flag = flag;
            }

            public String getBaselinkid() {
                return baselinkid;
            }

            public void setBaselinkid(String baselinkid) {
                this.baselinkid = baselinkid;
            }

            public String getSpecialcomputer() {
                return specialcomputer;
            }

            public void setSpecialcomputer(String specialcomputer) {
                this.specialcomputer = specialcomputer;
            }

            public String getTpl_var() {
                return tpl_var;
            }

            public void setTpl_var(String tpl_var) {
                this.tpl_var = tpl_var;
            }

            public String getTemplateid() {
                return templateid;
            }

            public void setTemplateid(String templateid) {
                this.templateid = templateid;
            }

            public String getMobile_templateid() {
                return mobile_templateid;
            }

            public void setMobile_templateid(String mobile_templateid) {
                this.mobile_templateid = mobile_templateid;
            }

            public String getAppearance() {
                return appearance;
            }

            public void setAppearance(String appearance) {
                this.appearance = appearance;
            }

            public String getDisplay_num() {
                return display_num;
            }

            public void setDisplay_num(String display_num) {
                this.display_num = display_num;
            }

            public String getHasdetail() {
                return hasdetail;
            }

            public void setHasdetail(String hasdetail) {
                this.hasdetail = hasdetail;
            }

            public String getHaspage() {
                return haspage;
            }

            public void setHaspage(String haspage) {
                this.haspage = haspage;
            }

            public String getLogtype() {
                return logtype;
            }

            public void setLogtype(String logtype) {
                this.logtype = logtype;
            }

            public List<List<Integer>> getLinkarr() {
                return linkarr;
            }

            public void setLinkarr(List<List<Integer>> linkarr) {
                this.linkarr = linkarr;
            }
        }

        public static class BoardsBean {
            private String boardid;
            private String boardname;
            private String categoryid;
            private String url;

            public String getBoardid() {
                return boardid;
            }

            public void setBoardid(String boardid) {
                this.boardid = boardid;
            }

            public String getBoardname() {
                return boardname;
            }

            public void setBoardname(String boardname) {
                this.boardname = boardname;
            }

            public String getCategoryid() {
                return categoryid;
            }

            public void setCategoryid(String categoryid) {
                this.categoryid = categoryid;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class TopwordsBean {
            private String keyword;
            private int searches;
            private int changeRate;
            private String isNew;
            private String trend;
            private String gentime;

            public String getKeyword() {
                return keyword;
            }

            public void setKeyword(String keyword) {
                this.keyword = keyword;
            }

            public int getSearches() {
                return searches;
            }

            public void setSearches(int searches) {
                this.searches = searches;
            }

            public int getChangeRate() {
                return changeRate;
            }

            public void setChangeRate(int changeRate) {
                this.changeRate = changeRate;
            }

            public String getIsNew() {
                return isNew;
            }

            public void setIsNew(String isNew) {
                this.isNew = isNew;
            }

            public String getTrend() {
                return trend;
            }

            public void setTrend(String trend) {
                this.trend = trend;
            }

            public String getGentime() {
                return gentime;
            }

            public void setGentime(String gentime) {
                this.gentime = gentime;
            }
        }

        public static class DescsBean {
            private String name;
            private ContentBean content;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public ContentBean getContent() {
                return content;
            }

            public void setContent(ContentBean content) {
                this.content = content;
            }

            public static class ContentBean {
                private String morelink;
                private List<DataBean> data;

                public String getMorelink() {
                    return morelink;
                }

                public void setMorelink(String morelink) {
                    this.morelink = morelink;
                }

                public List<DataBean> getData() {
                    return data;
                }

                public void setData(List<DataBean> data) {
                    this.data = data;
                }

                public static class DataBean {
                    private String title;
                    private String link;
                    private String originlink;
                    private String description;
                    private String pubDate;
                    private String source;
                    private String image;
                    private String postTime;

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public String getLink() {
                        return link;
                    }

                    public void setLink(String link) {
                        this.link = link;
                    }

                    public String getOriginlink() {
                        return originlink;
                    }

                    public void setOriginlink(String originlink) {
                        this.originlink = originlink;
                    }

                    public String getDescription() {
                        return description;
                    }

                    public void setDescription(String description) {
                        this.description = description;
                    }

                    public String getPubDate() {
                        return pubDate;
                    }

                    public void setPubDate(String pubDate) {
                        this.pubDate = pubDate;
                    }

                    public String getSource() {
                        return source;
                    }

                    public void setSource(String source) {
                        this.source = source;
                    }

                    public String getImage() {
                        return image;
                    }

                    public void setImage(String image) {
                        this.image = image;
                    }

                    public String getPostTime() {
                        return postTime;
                    }

                    public void setPostTime(String postTime) {
                        this.postTime = postTime;
                    }
                }
            }
        }
    }
}
