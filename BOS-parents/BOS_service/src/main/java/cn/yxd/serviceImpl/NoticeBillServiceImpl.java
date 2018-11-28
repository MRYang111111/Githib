package cn.yxd.serviceImpl;

import cn.yxd.bean.*;
import cn.yxd.crm.CustomerService;
import cn.yxd.dao.DecidedzoneDao;
import cn.yxd.dao.NoticeBillDao;
import cn.yxd.dao.WorkBillDao;
import cn.yxd.servce.NoticeBillService;
import cn.yxd.utils.BosUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
@Transactional
public class NoticeBillServiceImpl implements NoticeBillService {
    @Autowired
    private WorkBillDao workBillDao;
    @Autowired
    private  DecidedzoneDao decidedzoneDao;
    @Autowired
    private NoticeBillDao noticeBillDao;
    //根据定区的id寻找寻找staff
    @Autowired
    private CustomerService customerService;

    @Override
    public void save(Noticebill modle) {
        //获取user
        User user = BosUtils.getLoginUser();
        modle.setUser(user);
        noticeBillDao.save(modle);
        //查询地址查询定区的id，根据定区的id查询出定区对象，根据定区对象查询出取派员
        String decidedzoneId = customerService.findListByAddress(modle.getPickaddress());
        //如果定区的id不为空，则进行人工的分单
        if(decidedzoneId!=null){
            //进行人工分单，给取派员分配任务
            Decidedzone decidedzone = decidedzoneDao.findById(decidedzoneId);
            Staff staff = decidedzone.getStaff();
            //通知取派员取单
            modle.setOrdertype(Noticebill.ORDERTYPE_AUTO);
            modle.setStaff(staff);
            //取派员根据地址送货后开出工单
            Workbill workbill=new Workbill();
            workbill.setStaff(staff);//设置开单的取派员
            workbill.setType(Workbill.TYPE_1);//设置新单的类型
            workbill.setAttachbilltimes(0);//订单的类型，新单，追单
            workbill.setRemark(modle.getRemark());//修改备注
            workbill.setPickstate(Workbill.PICKSTATE_NO);//设置取件状态
            workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));//取件时间
            workbill.setNoticebill(modle);
            workBillDao.save(workbill);
        }else {
            //进行人工分单
            modle.setOrdertype(Noticebill.ORDERTYPE_MAN);
        }

    }
//    @Override
//    public void save(Noticebill modle) {
//        User user =BosUtils.getLoginUser();
//        modle.setUser(user);
//        //保存新单
//        noticeBillDao.save(modle);
//        //远程调用crm服务，查询出定区的id
//        String address=modle.getPickaddress();
//        String decidedzoneId=customerService.findListByAddress(address);
//        if(decidedzoneId!=null){
//            //根据定区的id寻找出取派员
//            Decidedzone decidedzone = decidedzoneDao.findById(decidedzoneId);
//            System.out.println(decidedzone);
//            Staff staff = decidedzone.getStaff();
//            //通知取派员取单
//            modle.setOrdertype(Noticebill.ORDERTYPE_AUTO);
//            modle.setStaff(staff);
//            //分配任务后开出工单
//            Workbill workbill=new Workbill();//为取派员产生一个工单
//            workbill.setAttachbilltimes(0);//订单的类型，新单，追单
//            workbill.setNoticebill(modle);//设置新单
//            workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));//工单的产生时间
//            workbill.setStaff(staff);//关联工单额取派员
//            workbill.setPickstate(Workbill.PICKSTATE_NO);//取件状态
//            workbill.setType(Workbill.TYPE_1);//工单的类型，初始保存为新单
//            workbill.setRemark(modle.getRemark());//备注信息
//            workBillDao.save(workbill);
//
//        }else {
//            modle.setOrdertype(Noticebill.ORDERTYPE_MAN);
//        }
//    }
}
