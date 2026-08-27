package com.example.shop01.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.shop01.dao.ItemDao;
import com.example.shop01.entity.Item;
import com.example.shop01.form.ItemForm;
import com.example.shop01.service.ItemService;

/**
 * ItemService の実装クラス。
 * 画面向けの文言は持たず、業務判断（重複・対象有無）のみを行う。
 */
@Service
public class ItemServiceImpl implements ItemService {

	private final ItemDao itemDao;

	/** コンストラクタが1つのため、@Autowired なしで注入される */
	public ItemServiceImpl(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	@Override
	public List<Item> findAll() {
		return itemDao.findAll();
	}

	@Override
	public Item findById(String id) {
		return itemDao.findById(id);
	}

	@Override
	public boolean existsById(String id) {
		return itemDao.countById(id) > 0;
	}

	@Override
	@Transactional
	public boolean register(ItemForm form) {
		// 商品IDの重複チェックは Controller で existsById を用いて実施済み
		return itemDao.insert(toEntity(form)) > 0;
	}

	@Override
	@Transactional
	public boolean update(ItemForm form) {
		// 更新件数0は対象が存在しなかったことを意味する
		return itemDao.update(toEntity(form)) > 0;
	}

	@Override
	@Transactional
	public boolean delete(String id) {
		// 削除件数0は対象が存在しなかったことを意味する
		return itemDao.delete(id) > 0;
	}

	/**
	 * 画面の入力値を DB 登録用のオブジェクトへ詰め替える。
	 *
	 * @param form 入力値
	 * @return 詰め替えた商品
	 */
	private Item toEntity(ItemForm form) {
		Item item = new Item();
		item.setId(form.getId());
		item.setName(form.getName());
		item.setPrice(form.getPrice());
		item.setStock(form.getStock());
		return item;
	}
}
