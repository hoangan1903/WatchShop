package com.seuit.spring.watchshop.controller;

import com.seuit.spring.watchshop.entity.DBFile;
import com.seuit.spring.watchshop.entity.ProductApi;
import com.seuit.spring.watchshop.helper.ProductExcelHelper;
import com.seuit.spring.watchshop.repository.RoleRepository;
import com.seuit.spring.watchshop.service.DBFileStorageService;
import com.seuit.spring.watchshop.service.ProductService;
import com.seuit.spring.watchshop.service.UserService;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/manager")
public class ManagementController {
	@Autowired
	private ProductService productService;

	@Autowired
	private DBFileStorageService dbFileStorageService;

	private Logger logger = Logger.getLogger(this.getClass().getName());

	@GetMapping("/index")
	public String showIndexManagerMentPage() {
		return "manager/index";
	}

	@GetMapping(value = "/CRUD_Products")
	public String showCRUDProductPage() {
		return "manager/CRUDProducts";
	}

	@GetMapping(value = "/CRUD_Employees")
	public String showCRUDEmployeePage() {
		return "manager/CRUDEmployee";
	}

	@PostMapping(value = "/CRUD_Products/importFromExcel")
	public String importExcel(@RequestParam("file") MultipartFile file, RedirectAttributes redirect) {
		ProductExcelHelper productExcelHelper = new ProductExcelHelper();
		File excelFile = dbFileStorageService.convert(file);
		List<ProductApi> productApis;
		try {
			productApis = productExcelHelper.saveProductsFromExcelFile(excelFile);
			productApis.stream().forEach((productApi) -> {
				productService.saveOrUpdate(productApi, null);
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/manager/CRUD_Products";
	}

	

}
