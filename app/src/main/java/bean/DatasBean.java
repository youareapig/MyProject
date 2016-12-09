package bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/8.
 */
public class DatasBean {
    private List<orderBean> Goods;
    private DataBean Member;
    public DataBean getMember() {
        return Member;
    }

    public void setMember(DataBean member) {
        Member = member;
    }

    public List<orderBean> getGoods() {
        return Goods;
    }

    public void setGoods(List<orderBean> goods) {
        Goods = goods;
    }


}
