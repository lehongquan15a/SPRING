package com.hongquan.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongquan.dao.BillDao;
import com.hongquan.entity.Bill;
import com.hongquan.entity.BillProduct;
import com.hongquan.entity.User;
import com.hongquan.model.BillDTO;
import com.hongquan.model.BillProductDTO;
import com.hongquan.model.UserDTO;
import com.hongquan.service.BillService;

@Transactional
@Service
public class BillServiceImpl implements BillService {

	@Autowired
	BillDao billDao;

	@Override
	public void addBillDTO(BillDTO billDTO) {
		// TODO Auto-generated method stub
		Bill bill = new Bill();
		bill.setBuyDate(new Date());

		bill.setPriceTotal(billDTO.getPriceTotal());
		bill.setDiscountPercent(billDTO.getDiscountPercent());
		User user = new User();
		user.setUsername(billDTO.getUser().getUsername());
		bill.setUser(user);

		billDao.addBill(bill);
		billDTO.setId(bill.getId());

	}

	@Override
	public void deleteBillDTO(int id) {
		// TODO Auto-generated method stub
		Bill bill = billDao.getBillById(id);
		if (bill != null) {
			billDao.deleteBill(bill);
		}
	}

	@Override
	public void updateBillDTO(BillDTO billDTO) {
		// TODO Auto-generated method stub
		Bill bill = billDao.getBillById(billDTO.getId());
		if (bill != null) {
			bill.setBuyDate(new Date());

			bill.setPriceTotal(billDTO.getPriceTotal());
			bill.setDiscountPercent(billDTO.getDiscountPercent());
			User user = new User();
			user.setUsername(billDTO.getUser().getUsername());
			bill.setUser(user);

			billDao.updateBill(bill);
		}
	}

	@Override
	public BillDTO getBillDTOById(int id) {
		Bill bill = billDao.getBillById(id);
		if (bill != null) {
			BillDTO billDTO = new BillDTO();
			billDTO.setBuyDate(bill.getBuyDate());

			billDTO.setPriceTotal(bill.getPriceTotal());
			billDTO.setDiscountPercent(bill.getDiscountPercent());
			UserDTO userDTO = new UserDTO();
			userDTO.setUsername(bill.getUser().getUsername());
			billDTO.setUser(userDTO);
			
			List<BillProduct> billProducts = bill.getBillProducts();
			List<BillProductDTO>billProductDTOs = new ArrayList<BillProductDTO>();
			for (BillProduct billProduct : billProducts) {
						BillProductDTO billProductDTO = new BillProductDTO();
						billProductDTO.setId(billProduct.getId());
						//còn nữa
						
						billProductDTOs.add(billProductDTO);				
			}

			return billDTO;
		}
		return null;
	}

	@Override
	public List<BillDTO> search(String keyword, int start, int length) {
		// TODO Auto-generated method stub
		List<Bill> listBills = billDao.search(keyword, start, length);
		List<BillDTO> listBillDTOs = new ArrayList<BillDTO>();
		for (Bill bill : listBills) {
			BillDTO billDTO = new BillDTO();
			billDTO.setId(bill.getId());
			billDTO.setBuyDate(bill.getBuyDate());

			billDTO.setPriceTotal(bill.getPriceTotal());
			billDTO.setDiscountPercent(bill.getDiscountPercent());
			UserDTO userDTO = new UserDTO();
			userDTO.setUsername(bill.getUser().getUsername());
			userDTO.setName(bill.getUser().getName());
			userDTO.setAddress(bill.getUser().getAddress());
			userDTO.setPhone(bill.getUser().getPhone());
			billDTO.setUser(userDTO);
			listBillDTOs.add(billDTO);

		}

		return listBillDTOs;
	}

	@Override
	public int countBillDTOWhenSearch(String keyword) {
		return billDao.countBillWhenSearch(keyword);
	}

	@Override
	public List<BillDTO> searchByUsername(String username, int start, int length) {
		List<Bill> listBills = billDao.searchByUsername(username, start, length);
		List<BillDTO> listBillDTOs = new ArrayList<BillDTO>();
		for (Bill bill : listBills) {
			BillDTO billDTO = new BillDTO();
			billDTO.setId(bill.getId());
			billDTO.setBuyDate(bill.getBuyDate());

			billDTO.setPriceTotal(bill.getPriceTotal());
			billDTO.setDiscountPercent(bill.getDiscountPercent());
			UserDTO userDTO = new UserDTO();
			userDTO.setUsername(bill.getUser().getUsername());
			userDTO.setName(bill.getUser().getName());
			userDTO.setAddress(bill.getUser().getAddress());
			userDTO.setPhone(bill.getUser().getPhone());
			billDTO.setUser(userDTO);
			listBillDTOs.add(billDTO);

		}

		return listBillDTOs;
	}

	@Override
	public int countBillWhenSearchByUsername(String username) {
		return billDao.countBillWhenSearchByUsername(username);
	}

}
