package org.moonzhou.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.moonzhou.mybatisplus.model.entity.HardShipAidApply;

/**
 * @author moonzhou
 */
public interface HardShipAidApplyMapper extends BaseMapper<HardShipAidApply> {

    HardShipAidApply findHardShipAidApplyById(int id);
}
