package cn.yxd.test;

import cn.yxd.utils.PinYin4jUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class PingYIn4Test {
    @Test
    public  void  fun1(){
        //测试pinyon4j
//        河北省	石家庄市	桥西区
        String  privice="河北省";
        String city="石家庄市";
        String  area="桥西区";
        //分割字符串
        String privice1=privice.substring(0,privice.length()-1 );
        String city1=city.substring(0,city.length()-1);
        String area1=area.substring(0,privice.length()-1 );
        //简码
        String concal=privice1+city1+area1;
        System.out.println(concal);
        //获取字母的大写字母
        String[] headByString = PinYin4jUtils.getHeadByString(concal);
        System.out.println(StringUtils.join(headByString));
    //城市编码---->>shijiazhuang
        String s = PinYin4jUtils.hanziToPinyin(city1 + " ");
        System.out.println(s);


    }

}
