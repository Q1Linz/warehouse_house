package com.q1linz.controller;

import com.q1linz.entity.*;
import com.q1linz.page.Page;
import com.q1linz.service.*;
import com.q1linz.utils.TokenUtils;
import com.q1linz.utils.WarehouseConstants;
import io.netty.util.internal.ResourcesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private StoreService storeService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private SupplyService supplyService;
    @Autowired
    private PlaceService placeService;
    @Autowired
    private UnitService unitService;
    @Autowired
    private ProductService productService;
    @Autowired
    private TokenUtils tokenUtils;
    @Value("${file.upload-path}")
    private String uploadPath;

    @CrossOrigin
    @RequestMapping("/img-upload")
    public Result uploadImg(MultipartFile file){

        try {
            File uploadDirFile = new File(this.getClass().getResource("/").getPath());

            File uploadDirPath = uploadDirFile.getAbsoluteFile();

//            String fileUploadPath = uploadDirPath+uploadPath+"\\"+file.getOriginalFilename();
            String fileUploadPath = uploadPath+File.separator+file.getOriginalFilename();;

            file.transferTo(new File(fileUploadPath));
            System.out.println("fileUploadPath = " + fileUploadPath);
            return Result.ok("图片上传成功");
        } catch (Exception e) {
            return Result.err(Result.CODE_ERR_BUSINESS,"图片上传失败");
        }
    }
    @RequestMapping("/product-add")
    public Result addProduct(@RequestBody Product product,
                             @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){

        //获取当前登录的用户
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        //获取当前登录的用户id,即添加商品的用户id
        int createBy = currentUser.getUserId();
        product.setCreateBy(createBy);

        //执行业务
        Result result = productService.saveProduct(product);

        //响应
        return result;
    }


    @RequestMapping("/product-delete/{productId}")
    public Result deleteProduct(@PathVariable Integer productId){
        Result result = productService.deleteProduct(productId);
        return result;
    }

    @RequestMapping("/product-update")
    public Result updateProduct(@RequestBody Product product,
                                @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){

        //获取当前登录的用户
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        //获取当前登录的用户id,即修改商品的用户id
        int updateBy = currentUser.getUserId();
        product.setUpdateBy(updateBy);

        //执行业务
        Result result = productService.updateProduct(product);

        //响应
        return result;
    }

    @RequestMapping("/store-list")
    public Result storeList(){
        //执行业务
        List<Store> storeList = storeService.queryAllStore();
        //响应
        return Result.ok(storeList);
    }

    @RequestMapping("/brand-list")
    public Result brandList(){
        //执行业务
        List<Brand> brandList = brandService.queryAllBrand();
        //响应
        return Result.ok(brandList);
    }

    @RequestMapping("/category-tree")
    public Result categoryTree(){
        //执行业务
        List<ProductType> typeTreeList = productTypeService.allProductTypeTree();
        //响应
        return Result.ok(typeTreeList);
    }

    @RequestMapping("/supply-list")
    public Result supplyList(){
        //执行业务
        List<Supply> supplyList = supplyService.queryAllSupply();
        //响应
        return Result.ok(supplyList);
    }

    @RequestMapping("/place-list")
    public Result placeList(){
        //执行业务
        List<Place> placeList = placeService.queryAllPlace();
        //响应
        return Result.ok(placeList);
    }

    @RequestMapping("/unit-list")
    public Result unitList(){
        //执行业务
        List<Unit> unitList = unitService.queryAllUnit();
        //响应
        return Result.ok(unitList);
    }

    @RequestMapping("/product-page-list")
    public Result productPageList(Page page, Product product){
        //执行业务
        page = productService.queryProductPage(page, product);
        //响应
        return Result.ok(page);
    }


    @RequestMapping("/state-change")
    public Result changeProductState(@RequestBody Product product){
        //执行业务
        Result result = productService.updateProductState(product);
        //响应
        return result;
    }

    @RequestMapping("/exportTable")
    public Result exportTable(Long pageNum,Long pageSize, Product product){

        Page page = new Page();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        //分页查询仓库
        Page productPage = productService.queryProductPage(page, product);
        //拿到当前页数据
        List<?> resultList = productPage.getResultList();
        //响应
        return Result.ok(resultList);
    }



}