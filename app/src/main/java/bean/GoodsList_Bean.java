package bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/10/31 0031.
 */
public class GoodsList_Bean implements Serializable{

    /**
     * code : 1000
     * message : 数据查询成功
     * data : [{"goods_id":"1","goods_name":"1酷斯特/KUST 雪佛兰科鲁兹 刹车灯 奥烁40灯高亮LED刹车灯灯泡 09-14款老科 【40粒灯珠红光】单个装专车专用 ","goods_sn":"1479437393695583","cat_id":"3","mycatid":null,"areaid":"110000","level":"0","type_id":"0","goods_desc":"                       ","elite":"0","goods_weight":"0.00","goods_order":"10","fee":"0","introduce":null,"brand_id":"1","shop_price":"58.00","amount":"100","unit":null,"tag":null,"keyword":null,"pptword":null,"hints":null,"orders":null,"sales":null,"is_cod":"2","status":"3","comments":null,"thumb":"Uploads/Goodspic/2016-11-18//582e6c5169888.jpg","sm_thumb":"Uploads/Goodspic/2016-11-18//sm_582e6c5169888.jpg","relate_name":null,"relate_id":null,"relate_title":null,"n1":null,"n2":null,"n3":null,"v1":null,"v2":null,"v3":null,"express_1":null,"express_name_1":null,"fee_start_1":"0.00","fee_step_1":"0.00","express_2":null,"express_name_2":null,"fee_start_2":"0.00","fee_step_2":"0.00","express_3":"0","express_name_3":null,"fee_start_3":"0.00","fee_step_3":"0.00","username":"admin","grouid":null,"company":null,"vip":null,"validated":"0","truename":null,"telephone":null,"mobile":null,"address":null,"email":null,"msn":null,"qq":null,"ali":null,"skepy":null,"editor":null,"edittime":null,"editate":null,"addtime":"1479437393","adddate":null,"ip":"127.0.0.1","template":null,"linkurl":null,"filepath":null,"note":null,"service_fee":"0.00","is_best":"1","is_new":"1","is_host":"0"},{"goods_id":"2","goods_name":"NFS 丰田汉兰达 示宽灯 15-16款 【冰蓝款】一对装独特颜色 安装简单 超亮","goods_sn":"1479437743954888","cat_id":"3","mycatid":null,"areaid":"110000","level":"0","type_id":"0","goods_desc":"                       ","elite":"0","goods_weight":"0.00","goods_order":"10","fee":"0","introduce":null,"brand_id":"1","shop_price":"48.00","amount":"100","unit":null,"tag":null,"keyword":null,"pptword":null,"hints":null,"orders":null,"sales":null,"is_cod":"2","status":"3","comments":null,"thumb":"Uploads/Goodspic/2016-11-18//582e6daf415db.jpg","sm_thumb":"Uploads/Goodspic/2016-11-18//sm_582e6daf415db.jpg","relate_name":null,"relate_id":null,"relate_title":null,"n1":null,"n2":null,"n3":null,"v1":null,"v2":null,"v3":null,"express_1":null,"express_name_1":null,"fee_start_1":"0.00","fee_step_1":"0.00","express_2":null,"express_name_2":null,"fee_start_2":"0.00","fee_step_2":"0.00","express_3":"0","express_name_3":null,"fee_start_3":"0.00","fee_step_3":"0.00","username":"admin","grouid":null,"company":null,"vip":null,"validated":"0","truename":null,"telephone":null,"mobile":null,"address":null,"email":null,"msn":null,"qq":null,"ali":null,"skepy":null,"editor":null,"edittime":null,"editate":null,"addtime":"1479437743","adddate":null,"ip":"127.0.0.1","template":null,"linkurl":null,"filepath":null,"note":null,"service_fee":"0.00","is_best":"1","is_new":"1","is_host":"0"}]
     */

    private int code;
    private String message;
    /**
     * goods_id : 1
     * goods_name : 1酷斯特/KUST 雪佛兰科鲁兹 刹车灯 奥烁40灯高亮LED刹车灯灯泡 09-14款老科 【40粒灯珠红光】单个装专车专用
     * goods_sn : 1479437393695583
     * cat_id : 3
     * mycatid : null
     * areaid : 110000
     * level : 0
     * type_id : 0
     * goods_desc :
     * elite : 0
     * goods_weight : 0.00
     * goods_order : 10
     * fee : 0
     * introduce : null
     * brand_id : 1
     * shop_price : 58.00
     * amount : 100
     * unit : null
     * tag : null
     * keyword : null
     * pptword : null
     * hints : null
     * orders : null
     * sales : null
     * is_cod : 2
     * status : 3
     * comments : null
     * thumb : Uploads/Goodspic/2016-11-18//582e6c5169888.jpg
     * sm_thumb : Uploads/Goodspic/2016-11-18//sm_582e6c5169888.jpg
     * relate_name : null
     * relate_id : null
     * relate_title : null
     * n1 : null
     * n2 : null
     * n3 : null
     * v1 : null
     * v2 : null
     * v3 : null
     * express_1 : null
     * express_name_1 : null
     * fee_start_1 : 0.00
     * fee_step_1 : 0.00
     * express_2 : null
     * express_name_2 : null
     * fee_start_2 : 0.00
     * fee_step_2 : 0.00
     * express_3 : 0
     * express_name_3 : null
     * fee_start_3 : 0.00
     * fee_step_3 : 0.00
     * username : admin
     * grouid : null
     * company : null
     * vip : null
     * validated : 0
     * truename : null
     * telephone : null
     * mobile : null
     * address : null
     * email : null
     * msn : null
     * qq : null
     * ali : null
     * skepy : null
     * editor : null
     * edittime : null
     * editate : null
     * addtime : 1479437393
     * adddate : null
     * ip : 127.0.0.1
     * template : null
     * linkurl : null
     * filepath : null
     * note : null
     * service_fee : 0.00
     * is_best : 1
     * is_new : 1
     * is_host : 0
     */

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

    public static class DataBean implements Serializable{
        private String goods_id;
        private String goods_name;
        private String goods_sn;
        private String cat_id;
        private Object mycatid;
        private String areaid;
        private String level;
        private String type_id;
        private String goods_desc;
        private String elite;
        private String goods_weight;
        private String goods_order;
        private String fee;
        private Object introduce;
        private String brand_id;
        private String shop_price;
        private String amount;
        private Object unit;
        private Object tag;
        private Object keyword;
        private Object pptword;
        private Object hints;
        private Object orders;
        private Object sales;
        private String is_cod;
        private String status;
        private Object comments;
        private String thumb;
        private String sm_thumb;
        private Object relate_name;
        private Object relate_id;
        private Object relate_title;
        private Object n1;
        private Object n2;
        private Object n3;
        private Object v1;
        private Object v2;
        private Object v3;
        private Object express_1;
        private Object express_name_1;
        private String fee_start_1;
        private String fee_step_1;
        private Object express_2;
        private Object express_name_2;
        private String fee_start_2;
        private String fee_step_2;
        private String express_3;
        private Object express_name_3;
        private String fee_start_3;
        private String fee_step_3;
        private String username;
        private Object grouid;
        private Object company;
        private Object vip;
        private String validated;
        private Object truename;
        private Object telephone;
        private Object mobile;
        private Object address;
        private Object email;
        private Object msn;
        private Object qq;
        private Object ali;
        private Object skepy;
        private Object editor;
        private Object edittime;
        private Object editate;
        private String addtime;
        private Object adddate;
        private String ip;
        private Object template;
        private Object linkurl;
        private Object filepath;
        private Object note;
        private String service_fee;
        private String is_best;
        private String is_new;
        private String is_host;

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_sn() {
            return goods_sn;
        }

        public void setGoods_sn(String goods_sn) {
            this.goods_sn = goods_sn;
        }

        public String getCat_id() {
            return cat_id;
        }

        public void setCat_id(String cat_id) {
            this.cat_id = cat_id;
        }

        public Object getMycatid() {
            return mycatid;
        }

        public void setMycatid(Object mycatid) {
            this.mycatid = mycatid;
        }

        public String getAreaid() {
            return areaid;
        }

        public void setAreaid(String areaid) {
            this.areaid = areaid;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getType_id() {
            return type_id;
        }

        public void setType_id(String type_id) {
            this.type_id = type_id;
        }

        public String getGoods_desc() {
            return goods_desc;
        }

        public void setGoods_desc(String goods_desc) {
            this.goods_desc = goods_desc;
        }

        public String getElite() {
            return elite;
        }

        public void setElite(String elite) {
            this.elite = elite;
        }

        public String getGoods_weight() {
            return goods_weight;
        }

        public void setGoods_weight(String goods_weight) {
            this.goods_weight = goods_weight;
        }

        public String getGoods_order() {
            return goods_order;
        }

        public void setGoods_order(String goods_order) {
            this.goods_order = goods_order;
        }

        public String getFee() {
            return fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
        }

        public Object getIntroduce() {
            return introduce;
        }

        public void setIntroduce(Object introduce) {
            this.introduce = introduce;
        }

        public String getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(String brand_id) {
            this.brand_id = brand_id;
        }

        public String getShop_price() {
            return shop_price;
        }

        public void setShop_price(String shop_price) {
            this.shop_price = shop_price;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public Object getUnit() {
            return unit;
        }

        public void setUnit(Object unit) {
            this.unit = unit;
        }

        public Object getTag() {
            return tag;
        }

        public void setTag(Object tag) {
            this.tag = tag;
        }

        public Object getKeyword() {
            return keyword;
        }

        public void setKeyword(Object keyword) {
            this.keyword = keyword;
        }

        public Object getPptword() {
            return pptword;
        }

        public void setPptword(Object pptword) {
            this.pptword = pptword;
        }

        public Object getHints() {
            return hints;
        }

        public void setHints(Object hints) {
            this.hints = hints;
        }

        public Object getOrders() {
            return orders;
        }

        public void setOrders(Object orders) {
            this.orders = orders;
        }

        public Object getSales() {
            return sales;
        }

        public void setSales(Object sales) {
            this.sales = sales;
        }

        public String getIs_cod() {
            return is_cod;
        }

        public void setIs_cod(String is_cod) {
            this.is_cod = is_cod;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getComments() {
            return comments;
        }

        public void setComments(Object comments) {
            this.comments = comments;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getSm_thumb() {
            return sm_thumb;
        }

        public void setSm_thumb(String sm_thumb) {
            this.sm_thumb = sm_thumb;
        }

        public Object getRelate_name() {
            return relate_name;
        }

        public void setRelate_name(Object relate_name) {
            this.relate_name = relate_name;
        }

        public Object getRelate_id() {
            return relate_id;
        }

        public void setRelate_id(Object relate_id) {
            this.relate_id = relate_id;
        }

        public Object getRelate_title() {
            return relate_title;
        }

        public void setRelate_title(Object relate_title) {
            this.relate_title = relate_title;
        }

        public Object getN1() {
            return n1;
        }

        public void setN1(Object n1) {
            this.n1 = n1;
        }

        public Object getN2() {
            return n2;
        }

        public void setN2(Object n2) {
            this.n2 = n2;
        }

        public Object getN3() {
            return n3;
        }

        public void setN3(Object n3) {
            this.n3 = n3;
        }

        public Object getV1() {
            return v1;
        }

        public void setV1(Object v1) {
            this.v1 = v1;
        }

        public Object getV2() {
            return v2;
        }

        public void setV2(Object v2) {
            this.v2 = v2;
        }

        public Object getV3() {
            return v3;
        }

        public void setV3(Object v3) {
            this.v3 = v3;
        }

        public Object getExpress_1() {
            return express_1;
        }

        public void setExpress_1(Object express_1) {
            this.express_1 = express_1;
        }

        public Object getExpress_name_1() {
            return express_name_1;
        }

        public void setExpress_name_1(Object express_name_1) {
            this.express_name_1 = express_name_1;
        }

        public String getFee_start_1() {
            return fee_start_1;
        }

        public void setFee_start_1(String fee_start_1) {
            this.fee_start_1 = fee_start_1;
        }

        public String getFee_step_1() {
            return fee_step_1;
        }

        public void setFee_step_1(String fee_step_1) {
            this.fee_step_1 = fee_step_1;
        }

        public Object getExpress_2() {
            return express_2;
        }

        public void setExpress_2(Object express_2) {
            this.express_2 = express_2;
        }

        public Object getExpress_name_2() {
            return express_name_2;
        }

        public void setExpress_name_2(Object express_name_2) {
            this.express_name_2 = express_name_2;
        }

        public String getFee_start_2() {
            return fee_start_2;
        }

        public void setFee_start_2(String fee_start_2) {
            this.fee_start_2 = fee_start_2;
        }

        public String getFee_step_2() {
            return fee_step_2;
        }

        public void setFee_step_2(String fee_step_2) {
            this.fee_step_2 = fee_step_2;
        }

        public String getExpress_3() {
            return express_3;
        }

        public void setExpress_3(String express_3) {
            this.express_3 = express_3;
        }

        public Object getExpress_name_3() {
            return express_name_3;
        }

        public void setExpress_name_3(Object express_name_3) {
            this.express_name_3 = express_name_3;
        }

        public String getFee_start_3() {
            return fee_start_3;
        }

        public void setFee_start_3(String fee_start_3) {
            this.fee_start_3 = fee_start_3;
        }

        public String getFee_step_3() {
            return fee_step_3;
        }

        public void setFee_step_3(String fee_step_3) {
            this.fee_step_3 = fee_step_3;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Object getGrouid() {
            return grouid;
        }

        public void setGrouid(Object grouid) {
            this.grouid = grouid;
        }

        public Object getCompany() {
            return company;
        }

        public void setCompany(Object company) {
            this.company = company;
        }

        public Object getVip() {
            return vip;
        }

        public void setVip(Object vip) {
            this.vip = vip;
        }

        public String getValidated() {
            return validated;
        }

        public void setValidated(String validated) {
            this.validated = validated;
        }

        public Object getTruename() {
            return truename;
        }

        public void setTruename(Object truename) {
            this.truename = truename;
        }

        public Object getTelephone() {
            return telephone;
        }

        public void setTelephone(Object telephone) {
            this.telephone = telephone;
        }

        public Object getMobile() {
            return mobile;
        }

        public void setMobile(Object mobile) {
            this.mobile = mobile;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getMsn() {
            return msn;
        }

        public void setMsn(Object msn) {
            this.msn = msn;
        }

        public Object getQq() {
            return qq;
        }

        public void setQq(Object qq) {
            this.qq = qq;
        }

        public Object getAli() {
            return ali;
        }

        public void setAli(Object ali) {
            this.ali = ali;
        }

        public Object getSkepy() {
            return skepy;
        }

        public void setSkepy(Object skepy) {
            this.skepy = skepy;
        }

        public Object getEditor() {
            return editor;
        }

        public void setEditor(Object editor) {
            this.editor = editor;
        }

        public Object getEdittime() {
            return edittime;
        }

        public void setEdittime(Object edittime) {
            this.edittime = edittime;
        }

        public Object getEditate() {
            return editate;
        }

        public void setEditate(Object editate) {
            this.editate = editate;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public Object getAdddate() {
            return adddate;
        }

        public void setAdddate(Object adddate) {
            this.adddate = adddate;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public Object getTemplate() {
            return template;
        }

        public void setTemplate(Object template) {
            this.template = template;
        }

        public Object getLinkurl() {
            return linkurl;
        }

        public void setLinkurl(Object linkurl) {
            this.linkurl = linkurl;
        }

        public Object getFilepath() {
            return filepath;
        }

        public void setFilepath(Object filepath) {
            this.filepath = filepath;
        }

        public Object getNote() {
            return note;
        }

        public void setNote(Object note) {
            this.note = note;
        }

        public String getService_fee() {
            return service_fee;
        }

        public void setService_fee(String service_fee) {
            this.service_fee = service_fee;
        }

        public String getIs_best() {
            return is_best;
        }

        public void setIs_best(String is_best) {
            this.is_best = is_best;
        }

        public String getIs_new() {
            return is_new;
        }

        public void setIs_new(String is_new) {
            this.is_new = is_new;
        }

        public String getIs_host() {
            return is_host;
        }

        public void setIs_host(String is_host) {
            this.is_host = is_host;
        }
    }
}
