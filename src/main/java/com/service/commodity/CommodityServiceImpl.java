package com.service.commodity;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.common.domain.Commodity;
import com.dao.commodity.CommodityDao;

@Service("commodityService")
public class CommodityServiceImpl implements CommodityService {

	@Autowired
	private CommodityDao commodityDao;

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public Long create(Commodity commodity) {
		Commodity byName = getByName(commodity.getName());
		if(byName != null){
			byName.setCount(commodity.getCount());
			update(byName);
			return null;
		}
		return commodityDao.create(commodity);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Commodity get(Long commodityId) {
		if (commodityId == null) {
			return null;
		}
		return commodityDao.get(commodityId);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void update(Commodity commodity) {
		commodityDao.update(commodity);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void delete(Long id) {
		commodityDao.delete(id);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Map<String, Object>> findShoeList() {
		return commodityDao.findShoeList();
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Commodity getByName(String name) {
		return commodityDao.getByName(name);
	}

}
