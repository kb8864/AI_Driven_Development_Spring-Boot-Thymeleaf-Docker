package com.example.shop01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.shop01.entity.Item;
import com.example.shop01.form.ItemForm;
import com.example.shop01.service.ItemService;
import com.example.shop01.util.AppConst;

/**
 * 商品の一覧・登録・更新・削除を制御するコントローラ。
 * @RequestMapping は使用しないため、各メソッドにフルパスを指定する。
 */
@Controller
public class ItemController {

	private final ItemService itemService;

	/** コンストラクタが1つのため、@Autowired なしで注入される */
	public ItemController(ItemService itemService) {
		this.itemService = itemService;
	}

	/** ルートは商品一覧へ転送する */
	@GetMapping("/")
	public String root() {
		return AppConst.REDIRECT_ITEM_LIST;
	}

	/** 商品一覧を表示する */
	@GetMapping("/items")
	public String list(Model model) {
		model.addAttribute("items", itemService.findAll());
		return AppConst.VIEW_ITEM_LIST;
	}

	/** 商品登録画面を表示する */
	@GetMapping("/items/add")
	public String addForm(ItemForm itemForm) {
		return AppConst.VIEW_ITEM_ADD;
	}

	/** 商品を新規登録する */
	@PostMapping("/items/add")
	public String add(@Validated ItemForm itemForm, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		// 入力チェックエラーは登録画面を再表示する（入力値とエラーを保持するためリダイレクトしない）
		if (bindingResult.hasErrors()) {
			return AppConst.VIEW_ITEM_ADD;
		}

		// 商品IDの重複は入力エラーとして積む
		if (itemService.existsById(itemForm.getId())) {
			bindingResult.rejectValue("id", null, AppConst.MSG_DUPLICATE_ID);
			return AppConst.VIEW_ITEM_ADD;
		}

		itemService.register(itemForm);
		redirectAttributes.addFlashAttribute(AppConst.ATTR_SUCCESS, AppConst.MSG_REGISTERED);
		return AppConst.REDIRECT_ITEM_LIST;
	}

	/** 商品更新画面を表示する */
	@GetMapping("/items/edit/{id}")
	public String editForm(@PathVariable String id, Model model, RedirectAttributes redirectAttributes) {

		Item item = itemService.findById(id);
		if (item == null) {
			redirectAttributes.addFlashAttribute(AppConst.ATTR_ERROR, AppConst.MSG_NOT_FOUND);
			return AppConst.REDIRECT_ITEM_LIST;
		}

		model.addAttribute("itemForm", toForm(item));
		return AppConst.VIEW_ITEM_EDIT;
	}

	/** 商品を更新する */
	@PostMapping("/items/edit")
	public String edit(@Validated ItemForm itemForm, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		// 入力チェックエラーは更新画面を再表示する
		if (bindingResult.hasErrors()) {
			return AppConst.VIEW_ITEM_EDIT;
		}

		if (itemService.update(itemForm)) {
			redirectAttributes.addFlashAttribute(AppConst.ATTR_SUCCESS, AppConst.MSG_UPDATED);
		} else {
			redirectAttributes.addFlashAttribute(AppConst.ATTR_ERROR, AppConst.MSG_NOT_FOUND);
		}
		return AppConst.REDIRECT_ITEM_LIST;
	}

	/** 商品を削除する */
	@PostMapping("/items/delete/{id}")
	public String delete(@PathVariable String id, RedirectAttributes redirectAttributes) {

		if (itemService.delete(id)) {
			redirectAttributes.addFlashAttribute(AppConst.ATTR_SUCCESS, AppConst.MSG_DELETED);
		} else {
			redirectAttributes.addFlashAttribute(AppConst.ATTR_ERROR, AppConst.MSG_NOT_FOUND);
		}
		return AppConst.REDIRECT_ITEM_LIST;
	}

	/**
	 * 取得した商品を画面表示用の入力値へ詰め替える。
	 *
	 * @param item 商品
	 * @return 詰め替えた入力値
	 */
	private ItemForm toForm(Item item) {
		ItemForm form = new ItemForm();
		form.setId(item.getId());
		form.setName(item.getName());
		form.setPrice(item.getPrice());
		form.setStock(item.getStock());
		return form;
	}
}
