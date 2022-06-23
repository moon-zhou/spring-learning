package org.moonzhou.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.moonzhou.mybatisplus.dao.HardShipAidApplyMapper;
import org.moonzhou.mybatisplus.model.entity.HardShipAidApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author moon zhou
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HardShipAidApplyTest {
    @Autowired
    private HardShipAidApplyMapper hardShipAidApplyMapper;

    @Test
    public void lambdaQueryTableField() {

        // SELECT id,name,id_no,age,difficult_description,difficult_description AS description,user_no,dept,deleted,create_time,create_user,update_time,update_user FROM hardship_aid_apply WHERE deleted=0 AND (id = ?)
        HardShipAidApply hardShipAidApply = hardShipAidApplyMapper.selectOne(new LambdaQueryWrapper<HardShipAidApply>().eq(HardShipAidApply::getId, 1));

        Assert.assertNotNull(hardShipAidApply);
        Assert.assertTrue(StringUtils.isNotEmpty(hardShipAidApply.getDescription()));



        HardShipAidApply hardShipAidApplySql = hardShipAidApplyMapper.findHardShipAidApplyById(1);

        Assert.assertNotNull(hardShipAidApplySql);
        Assert.assertTrue(StringUtils.isEmpty(hardShipAidApplySql.getDescription()));


    }

}
