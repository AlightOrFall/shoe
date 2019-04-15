package com.service.shoe;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.common.domain.Commodity;
import com.dao.shoe.ShoeDao;
import com.service.commodity.CommodityService;
import com.shoe.demo.Shoe;
import com.shoe.transferobj.ShoeTo;

@Service("shoeService")
public class ShoeServiceImpl implements ShoeService {

	@Autowired
	private ShoeDao shoeDao;
	@Autowired
	private CommodityService commodityService;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Shoe> find(Map<String, Object> params) {
		return shoeDao.find(params);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Shoe get(Long id) {
		Shoe shoe = shoeDao.get(id);
		return shoe;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Map<String, Object>> findParams(Map<String, Object> params) {
		return shoeDao.findParams(params);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void create(Shoe shoe) {
		shoeDao.create(shoe);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void update(Shoe shoe, Commodity commodity) {
		commodityService.update(commodity);
		shoeDao.update(shoe);
	}

	@Override
	public void delete(Long shoeId) {
		shoeDao.delete(shoeId);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Map<String, Object>> findAll() {
		return shoeDao.findAll();
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Shoe> find(ShoeTo shoeTo) {
		return shoeDao.find2(shoeTo);
	}

}
