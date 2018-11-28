package cn.yxd.web.action;

import cn.yxd.bean.Region;
import cn.yxd.servce.RegionServcie;
import cn.yxd.utils.PageBean;
import cn.yxd.utils.PinYin4jUtils;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region> {
    //创建集合对数据进行分封装
    private List<Region> list=new ArrayList<Region>();
    @Autowired
    private RegionServcie regionServcie;
    private  String q;
    public void setQ(String q) {
        this.q = q;
    }
    //用于属性驱动
    private File regionFile;
    public void setRegionFile(File regionFile) {
        this.regionFile = regionFile;
    }
   //导入文件上传的路径
    public String inputFile() throws Exception {
        //将excal的数据传入数据库
        System.out.println(regionFile.getName());
        //表单提交过来的枯竭
        HSSFWorkbook hssfWorkbook=new HSSFWorkbook(new FileInputStream(regionFile));
        //读取顶第一个标签页
        HSSFSheet hssfSheet=hssfWorkbook.getSheet("Sheet1");
        //读取行内的数据
        for (Row row:hssfSheet){
            //获取每列的数据
            int rowNum = row.getRowNum();
            //对第一行的标题进行清除
            if(rowNum==0){
                continue;
            }
            //获取无列的数据
            String id = row.getCell(0).getStringCellValue();
            String provice = row.getCell(1).getStringCellValue();
            String city = row.getCell(2).getStringCellValue();
            String districe = row.getCell(3).getStringCellValue();
            String postcode = row.getCell(4).getStringCellValue();
            //对数据进行分装
            Region region=new Region(id,provice,city,districe,postcode,null,null,null);
            provice=provice.substring(0,provice.length()-1 );
            city=city.substring(0,city.length()-1 );
            districe=districe.substring(0,districe.length()-1 );
            String info=provice+city+districe;
            //获取shortcode
            String[] headByString = PinYin4jUtils.getHeadByString(info);
            String shortcode = StringUtils.join(headByString);
            //获取citycode
            String citycode = PinYin4jUtils.hanziToPinyin(city, "");
            region.setShortcode(shortcode);
            region.setCitycode(citycode);
            list.add(region);
        }
        //保存区域数据
        regionServcie.inputFile(list);
        return NONE;
    }
    public String pageQuery() throws Exception {
        regionServcie.pageQugery(pageBean);
        javaToJson(pageBean, new String[]{"currentPage","detachedCriteria","pageSize","subareas"});
        return null;
    }
    //添加区域
    public String add() throws Exception {
            regionServcie.add(modle);
         return  "toRegionList";
    }
    //根据json回显区域
    public String listajax() throws Exception {
        //根据其他的条件进行模糊查询
        //查询所有的区域
        List<Region> regions=null;
        if(StringUtils.isNoneBlank(q)){
            regions=regionServcie.findRegionByQ(q);
        }else {
            regions=regionServcie.findAll();
        }
        //将json转换成json格式
        this.listToJson(regions, new String[]{"subareas"});
        return null;
    }
}
