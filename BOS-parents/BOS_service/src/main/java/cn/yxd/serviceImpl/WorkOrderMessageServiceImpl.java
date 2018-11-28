package cn.yxd.serviceImpl;

import cn.yxd.bean.Workordermanage;
import cn.yxd.dao.WorkOrderMessageDao;
import cn.yxd.servce.WorkOrderMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WorkOrderMessageServiceImpl implements WorkOrderMessageService {
    @Autowired
    private WorkOrderMessageDao workOrderMessageDao;
    @Override
    public void save(Workordermanage modle) {
        workOrderMessageDao.save(modle);
    }
}
