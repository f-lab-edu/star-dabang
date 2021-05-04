package dabang.star.cafe.application;

import dabang.star.cafe.application.command.MyMenuCreateCommand;
import dabang.star.cafe.application.data.*;
import dabang.star.cafe.application.exception.ResourceNotFoundException;
import dabang.star.cafe.domain.category.CategoryRepository;
import dabang.star.cafe.domain.category.CategoryType;
import dabang.star.cafe.domain.mymenu.MyMenu;
import dabang.star.cafe.domain.mymenu.MyMenuRepository;
import dabang.star.cafe.domain.product.ProductRepository;
import dabang.star.cafe.utils.CacheKey;
import dabang.star.cafe.utils.CacheName;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MenuService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final MyMenuRepository myMenuRepository;

    @Cacheable(value = CacheName.CATEGORY, key = CacheKey.DEFAULT)
    public List<TypeCategoryData> getAllCategories() {
        List<TypeCategoryData> typeCategories = new ArrayList<>();
        CategoryType[] types = CategoryType.values();

        for (CategoryType type : types) {
            List<CategoryData> categoryData = categoryRepository.findAllByType(type.getKey());
            typeCategories.add(new TypeCategoryData(type, categoryData));
        }

        return typeCategories;
    }

    @Cacheable(value = CacheName.PRODUCT, key = "#categoryId")
    public List<ProductData> getProductsByCategoryId(int categoryId) {
        return productRepository.findAllByCategoryIdAndActive(categoryId);
    }

    @Transactional
    public void createMyMenu(long memberId, MyMenuCreateCommand myMenuCreateCommand) {
        Long productId = myMenuCreateCommand.getProductId();
        ProductData productData = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("product id does not exist : " + productId)
        );

        Set<Long> findOptionIds = productData.getOptions().stream()
                .map(ProductOptionData::getOptionId)
                .collect(Collectors.toSet());

        Set<Long> optionIds = myMenuCreateCommand.getOptionInfo().keySet();

        if (findOptionIds.size() != optionIds.size()) {
            throw new ResourceNotFoundException("options not match");
        }

        for (long optionId : optionIds) {
            if (!findOptionIds.contains(optionId)) {
                throw new ResourceNotFoundException("option id does not exist : " + optionId);
            }
        }

        MyMenu myMenu = myMenuCreateCommand.toMyMenu(memberId);
        myMenuRepository.save(myMenu);
    }

    @Transactional(readOnly = true)
    public List<MyMenuInfoData> getMyMenu(long memberId) {
        List<MyMenuInfoData> myMenuInfoData = new ArrayList<>();
        List<MyMenuData> myMenus = myMenuRepository.findAllByMemberId(memberId);

        if (!myMenus.isEmpty()) {
            List<Long> productIds = myMenus.stream()
                    .map(MyMenuData::getId)
                    .distinct()
                    .collect(Collectors.toList());

            Map<Long, ProductData> productDataMap = productRepository.findByIds(productIds)
                    .stream()
                    .collect(Collectors.toMap(ProductData::getId, productData -> productData));


            myMenus.stream().map(myMenu -> {
                ProductData product = productDataMap.get(myMenu.getProductId()).clone();
                product.calcPrice(myMenu.getOptionInfo());
                return new MyMenuInfoData(myMenu.getId(), myMenu.getName(), product);
            }).forEach(myMenuInfoData::add);
        }

        return myMenuInfoData;
    }

}