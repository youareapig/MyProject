package bean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
public class ClassifyBean {



    private int code;
    private String message;


    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String cat_id;
        private String module_id;
        private String parentid;
        private Object arrparentid;
        private Object is_child;
        private Object arrchildid;
        private String listorder;
        private String templates;
        private String show_template;
        private String cat_name;
        private String cat_dir;
        private String link_url;
        private String letter;
        private Object item;
        private Object property;
        private String seo_title;
        private String seo_keywords;
        private String seo_desc;
        private String group_list;
        private String group_show;
        private String group_add;
        private String tbid;
        private Object pic;
        /**
         * cat_id : 2
         * module_id : 1
         * parentid : 1
         * arrparentid : null
         * is_child : null
         * arrchildid : null
         * listorder : 10
         * templates : 默认模板
         * show_template : 默认模板
         * cat_name : 内饰用品
         * cat_dir :
         * link_url :
         * letter :
         * item : null
         * property : null
         * seo_title :
         * seo_keywords :
         * seo_desc :
         * group_list : 1,2,3,4,5,6,7
         * group_show : 1,2,3,4,5,6,7
         * group_add : 1,2,3,4,5,6,7
         * tbid : 0
         * pic : null
         * children1 : [{"cat_id":"3","module_id":"1","parentid":"2","arrparentid":null,"is_child":null,"arrchildid":null,"listorder":"10","templates":"默认模板","show_template":"默认模板","cat_name":"装饰灯","cat_dir":"","link_url":"","letter":"","item":null,"property":null,"seo_title":"","seo_keywords":"","seo_desc":"","group_list":"1,2,3,4,5,6,7","group_show":"1,2,3,4,5,6,7","group_add":"1,2,3,4,5,6,7","tbid":"0","pic":null},{"cat_id":"4","module_id":"1","parentid":"2","arrparentid":null,"is_child":null,"arrchildid":null,"listorder":"10","templates":"默认模板","show_template":"默认模板","cat_name":"儿童座椅","cat_dir":"","link_url":"","letter":"","item":null,"property":null,"seo_title":"","seo_keywords":"","seo_desc":"","group_list":"1,2,3,4,5,6,7","group_show":"1,2,3,4,5,6,7","group_add":"1,2,3,4,5,6,7","tbid":"0","pic":null},{"cat_id":"5","module_id":"1","parentid":"2","arrparentid":null,"is_child":null,"arrchildid":null,"listorder":"10","templates":"默认模板","show_template":"默认模板","cat_name":"方向盘装饰","cat_dir":"","link_url":"","letter":"","item":null,"property":null,"seo_title":"","seo_keywords":"","seo_desc":"","group_list":"1,2,3,4,5,6,7","group_show":"1,2,3,4,5,6,7","group_add":"1,2,3,4,5,6,7","tbid":"0","pic":null},{"cat_id":"6","module_id":"1","parentid":"2","arrparentid":null,"is_child":null,"arrchildid":null,"listorder":"10","templates":"默认模板","show_template":"默认模板","cat_name":"方垫","cat_dir":"","link_url":"","letter":"","item":null,"property":null,"seo_title":"","seo_keywords":"","seo_desc":"","group_list":"1,2,3,4,5,6,7","group_show":"1,2,3,4,5,6,7","group_add":"1,2,3,4,5,6,7","tbid":"0","pic":null},{"cat_id":"31","module_id":"1","parentid":"2","arrparentid":null,"is_child":null,"arrchildid":null,"listorder":"10","templates":null,"show_template":"默认模板","cat_name":"123","cat_dir":null,"link_url":"","letter":"1","item":null,"property":null,"seo_title":"","seo_keywords":"","seo_desc":"","group_list":"1,2,3,4,5,6,7,8","group_show":"1,2,3,4,5,6,7,8","group_add":"1,2,3,4,5,6,7,8","tbid":"0","pic":"Uploads/Category/2016-11-15//582aaeb201d95.jpg"}]
         */

        private List<ChildrenBean> children;

        public String getCat_id() {
            return cat_id;
        }

        public void setCat_id(String cat_id) {
            this.cat_id = cat_id;
        }

        public String getModule_id() {
            return module_id;
        }

        public void setModule_id(String module_id) {
            this.module_id = module_id;
        }

        public String getParentid() {
            return parentid;
        }

        public void setParentid(String parentid) {
            this.parentid = parentid;
        }

        public Object getArrparentid() {
            return arrparentid;
        }

        public void setArrparentid(Object arrparentid) {
            this.arrparentid = arrparentid;
        }

        public Object getIs_child() {
            return is_child;
        }

        public void setIs_child(Object is_child) {
            this.is_child = is_child;
        }

        public Object getArrchildid() {
            return arrchildid;
        }

        public void setArrchildid(Object arrchildid) {
            this.arrchildid = arrchildid;
        }

        public String getListorder() {
            return listorder;
        }

        public void setListorder(String listorder) {
            this.listorder = listorder;
        }

        public String getTemplates() {
            return templates;
        }

        public void setTemplates(String templates) {
            this.templates = templates;
        }

        public String getShow_template() {
            return show_template;
        }

        public void setShow_template(String show_template) {
            this.show_template = show_template;
        }

        public String getCat_name() {
            return cat_name;
        }

        public void setCat_name(String cat_name) {
            this.cat_name = cat_name;
        }

        public String getCat_dir() {
            return cat_dir;
        }

        public void setCat_dir(String cat_dir) {
            this.cat_dir = cat_dir;
        }

        public String getLink_url() {
            return link_url;
        }

        public void setLink_url(String link_url) {
            this.link_url = link_url;
        }

        public String getLetter() {
            return letter;
        }

        public void setLetter(String letter) {
            this.letter = letter;
        }

        public Object getItem() {
            return item;
        }

        public void setItem(Object item) {
            this.item = item;
        }

        public Object getProperty() {
            return property;
        }

        public void setProperty(Object property) {
            this.property = property;
        }

        public String getSeo_title() {
            return seo_title;
        }

        public void setSeo_title(String seo_title) {
            this.seo_title = seo_title;
        }

        public String getSeo_keywords() {
            return seo_keywords;
        }

        public void setSeo_keywords(String seo_keywords) {
            this.seo_keywords = seo_keywords;
        }

        public String getSeo_desc() {
            return seo_desc;
        }

        public void setSeo_desc(String seo_desc) {
            this.seo_desc = seo_desc;
        }

        public String getGroup_list() {
            return group_list;
        }

        public void setGroup_list(String group_list) {
            this.group_list = group_list;
        }

        public String getGroup_show() {
            return group_show;
        }

        public void setGroup_show(String group_show) {
            this.group_show = group_show;
        }

        public String getGroup_add() {
            return group_add;
        }

        public void setGroup_add(String group_add) {
            this.group_add = group_add;
        }

        public String getTbid() {
            return tbid;
        }

        public void setTbid(String tbid) {
            this.tbid = tbid;
        }

        public Object getPic() {
            return pic;
        }

        public void setPic(Object pic) {
            this.pic = pic;
        }

        public List<ChildrenBean> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBean> children) {
            this.children = children;
        }

        public static class ChildrenBean {
            private String cat_id;
            private String module_id;
            private String parentid;
            private Object arrparentid;
            private Object is_child;
            private Object arrchildid;
            private String listorder;
            private String templates;
            private String show_template;
            private String cat_name;
            private String cat_dir;
            private String link_url;
            private String letter;
            private Object item;
            private Object property;
            private String seo_title;
            private String seo_keywords;
            private String seo_desc;
            private String group_list;
            private String group_show;
            private String group_add;
            private String tbid;
            private Object pic;
            /**
             * cat_id : 3
             * module_id : 1
             * parentid : 2
             * arrparentid : null
             * is_child : null
             * arrchildid : null
             * listorder : 10
             * templates : 默认模板
             * show_template : 默认模板
             * cat_name : 装饰灯
             * cat_dir :
             * link_url :
             * letter :
             * item : null
             * property : null
             * seo_title :
             * seo_keywords :
             * seo_desc :
             * group_list : 1,2,3,4,5,6,7
             * group_show : 1,2,3,4,5,6,7
             * group_add : 1,2,3,4,5,6,7
             * tbid : 0
             * pic : null
             */

            private List<Children1Bean> children1;

            public String getCat_id() {
                return cat_id;
            }

            public void setCat_id(String cat_id) {
                this.cat_id = cat_id;
            }

            public String getModule_id() {
                return module_id;
            }

            public void setModule_id(String module_id) {
                this.module_id = module_id;
            }

            public String getParentid() {
                return parentid;
            }

            public void setParentid(String parentid) {
                this.parentid = parentid;
            }

            public Object getArrparentid() {
                return arrparentid;
            }

            public void setArrparentid(Object arrparentid) {
                this.arrparentid = arrparentid;
            }

            public Object getIs_child() {
                return is_child;
            }

            public void setIs_child(Object is_child) {
                this.is_child = is_child;
            }

            public Object getArrchildid() {
                return arrchildid;
            }

            public void setArrchildid(Object arrchildid) {
                this.arrchildid = arrchildid;
            }

            public String getListorder() {
                return listorder;
            }

            public void setListorder(String listorder) {
                this.listorder = listorder;
            }

            public String getTemplates() {
                return templates;
            }

            public void setTemplates(String templates) {
                this.templates = templates;
            }

            public String getShow_template() {
                return show_template;
            }

            public void setShow_template(String show_template) {
                this.show_template = show_template;
            }

            public String getCat_name() {
                return cat_name;
            }

            public void setCat_name(String cat_name) {
                this.cat_name = cat_name;
            }

            public String getCat_dir() {
                return cat_dir;
            }

            public void setCat_dir(String cat_dir) {
                this.cat_dir = cat_dir;
            }

            public String getLink_url() {
                return link_url;
            }

            public void setLink_url(String link_url) {
                this.link_url = link_url;
            }

            public String getLetter() {
                return letter;
            }

            public void setLetter(String letter) {
                this.letter = letter;
            }

            public Object getItem() {
                return item;
            }

            public void setItem(Object item) {
                this.item = item;
            }

            public Object getProperty() {
                return property;
            }

            public void setProperty(Object property) {
                this.property = property;
            }

            public String getSeo_title() {
                return seo_title;
            }

            public void setSeo_title(String seo_title) {
                this.seo_title = seo_title;
            }

            public String getSeo_keywords() {
                return seo_keywords;
            }

            public void setSeo_keywords(String seo_keywords) {
                this.seo_keywords = seo_keywords;
            }

            public String getSeo_desc() {
                return seo_desc;
            }

            public void setSeo_desc(String seo_desc) {
                this.seo_desc = seo_desc;
            }

            public String getGroup_list() {
                return group_list;
            }

            public void setGroup_list(String group_list) {
                this.group_list = group_list;
            }

            public String getGroup_show() {
                return group_show;
            }

            public void setGroup_show(String group_show) {
                this.group_show = group_show;
            }

            public String getGroup_add() {
                return group_add;
            }

            public void setGroup_add(String group_add) {
                this.group_add = group_add;
            }

            public String getTbid() {
                return tbid;
            }

            public void setTbid(String tbid) {
                this.tbid = tbid;
            }

            public Object getPic() {
                return pic;
            }

            public void setPic(Object pic) {
                this.pic = pic;
            }

            public List<Children1Bean> getChildren1() {
                return children1;
            }

            public void setChildren1(List<Children1Bean> children1) {
                this.children1 = children1;
            }

            public static class Children1Bean {
                private String cat_id;
                private String module_id;
                private String parentid;
                private Object arrparentid;
                private Object is_child;
                private Object arrchildid;
                private String listorder;
                private String templates;
                private String show_template;
                private String cat_name;
                private String cat_dir;
                private String link_url;
                private String letter;
                private Object item;
                private Object property;
                private String seo_title;
                private String seo_keywords;
                private String seo_desc;
                private String group_list;
                private String group_show;
                private String group_add;
                private String tbid;
                private Object pic;

                public String getCat_id() {
                    return cat_id;
                }

                public void setCat_id(String cat_id) {
                    this.cat_id = cat_id;
                }

                public String getModule_id() {
                    return module_id;
                }

                public void setModule_id(String module_id) {
                    this.module_id = module_id;
                }

                public String getParentid() {
                    return parentid;
                }

                public void setParentid(String parentid) {
                    this.parentid = parentid;
                }

                public Object getArrparentid() {
                    return arrparentid;
                }

                public void setArrparentid(Object arrparentid) {
                    this.arrparentid = arrparentid;
                }

                public Object getIs_child() {
                    return is_child;
                }

                public void setIs_child(Object is_child) {
                    this.is_child = is_child;
                }

                public Object getArrchildid() {
                    return arrchildid;
                }

                public void setArrchildid(Object arrchildid) {
                    this.arrchildid = arrchildid;
                }

                public String getListorder() {
                    return listorder;
                }

                public void setListorder(String listorder) {
                    this.listorder = listorder;
                }

                public String getTemplates() {
                    return templates;
                }

                public void setTemplates(String templates) {
                    this.templates = templates;
                }

                public String getShow_template() {
                    return show_template;
                }

                public void setShow_template(String show_template) {
                    this.show_template = show_template;
                }

                public String getCat_name() {
                    return cat_name;
                }

                public void setCat_name(String cat_name) {
                    this.cat_name = cat_name;
                }

                public String getCat_dir() {
                    return cat_dir;
                }

                public void setCat_dir(String cat_dir) {
                    this.cat_dir = cat_dir;
                }

                public String getLink_url() {
                    return link_url;
                }

                public void setLink_url(String link_url) {
                    this.link_url = link_url;
                }

                public String getLetter() {
                    return letter;
                }

                public void setLetter(String letter) {
                    this.letter = letter;
                }

                public Object getItem() {
                    return item;
                }

                public void setItem(Object item) {
                    this.item = item;
                }

                public Object getProperty() {
                    return property;
                }

                public void setProperty(Object property) {
                    this.property = property;
                }

                public String getSeo_title() {
                    return seo_title;
                }

                public void setSeo_title(String seo_title) {
                    this.seo_title = seo_title;
                }

                public String getSeo_keywords() {
                    return seo_keywords;
                }

                public void setSeo_keywords(String seo_keywords) {
                    this.seo_keywords = seo_keywords;
                }

                public String getSeo_desc() {
                    return seo_desc;
                }

                public void setSeo_desc(String seo_desc) {
                    this.seo_desc = seo_desc;
                }

                public String getGroup_list() {
                    return group_list;
                }

                public void setGroup_list(String group_list) {
                    this.group_list = group_list;
                }

                public String getGroup_show() {
                    return group_show;
                }

                public void setGroup_show(String group_show) {
                    this.group_show = group_show;
                }

                public String getGroup_add() {
                    return group_add;
                }

                public void setGroup_add(String group_add) {
                    this.group_add = group_add;
                }

                public String getTbid() {
                    return tbid;
                }

                public void setTbid(String tbid) {
                    this.tbid = tbid;
                }

                public Object getPic() {
                    return pic;
                }

                public void setPic(Object pic) {
                    this.pic = pic;
                }
            }
        }
    }
}
