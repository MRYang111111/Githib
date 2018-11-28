package cn.yxd.web.action;

import cn.yxd.bean.Decidedzone;
import cn.yxd.bean.Region;
import cn.yxd.bean.Subarea;
import cn.yxd.servce.SubAreaService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletOutputStream;
import java.io.PrintWriter;
import java.util.List;

@Controller
@Scope("prototype")
public class SubAreaAction extends  BaseAction<Subarea> {
    @Autowired
    private SubAreaService subAreaService;
    public String add() throws Exception {
        //保存分区
        subAreaService.add(modle);
        return "add";
    }
    //分页查询的实现，没有带有过滤条件
    public String pageQuery() throws Exception {
        //修改分页的查询条件
        DetachedCriteria detachedCriteria =pageBean.getDetachedCriteria();
      //动态的添加过滤条件，查询关键字，省，市,区，等
        String addresskey = modle.getAddresskey();
        if(StringUtils.isNoneBlank(addresskey)){
            //添加过滤条件,进内连接查询，即多表查询
            detachedCriteria.add(Restrictions.like("addresskey", "%"+addresskey+"%"));
        }
        //封装Resion对象
        Region region = modle.getRegion();
      if(region!=null){
          //获得省
          String province = region.getProvince();
          String city = region.getCity();
          String district = region.getDistrict();
          detachedCriteria.createAlias("region", "r");
          if(StringUtils.isNoneBlank(province)){
              //添加过滤条件，根据身份进行模糊查询--对region表进行关联 ，给resion对象起别名
              detachedCriteria.add(Restrictions.like("r.province", "%"+province+"%"));
          }
          if(StringUtils.isNoneBlank(city)){
              //添加过滤条件，根据身份进行模糊查询--对region表进行关联 ，给resion对象起别名
              detachedCriteria.add(Restrictions.like("r.city", "%"+city+"%"));
          }
          if(StringUtils.isNoneBlank(district)){
              //添加过滤条件，根据身份进行模糊查询--对region表进行关联 ，给resion对象起别名
              detachedCriteria.add(Restrictions.like("r.district", "%"+district+"%"));
          }
      }

        subAreaService.pageQuery(pageBean);
        this.javaToJson(pageBean, new String[]{"currentPage","detachedCriteria","pageSize","decidedzone","subareas"});
        return null;
    }
    //导出分区数据,进行文件的下载
    public String exportXls() throws Exception {
        //查询所有的分区数据
        List<Subarea> list=subAreaService.findAll();
        System.out.println(list);
        //创建excle文件
        HSSFWorkbook hssfWorkbook=new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet("分区数据");
        //设置标题对象
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("分区编号");
        row.createCell(1).setCellValue("开始编号");
        row.createCell(2).setCellValue("结束编号");
        row.createCell(3).setCellValue("位置信息");
        row.createCell(4).setCellValue("省市区");
        //动态的遍对象
        for (Subarea subarea:list){
            //获取最后一行的位置进行加一
            HSSFRow hssfRow = sheet.createRow(sheet.getLastRowNum() + 1);
            hssfRow.createCell(0).setCellValue(subarea.getId());
            hssfRow.createCell(1).setCellValue(subarea.getStartnum());
            hssfRow.createCell(2).setCellValue(subarea.getEndnum());
            hssfRow.createCell(3).setCellValue(subarea.getPosition());
            hssfRow.createCell(4).setCellValue(subarea.getRegion().getName());
        }

        //文件下载，一个流，两个头
        String filename="分区数据.xls";
        ServletOutputStream outputStream = ServletActionContext.getResponse().getOutputStream();
        ServletActionContext.getResponse().setContentType("application/vnd.ms-excle");
        ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename="+filename);
        hssfWorkbook.write(outputStream);
        return null;
    }
    //查询未关联的分区
    public String listajax() throws Exception {
        List<Subarea> list=subAreaService.findListAssociation();
        this.listToJson(list, new String[]{"decidedzone","region","startnum","endnum","single"});
        return null;
    }
    //根据定区的id查询分区的所有信息
    private  String decidedzoneId;

    public void setDecidedzoneId(String decidedzoneId) {
        this.decidedzoneId = decidedzoneId;
    }

    public String findListByDecidedzoneId () throws Exception {
        System.out.println(decidedzoneId);
        List<Subarea> list=subAreaService.findListByDecidedzoneId(decidedzoneId);
        listToJson(list, new String[]{"decidedzone","subareas"});
        return null;
    }
}
